/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.domain;

import javax.persistence.Embeddable;

/**
 * Some person authorised to use this system.
 * @author dave.clarke@blc-services.co.uk
 *
 */
@Embeddable
public class User {
	
	private String userId;
	private String displayName;
	
	
	public User(String userId, String displayName) {
		super();
		this.userId = userId;
		this.displayName = displayName;
	}
	public String getUserId() {
		return userId;
	}
	public String getDisplayName() {
		return displayName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", displayName=" + displayName + "]";
	}
	
	

}
