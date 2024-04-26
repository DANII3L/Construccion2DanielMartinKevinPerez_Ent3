package tdea.construccion2.app.validators;

import org.springframework.stereotype.Component;

@Component
public class PetValidator extends InputsValidators {
	public int petIdValidator(String petId) throws Exception {
		return super.integerValidator(petId, "Id de la mascota");
	}
	public void namePetValidator(String namePet) throws Exception {
		super.stringValidator(namePet, "Nombre de mascota");
	}
	public int agePetValidator(String agePet) throws Exception {
		return super.integerValidator(agePet, "Edad de la mascota");
	}
	public void speciesPetValidator(String speciesPet) throws Exception {
		super.stringValidator(speciesPet, "Especie de mascota");
	}
	public void racePetValidator(String racePet) throws Exception {
		super.stringValidator(racePet, "Raza de mascota");
	}
	public void characteristicsPetValidator(String characteristicsPet) throws Exception {
		super.stringValidator(characteristicsPet, "Caracteristica de mascota");
	}
	public Double weightPetValidator(String weightPet) throws Exception {
		return super.doubleValidator(weightPet, "Peso de mascota");
	}
}
