package tdea.construccion2.app.service;

import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dao.PetDao;
import tdea.construccion2.app.dao.HistoryClinicalDao;
import tdea.construccion2.app.dao.PersonDao;
import tdea.construccion2.app.dao.FactureDao;
import tdea.construccion2.app.dao.LoginDao;
import tdea.construccion2.app.dao.OrderDao;
import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.OrderDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;
import tdea.construccion2.app.dto.SessionDto;
import tdea.construccion2.app.jwt.CustomerDetailsService;
import tdea.construccion2.app.jwt.JwtUtil;

@Service
public class VeterinaryService implements VeterinarianService, LoginService, HistorialClinicalService, OrderService {
	List<String> rolesAdmin = Arrays.asList("Vendedor", "Veterinario");
	String rolVet = "Dueño";
	private static long sessionId = 0L;

	@Autowired
	private PersonDao personDao;
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PetDao petDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private FactureDao factureDao;
	@Autowired
	private HistoryClinicalDao historyDao;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Override
	public String createUser(PersonDto personDto, String rol) throws Exception {
		if (rol.equals("Administrador")) {
			if (!rolesAdmin.contains(personDto.getRol()))
				throw new  RuntimeException("el rol no es valido");
		} else {
			if (!rolVet.equals(personDto.getRol()))
				throw new RuntimeException("el rol no es valido");
		}

		if (personDao.findUserExist(personDto))
			throw new RuntimeException("ya existe un usuario con esa cedula");

		if (personDao.existUserByUserName(personDto))
			throw new RuntimeException("ya existe el usuario");

		personDao.createPerson(personDto);
		
		return "Usuario creado exitosamente!";
	}

	@Override
	public String createFacture(FactureDto factureDto) throws Exception {

		List<HistoryClinicalDto> listHistorySearch = consultHistoryClinical(
				new HistoryClinicalDto(factureDto.getOrderId()));

		for (HistoryClinicalDto HistoryDtoSearch : listHistorySearch)
			if (HistoryDtoSearch.getOrderCancellation())
				throw new RuntimeException(
						"No se puede realizar la venta de medicamentos debido a que la orden a sido cancelada.");

		PetDto pet = petDao.findById(new PetDto(factureDto.getPetId()));

		if (pet == null)
			throw new Exception("No existe la mascota.");

		// Enviar el codigo del dueño
		factureDto.setOwnerId(pet.getOwnerId());

		PersonDto ownerSearch = personDao.findUserById(new PersonDto(factureDto.getOwnerId()));
		if (ownerSearch == null)
			throw new RuntimeException("No existe el dueño.");
		else if (!ownerSearch.getRol().equals("Dueño"))
			throw new RuntimeException(
					"El usuario selecciona no es dueño de mascotas, tiene el rol de " + ownerSearch.getRol() + ".");

		if (!orderDao.findOrderExist(new OrderDto(factureDto.getOrderId())))
			throw new RuntimeException("No existe la orden.");

		factureDao.createFacture(factureDto);
		return "se ha creado la factura";
	}

	@Override
	public void setSesionID(long sesionId) {
		sessionId = sesionId;
	}

	@Override
	public String login(PersonDto personDto) throws Exception {
		PersonDto personDtoValidate = personDao.findUserByUserName(personDto);

		try {
			org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(personDto.getUserName(), personDto.getPassword())
					);
			
			if (authentication.isAuthenticated())
			{
				personDto.setRol(personDtoValidate.getRol());
				SessionDto sesionDto = loginDao.login(personDtoValidate);
				setSesionID(sesionDto.getId());
				
				return "{\"token\" : \""+ jwtUtil.generateToken(personDtoValidate.getUserName(), personDtoValidate.getRol()) + "\"}";
			}else
				throw new RuntimeException("usuario o contraseña incorrectos");
			
		}catch (Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public void logout() throws Exception {
		loginDao.logout(sessionId);
		setSesionID(0);
	}

	@Override
	public String createPet(PetDto petDto) throws Exception {
		
		PersonDto owner = personDao.findUserById(new PersonDto(petDto.getOwnerId()));
		if (owner==null)
			throw new RuntimeException("No existe el dueño.");
		else if(!owner.getRol().equals("Dueño"))
			throw new RuntimeException("El usuario seleccionado no tiene rol de dueño.");
		
		petDao.createPet(petDto);
		return "Se ha creado la mascota";
	}

	@Override
	public List<HistoryClinicalDto> consultHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception {
		return historyDao.findHistoryByOrden(historyClinicalDto);
	}

	@Override
	public String createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception {
		PetDto petDto = petDao.findById(new PetDto(historyClinicalDto.getPetId()));
		if (petDto == null)
			throw new RuntimeException("No existe la mascota.");
		if (!personDao.findUserExist(new PersonDto(historyClinicalDto.getVetId())))
			throw new RuntimeException("No existe el veterinario.");

		OrderDto orderCreate = createOrdern(new OrderDto(historyClinicalDto.getPetId(), petDto.getOwnerId(),
				historyClinicalDto.getVetId(), historyClinicalDto.getMedicament(), historyClinicalDto.getDate()));

		historyClinicalDto.setOrderID(orderCreate.getId());

		historyDao.createHistory(historyClinicalDto);
		return "Se ha creado la historia clinica";
	}

	public OrderDto consultOrdern(OrderDto orderDto) throws Exception {
		OrderDto orderSearch = orderDao.searchOrder(new OrderDto(orderDto.getId()));
		//if (orderSearch == null)
			//System.out.println("No se ha encontrado una orden");

		return orderSearch;
	}

	@Override
	public void cancelOrdern(OrderDto orderDto) throws Exception {
		OrderDto orderSearch = orderDao.searchOrder(new OrderDto(orderDto.getId()));
		//if (orderSearch == null)
			//System.out.println("No se ha encontrado una orden");

		historyDao.cancelOrder(orderSearch.getId());
	}

	@Override
	public OrderDto createOrdern(OrderDto orderDto) throws Exception {
		OrderDto orderCreate = orderDao.createOrder(orderDto);
		if (orderCreate == null)
			throw new RuntimeException("No se ha creado la orden.");
		return orderCreate;
	}

	@Override
	public long findMedicoIdSesion() throws Exception {
		SessionDto session = loginDao.findSessionById(sessionId);
		if (session == null)
			throw new RuntimeException("No se ha encontrado la sesión actual.");

		PersonDto personDto = personDao.findUserByUserName(new PersonDto(session.getUserName()));
		if (personDto == null)
			throw new RuntimeException("No se ha encontrado el medico de la sesión actual.");

		return personDto.getCedula();
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public PetDao getPetDao() {
		return petDao;
	}

	public void setPetDao(PetDao petDao) {
		this.petDao = petDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public FactureDao getFactureDao() {
		return factureDao;
	}

	public void setFactureDao(FactureDao factureDao) {
		this.factureDao = factureDao;
	}

	public HistoryClinicalDao getHistoryDao() {
		return historyDao;
	}

	public void setHistoryDao(HistoryClinicalDao historyDao) {
		this.historyDao = historyDao;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public JwtUtil getJwtUtil() {
		return jwtUtil;
	}

	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public CustomerDetailsService getCustomerDetailsService() {
		return customerDetailsService;
	}

	public void setCustomerDetailsService(CustomerDetailsService customerDetailsService) {
		this.customerDetailsService = customerDetailsService;
	}
}
