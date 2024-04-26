package tdea.construccion2.app.validators;

import org.springframework.stereotype.Component;

@Component
public class PersonValidator extends InputsValidators {
	public void fullNameValidator(String fullName) throws Exception {
		super.stringValidator(fullName, "Nombre de usuario");
	}

	public long idValidator(String id) throws Exception {
		return super.longValidator(id, "Id de usuario");
	}

	public void rollValidator(String roll) throws Exception {
		super.stringValidator(roll, "Roll de usuario");
	}

	public void userNameValidator(String roll) throws Exception {
		super.stringValidator(roll, "Usuario");
	}

	public void passwordValidator(String password) throws Exception {
		super.stringValidator(password, "Contraseña de usuario");
	}
	
	public int ageValidator(String age) throws Exception {
		return super.integerValidator(age, "Edad de usuario");
	}
}
