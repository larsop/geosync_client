package itest.no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;

import java.math.BigInteger;
import java.rmi.RemoteException;

import no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering.GeosynkroniseringFactory;
import no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering.GeosynkroniseringInterface;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogIdentificationType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogOrderType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogStatusType.Enum;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.StoredChangelogListe;

import org.apache.xmlbeans.ServiceExceptionReportMessage;
import org.exolab.castor.util.ChangeLog2XML.Changelog;
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

	/**
	 * Test getChangelogStatus
	 * 
	 * @throws Exception
	 */
	@Test
	public void testgetChangelogStatus() throws Exception {
		ChangelogIdentificationType changelogId = null;
		Enum getChangelogStatus = geosynkroniseringImpl.GetChangelogStatus(changelogId);
		Assert.assertNotNull("No status from getChangelogStatus", getChangelogStatus);
		
	}
	
	/**
	 * Test getChangelog
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetChangelog() throws Exception {
		ChangelogIdentificationType changelogId = null;
		ChangelogType changelogType = geosynkroniseringImpl.GetChangelog(changelogId);
		
		Assert.assertNotNull("No uri from getChangelog", changelogType.getDownloadUri());
		
	}
	
	/**
	 * Test storedChangelogList
	 * 
	 * @throws Exception
	 */
	@Test 
	public void testStoredChangelogList() throws Exception {
		StoredChangelogListe storedChangelogListe = geosynkroniseringImpl.ListStoredChangelogs("3");
		
		Assert.assertNotNull("No list from listStoredChangelog", storedChangelogListe.getStoredchangelogArray());
	}
	
	/**
	 * Test orderChangelog
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOrderChangelog() throws Exception {
		ChangelogOrderType order = null;
		ChangelogIdentificationType changelogType = geosynkroniseringImpl.OrderChangelog(order);
		
		Assert.assertNotNull("No changelogId from orderChangelog", changelogType.getChangelogId());
	}
}

