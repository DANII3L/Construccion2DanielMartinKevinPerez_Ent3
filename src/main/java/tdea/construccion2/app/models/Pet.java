package tdea.construccion2.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tdea.construccion2.app.dto.PetDto;

@Entity
@Table(name = "mascota")
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nombre")
	private String name;
	@Column(name = "propietario")
	private long ownerId;
	@Column(name = "edad")
	private int age;
	@Column(name = "especie")
	private String species;
	@Column(name = "raza")
	private String race;
	@Column(name = "caracteristicas")
	private String characteristics;
	@Column(name = "peso")
	private Double weight;
	
	public Pet(PetDto petDto) {
		this.id=petDto.getId();
		this.name=petDto.getName();
		this.age=petDto.getAge();
		this.ownerId=petDto.getOwnerId();
		this.species=petDto.getSpecies();
		this.race=petDto.getRace();
		this.characteristics=petDto.getCharacteristics();
		this.weight=petDto.getWeight();
	}
	
	public Pet() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
