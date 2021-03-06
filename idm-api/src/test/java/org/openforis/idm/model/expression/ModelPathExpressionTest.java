/**
 * 
 */
package org.openforis.idm.model.expression;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.openforis.idm.AbstractTest;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.model.Code;
import org.openforis.idm.model.Entity;
import org.openforis.idm.model.Node;
import org.openforis.idm.model.RealAttribute;

/**
 * @author M. Togna
 * 
 */
public class ModelPathExpressionTest extends AbstractTest {

	@Test
	public void testIteratePath() throws InvalidExpressionException {
		String entityName = "plot";
		cluster.addEntity(entityName);
		cluster.addEntity(entityName);
		cluster.addEntity(entityName);
		List<Node<?>> list = iterateExpression(entityName, cluster);

		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testParent() throws InvalidExpressionException{
		Entity plot = cluster.addEntity("plot");
		List<Node<?>> plots = iterateExpression("parent()", plot);
		Assert.assertEquals(1, plots.size());
	}

	@Test
	public void testAttributeParent() throws InvalidExpressionException{
		Entity plot = cluster.addEntity("plot");
		RealAttribute canopyCover = plot.addValue("canopy_cover", 12.56);
		List<Node<?>> plots = iterateExpression("parent()", canopyCover);
		Assert.assertEquals(1, plots.size());
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testIterateInvalidPath() throws InvalidExpressionException {
		String entityName = "plot";
		cluster.addEntity(entityName);
		cluster.addEntity(entityName);
		cluster.addEntity(entityName);
		String expr = "plot^2";
		List<Node<?>> list = iterateExpression(expr, cluster);

		Assert.assertEquals(3, list.size());
	}

	@Test
	public void testIteratePath2() throws InvalidExpressionException {
		String entityName = "plot";
		Entity plot1 = cluster.addEntity(entityName);
		plot1.addValue("no", new Code("1"));
		Entity plot2 = cluster.addEntity(entityName);
		plot2.addValue("no", new Code("1"));
		Entity plot3 = cluster.addEntity(entityName);
		plot3.addValue("no", new Code("1"));

		String expr = "plot/no";
		List<Node<?>> list = iterateExpression(expr, cluster);

		Assert.assertEquals(3, list.size());
	}


	@Test
	public void testParentFunction() throws InvalidExpressionException {
		String entityName = "plot";
		Entity plot = cluster.addEntity(entityName);
		cluster.addValue("id", new Code("123_456"));

		String expr = "parent()/id";
		List<Node<?>> list = iterateExpression(expr, plot);

		Assert.assertEquals(1, list.size());
	}

	@Test
	public void testIteratePath3() throws InvalidExpressionException {
		String entityName = "time_study";
		cluster.addEntity(entityName);
		cluster.addEntity(entityName);
		List<Node<?>> list = iterateExpression(entityName, cluster);

		Assert.assertEquals(2, list.size());
	}

	private List<Node<?>> iterateExpression(String expr, Node<? extends NodeDefinition> context) throws InvalidExpressionException {
		ModelPathExpression expression = context.getRecord().getSurveyContext().getExpressionFactory().createModelPathExpression(expr);
		List<Node<?>> l = expression.iterate(context, null);
		return l;
	}
}
