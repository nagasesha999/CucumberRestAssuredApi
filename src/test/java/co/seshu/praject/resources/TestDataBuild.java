package co.seshu.praject.resources;



import co.seshu.praject.pojo.Location;
import co.seshu.praject.pojo.MapPayload;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public MapPayload addPlacePayLoad(){
        MapPayload m = new MapPayload();
        Location pojoPayLoad = new Location();
        List<String> type = new ArrayList<String>();
        m.setAccuracy(50);
        m.setAddress("29, side layout, cohen 09");
        m.setName("Frontline house");
        m.setPhone_number("(+91) 983 893 3937");
        m.setWebsite("http://google.com");
        m.setLanguage("French-IN");
        pojoPayLoad.setLat("-38.383494");
        pojoPayLoad.setLng("33.427362");
        m.setLocation(pojoPayLoad);
        type.add("shoe park");
        type.add("shop");
        m.setTypes(type);
        return m;
    }

    public MapPayload addPlacePayLoad(String name, String language, String address){
        MapPayload m = new MapPayload();
        Location pojoPayLoad = new Location();
        List<String> type = new ArrayList<String>();
        m.setAccuracy(50);
        m.setAddress(address);
        m.setName(name);
        m.setPhone_number("(+91) 983 893 3937");
        m.setWebsite("http://google.com");
        m.setLanguage(language);
        pojoPayLoad.setLat("-38.383494");
        pojoPayLoad.setLng("33.427362");
        m.setLocation(pojoPayLoad);
        type.add("shoe park");
        type.add("shop");
        m.setTypes(type);
        return m;
    }

    public String delete_PayLoad(String place_id){
        return "{\n" +
                "    \"place_id\": \""+place_id+"\"\n" +
                "}";
    }
}
