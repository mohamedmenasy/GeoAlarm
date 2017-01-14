package mohamedmenasy.com.geoalaram.data;

import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import rx.Observable;

/**
 * Created by MAX on 1/13/2017.
 */

public interface Repository {
    Observable getAlarms();

    Observable getAllCompletedAlarms();

    Observable saveAlarm(GeoAlarm geoAlarm);
}

