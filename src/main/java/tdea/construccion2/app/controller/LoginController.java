package tdea.construccion2.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.responseRequest.ModelLogin;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.PersonValidator;

@RestController
@RequestMapping("/Home")
public class LoginController {
	@Autowired
	private PersonValidator personInputValidator;
	@Autowired
	private VeterinaryService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody ModelLogin model) throws Exception {
		String username = model.getUsername();
		personInputValidator.userNameValidator(username);
		String pwd = model.getPassword();
		personInputValidator.passwordValidator(pwd);
		String message = loginService.login(new PersonDto(username, pwd));
		
		return ResponseEntity.ok(message);
	}
	
	public PersonValidator getPersonInputValidator() {
		return personInputValidator;
	}

	public void setPersonInputValidator(PersonValidator personInputValidator) {
		this.personInputValidator = personInputValidator;
	}

	public VeterinaryService getLoginService() {
		return loginService;
	}

	public void setLoginService(VeterinaryService loginService) {
		this.loginService = loginService;
	}
}
