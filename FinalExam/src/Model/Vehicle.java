package Model;

import java.sql.Date;


public class Vehicle {
	
	private String ownerName; // Ten chu so huu
	private int identityCard; // CCCD
	private String type; // Loai phuong tien
	private String licensePlate; // Bien so
	private String brand; // Nhan hieu
	private String chassisNumber; // So khung
	private String engineNumber; // So may
	private Date date;
	
	public Vehicle() {
		
	}	

	public Vehicle(String ownerName, int identityCard, String type, String licensePlate, String brand,
			String chassisNumber, String engineNumber) {
		super();
		this.ownerName = ownerName;
		this.identityCard = identityCard;
		this.type = type;
		this.licensePlate = licensePlate;
		this.brand = brand;
		this.chassisNumber = chassisNumber;
		this.engineNumber = engineNumber;

	}

	public Vehicle(String ownerName, int identityCard, String type, String licensePlate, String brand,
			String chassisNumber, String engineNumber, Date date) {
		super();
		this.ownerName = ownerName;
		this.identityCard = identityCard;
		this.type = type;
		this.licensePlate = licensePlate;
		this.brand = brand;
		this.chassisNumber = chassisNumber;
		this.engineNumber = engineNumber;
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(int identityCard) {
		this.identityCard = identityCard;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Vehicle [type=" + type + ", ownerName=" + ownerName + ", identityCard=" + identityCard
				+ ", chassisNumber=" + chassisNumber + ", engineNumber=" + engineNumber + ", brand=" + brand
				+ ", licensePlate=" + licensePlate + ", date=" + date + "]";
	}
	
	
}
