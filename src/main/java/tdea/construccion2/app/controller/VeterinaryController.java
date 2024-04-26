package tdea.construccion2.app.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.OrderDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;
import tdea.construccion2.app.models.HistoryClinical;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.PersonValidator;
import tdea.construccion2.app.validators.PetValidator;
import tdea.construccion2.app.validators.HistoryClinicalValidator;
import tdea.construccion2.app.validators.OrderValidator;

@Component
public class VeterinaryController {
	private static Scanner reader = new Scanner(System.in);
	@Autowired
	private PersonValidator personValidator;
	@Autowired
	private HistoryClinicalValidator historyCValidator;
	@Autowired
	private PetValidator petValidator;
	@Autowired
	private OrderValidator orderValidator;
	@Autowired
	private VeterinaryService veterinaryService;
	private static final String MENU = "ingrese\n1.Para crear un dueño de mascota.\n2.Para crear una mascota.\n3.Para crear una historia clinica"
			+ "\n4.Para consultar una historia clinica\n5.Para cancelar orden\n6.Para cerrar Sesion";

	private void createUser() throws Exception {
		System.out.println("ingrese el nombre completo");
		String fullName = reader.nextLine();
		personValidator.fullNameValidator(fullName);
		System.out.println("ingrese la cedula del usuario");
		Long id = personValidator.idValidator(reader.nextLine());
		System.out.println("ingrese el rol completo");
		String rol = reader.nextLine();
		personValidator.fullNameValidator(rol);
		System.out.println("ingrese la edad del usuario");
		int age = personValidator.ageValidator(reader.nextLine());
		System.out.println("ingrese nombre de usuario");
		String userName = reader.nextLine();
		personValidator.fullNameValidator(userName);
		System.out.println("ingrese la contraseña");
		String password = reader.nextLine();
		personValidator.fullNameValidator(password);

		veterinaryService.createUser(new PersonDto(id, fullName, rol, userName, password, age), "Veterinario");
	}

	private void createPet() throws Exception {
		System.out.println("ingrese el nombre de la mascota");
		String name = reader.nextLine();
		personValidator.fullNameValidator(name);
		System.out.println("ingrese la cedula del dueño");
		long id = personValidator.idValidator(reader.nextLine());
		System.out.println("ingrese la edad de la mascota");
		int age = petValidator.agePetValidator(reader.nextLine());
		System.out.println("ingrese la especie de la mascota");
		String species = reader.nextLine();
		petValidator.speciesPetValidator(species);
		System.out.println("ingrese la raza de la mascota");
		String race = reader.nextLine();
		petValidator.racePetValidator(race);
		System.out.println("ingrese las caracteristicas de la mascota");
		String characteristics = reader.nextLine();
		petValidator.characteristicsPetValidator(characteristics);
		System.out.println("ingrese el peso de la mascota");
		Double weight = petValidator.weightPetValidator(reader.nextLine());

		veterinaryService.createPet(new PetDto(0, name, id, age, species, race, characteristics, weight));
	}

	private void createHistoryClinical() throws Exception {
		System.out.println("ingrese el id de la mascota");
		int petId = petValidator.petIdValidator(reader.nextLine());
		//System.out.println("ingrese el id del medico");
		long vetId = veterinaryService.findMedicoIdSesion();
		System.out.println("ingrese la razón de la consulta");
		String reasonConsult = reader.nextLine();
		historyCValidator.reasonConsultValidator(reasonConsult);
		System.out.println("ingrese sintomatologia");
		String symptomatology = reader.nextLine();
		historyCValidator.symptomatologyValidator(symptomatology);
		System.out.println("ingrese el diagnostico");
		String diagnosis = reader.nextLine();
		historyCValidator.diagnosisValidator(diagnosis);
		System.out.println("ingrese el procedimiento");
		String procedure = reader.nextLine();
		historyCValidator.procedureValidator(procedure);
		System.out.println("ingrese el/los medicamento/s");
		String medicament = reader.nextLine();
		historyCValidator.medicamentValidator(medicament);
		System.out.println("ingrese el historial de vacunación");
		String vaccinationHistory = reader.nextLine();
		historyCValidator.vaccinationHistoryValidator(vaccinationHistory);
		System.out.println("ingrese dosis de medicamento");
		String medicationDosage = reader.nextLine();
		historyCValidator.medicationDosageValidator(medicationDosage);
		System.out.println("ingrese medicamentos a los que presenta alergia");
		String drugAllergy = reader.nextLine();
		historyCValidator.drugAllergyValidator(drugAllergy);
		System.out.println("ingrese detalle del procedimiento");
		String detailProcedure = reader.nextLine();
		historyCValidator.detailProcedureValidator(detailProcedure);
		System.out.println("ingrese estado para anulación orden (1/anular - 0/activa)");
		boolean orderCancellation = historyCValidator.orderCancellationValidator(reader.nextLine());

		HistoryClinical historyC = new HistoryClinical(petId, vetId, reasonConsult, symptomatology, diagnosis,
				procedure, medicament, vaccinationHistory, medicationDosage, drugAllergy, detailProcedure,
				orderCancellation);
		veterinaryService.createHistoryClinical(new HistoryClinicalDto(historyC));
	}

	private void searchHistoryClinical() throws Exception {
		System.out.println("ingrese el id de la orden");
		int orderId = orderValidator.orderIdValidator(reader.nextLine());

		List<HistoryClinicalDto> listHistoryDtoSearch = veterinaryService
				.consultHistoryClinical(new HistoryClinicalDto(orderId));
		
		if (!listHistoryDtoSearch.isEmpty()) {
			System.out.println("Historias clínicas encontradas:");
			for(HistoryClinicalDto HistoryDtoSearch:listHistoryDtoSearch)
			{
				System.out.println("Fecha: " + HistoryDtoSearch.getDate());
				System.out.println("Mascota: " + HistoryDtoSearch.getPetId());
				System.out.println("Razón de consulta: " + HistoryDtoSearch.getReasonConsult());
				System.out.println("Sintomatologia: " + HistoryDtoSearch.getSymptomatology());
				System.out.println("Diagnostico: " + HistoryDtoSearch.getDiagnosis());
				System.out.println("Procedimiento: " + HistoryDtoSearch.getProcedure());
				System.out.println("Medicamento/s: " + HistoryDtoSearch.getMedicament());
				System.out.println("Dosis de medicamento: " + HistoryDtoSearch.getMedicationDosage());
				System.out.println("ID de la orden: " + HistoryDtoSearch.getOrderID());
				System.out.println("Historial de vacunación: " + HistoryDtoSearch.getVaccinationHistory());
				System.out.println("Medicamentos a los que presenta alergia: " + HistoryDtoSearch.getDrugAllergy());
				System.out.println("Detalle del procedimiento: " + HistoryDtoSearch.getDetailProcedure());
				System.out.println("Anulación orden: " + HistoryDtoSearch.getOrderCancellation());
				System.out.println("-------------------------------------");
			}
		} else
			System.out.println("No se encontró alguna historia clínica con código de orden " + orderId);
	}
	
	public void searchOrder() throws Exception {
		System.out.println("ingrese el id de la orden");
		int orderId = orderValidator.orderIdValidator(reader.nextLine());
		OrderDto orderSearch = veterinaryService.consultOrdern(new OrderDto(orderId));
		if(orderSearch != null)
		{
			System.out.println("Orden encontrada:");
			System.out.println("Mascota: " + orderSearch.getPetId());
			System.out.println("Cédula de dueño: " + orderSearch.getOwnerID());
			System.out.println("Veterinario: " + orderSearch.getVetId());
			System.out.println("Nombre de medicamento/s: " + orderSearch.getMedicineName());
			System.out.println("Fecha de registro: " + orderSearch.getDateRegister());
			List<HistoryClinicalDto> listHistorySearch = veterinaryService.consultHistoryClinical(new HistoryClinicalDto(orderSearch.getId()));
			for(HistoryClinicalDto HistoryDtoSearch:listHistorySearch)
				if(HistoryDtoSearch.getOrderCancellation())
					System.out.println("El estado de la orden es cancelado.");
			System.out.println("-------------------------------------");
		}else
			System.out.println("No se ha encontrado una orden");
	}
	
	public void cancelOrder() throws Exception {
		System.out.println("ingrese el id de la orden");
		int orderId = orderValidator.orderIdValidator(reader.nextLine());
		
		veterinaryService.cancelOrdern(new OrderDto(orderId));
		System.out.println("Orden cancelada correctamente.");
	}

	public void session() {
		boolean runApp = true;
		while (runApp)
			try {
				System.out.println(MENU);
				runApp = menu(reader.nextLine());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}

	private boolean menu(String option) throws Exception {
		switch (option) {
		case "1":
			createUser();
			return true;
		case "2":
			createPet();
			return true;
		case "3":
			createHistoryClinical();
			return true;
		case "4":
			searchHistoryClinical();
			return true;
		case "5":
			cancelOrder();
			return true;
		case "6":
			return false;
		default:
			System.out.println("ingrese una opcion valida");
			return true;
		}
	}

	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}

	public HistoryClinicalValidator getHistoryCValidator() {
		return historyCValidator;
	}

	public void setHistoryCValidator(HistoryClinicalValidator historyCValidator) {
		this.historyCValidator = historyCValidator;
	}

	public PetValidator getPetValidator() {
		return petValidator;
	}

	public void setPetValidator(PetValidator petValidator) {
		this.petValidator = petValidator;
	}

	public OrderValidator getOrderValidator() {
		return orderValidator;
	}

	public void setOrderValidator(OrderValidator orderValidator) {
		this.orderValidator = orderValidator;
	}

	public VeterinaryService getVeterinaryService() {
		return veterinaryService;
	}

	public void setVeterinaryService(VeterinaryService veterinaryService) {
		this.veterinaryService = veterinaryService;
	}
}
