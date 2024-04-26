package tdea.construccion2.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dto.PetDto;
import tdea.construccion2.app.models.Pet;
import tdea.construccion2.app.repository.PetRepository;

@Service
public class PetDaoImp implements PetDao {
	@Autowired
	private PetRepository petRepository;

	@Override
	public PetDto findById(PetDto petDto) throws Exception {
		Pet pet = petRepository.findById(petDto.getId());
		if (pet == null)
			return null;

		return new PetDto(pet);
	}

	@Override
	public boolean findPetExist(PetDto petDto) throws Exception {
		return petRepository.existsById(petDto.getId());
	}

	@Override
	public void createPet(PetDto petDto) throws Exception {
		petRepository.save(new Pet(petDto));
	}

	public PetRepository getPetRepository() {
		return petRepository;
	}

	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
}
