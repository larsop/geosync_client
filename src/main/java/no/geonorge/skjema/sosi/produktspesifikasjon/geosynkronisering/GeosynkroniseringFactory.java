package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
*
* This is a wrapper class used wrap all settings inn on single file. This means that caller don't need any info this.
* 
* Here we can call Spring enabled classes with the correct application context.
* 
* @author Lars Opsahl
*
*/

public class GeosynkroniseringFactory {

	/**
	 * 
	 */
	private static BeanFactory beanFactory = null;

	
	public static GeosynkroniseringInterface getGeosynkronisering() {
		return (GeosynkroniseringInterface) getBeanEstilConnector();
	}

	
	/**
	 * Get a bean with given config file
	 * @return
	 */
	private static Object getBeanEstilConnector() {
		String beanName = "geosynkroniseringImpl";
		if (beanFactory == null) {
			
			BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance("geosyncWsBeanRefContext.xml");
			final BeanFactoryReference ref = locator.useBeanFactory("no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering");
			beanFactory = ref.getFactory();
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					ref.release();
				}
			});
		}
		return beanFactory.getBean(beanName);
	}


}
