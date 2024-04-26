package tdea.construccion2.app.models;

import java.sql.Date;

import tdea.construccion2.app.dto.FactureDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura")
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "petid")
	private int petId;
	@Column(name = "ownerid")
	private long ownerId;
	@Column(name = "orderid")
	private int orderId;
	@Column(name = "producto")
	private String medicineName;
	@Column(name = "valor")
	private double cost;
	@Column(name = "cantidad")
	private int amount;
	@Column(name = "fecha")
	private Date date;

	public Facture(FactureDto factureDto) {
		this.id = factureDto.getId();
		this.petId = factureDto.getPetId();
		this.ownerId = factureDto.getOwnerId();
		this.orderId = factureDto.getOrderId();
		this.medicineName = factureDto.getMedicineName();
		this.cost = factureDto.getCost();
		this.amount = factureDto.getAmount();
		this.date = factureDto.getDate();
	}
	
	public Facture() {
		
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
