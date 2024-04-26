package tdea.construccion2.app.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.PersonValidator;

@Component
public class LoginController {
	private static Scanner reader = new Scanner(System.in);
	
	@Autowired
	private PersonValidator personInputValidator;
	@Autowired
	private VeterinaryService loginService;
	@Autowired
	private SellerController sellerController;
	@Autowired
	private VeterinaryController veterinaryController;
	@Autowired
	private AdminController adminController;

	public void login() throws Exception {
		System.out.println("ingrese su usuario");
		String userName = reader.nextLine();
		personInputValidator.userNameValidator(userName);
		System.out.println("ingrese su contraseña");
		String password = reader.nextLine();
		personInputValidator.passwordValidator(password);
		PersonDto personDto = new PersonDto(userName, password);
		loginService.login(personDto);
		loginRouter(personDto);
		loginService.logout();
	}

	private void loginRouter(PersonDto personDto) {
		if (personDto.getRol().equals("Veterinario"))
			veterinaryController.session();
		else if (personDto.getRol().equals("Vendedor"))
			sellerController.session();
		else if (personDto.getRol().equals("Administrador"))
			adminController.session();
		else if(personDto.getRol().equals("Dueño"))
			System.out.println("Rol dueños no tiene permisos para manejo de aplicativo.");
		else
			System.out.println("Rol no encontrado en el aplicativo.");
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

	public SellerController getSellerController() {
		return sellerController;
	}

	public void setSellerController(SellerController sellerController) {
		this.sellerController = sellerController;
	}

	public VeterinaryController getVeterinaryController() {
		return veterinaryController;
	}

	public void setVeterinaryController(VeterinaryController veterinaryController) {
		this.veterinaryController = veterinaryController;
	}

	public AdminController getAdminController() {
		return adminController;
	}

	public void setAdminController(AdminController adminController) {
		this.adminController = adminController;
	}
}
