package chapter4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyuan on 17-10-23.
 */
public class MonitorVehicleTracker {
    private final Map<String,MutablePoint> locations ;

    public MonitorVehicleTracker(Map<String,MutablePoint> locations){
        this.locations = deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id){
        return locations.get(id);
    }

    public synchronized Map<String,MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized void setLocations(String id,int x ,int y){
        MutablePoint loc = locations.get(id);
        if(loc == null){
            throw  new IllegalArgumentException("NO such id");
        }
        loc.x = x ;
        loc.y = y ;
    }
    //拷贝map的内容
    private Map<String,MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        Map<String,MutablePoint> result = new HashMap<>();
        for(String id : locations.keySet()){
            result.put(id,new MutablePoint(locations.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
