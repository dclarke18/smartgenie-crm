/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Represents the SmartGenie Smart repair Job/Order/Lead.
 * 
 * Example: NA-Insurance job REV-03458- Complectus Ltd
 * 
 * Client details
 * 
 * Name: Mr P Cook
 * 
 * Address: Ladywood Langley Road Chipperfield WD4 9JS
 * 
 * Email Address:
 * 
 * Tel No.: 01923 262 120
 * 
 * Vehicle Make & Model: Range Rover Sport
 * 
 * Vehicle Reg: FY11 KFP
 * 
 * Damage: rear bumper (o/s of number plate)
 * 
 * Price: £105.00
 * 
 * @author dave.clarke@blc-services.co.uk
 *
 */
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Type of the job - this should probably be a class per type consider
	 * refactoring into class per type if type attributes diverge further.
	 */
	private JobType type;

	/**
	 * status of the job
	 */
	private JobStatus status;

	/**
	 * Date this job was received by company. Not necessarily the date this was
	 * logged into this system.
	 */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateReceived;

	/**
	 * System metadata should only be populated by the DB/JPA.
	 */
	@Version
	private int version;

	/**
	 * System metadata should only be populated by the DB/JPA.
	 */
	private Instant createdDate;
	/**
	 * System metadata should only be populated by the DB/JPA.
	 */
	private Instant lastModified;

	/**
	 * Customers full name
	 */
	private String name;

	/**
	 * address of the customer
	 */
	private String address;

	/**
	 * postcode of the customer
	 */
	private String postcode;

	/**
	 * customers email address
	 */
	private String emailAddress;

	/**
	 * Customer telephone number
	 */
	private String telNo;

	private String vehicleMakeAndModel;

	private String vehicleReg;
	private String damageDescription;

	/**
	 * Does the repairing technician need to take photos and upload them.
	 */
	private Boolean imagesRequired;

	/**
	 * price of the repair excluding vat
	 */
	private BigDecimal price;

	/* ******************* *
	 * TYPE SPECIFIC FIELDS ********************
	 */

	/* NATIONAL ACCOUNT */
	/**
	 * National account order id
	 */
	private String orderId;

	/* RETAIL */
	/**
	 * Where the lead came from, what made them call Revive?
	 */
	private String leadSource;

	/* TRADE */
	/**
	 * What trade customer requested the job
	 */
	private String traderName;

	/**
	 * Name of the contact (normally a sales person) who requested the job.
	 */
	private String tradeContactName;

	/* ************************** *
	 * End of TYPE SPECIFIC FIELDS ***************************
	 */

	@PrePersist
	void createdAt() {
		this.createdDate = this.lastModified = Instant.now();
	}

	@PreUpdate
	void updatedAt() {
		this.lastModified = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public LocalDate getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(LocalDate dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Instant getLastModified() {
		return lastModified;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postCode) {
		this.postcode = postCode;
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

	public Boolean getImagesRequired() {
		return imagesRequired;
	}

	public void setImagesRequired(Boolean imagesRequired) {
		this.imagesRequired = imagesRequired;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public String getTradeContactName() {
		return tradeContactName;
	}

	public void setTradeContactName(String tradeContactName) {
		this.tradeContactName = tradeContactName;
	}

	public int getVersion() {
		return version;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", type=" + type + ", status=" + status
				+ ", dateReceived=" + dateReceived + ", version=" + version
				+ ", createdDate=" + createdDate + ", lastModified="
				+ lastModified + ", name=" + name + ", address=" + address
				+ ", postcode=" + postcode + ", emailAddress=" + emailAddress
				+ ", telNo=" + telNo + ", vehicleMakeAndModel="
				+ vehicleMakeAndModel + ", vehicleReg=" + vehicleReg
				+ ", damageDescription=" + damageDescription
				+ ", imagesRequired=" + imagesRequired + ", price=" + price
				+ ", orderId=" + orderId + ", leadSource=" + leadSource
				+ ", traderName=" + traderName + ", tradeContactName="
				+ tradeContactName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime
				* result
				+ ((damageDescription == null) ? 0 : damageDescription
						.hashCode());
		result = prime * result
				+ ((dateReceived == null) ? 0 : dateReceived.hashCode());
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((imagesRequired == null) ? 0 : imagesRequired.hashCode());
		result = prime * result
				+ ((leadSource == null) ? 0 : leadSource.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result
				+ ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((telNo == null) ? 0 : telNo.hashCode());
		result = prime
				* result
				+ ((tradeContactName == null) ? 0 : tradeContactName.hashCode());
		result = prime * result
				+ ((traderName == null) ? 0 : traderName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (damageDescription == null) {
			if (other.damageDescription != null)
				return false;
		} else if (!damageDescription.equals(other.damageDescription))
			return false;
		if (dateReceived == null) {
			if (other.dateReceived != null)
				return false;
		} else if (!dateReceived.equals(other.dateReceived))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagesRequired == null) {
			if (other.imagesRequired != null)
				return false;
		} else if (!imagesRequired.equals(other.imagesRequired))
			return false;
		if (leadSource == null) {
			if (other.leadSource != null)
				return false;
		} else if (!leadSource.equals(other.leadSource))
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
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (status != other.status)
			return false;
		if (telNo == null) {
			if (other.telNo != null)
				return false;
		} else if (!telNo.equals(other.telNo))
			return false;
		if (tradeContactName == null) {
			if (other.tradeContactName != null)
				return false;
		} else if (!tradeContactName.equals(other.tradeContactName))
			return false;
		if (traderName == null) {
			if (other.traderName != null)
				return false;
		} else if (!traderName.equals(other.traderName))
			return false;
		if (type != other.type)
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

}
