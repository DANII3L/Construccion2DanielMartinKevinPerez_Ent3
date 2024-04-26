package tdea.construccion2.app.models;

import tdea.construccion2.app.dto.PersonDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Person {
	@Id
	@Column(name = "cedula")
	private long cedula;
	@Column(name = "nombre")
	private String fullName;
	@Column(name = "rol")
	private String rol;
	@Column(name = "edad")
	private int Age;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	public Person(PersonDto personDto) {
		this.cedula = personDto.getCedula();
		this.fullName = personDto.getFullName();
		this.rol = personDto.getRol();
		this.Age = personDto.getAge();
		this.username = personDto.getUserName();
		this.password = personDto.getPassword();
	}
	
	public Person() {
		
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
