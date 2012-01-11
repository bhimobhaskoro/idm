package org.openforis.idm.metamodel.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.openforis.idm.metamodel.Check.Flag;
import org.openforis.idm.metamodel.xml.internal.ConfigurationXmlAdapter;
import org.openforis.idm.metamodel.xml.internal.DefaultConfigurationAdapter;
import org.openforis.idm.metamodel.xml.internal.EnumAdapter;
import org.openforis.idm.metamodel.CodeList;
import org.openforis.idm.metamodel.CodeListLabel;
import org.openforis.idm.metamodel.Configuration;
import org.openforis.idm.metamodel.NodeLabel;
import org.openforis.idm.metamodel.NumericAttributeDefinition;
import org.openforis.idm.metamodel.Prompt;
import org.openforis.idm.metamodel.Survey;
import org.openforis.idm.metamodel.TextAttributeDefinition;

/**
 * @author G. Miceli
 */
class BindingContext {
	private static final JAXBContext INSTANCE;
	
	private static final XmlAdapter<?,?>[] ADAPTERS = {
		new EnumAdapter<Flag>(Flag.class),
		new EnumAdapter<CodeListLabel.Type>(CodeListLabel.Type.class),
		new EnumAdapter<NumericAttributeDefinition.Type>(NumericAttributeDefinition.Type.class),
		new EnumAdapter<NodeLabel.Type>(NodeLabel.Type.class),
		new EnumAdapter<CodeList.CodeScope>(CodeList.CodeScope.class),
		new EnumAdapter<CodeList.CodeType>(CodeList.CodeType.class),
		new EnumAdapter<Prompt.Type>(Prompt.Type.class),
		new EnumAdapter<TextAttributeDefinition.Type>(TextAttributeDefinition.Type.class)
	};
	
	static {
		try {
			INSTANCE = JAXBContext.newInstance(Survey.class);
		} catch (JAXBException e) {
			throw new RuntimeException("Failed to initialize JAXBContext", e);
		}
	}
	
	static JAXBContext getInstance() {
		return INSTANCE;
	}
	
	public static void setAdapters(Marshaller marshaller, ConfigurationAdapter<? extends Configuration> configurationAdapter) {
		for (XmlAdapter<?,?> adapter : ADAPTERS) {
			marshaller.setAdapter(adapter);
		}
		if ( configurationAdapter == null ) {
			marshaller.setAdapter(new ConfigurationXmlAdapter(DefaultConfigurationAdapter.getInstance()));
		} else {
			marshaller.setAdapter(new ConfigurationXmlAdapter(configurationAdapter));
		}
	}
	
	public static void setAdapters(Unmarshaller unmarshaller, ConfigurationAdapter<? extends Configuration> configurationAdapter) {
		for (XmlAdapter<?,?> adapter : ADAPTERS) {
			unmarshaller.setAdapter(adapter);
		}
		if ( configurationAdapter == null ) {
			unmarshaller.setAdapter(new ConfigurationXmlAdapter(DefaultConfigurationAdapter.getInstance()));
		} else {
			unmarshaller.setAdapter(new ConfigurationXmlAdapter(configurationAdapter));
		}
	}
}
