package tdea.construccion2.app.validators;

import org.springframework.stereotype.Component;

@Component
public class FactureValidator extends InputsValidators {
	public void medicineNameValidator(String medicineName) throws Exception {
		super.stringValidator(medicineName, "Nombre de medicamento");
	}
	
	public double costValidator(String cost) throws Exception {
		return super.doubleValidator(cost, "Costo de medicamento");
	}
	
	public int amountValidator(String amount) throws Exception {
		return super.integerValidator(amount, "Cantidad del medicamento");
	}
	
	public int factureIdValidator(String factureId) throws Exception {
		return super.integerValidator(factureId, "Cantidad del medicamento");
	}
}
