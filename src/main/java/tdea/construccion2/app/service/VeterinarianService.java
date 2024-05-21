package tdea.construccion2.app.service;

import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;

@Component
public interface VeterinarianService {
	public String createUser(PersonDto personDto, String rol) throws Exception;

	public String createPet(PetDto petDto) throws Exception;

	public String createFacture(FactureDto factureDto) throws Exception;
}
