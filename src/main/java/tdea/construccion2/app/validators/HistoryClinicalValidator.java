package tdea.construccion2.app.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class HistoryClinicalValidator extends InputsValidators {
	public void reasonConsultValidator(String reasonConsult) throws Exception {
		super.stringValidator(reasonConsult, "Razón de consulta");
	}
	public void symptomatologyValidator(String symptomatology) throws Exception {
		super.stringValidator(symptomatology, "Sintomatologia");
	}
	public void diagnosisValidator(String diagnosis) throws Exception {
		super.stringValidator(diagnosis, "Diagnostico");
	}
	public void procedureValidator(String procedure) throws Exception {
		super.stringValidator(procedure, "Procedimiento");
	}
	public void medicamentValidator(String medicament) throws Exception {
		super.stringValidator(medicament, "Medicamento");
	}
	public void vaccinationHistoryValidator(String vaccinationHistory) throws Exception {
		super.stringValidator(vaccinationHistory, "Historial de vacunación");
	}
	public void medicationDosageValidator(String medicationDosage) throws Exception {
		super.stringValidator(medicationDosage, "Dosis de medicamento");
	}
	public void drugAllergyValidator(String medicationDosage) throws Exception {
		super.stringValidator(medicationDosage, "Alergia a droga");
	}
	public void detailProcedureValidator(String medicationDosage) throws Exception {
		super.stringValidator(medicationDosage, "Detalle de procedimiento");
	}
	public boolean orderCancellationValidator(String medicationDosage) throws Exception {
		return super.booleanValidator(medicationDosage, "Cancelar orden");
	}
	public Date dateRegisterValidator(String date) throws Exception {
		return super.dateValidator(date, "Fecha de registro");
	}
}
