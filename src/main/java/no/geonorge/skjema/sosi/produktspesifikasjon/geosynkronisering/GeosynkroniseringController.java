package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;


import java.math.BigInteger;
import java.rmi.RemoteException;

import org.apache.xmlbeans.ServiceExceptionReportMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering.GeosynkroniseringInterface;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogIdentificationType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogOrderType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogStatusType.Enum;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.ChangelogType;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.StoredChangelogListe;
import no.geonorge.skjema.standard.geosynkronisering._1_0.produkt.StoredChangelogType;



@Controller
@RequestMapping("/")
public class GeosynkroniseringController {
	
	GeosynkroniseringInterface geosynkroniseringImpl;
	//default settings
	String datasetId = "AR5";
	String changelogId = "0";
	String startIndex = "0";
	String count = "1000";
	String ResultType = "results";
	String outputFormat = "application/gml+xml; version=3.2";
	
	
	@RequestMapping(value="/start", method = RequestMethod.GET)
	public String startUttak(ModelMap model) {
		model.addAttribute("datasetId", datasetId);
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		return "index";
 
	}
	
	@RequestMapping(value="/getLastIndex", method = RequestMethod.GET)
	public String getLastIndex(ModelMap model) throws RemoteException, ServiceExceptionReportMessage {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		BigInteger getLastIndex = geosynkroniseringImpl.GetLastIndex(datasetId);
		
		model.addAttribute("datasetId", datasetId);
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		model.addAttribute("lastIndex", "LastIndex "+ getLastIndex);
		return "index";
 
	}
	@RequestMapping(value="/ListStoredChangelogs", method = RequestMethod.GET)
	public String listStoredChangelogs(ModelMap model) throws RemoteException, ServiceExceptionReportMessage {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		StoredChangelogListe storedChangelogListe = geosynkroniseringImpl.ListStoredChangelogs(datasetId);
		
		String storedChangelogListText = "";
		for (StoredChangelogType type : storedChangelogListe.getStoredchangelogArray()){
			storedChangelogListText+= type.getDownloadUri() +" "+ type.getName() +" "+  type.getStartIndex() +" "+ type.getEndIndex() +" "
					+ type.getId().getChangelogId() ;//type.getOrder()
		}
		
		model.addAttribute("datasetId", datasetId);
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		model.addAttribute("lastIndex", "storedChangelogList element 0 with end index "+ storedChangelogListe.getStoredchangelogArray(0).getName());
		return "index";
 
	}
	@RequestMapping(value="/cancelChangelog", method = RequestMethod.GET)
	public String cancelChangelog(ModelMap model) throws RemoteException, ServiceExceptionReportMessage {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		ChangelogIdentificationType changelogId2 = ChangelogIdentificationType.Factory.newInstance();
		BigInteger changelogIdInt = new BigInteger(changelogId);
		changelogId2.setChangelogId(changelogIdInt);
		geosynkroniseringImpl.CancelChangelog(changelogId2);
		
		model.addAttribute("datasetId", datasetId);
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		model.addAttribute("lastIndex","Changelog " + changelogId2.getChangelogId() + " cancelled");
		return "index";
 
	}
	
	@RequestMapping(value="/getChangelogStatus", method = RequestMethod.GET)
	public String getChangelogStatus(ModelMap model) throws RemoteException, ServiceExceptionReportMessage {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		ChangelogIdentificationType changelogId2 = ChangelogIdentificationType.Factory.newInstance();
		BigInteger changelogIdInt = new BigInteger(changelogId);
		changelogId2.setChangelogId(changelogIdInt);
		Enum getChangelogStatus = geosynkroniseringImpl.GetChangelogStatus(changelogId2);
		
		model.addAttribute("datasetId", datasetId);
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		model.addAttribute("lastIndex","status " + getChangelogStatus);
		return "index";
 
	}
	
	@RequestMapping(value="/orderChangelog", method = RequestMethod.GET)
	public String orderChangelog(ModelMap model) throws RemoteException, ServiceExceptionReportMessage {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		ChangelogOrderType orderType = ChangelogOrderType.Factory.newInstance();
		BigInteger startIndexInt = new BigInteger(startIndex);
		BigInteger countInt = new BigInteger(count);
		net.opengis.www.wfs._2_0.ResultTypeType.Enum resultTypeEnum = net.opengis.www.wfs._2_0.ResultTypeType.Enum.forString(ResultType);
		orderType.setDatasetId(datasetId);
		orderType.setStartIndex(startIndexInt);
		orderType.setCount(countInt);
		orderType.setResultType(resultTypeEnum);
		orderType.setOutputFormat(outputFormat);
		
		ChangelogIdentificationType order = geosynkroniseringImpl.OrderChangelog(orderType);
		
		model.addAttribute("datasetId", datasetId);
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		model.addAttribute("lastIndex","order with changelogId " + order.getChangelogId());
		return "index";
 
	}
	
	@RequestMapping(value="/getChangelog", method = RequestMethod.GET)
	public String getChangelog(ModelMap model) throws RemoteException, ServiceExceptionReportMessage {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		ChangelogIdentificationType changelogId2 = ChangelogIdentificationType.Factory.newInstance();
		BigInteger changelogIdInt = new BigInteger(changelogId);
		changelogId2.setChangelogId(changelogIdInt);
		ChangelogType getChangelog = geosynkroniseringImpl.GetChangelog(changelogId2);
		
		model.addAttribute("datasetId", datasetId);
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		model.addAttribute("lastIndex","End index "+getChangelog.getEndIndex() + "<br> DownloadURI " + getChangelog.getDownloadUri() + "<br> changelogId " + changelogId);// + getChangelog.getId().getChangelogId());
		return "index";
 
	}
}
