package tdea.construccion2.app.dao;

import tdea.construccion2.app.dto.PetDto;

public interface PetDao {
	public PetDto findById(PetDto petDto) throws Exception;
	public boolean findPetExist(PetDto petDto) throws Exception;
	public void createPet(PetDto petDto) throws Exception;
}
