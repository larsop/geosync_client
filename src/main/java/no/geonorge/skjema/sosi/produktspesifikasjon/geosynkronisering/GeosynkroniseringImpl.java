package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;


import java.math.BigInteger;
import java.rmi.RemoteException;


import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexDocument.GetLastIndex;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexResponseDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexResponseDocument.GetLastIndexResponse;

import org.apache.axis2.AxisFault;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.ServiceExceptionReportMessage;
import org.apache.xmlbeans.WebFeatureServiceReplicationStub;

public class GeosynkroniseringImpl implements GeosynkroniseringInterface {

	WebFeatureServiceReplicationStub stub;
	private String targetEndpoint;

	public GeosynkroniseringImpl(String targetEndpoint) throws AxisFault {
		this.targetEndpoint = targetEndpoint;
		stub = new WebFeatureServiceReplicationStub(targetEndpoint);
	}

	/**
	 * Return 
	 * @param dataset
	 * @return
	 * @throws RemoteException
	 * @throws ServiceExceptionReportMessage 
	 */
	public BigInteger GetLastIndex(String datasetId) throws RemoteException, ServiceExceptionReportMessage {

		GetLastIndexDocument getLastIndex2 = GetLastIndexDocument.Factory.newInstance();

		GetLastIndex addNewGetLastIndex = getLastIndex2.addNewGetLastIndex();
		addNewGetLastIndex.setDatasetId(datasetId);
		

		GetLastIndexResponseDocument lastIndex = stub.getLastIndex(getLastIndex2);

		GetLastIndexResponse getLastIndexResponse = lastIndex.getGetLastIndexResponse();

		BigInteger return1 = getLastIndexResponse.getReturn();
		
		System.out.println(return1 );System.out.println(return1 );System.out.println(return1 );System.out.println(return1 );

		return return1;

	}


}
