package tdea.construccion2.app.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tdea.construccion2.app.models.Order;

@JsonIgnoreProperties({"id", "vetId"})
public class OrderDto {
	private int id;
	private int petId;
	private long ownerID;
	private long vetId;
	private String medicineName;
	private Date dateRegister;

	public OrderDto(int id, int petId, long ownerID, long vetId, String medicineName, Date dateRegister) {
		this.id = id;
		this.petId = petId;
		this.ownerID = ownerID;
		this.vetId = vetId;
		this.medicineName = medicineName;
		this.dateRegister = dateRegister;
	}
	
	public OrderDto(int petId, long ownerID, long vetId, String medicineName, Date dateRegister) {
		this.petId = petId;
		this.ownerID = ownerID;
		this.vetId = vetId;
		this.medicineName = medicineName;
		this.dateRegister = dateRegister;
	}
	
	public OrderDto(Order order) {
		this.id = order.getId();
		this.petId = order.getPetId();
		this.ownerID = order.getOwnerID();
		this.vetId = order.getVetId();
		this.medicineName = order.getMedicineName();
		this.dateRegister = order.getDateRegister();
	}
	
	public OrderDto(int id) {
		this.id = id;
	}
	
	public OrderDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}

	public long getVetId() {
		return vetId;
	}

	public void setVetId(long vetId) {
		this.vetId = vetId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
}
