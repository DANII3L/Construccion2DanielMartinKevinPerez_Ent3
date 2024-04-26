package tdea.construccion2.app.validators;

import org.springframework.stereotype.Component;

@Component
public class OrderValidator extends InputsValidators {
	public int orderIdValidator(String orderId) throws Exception {
		return super.integerValidator(orderId, "Id de orden");
	}
	public void medicineNameValidator(String medicineName) throws Exception {
		super.stringValidator(medicineName, "Nombre de medicina");
	}
}
