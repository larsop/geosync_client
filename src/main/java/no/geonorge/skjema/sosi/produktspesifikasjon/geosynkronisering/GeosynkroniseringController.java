package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;


import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.ServiceExceptionReportMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	String changelogId = "0";
	String startIndex = "0";
	String count = "1000";
	String ResultType = "results";
	String outputFormat = "application/gml+xml; version=3.2";
	
	List<String> logList = Collections.synchronizedList(new ArrayList<String>());
	
	
	@RequestMapping(value="/start", method = RequestMethod.GET)
	public String startUttak(ModelMap model) {
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		return "index";
 
	}
	
	@RequestMapping(value="/getLastIndex", method = RequestMethod.GET)
	public String getLastIndex(ModelMap model, @RequestParam("datasetId") String datasetId) throws RemoteException, ServiceExceptionReportMessage {
		Date date = new Date();
		String logDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
		
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		BigInteger getLastIndex = geosynkroniseringImpl.GetLastIndex(datasetId);
		
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		logList.add(logDate + " getLastIndex, LastIndex "+ getLastIndex);
		model.addAttribute("lastIndex", logList);	
		return "index";
 
	}
	@RequestMapping(value="/ListStoredChangelogs", method = RequestMethod.GET)
	public String listStoredChangelogs(ModelMap model, @RequestParam("datasetId") String datasetId) throws RemoteException, ServiceExceptionReportMessage {
		Date date = new Date();
		String logDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
		
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		StoredChangelogListe storedChangelogListe = geosynkroniseringImpl.ListStoredChangelogs(datasetId);
		
		String storedChangelogListText = "";
		for (StoredChangelogType type : storedChangelogListe.getStoredchangelogArray()){
			storedChangelogListText+= type.getDownloadUri() +" "+ type.getName() +" "+  type.getStartIndex() +" "+ type.getEndIndex() +" "
					+ type.getId().getChangelogId() ;//type.getOrder()
		}
		
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		logList.add(logDate + " ListStoredChangelogs, " + storedChangelogListText);
		model.addAttribute("lastIndex", logList);
		return "index";
 
	}
	@RequestMapping(value="/cancelChangelog", method = RequestMethod.GET)
	public String cancelChangelog(ModelMap model, @RequestParam("changelogId") String changelogId) throws RemoteException, ServiceExceptionReportMessage {
		Date date = new Date();
		String logDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
		
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		ChangelogIdentificationType changelogId2 = ChangelogIdentificationType.Factory.newInstance();
		BigInteger changelogIdInt = new BigInteger(changelogId);
		changelogId2.setChangelogId(changelogIdInt);
		geosynkroniseringImpl.CancelChangelog(changelogId2);
		
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		logList.add(logDate + " cancelChangelog, Changelog " + changelogId2.getChangelogId() + " cancelled");
		model.addAttribute("lastIndex",logList);
		return "index";
 
	}
	
	@RequestMapping(value="/getChangelogStatus", method = RequestMethod.GET)
	public String getChangelogStatus(ModelMap model, @RequestParam("changelogId") String changelogId) throws RemoteException, ServiceExceptionReportMessage {
		Date date = new Date();
		String logDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
		
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		ChangelogIdentificationType changelogId2 = ChangelogIdentificationType.Factory.newInstance();
		BigInteger changelogIdInt = new BigInteger(changelogId);
		changelogId2.setChangelogId(changelogIdInt);
		Enum getChangelogStatus = geosynkroniseringImpl.GetChangelogStatus(changelogId2);
		
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		logList.add(logDate + " getChangelogStatus, status " + getChangelogStatus);
		model.addAttribute("lastIndex",logList);
		return "index";
 
	}
	
	@RequestMapping(value="/orderChangelog", method = RequestMethod.GET)
	public String orderChangelog(ModelMap model, @RequestParam("datasetId") String datasetId,
				@RequestParam("startIndex") String startIndex,
				@RequestParam("count") String count,
				@RequestParam("ResultType") String ResultType,
				@RequestParam("outputFormat") String outputFormat) throws RemoteException, ServiceExceptionReportMessage {
		Date date = new Date();
		String logDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
		
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
		
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		logList.add(logDate + " orderChangelog, order with changelogId " + order.getChangelogId());
		model.addAttribute("lastIndex",logList);
		return "index";
 
	}
	
	@RequestMapping(value="/getChangelog", method = RequestMethod.GET)
	public String getChangelog(ModelMap model, @RequestParam("changelogId") String changelogId) throws RemoteException, ServiceExceptionReportMessage {
		Date date = new Date();
		String logDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
		
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		ChangelogIdentificationType changelogId2 = ChangelogIdentificationType.Factory.newInstance();
		BigInteger changelogIdInt = new BigInteger(changelogId);
		changelogId2.setChangelogId(changelogIdInt);
		ChangelogType getChangelog = geosynkroniseringImpl.GetChangelog(changelogId2);
		
		model.addAttribute("changelogId", changelogId);
		model.addAttribute("startIndex", startIndex);
		model.addAttribute("count", count);
		model.addAttribute("ResultType", ResultType);
		model.addAttribute("outputFormat", outputFormat);
		logList.add(logDate + " getChangelog, End index "+getChangelog.getEndIndex() + "<br> DownloadURI " + getChangelog.getDownloadUri() + "<br> changelogId " + changelogId);
		model.addAttribute("lastIndex",logList);
		return "index";
 
	}
}
