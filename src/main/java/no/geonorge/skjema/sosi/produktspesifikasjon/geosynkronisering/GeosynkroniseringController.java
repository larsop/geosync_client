package no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering;


import java.math.BigInteger;
import java.rmi.RemoteException;

import org.apache.xmlbeans.ServiceExceptionReportMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import no.geonorge.skjema.sosi.produktspesifikasjon.geosynkronisering.GeosynkroniseringInterface;


@Controller
@RequestMapping("/")
public class GeosynkroniseringController {
	
	GeosynkroniseringInterface geosynkroniseringImpl;
	
	
	@RequestMapping(value="/start", method = RequestMethod.GET)
	public String startUttak(ModelMap model) {
		return "index";
 
	}
	
	@RequestMapping(value="/getLastIndex", method = RequestMethod.GET)
	public String getLastIndex(ModelMap model) throws RemoteException, ServiceExceptionReportMessage {
		geosynkroniseringImpl = GeosynkroniseringFactory.getGeosynkronisering();
		BigInteger getLastIndex = geosynkroniseringImpl.GetLastIndex("3");
		
		model.addAttribute("lastIndex", getLastIndex);
		return "index";
 
	}
	@RequestMapping(value="/ListStoredChangelogs", method = RequestMethod.GET)
	public String listStoredChangelogs(ModelMap model) {
		
		model.addAttribute("lastIndex", 2);
		return "index";
 
	}
}
