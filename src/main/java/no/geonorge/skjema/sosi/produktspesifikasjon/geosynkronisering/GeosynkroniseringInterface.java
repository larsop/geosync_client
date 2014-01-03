package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;

import java.math.BigInteger;
import java.rmi.RemoteException;

import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogIdentificationType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogOrderType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogStatusType.Enum;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.StoredChangelogListe;

import org.apache.xmlbeans.ServiceExceptionReportMessage;

public interface GeosynkroniseringInterface {
	
	public BigInteger GetLastIndex(String datasetId) throws RemoteException, ServiceExceptionReportMessage;
	
	public Enum GetChangelogStatus(ChangelogIdentificationType changelogId) throws RemoteException, ServiceExceptionReportMessage;

	public void CancelChangelog(ChangelogIdentificationType changelogId) throws RemoteException, ServiceExceptionReportMessage;
	
	public ChangelogType GetChangelog(ChangelogIdentificationType changelogId) throws RemoteException, ServiceExceptionReportMessage;
	
	public StoredChangelogListe ListStoredChangelogs(String datasetId) throws RemoteException, ServiceExceptionReportMessage;
	
	public ChangelogIdentificationType OrderChangelog(ChangelogOrderType order) throws RemoteException, ServiceExceptionReportMessage;
	
}
