package mohamedmenasy.com.geoalaram.usecases.utils;

import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;

/**
 * Created by MAX on 1/13/2017.
 */

public interface AlarmSetter {
    void setAlarm(GeoAlarm alarm,final AlarmSetter.CallBack callback);

    interface CallBack {
        void onSuccess();

        void onFail();
    }
}
