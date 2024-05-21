package tdea.construccion2.app.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;
import tdea.construccion2.app.service.VeterinaryService;

@Service
public class ValidatorsGenericImp implements ValidatorsGenericos {
	@Autowired
	private PersonValidator personValidator;
	@Autowired
	private PetValidator petValidator;
	@Autowired
	private HistoryClinicalValidator historyCValidator;
	@Autowired
	private OrderValidator orderValidator;
	@Autowired
	private VeterinaryService veterinaryService;
	@Autowired
	private FactureValidator factureValidator;
	
	public void createPerson (PersonDto personDto, String Rol) throws Exception {
		personValidator.fullNameValidator(personDto.getFullName());
		personValidator.idValidator(String.valueOf(personDto.getCedula()));
		if(Rol.equals("Veterinario"))
			personDto.setRol("Dueño");
		personValidator.rollValidator(personDto.getRol());
		personValidator.ageValidator(String.valueOf(personDto.getAge()));
		personValidator.userNameValidator(personDto.getUserName());
		personValidator.passwordValidator(personDto.getPassword());
	}
	
	public void createPet (PetDto petDto) throws Exception {
		petValidator.namePetValidator(petDto.getName());
		personValidator.idValidator(String.valueOf(petDto.getOwnerId()));
		petValidator.agePetValidator(String.valueOf(petDto.getAge()));
		petValidator.speciesPetValidator(petDto.getSpecies());
		petValidator.racePetValidator(petDto.getRace());
		petValidator.characteristicsPetValidator(petDto.getCharacteristics());
		petValidator.weightPetValidator(String.valueOf(petDto.getWeight()));
	}
	
	public void createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception {
		petValidator.petIdValidator(String.valueOf(historyClinicalDto.getPetId()));
		long vetId = veterinaryService.findMedicoIdSesion();
		historyClinicalDto.setVetId(vetId);
		historyCValidator.reasonConsultValidator(historyClinicalDto.getReasonConsult());
		historyCValidator.symptomatologyValidator(historyClinicalDto.getSymptomatology());
		historyCValidator.diagnosisValidator(historyClinicalDto.getDiagnosis());
		historyCValidator.procedureValidator(historyClinicalDto.getProcedure());
		historyCValidator.medicamentValidator(historyClinicalDto.getMedicament());
		historyCValidator.vaccinationHistoryValidator(historyClinicalDto.getVaccinationHistory());
		historyCValidator.medicationDosageValidator(historyClinicalDto.getMedicationDosage());
		historyCValidator.drugAllergyValidator(historyClinicalDto.getDrugAllergy());
		historyCValidator.detailProcedureValidator(historyClinicalDto.getDetailProcedure());
		historyCValidator.orderCancellationValidator(String.valueOf(historyClinicalDto.getOrderCancellation()));
	}
	
	public void sellProducts(FactureDto factureDto) throws Exception {
		petValidator.petIdValidator(String.valueOf(factureDto.getPetId()));
		orderValidator.orderIdValidator(String.valueOf(factureDto.getOrderId()));
		factureValidator.medicineNameValidator(factureDto.getMedicineName());
		factureValidator.costValidator(String.valueOf(factureDto.getCost()));
		factureValidator.amountValidator(String.valueOf(factureDto.getAmount()));
	}

	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}

	public PetValidator getPetValidator() {
		return petValidator;
	}

	public void setPetValidator(PetValidator petValidator) {
		this.petValidator = petValidator;
	}

	public HistoryClinicalValidator getHistoryCValidator() {
		return historyCValidator;
	}

	public void setHistoryCValidator(HistoryClinicalValidator historyCValidator) {
		this.historyCValidator = historyCValidator;
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

	public FactureValidator getFactureValidator() {
		return factureValidator;
	}

	public void setFactureValidator(FactureValidator factureValidator) {
		this.factureValidator = factureValidator;
	} 
}
