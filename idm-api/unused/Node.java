/**
 * 
 */
package org.openforis.idm.model;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.Schema;
import org.openforis.idm.metamodel.Survey;
import org.openforis.idm.model.expression.ExpressionFactory;


/**
 * @author G. Miceli
 * @author M. Togna
 */
public abstract class Node<D extends NodeDefinition> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	transient D definition;
	transient Record record;
	transient Integer id;
	transient Integer internalId;
	transient Entity parent;
	
	Integer definitionId;

	protected Node() {
	}
	
	public Node(D definition) {
		if ( definition == null ) {
			throw new NullPointerException("Definition required");
		}
		this.definition = definition;
		this.definitionId = definition.getId();
	}
	
	@SuppressWarnings("unchecked")
	protected void afterDeserialize() {
		Survey survey = record.getSurvey();
		Schema schema = survey.getSchema();
		if ( definitionId == null ) {
			throw new IllegalStateException("Definition id not set before saving; cannot deserialize");
		}
		NodeDefinition defn = schema.getById(definitionId);
		if ( defn == null ) {
			throw new IllegalStateException("Definition "+definitionId+" referenced by node "+id+" is not defined in this survey");
		}
		try {
			this.definition = (D) defn;
		} catch ( ClassCastException e ) {
			throw new IllegalStateException("Definition "+definitionId+" referenced by node "+id+" is of wrong type "+defn.getClass().getSimpleName());			
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInternalId() {
		return internalId;
	}
	
	public D getDefinition() {
		return this.definition;
	}

	public String getName() {
		return getDefinition().getName();
	}

	public Record getRecord() {
		return record;
	}
	
	protected void setRecord(Record record) {
		this.record = record;
		this.internalId = record.nextId();
		record.put(this);
	}
	
	public Entity getParent() {
		return this.parent;
	}

	protected void setParent(Entity parent) {
		this.parent = parent;
		if ( parent.getRecord() != null ) {
			setRecord(parent.getRecord());
		}
	}

	protected abstract void write(StringWriter sw, int indent);

	public abstract boolean isEmpty();

//	public abstract ValidationResults validate();

	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		write(sw,0);
		return sw.toString();
	}

	protected ExpressionFactory getExpressionFactory() {
		RecordContext context = getRecord().getContext();
		return context.getExpressionFactory();
	}

	public String getPath() {
		StringBuilder sb = new StringBuilder();
		getPath(sb);
		return sb.toString();
	}
	
	void getPath(StringBuilder sb) {
		if ( parent!=null ) {
			parent.getPath(sb);
		}
		String name = getName();
		int idx = getIndex();
		sb.append("/");
		sb.append(name);
		sb.append("[");
		sb.append(idx+1);
		sb.append("]");
	}

	public int getIndex() {
		if ( parent == null ) {
			return 0;
		} else {
			String name = getName();
			List<Node<?>> children = parent.getAll(name);
			for (int i=0; i<children.size(); i++) {
				if (children.get(i)==this) {
					return i;
				}
			}
			throw new IllegalStateException("Node parent relationship broken");
		}
	}
}
