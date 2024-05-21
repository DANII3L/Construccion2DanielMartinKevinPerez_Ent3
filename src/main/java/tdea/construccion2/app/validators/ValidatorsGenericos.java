package tdea.construccion2.app.validators;

import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;

public interface ValidatorsGenericos {
	public void createPerson (PersonDto personDto, String Rol) throws Exception;
	public void createPet (PetDto petDto) throws Exception;
	public void createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception;
	public void sellProducts(FactureDto factureDto) throws Exception;
}
