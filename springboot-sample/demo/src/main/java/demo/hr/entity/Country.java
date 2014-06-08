package demo.hr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the COUNTRIES database table.
 * 
 */
@Entity
@Table(name = "COUNTRIES")
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "countryId")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	private String countryId;
	private String countryName;
	private Region region;

	public Country() {
	}

	@Id
	@Column(name = "COUNTRY_ID", unique = true, nullable = false, length = 2)
	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	@Column(name = "COUNTRY_NAME", length = 40)
	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	// uni-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name = "REGION_ID")
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}