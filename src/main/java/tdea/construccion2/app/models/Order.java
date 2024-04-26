package tdea.construccion2.app.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tdea.construccion2.app.dto.OrderDto;

@Entity
@Table(name = "orden")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "mascota")
	private int petId;
	@Column(name = "propietario")
	private long ownerID;
	@Column(name = "medico")
	private long vetId;
	@Column(name = "medicamento")
	private String medicineName;
	@Column(name = "fecha")
	private Date dateRegister;

	public Order(OrderDto orderDto) {
		this.id = orderDto.getId();
		this.petId = orderDto.getPetId();
		this.ownerID = orderDto.getOwnerID();
		this.vetId = orderDto.getVetId();
		this.medicineName = orderDto.getMedicineName();
		this.dateRegister = orderDto.getDateRegister();
	}

	public Order() {
		this.dateRegister = new Date(System.currentTimeMillis());
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
