package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;

import java.math.BigInteger;
import java.rmi.RemoteException;

import org.apache.xmlbeans.ServiceExceptionReportMessage;

public interface GeosynkroniseringInterface {
	
	public BigInteger GetLastIndex(String datasetId) throws RemoteException, ServiceExceptionReportMessage;

}
