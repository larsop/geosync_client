package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;


import java.math.BigInteger;
import java.rmi.RemoteException;

import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.CancelChangelogDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.CancelChangelogResponseDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.CancelChangelogResponseDocument.CancelChangelogResponse;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogIdentificationType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogOrderType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogStatusType.Enum;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogResponseDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogResponseDocument.GetChangelogResponse;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogStatusDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogStatusDocument.GetChangelogStatus;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogStatusResponseDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogStatusResponseDocument.GetChangelogStatusResponse;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexDocument.GetLastIndex;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexResponseDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetLastIndexResponseDocument.GetLastIndexResponse;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ListStoredChangelogsDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ListStoredChangelogsResponseDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ListStoredChangelogsResponseDocument.ListStoredChangelogsResponse;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.OrderChangelogDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.OrderChangelogResponseDocument;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.OrderChangelogResponseDocument.OrderChangelogResponse;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.StoredChangelogListe;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.CancelChangelogDocument.CancelChangelog;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.GetChangelogDocument.GetChangelog;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ListStoredChangelogsDocument.ListStoredChangelogs;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.OrderChangelogDocument.OrderChangelog;

import org.apache.axis2.AxisFault;
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
		
		System.out.println("getLastIndex " + return1 );

		return return1;

	}

	public Enum GetChangelogStatus(ChangelogIdentificationType changelogId) throws RemoteException, ServiceExceptionReportMessage{
		
		GetChangelogStatusDocument getChangelogStatus2 = GetChangelogStatusDocument.Factory.newInstance();

		GetChangelogStatus addNewGetChangelogStatus = getChangelogStatus2.addNewGetChangelogStatus();
		addNewGetChangelogStatus.setChangelogid(changelogId);
		

		GetChangelogStatusResponseDocument status = stub.getChangelogStatus(getChangelogStatus2);

		GetChangelogStatusResponse 	getChangelogStatusResponse = status.getGetChangelogStatusResponse();

		Enum returnStatus = getChangelogStatusResponse.getReturn();
		
		System.out.println("getChangelogStatus " + returnStatus );

		return returnStatus;
	}

	public void CancelChangelog(ChangelogIdentificationType changelogId) throws RemoteException, ServiceExceptionReportMessage {
		
		CancelChangelogDocument cancelChangelog = CancelChangelogDocument.Factory.newInstance();
		
		CancelChangelog addNewCancelChangelog = cancelChangelog.addNewCancelChangelog();
		addNewCancelChangelog.setChangelogid(changelogId);
		
		CancelChangelogResponseDocument cancel = stub.cancelChangelog(cancelChangelog);	
		
	}

	public ChangelogType GetChangelog(ChangelogIdentificationType changelogId) throws RemoteException, ServiceExceptionReportMessage {
		
		GetChangelogDocument getChangelog2 = GetChangelogDocument.Factory.newInstance();

		GetChangelog addNewGetChangelog = getChangelog2.addNewGetChangelog();
		addNewGetChangelog.setChangelogid(changelogId);
		

		GetChangelogResponseDocument changelog = stub.getChangelog(getChangelog2);

		GetChangelogResponse 	getChangelogResponse = changelog.getGetChangelogResponse();

		ChangelogType returnChangelog = getChangelogResponse.getReturn();
		
		System.out.println("getChangelog downloadURI " + returnChangelog.getDownloadUri() );

		return returnChangelog;
	}

	public StoredChangelogListe ListStoredChangelogs(String datasetId) throws RemoteException, ServiceExceptionReportMessage {
		
		ListStoredChangelogsDocument listStoredChangelog2 = ListStoredChangelogsDocument.Factory.newInstance();

		ListStoredChangelogs addNewListStoredChangelog = listStoredChangelog2.addNewListStoredChangelogs();
		addNewListStoredChangelog.setDatasetId(datasetId);
		

		ListStoredChangelogsResponseDocument listStoredChangelog = stub.listStoredChangelogs(listStoredChangelog2);

		ListStoredChangelogsResponse listStoredChangelogResponse = listStoredChangelog.getListStoredChangelogsResponse();

		StoredChangelogListe returnListStoredChangelog = listStoredChangelogResponse.getReturn();
		
		System.out.println("listStoredChangelog size " + returnListStoredChangelog.getStoredchangelogArray().length);

		return returnListStoredChangelog;
	}

	public ChangelogIdentificationType OrderChangelog(ChangelogOrderType order)	throws RemoteException, ServiceExceptionReportMessage {
		
		OrderChangelogDocument orderChangelog2 = OrderChangelogDocument.Factory.newInstance();

		OrderChangelog addNewOrderChangelog = orderChangelog2.addNewOrderChangelog();
		addNewOrderChangelog.setOrder(order);
		

		OrderChangelogResponseDocument orderChangelog = stub.orderChangelog(orderChangelog2);

		OrderChangelogResponse orderChangelogResponse = orderChangelog.getOrderChangelogResponse();

		ChangelogIdentificationType returnOrderChangelog = orderChangelogResponse.getReturn();
		
		System.out.println("orderChangelog changelogId " + returnOrderChangelog.getChangelogId() );

		return returnOrderChangelog;
		
	}
}
