/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.joda.time.Instant;

/**
 * Record of some change in the history of the job.
 * 
 * @author dave.clarke@blc-services.co.uk
 *
 */
@Entity
public class JobChangeRecord {
	
	@Id
	private long id;
	
	@ManyToOne
	private Job job;
	
	private Instant dateChanged;
	
	private User changedBy;
	
	private String changeDescription;
	

	public Job getJob() {
		return job;
	}

	@PrePersist
	void createdAt() {
		this.dateChanged = Instant.now();
	}
	
	public void setJob(Job job) {
		this.job = job;
	}

	public Instant getDateChanged() {
		return dateChanged;
	}

	public User getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	@Override
	public String toString() {
		return "JobChangeRecord [job=" + job + ", dateChanged=" + dateChanged
				+ ", changedBy=" + changedBy + ", changeDescription="
				+ changeDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((changeDescription == null) ? 0 : changeDescription
						.hashCode());
		result = prime * result
				+ ((changedBy == null) ? 0 : changedBy.hashCode());
		result = prime * result
				+ ((dateChanged == null) ? 0 : dateChanged.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
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
		JobChangeRecord other = (JobChangeRecord) obj;
		if (changeDescription == null) {
			if (other.changeDescription != null)
				return false;
		} else if (!changeDescription.equals(other.changeDescription))
			return false;
		if (changedBy == null) {
			if (other.changedBy != null)
				return false;
		} else if (!changedBy.equals(other.changedBy))
			return false;
		if (dateChanged == null) {
			if (other.dateChanged != null)
				return false;
		} else if (!dateChanged.equals(other.dateChanged))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		return true;
	}

	
}