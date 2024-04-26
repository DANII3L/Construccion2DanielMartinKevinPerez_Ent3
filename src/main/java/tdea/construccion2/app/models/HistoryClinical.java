package tdea.construccion2.app.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tdea.construccion2.app.dto.HistoryClinicalDto;

@Entity
@Table(name = "historia")
public class HistoryClinical {
	@Id
	@Column(name = "fecha")
	private Date date;
	@Column(name = "mascota")
	private int petId;
	@Column(name = "medico")
	private long vetId;
	@Column(name = "motivo")
	private String reasonConsult;
	@Column(name = "sintomatologia")
	private String symptomatology;
	@Column(name = "diagnosis")
	private String diagnosis;
	@Column(name = "procedimiento")
	private String procedure;
	@Column(name = "medicamento")
	private String medicament;
	@Column(name = "orderid")
	private int orderID;
	@Column(name = "vacunacion")
	private String vaccinationHistory;
	@Column(name = "dosis")
	private String medicationDosage;
	@Column(name = "alergia")
	private String drugAllergy;
	@Column(name = "detalles_procedimiento")
	private String detailProcedure;
	@Column(name = "ordercancelation")
	private boolean orderCancellation;

	public HistoryClinical(HistoryClinicalDto historyClinicalDto) {
		this.date = historyClinicalDto.getDate();
		this.petId = historyClinicalDto.getPetId();
		this.vetId = historyClinicalDto.getVetId();
		this.reasonConsult = historyClinicalDto.getReasonConsult();
		this.symptomatology = historyClinicalDto.getSymptomatology();
		this.diagnosis = historyClinicalDto.getDiagnosis();
		this.procedure = historyClinicalDto.getProcedure();
		this.medicament = historyClinicalDto.getMedicament();
		this.orderID = historyClinicalDto.getOrderID();
		this.vaccinationHistory = historyClinicalDto.getVaccinationHistory();
		this.medicationDosage = historyClinicalDto.getMedicationDosage();
		this.drugAllergy = historyClinicalDto.getDrugAllergy();
		this.detailProcedure = historyClinicalDto.getDetailProcedure();
		this.orderCancellation = historyClinicalDto.getOrderCancellation();
	}

	public HistoryClinical(int petId, long vetId, String reasonConsult, String symptomatology, String diagnosis,
			String procedure, String medicament, String vaccinationHistory, String medicationDosage, String drugAllergy,
			String detailProcedure, boolean orderCancellation) {
		this.petId = petId;
		this.vetId = vetId;
		this.reasonConsult = reasonConsult;
		this.symptomatology = symptomatology;
		this.diagnosis = diagnosis;
		this.procedure = procedure;
		this.medicament = medicament;
		this.vaccinationHistory = vaccinationHistory;
		this.medicationDosage = medicationDosage;
		this.drugAllergy = drugAllergy;
		this.detailProcedure = detailProcedure;
		this.orderCancellation = orderCancellation;
		this.date = new Date(System.currentTimeMillis());
	}

	
	public HistoryClinical() {
		
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