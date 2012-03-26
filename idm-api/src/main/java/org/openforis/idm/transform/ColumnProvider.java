package org.openforis.idm.transform;

import java.util.List;

import org.openforis.idm.model.Node;

/**
 * @author G. Miceli
 */
public interface ColumnProvider {
	
	List<String> getColumnHeadings();
	
	List<String> extractValues(Node<?> n);
	
}
