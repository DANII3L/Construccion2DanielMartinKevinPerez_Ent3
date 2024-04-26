package tdea.construccion2.app.dto;

import tdea.construccion2.app.models.Person;

public class PersonDto {
	private long cedula;
	private String fullName;
	private String rol;
	private int Age;
	private String userName;
	private String password;
	
	public PersonDto(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public PersonDto(String userName) {
		this.userName = userName;
	}
	
	public PersonDto(long cedula) {
		this.cedula = cedula;
	}

	public PersonDto(long cedula, String fullName, String rol, String userName, String password, int age) {
		this.cedula = cedula;
		this.fullName = fullName;
		this.rol = rol;
		this.userName = userName;
		this.password = password;
		this.Age = age;
	}

	public PersonDto(Person person) {
		this.cedula = person.getCedula();
		this.fullName = person.getFullName();
		this.rol = person.getRol();
		this.userName = person.getUsername();
		this.password = person.getPassword();
		this.Age = person.getAge();
	}

	public PersonDto() {
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
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
