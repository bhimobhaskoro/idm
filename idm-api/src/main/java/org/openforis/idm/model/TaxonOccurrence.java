package org.openforis.idm.model;

/**
 * @author G. Miceli
 * @author M. Togna
 */
public final class TaxonOccurrence {

	private String code;
	private String scientificName;
	private String vernacularName;
	private String languageCode;
	private String languageVariety;

	public TaxonOccurrence() {
		super();
	}

	public TaxonOccurrence(String code, String scientificName, String vernacularName, String languageCode, String languageVariety) {
		this.code = code;
		this.scientificName = scientificName;
		this.vernacularName = vernacularName;
		this.languageCode = languageCode;
		this.languageVariety = languageVariety;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getVernacularName() {
		return vernacularName;
	}

	public void setVernacularName(String vernacularName) {
		this.vernacularName = vernacularName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageVariety() {
		return languageVariety;
	}

	public void setLanguageVariety(String languageVariety) {
		this.languageVariety = languageVariety;
	}

}