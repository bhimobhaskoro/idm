/**
 * 
 */
package org.openforis.idm.metamodel.impl;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openforis.idm.metamodel.LanguageSpecificText;
import org.openforis.idm.metamodel.Unit;

/**
 * @author M. Togna
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "dimension", "conversionFactor", "labels", "abbreviations" })
public class UnitImpl implements Unit {

	@XmlElement(name = "label", type = LanguageSpecificTextImpl.class)
	private List<LanguageSpecificText> labels;

	@XmlElement(name = "abbreviation", type = LanguageSpecificTextImpl.class)
	private List<LanguageSpecificText> abbreviations;

	@XmlAttribute(name = "name")
	private String name;

	@XmlAttribute(name = "dimension")
	private String dimension;

	@XmlAttribute(name = "conversionFactor")
	private Float conversionFactor;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDimension() {
		return this.dimension;
	}

	@Override
	public Number getConversionFactor() {
		return this.conversionFactor;
	}

	@Override
	public List<LanguageSpecificText> getLabels() {
		return this.labels;
	}

	@Override
	public List<LanguageSpecificText> getAbbreviations() {
		return this.abbreviations;
	}
}
