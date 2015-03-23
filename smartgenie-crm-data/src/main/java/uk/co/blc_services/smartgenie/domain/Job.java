/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents the SmartGenie Smart repair Job/Order/Lead.
 * 
 * Example:
 * NA-Insurance job  REV-03458- Complectus Ltd 

	Client details

	Name: Mr P Cook 

	Address:  Ladywood
	Langley Road
	Chipperfield WD4 9JS

	Email Address:  

	Tel No.:   01923 262 120    

	Vehicle Make & Model: Range Rover Sport 

	Vehicle Reg: FY11 KFP 

	Damage:  rear bumper (o/s of number plate)

	Price: £105.00
 * @author dave.clarke@blc-services.co.uk
 *
 */
@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/**
	 * National account order id
	 */
	private String orderId;
	private String name;
	private String address;
	private String emailAddress;
	private String telNo;
	private String vehicleMakeAndModel;
	private String vehicleReg;
	private String damageDescription;
	private BigDecimal price;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getVehicleMakeAndModel() {
		return vehicleMakeAndModel;
	}
	public void setVehicleMakeAndModel(String vehicleMakeAndModel) {
		this.vehicleMakeAndModel = vehicleMakeAndModel;
	}
	public String getVehicleReg() {
		return vehicleReg;
	}
	public void setVehicleReg(String vehicleReg) {
		this.vehicleReg = vehicleReg;
	}
	public String getDamageDescription() {
		return damageDescription;
	}
	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime
				* result
				+ ((damageDescription == null) ? 0 : damageDescription
						.hashCode());
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((telNo == null) ? 0 : telNo.hashCode());
		result = prime
				* result
				+ ((vehicleMakeAndModel == null) ? 0 : vehicleMakeAndModel
						.hashCode());
		result = prime * result
				+ ((vehicleReg == null) ? 0 : vehicleReg.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (damageDescription == null) {
			if (other.damageDescription != null)
				return false;
		} else if (!damageDescription.equals(other.damageDescription))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (telNo == null) {
			if (other.telNo != null)
				return false;
		} else if (!telNo.equals(other.telNo))
			return false;
		if (vehicleMakeAndModel == null) {
			if (other.vehicleMakeAndModel != null)
				return false;
		} else if (!vehicleMakeAndModel.equals(other.vehicleMakeAndModel))
			return false;
		if (vehicleReg == null) {
			if (other.vehicleReg != null)
				return false;
		} else if (!vehicleReg.equals(other.vehicleReg))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", orderId=" + orderId + ", name=" + name
				+ ", vehicleReg=" + vehicleReg + ", address=" + address
				+ ", emailAddress=" + emailAddress + ", telNo=" + telNo
				+ ", vehicleMakeAndModel=" + vehicleMakeAndModel
				+ ", damageDescription=" + damageDescription + ", price="
				+ price + "]";
	}
}
