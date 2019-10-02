package com.team.alpha.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_publisher")
public class Publisher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer publisherId;
	@Column(name="publisherName")
	private String publisherName;
	@Column(name="publisherAddress")
	private String publisherAddress;
	@Column(name="publisherPhone")
	private String publisherPhone;
	
	public Publisher() {	
	}
	
	public Publisher(int publisherId, String publisherName, String publisherAddress, String publisherPhone) {
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.publisherAddress = publisherAddress;
		this.publisherPhone = publisherPhone;
	}
	
	public String getPublisherName() {
		return publisherName;
	}
	
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	public String getPublisherAddress() {
		return publisherAddress;
	}
	
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	
	public int getPublisherId() {
		return publisherId;
	}
	
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
	public String getPublisherPhone() {
		return publisherPhone;
	}
	
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	
	@Override
	public String toString() {
		return "Publisher info: "+"ID ["+publisherId+"] "+"PUBLISHER NAME ["+publisherName+"]"+
	"PUBLISHER ADDRESS ["+publisherAddress+"]"+"PUBLISHER PHONE ["+publisherPhone+"]"+"\n";	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publisherAddress == null) ? 0 : publisherAddress.hashCode());
		result = prime * result + publisherId;
		result = prime * result + ((publisherName == null) ? 0 : publisherName.hashCode());
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
		Publisher other = (Publisher) obj;
		if (publisherAddress == null) {
			if (other.publisherAddress != null)
				return false;
		} else if (!publisherAddress.equals(other.publisherAddress))
			return false;
		if (publisherId != other.publisherId)
			return false;
		if (publisherName == null) {
			if (other.publisherName != null)
				return false;
		} else if (!publisherName.equals(other.publisherName))
			return false;
		return true;
	}	
}
