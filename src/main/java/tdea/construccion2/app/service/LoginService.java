package tdea.construccion2.app.service;

import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.PersonDto;

@Component
public interface LoginService {
	public void setSesionID(long sesionId);
	
	public long findMedicoIdSesion() throws Exception;

	public String login(PersonDto personDto) throws Exception;

	public void logout() throws Exception;
}