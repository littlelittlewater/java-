package chapter4;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liyuan on 17-10-23.
 */
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String,Ponit> locations;
    private final Map<String,Ponit> unModifiableMap;

    public DelegatingVehicleTracker(Map<String, Ponit> unModifiableMap) {
        locations = new ConcurrentHashMap<>(unModifiableMap);
        this.unModifiableMap = Collections.unmodifiableMap(unModifiableMap);
    }

    public Map<String, Ponit> getlocations() {
        return unModifiableMap;
    }

    public Ponit getLocation(String id){
        return locations.get(id);
    }

    public void setLocations(String id , int x, int y){
        if(locations.replace(id,new Ponit(x, y) )== null){
            throw new IllegalArgumentException("no shth id");
        }
    }
}
