package mohamedmenasy.com.geoalaram.ui.completed;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;

/**
 * Created by MAX on 1/14/2017.
 */

public interface CompletedView extends MvpView {
    void displayAlarms(List<GeoAlarm> geoAlarms);
}
