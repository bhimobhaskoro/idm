/**
 * 
 */
package org.openforis.idm.metamodel.validation;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openforis.idm.metamodel.LanguageSpecificText;
import org.openforis.idm.metamodel.xml.internal.CheckFlagAdapter;
import org.openforis.idm.model.Attribute;
import org.openforis.idm.util.CollectionUtil;

/**
 * @author G. Miceli
 * @author M. Togna
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public abstract class Check<T extends Attribute<?, ?>> implements Serializable, ValidationRule<T> {

	private static final long serialVersionUID = 1L;

	public enum Flag {
		ERROR, WARN
	}

	@XmlAttribute(name = "flag")
	@XmlJavaTypeAdapter(CheckFlagAdapter.class)
	private Flag flag;

	@XmlAttribute(name = "if")
	private String condition;

	@XmlElement(name = "message", type = LanguageSpecificText.class)
	private List<LanguageSpecificText> messages;

	public Flag getFlag() {
		return flag == null ? Flag.ERROR : flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}
	
	public String getCondition() {
		return this.condition;
	}

	public List<LanguageSpecificText> getMessages() {
		return CollectionUtil.unmodifiableList(this.messages);
	}
}
