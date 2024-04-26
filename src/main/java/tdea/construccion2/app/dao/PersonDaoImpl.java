package tdea.construccion2.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.models.Person;
import tdea.construccion2.app.repository.PersonRepository;

@Service
public class PersonDaoImpl implements PersonDao {
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void createPerson(PersonDto personDto) throws Exception {
		personRepository.save(new Person(personDto));
	}

	@Override
	public boolean findUserExist(PersonDto personDto) throws Exception {
		return personRepository.existsByCedula(personDto.getCedula());
	}

	@Override
	public PersonDto findUserById(PersonDto personDto) throws Exception {
		Person person = personRepository.findByCedula(personDto.getCedula());
		if (person == null)
			return null;

		return new PersonDto(person);
	}

	@Override
	public boolean existUserByUserName(PersonDto personDto) throws Exception {
		return personRepository.existsByUsername(personDto.getUserName());
	}

	@Override
	public PersonDto findUserByUserName(PersonDto personDto) throws Exception {
		Person person = personRepository.findByUsername(personDto.getUserName());
		if (person == null)
			return null;

		return new PersonDto(person);
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

}
