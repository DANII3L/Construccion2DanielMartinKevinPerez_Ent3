package tdea.construccion2.app.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.PersonValidator;

@Component
public class AdminController {
	private static Scanner reader = new Scanner(System.in);
	
	@Autowired
	private VeterinaryService veterinaryService;
	@Autowired
	private PersonValidator personValidator;
	private static final String MENU = "ingrese\n1.Para crear usuario\n2.Para cerrar Sesion";

	private void createUser() throws Exception {
		System.out.println("ingrese el nombre completo");
		String fullName = reader.nextLine();
		personValidator.fullNameValidator(fullName);
		System.out.println("ingrese la cedula del usuario");
		Long id = personValidator.idValidator(reader.nextLine());
		System.out.println("ingrese el rol completo");
		String rol = reader.nextLine();
		personValidator.fullNameValidator(rol);
		System.out.println("ingrese la edad del usuario");
		int age = personValidator.ageValidator(reader.nextLine());
		System.out.println("ingrese nombre de usuario");
		String userName = reader.nextLine();
		personValidator.fullNameValidator(userName);
		System.out.println("ingrese la contraseña");
		String password = reader.nextLine();
		personValidator.fullNameValidator(password);
		PersonDto personDto = new PersonDto(id, fullName, rol, userName, password, age);
		veterinaryService.createUser(personDto, "Administrador");
	}

	public void session() {
		boolean runApp = true;
		while (runApp) {
			try {
				System.out.println(MENU);
				String option = reader.nextLine();
				runApp = menu(option);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

	private boolean menu(String option) throws Exception {
		switch (option) {
		case "1": {
			createUser();
			return true;
		}
		case "2":
			return false;
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}

	public VeterinaryService getVeterinaryService() {
		return veterinaryService;
	}

	public void setVeterinaryService(VeterinaryService veterinaryService) {
		this.veterinaryService = veterinaryService;
	}

	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}
}
