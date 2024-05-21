package tdea.construccion2.app.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tdea.construccion2.app.models.HistoryClinical;

@JsonIgnoreProperties({"date", "vetId", "orderID"})
public class HistoryClinicalDto {
	private Date date;
	private int petId;
	private long vetId;
	private String reasonConsult;
	private String symptomatology;
	private String diagnosis;
	private String procedure;
	private String medicament;
	private int orderID;
	private String vaccinationHistory;
	private String medicationDosage;
	private String drugAllergy;
	private String detailProcedure;
	private boolean orderCancellation;

	public HistoryClinicalDto() {
		this.date = new Date(System.currentTimeMillis());
	}
	
	public HistoryClinicalDto(int orderID) {
		this.orderID = orderID;
	}


	public HistoryClinicalDto(long vetId, String reasonConsult, String symptomatology,
			String diagnosis, String procedure, String medicament, String medicationDosage, int orderID,
			String vaccinationHistory, String drugAllergy, String detailProcedure, boolean orderCancellation, Date date, int petId) {
		this.date = date;
		this.vetId = vetId;
		this.reasonConsult = reasonConsult;
		this.symptomatology = symptomatology;
		this.diagnosis = diagnosis;
		this.procedure = procedure;
		this.medicament = medicament;
		this.medicationDosage = medicationDosage;
		this.orderID = orderID;
		this.vaccinationHistory = vaccinationHistory;
		this.drugAllergy = drugAllergy;
		this.detailProcedure = detailProcedure;
		this.orderCancellation = orderCancellation;
		this.petId = petId;
	}
	
	public HistoryClinicalDto(HistoryClinical historyClinical) {
		this.date = historyClinical.getDate();
		this.vetId = historyClinical.getVetId();
		this.reasonConsult = historyClinical.getReasonConsult();
		this.symptomatology = historyClinical.getSymptomatology();
		this.diagnosis = historyClinical.getDiagnosis();
		this.procedure = historyClinical.getProcedure();
		this.medicament = historyClinical.getMedicament();
		this.medicationDosage = historyClinical.getMedicationDosage();
		this.orderID = historyClinical.getOrderID();
		this.vaccinationHistory = historyClinical.getVaccinationHistory();
		this.drugAllergy = historyClinical.getDrugAllergy();
		this.detailProcedure = historyClinical.getDetailProcedure();
		this.orderCancellation = historyClinical.getOrderCancellation();
		this.petId = historyClinical.getPetId();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getVetId() {
		return vetId;
	}

	public void setVetId(long vetId) {
		this.vetId = vetId;
	}

	public String getReasonConsult() {
		return reasonConsult;
	}

	public void setReasonConsult(String reasonConsult) {
		this.reasonConsult = reasonConsult;
	}

	public String getSymptomatology() {
		return symptomatology;
	}

	public void setSymptomatology(String symptomatology) {
		this.symptomatology = symptomatology;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String medicament) {
		this.medicament = medicament;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getVaccinationHistory() {
		return vaccinationHistory;
	}

	public void setVaccinationHistory(String vaccinationHistory) {
		this.vaccinationHistory = vaccinationHistory;
	}
	
	public String getMedicationDosage() {
		return medicationDosage;
	}

	public void setMedicationDosage(String medicationDosage) {
		this.medicationDosage = medicationDosage;
	}

	public String getDrugAllergy() {
		return drugAllergy;
	}

	public void setDrugAllergy(String drugAllergy) {
		this.drugAllergy = drugAllergy;
	}

	public String getDetailProcedure() {
		return detailProcedure;
	}

	public void setDetailProcedure(String detailProcedure) {
		this.detailProcedure = detailProcedure;
	}

	public boolean getOrderCancellation() {
		return orderCancellation;
	}

	public void setOrderCancellation(boolean orderCancellation) {
		this.orderCancellation = orderCancellation;
	}
	
	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}
}
