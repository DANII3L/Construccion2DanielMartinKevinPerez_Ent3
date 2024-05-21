package tdea.construccion2.app.jwt;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdea.construccion2.app.models.Person;
import tdea.construccion2.app.repository.PersonRepository;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personRepository.findByUsername(username);
		
		if(!Objects.isNull(person))
			return new org.springframework.security.core.userdetails.User(person.getUsername(),person.getPassword(),new ArrayList<>());
		else
			throw new RuntimeException("Usuario no encontrado");
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
}
