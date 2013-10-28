package itest.no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;

import java.math.BigInteger;

import no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering.GeosynkroniseringFactory;
import no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering.GeosynkroniseringInterface;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * Test the LibContentInterface
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/geosyncWsApplicationContext.xml"})
public class GeosynkroniseringTest {
	
	GeosynkroniseringInterface geosynkroniseringImpl;
	
	/**
	 * Set up default value for constructors
	 */
	public GeosynkroniseringTest() {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		
	}

	/**
	 * Test that lib content interface is not null
	 * @throws Exception
	 */
	@Test
	public void testLibContent() throws Exception {
		Assert.assertNotNull(geosynkroniseringImpl);
	}
	

	
	
	/**
	 * Test getLastIndex
	 * 
	 * @throws Exception
	 */
	@Test
	public void testgetLastIndex() throws Exception {
		BigInteger getLastIndex = geosynkroniseringImpl.GetLastIndex("3");
		Assert.assertNotNull("No data from getLastIndex", getLastIndex);
		

		Assert.assertTrue("No value found in getLastIndex ", getLastIndex.intValue() > 0);
		
		
	}
	
}

