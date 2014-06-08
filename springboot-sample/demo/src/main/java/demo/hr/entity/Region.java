package demo.hr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the REGIONS database table.
 * 
 */
@Entity
@Table(name = "REGIONS")
@NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "regionId")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	private long regionId;
	private String regionName;

	public Region() {
	}

	@Id
	@Column(name = "REGION_ID", unique = true, nullable = false)
	public long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	@Column(name = "REGION_NAME", length = 25)
	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}