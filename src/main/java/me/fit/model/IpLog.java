package me.fit.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IpLog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iplog_seq")
	private Long id;
	private String ipString;
	private String ipType;
	private Date createdDate;

	public IpLog() {
		super();
	}

	public IpLog(Long id, String ipString, String ipType, Date createdDate) {
		super();
		this.id = id;
		this.ipString = ipString;
		this.ipType = ipType;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIpString() {
		return ipString;
	}

	public void setIpString(String ipString) {
		this.ipString = ipString;
	}

	public String getIpType() {
		return ipType;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ipString == null) ? 0 : ipString.hashCode());
		result = prime * result + ((ipType == null) ? 0 : ipType.hashCode());
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
		IpLog other = (IpLog) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ipString == null) {
			if (other.ipString != null)
				return false;
		} else if (!ipString.equals(other.ipString))
			return false;
		if (ipType == null) {
			if (other.ipType != null)
				return false;
		} else if (!ipType.equals(other.ipType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IpLog [id=" + id + ", ipString=" + ipString + ", ipType=" + ipType + ", createdDate=" + createdDate
				+ "]";
	}

}
