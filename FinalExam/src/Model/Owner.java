package Model;

import java.sql.Date;

public class Owner {
	
	private String name;
	private int identityCard;
	private Date dayOfBirth;
	private String gender;
	private String address;
	
	public Owner() {

	}
	
	public Owner(String name, int identityCard, Date dayOfBirth, String gender, String address) {
		super();
		this.name = name;
		this.identityCard = identityCard;
		this.dayOfBirth = dayOfBirth;
		this.gender = gender;
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getIdentityCard() {
		return identityCard;
	}


	public void setIdentityCard(int identityCard) {
		this.identityCard = identityCard;
	}


	public Date getDayOfBirth() {
		return dayOfBirth;
	}


	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Owner [name=" + name + ", identityCard=" + identityCard + ", dayOfBirth=" + dayOfBirth + ", gender="
				+ gender + ", address=" + address + "]";
	}
	
	
	
}
