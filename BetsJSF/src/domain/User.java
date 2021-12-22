package domain;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {
	
	private String name;
	private String surname;
	@Id
	private String DNI;
	private String email;
	private String password;
	private float money;


	public User() {
		super();
	}

	/**
	 * @param name
	 * @param surname
	 * @param dNI
	 * @param email
	 * @param password
	 */
	public User(String name, String surname, String dNI, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		DNI = dNI;
		this.email = email;
		this.password = password;
		this.money = 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the dNI
	 */
	public String getDNI() {
		return DNI;
	}

	/**
	 * @param dNI the dNI to set
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the balance
	 */
	public float getMoney() {
		return money;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setMoney(float money) {
		this.money = money;
	}
	

}
