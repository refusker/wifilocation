package hk.org.ha.geek.wifilocation;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class WifiLocationController {	
    
    @RequestMapping(value="/api/v1/poilist",method = RequestMethod.GET, headers="Accept=application/json")
    public String getFullList(@RequestParam(value="isSameDayEvent") String isSameDayEvent) {        
    String json = "";
    	    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {    
    		
    		List eventList = new ArrayList();
    		HashMap poiMap = new HashMap();

    		StringBuilder contentBuilder = new StringBuilder();
    		try {

    		    BufferedReader in = new BufferedReader(new FileReader("/home/pbrcadm/projects/poi_list.csv"));
    		    BufferedReader in2 = new BufferedReader(new FileReader("/home/pbrcadm/projects/event_list.csv"));

                POIBean bean = new POIBean();

    		    String str;
    		    while ((str = in.readLine()) != null) {
    		        String[] poi = str.split(",");
    		        poiMap.put(poi[0], poi);
    		    }
    		    
    		    while ((str = in2.readLine()) != null) {
    		        String[] event = str.split(",");
                    String[] poi = (String[])poiMap.get(event[0]);
                    bean.setRegionId(event[0]);
                    bean.setPoiName(poi[1]);
                    bean.setActions(event[1],event[2],event[3]);
                    if(isSameDayEvent!=null&&isSameDayEvent.equals("T")) {
                        if(isSameDayEvent.equals(event[3])) {
                            eventList.add(bean);
                        }
                    }
                    else {
                        eventList.add(bean);
                    }
    		    }
    		    
    		    in.close();
    		} catch (IOException e) {
    		}
    		
    		json = mapper.writeValueAsString(eventList);
    		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return json;
    
    }
    
    @RequestMapping(value="/api/v1/poi",method = RequestMethod.GET, headers="Accept=application/json")
    public String getPoi(@RequestParam(value="id") String id) {        
        
    	String json = "";
    	    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {    
    		
    		/*
    		POIBean bean = new POIBean();
    		bean.setRegionId(id);    		
    		if(id.equals("HAB-0001")) {
    			bean.setPoiName("Leature Theatre");
    			bean.setActions("MSG","Auto Check-in completed.");
    		}
    		else if(id.equals("HAB-0002")) {     		
    			bean.setPoiName("Main Lobby");
    			bean.setActions("URL","http://www.ha.org.hk/vistor/ha_index.asp");
    		}
    		else if(id.equals("HAB-0003")) {
    			bean.setPoiName("M/F Room 1");
    			bean.setActions("URL","http://www.ha.org.hk/vistor/ha_index/asp");
    			bean.setActions("EVENT", "Blood Donation Campaign||2018-02-24 13:00||2018-02-24 17:30");
    		}
    		*/
    		HashMap poiMap = new HashMap();
    		HashMap eventMap = new HashMap();

    		StringBuilder contentBuilder = new StringBuilder();
    		try {

    		    BufferedReader in = new BufferedReader(new FileReader("/home/pbrcadm/projects/poi_list.csv"));
    		    BufferedReader in2 = new BufferedReader(new FileReader("/home/pbrcadm/projects/event_list.csv"));

    		    String str;
    		    while ((str = in.readLine()) != null) {
    		        String[] poi = str.split(",");
    		        System.out.println(poi[0]+"; "+poi[1]+"; "+poi[2]);
    		        poiMap.put(poi[0], poi);
    		    }
    		    
    		    while ((str = in2.readLine()) != null) {
    		        String[] event = str.split(",");
    		        eventMap.put(event[0], event);
    		    }
    		    
    		    in.close();
    		} catch (IOException e) {
    		}
    		
            
            String[] poi = (String[])poiMap.get(id);
            String[] event = (String[])eventMap.get(id);
            
    		POIBean bean = new POIBean();
            bean.setRegionId(id);
            bean.setPoiName(poi[1]);
            bean.setActions(event[1],event[2],event[3]);
            
    		json = mapper.writeValueAsString(bean);
    		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return json;
    }
    
    
}
