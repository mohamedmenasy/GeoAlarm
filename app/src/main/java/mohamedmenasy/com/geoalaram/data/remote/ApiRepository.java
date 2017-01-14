package mohamedmenasy.com.geoalaram.data.remote;

import mohamedmenasy.com.geoalaram.data.Repository;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import rx.Observable;

/**
 * Created by MAX on 1/13/2017.
 */
public class ApiRepository implements Repository {
    @Override
    public Observable getAlarms() {
        return null;
    }

    @Override
    public Observable getAllCompletedAlarms() {
        return null;
    }

    @Override
    public Observable saveAlarm(GeoAlarm geoAlarm) {
        return null;
    }
}
