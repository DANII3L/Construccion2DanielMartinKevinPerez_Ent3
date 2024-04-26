package tdea.construccion2.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dao.PetDao;
import tdea.construccion2.app.dao.HistoryClinicalDao;
import tdea.construccion2.app.dao.PersonDao;
import tdea.construccion2.app.dao.FactureDao;
import tdea.construccion2.app.dao.LoginDao;
import tdea.construccion2.app.dao.LoginDaoImp;
import tdea.construccion2.app.dao.OrderDao;
import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.OrderDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;
import tdea.construccion2.app.dto.SessionDto;

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

	@Override
	public void createUser(PersonDto personDto, String rol) throws Exception {
		if (rol.equals("Administrador")) {
			if (!rolesAdmin.contains(personDto.getRol()))
				throw new Exception("el rol no es valido");
		} else {
			if (!rolVet.equals(personDto.getRol()))
				throw new Exception("el rol no es valido");
		}

		if (personDao.findUserExist(personDto))
			throw new Exception("ya existe un usuario con esa cedula");

		if (personDao.existUserByUserName(personDto))
			throw new Exception("ya existe el usuario");

		personDao.createPerson(personDto);
		System.out.println("se ha creado el usuario");
	}

	@Override
	public void createFacture(FactureDto factureDto) throws Exception {

		List<HistoryClinicalDto> listHistorySearch = consultHistoryClinical(
				new HistoryClinicalDto(factureDto.getOrderId()));

		for (HistoryClinicalDto HistoryDtoSearch : listHistorySearch)
			if (HistoryDtoSearch.getOrderCancellation())
				throw new Exception(
						"No se puede realizar la venta de medicamentos debido a que la orden a sido cancelada.");

		PetDto pet = petDao.findById(new PetDto(factureDto.getPetId()));

		if (pet == null)
			throw new Exception("No existe la mascota.");

		// Enviar el codigo del dueño
		factureDto.setOwnerId(pet.getOwnerId());

		PersonDto ownerSearch = personDao.findUserById(new PersonDto(factureDto.getOwnerId()));
		if (ownerSearch == null)
			throw new Exception("No existe el dueño.");
		else if (!ownerSearch.getRol().equals("Dueño"))
			throw new Exception(
					"El usuario selecciona no es dueño de mascotas, tiene el rol de " + ownerSearch.getRol() + ".");

		if (!orderDao.findOrderExist(new OrderDto(factureDto.getOrderId())))
			throw new Exception("No existe la orden.");

		factureDao.createFacture(factureDto);
		System.out.println("se ha creado la factura");
	}

	@Override
	public void setSesionID(long sesionId) {
		sessionId = sesionId;
	}

	@Override
	public void login(PersonDto personDto) throws Exception {
		PersonDto personDtoValidate = personDao.findUserByUserName(personDto);
		if (personDtoValidate == null)
			throw new Exception("usuario no valido");

		if (!personDto.getPassword().equals(personDtoValidate.getPassword()))
			throw new Exception("usuario o contraseña incorrectos");

		personDto.setRol(personDtoValidate.getRol());
		SessionDto sesionDto = loginDao.login(personDtoValidate);
		setSesionID(sesionDto.getId());
		System.out.println("se inicia la sesion " + sessionId);
	}

	@Override
	public void logout() throws Exception {
		loginDao.logout(sessionId);
		setSesionID(0);
	}

	@Override
	public void createPet(PetDto petDto) throws Exception {
		if (!personDao.findUserExist(new PersonDto(petDto.getOwnerId())))
			throw new Exception("No existe el dueño.");
		petDao.createPet(petDto);
		System.out.println("Se ha creado la mascota");
	}

	@Override
	public List<HistoryClinicalDto> consultHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception {
		return historyDao.findHistoryByOrden(historyClinicalDto);
	}

	@Override
	public void createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception {
		PetDto petDto = petDao.findById(new PetDto(historyClinicalDto.getPetId()));
		if (petDto == null)
			throw new Exception("No existe la mascota.");
		if (!personDao.findUserExist(new PersonDto(historyClinicalDto.getVetId())))
			throw new Exception("No existe el veterinario.");

		OrderDto orderCreate = createOrdern(new OrderDto(historyClinicalDto.getPetId(), petDto.getOwnerId(),
				historyClinicalDto.getVetId(), historyClinicalDto.getMedicament(), historyClinicalDto.getDate()));

		historyClinicalDto.setOrderID(orderCreate.getId());

		// Creación de historia clinica
		historyDao.createHistory(historyClinicalDto);
		System.out.println("Se ha creado la historia clinica");
	}

	public OrderDto consultOrdern(OrderDto orderDto) throws Exception {
		OrderDto orderSearch = orderDao.searchOrder(new OrderDto(orderDto.getId()));
		if (orderSearch == null)
			System.out.println("No se ha encontrado una orden");

		return orderSearch;
	}

	@Override
	public void cancelOrdern(OrderDto orderDto) throws Exception {
		OrderDto orderSearch = orderDao.searchOrder(new OrderDto(orderDto.getId()));
		if (orderSearch == null)
			System.out.println("No se ha encontrado una orden");

		historyDao.cancelOrder(orderSearch.getId());
		/*
		 * List<HistoryClinicalDto> listHistory = this.consultHistoryClinical(new
		 * HistoryClinicalDto(orderSearch.getId()));
		 * 
		 * for (HistoryClinicalDto elemento : listHistory) { historyDao.cancelOrder(new
		 * HistoryClinicalDto(orderSearch.getId())); }
		 */
	}

	@Override
	public OrderDto createOrdern(OrderDto orderDto) throws Exception {
		OrderDto orderCreate = orderDao.createOrder(orderDto);
		if (orderCreate == null)
			throw new Exception("No se ha creado la orden.");
		return orderCreate;
	}

	@Override
	public long findMedicoIdSesion() throws Exception {
		SessionDto session = loginDao.findSessionById(sessionId);
		if (session == null)
			throw new Exception("No se ha encontrado la sesión actual.");

		PersonDto personDto = personDao.findUserByUserName(new PersonDto(session.getUserName()));
		if (personDto == null)
			throw new Exception("No se ha encontrado el medico de la sesión actual.");

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
}
