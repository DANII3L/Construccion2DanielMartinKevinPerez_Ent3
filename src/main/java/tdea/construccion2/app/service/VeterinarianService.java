package tdea.construccion2.app.service;

import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;

@Component
public interface VeterinarianService {
	public void createUser(PersonDto personDto, String rol) throws Exception;

	public void createPet(PetDto petDto) throws Exception;

	public void createFacture(FactureDto factureDto) throws Exception;
}
