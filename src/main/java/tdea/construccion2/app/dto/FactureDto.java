package tdea.construccion2.app.dto;

import java.sql.Date;

import tdea.construccion2.app.models.Facture;

public class FactureDto {
	private int id;
	private int petId;
	private long ownerId;
	private int orderId;
	private String medicineName;
	private double cost;
	private int amount;
	private Date date;

	public FactureDto(int petId, int orderId, String medicineName, double cost, int amount) {
		this.petId = petId;
		this.orderId = orderId;
		this.medicineName = medicineName;
		this.cost = cost;
		this.amount = amount;
		this.date = new Date(System.currentTimeMillis());;
	}
	
	public FactureDto(Facture facture) {
		this.id = facture.getId();
		this.petId = facture.getPetId();
		this.ownerId = facture.getOwnerId();
		this.orderId = facture.getOrderId();
		this.medicineName = facture.getMedicineName();
		this.cost = facture.getCost();
		this.amount = facture.getAmount();
		this.date = facture.getDate();
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

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
