/**
 * 
 */
package org.openforis.idm.metamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openforis.idm.model.Node;
import org.openforis.idm.model.Time;
import org.openforis.idm.model.TimeAttribute;

/**
 * @author G. Miceli
 * @author M. Togna
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {"name", "relevantExpression","required", "requiredExpression", "multiple", "minCount", "maxCount", "sinceVersionName", "deprecatedVersionName", 
	"labels", "prompts", "descriptions", "attributeDefaults", "checks"})
public class TimeAttributeDefinition extends AttributeDefinition {

	private static final long serialVersionUID = 1L;

	@Override
	public Node<?> createNode() {
		return new TimeAttribute(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Time createValue(String string) {
		return Time.parseTime(string);
	}
	
}
