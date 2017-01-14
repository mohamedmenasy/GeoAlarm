package mohamedmenasy.com.geoalaram.ui.home;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.usecases.AlarmUseCase;

/**
 * Created by MAX on 1/14/2017.
 */

public class HomePresenter extends MvpBasePresenter<HomeView> {
    private AlarmUseCase alarmUseCase;

    public HomePresenter(Context context) {
        alarmUseCase = new AlarmUseCase(context);
    }

    public void getAlarms() {
        alarmUseCase.getAllAlarms(new AlarmUseCase.Callback<List<GeoAlarm>>() {
            @Override
            public void onSuccess(List<GeoAlarm> data) {
                getView().displayAlarms(data);
            }

            @Override
            public void onFail() {

            }
        });
    }

    void detach() {
        alarmUseCase.unSubscribe();
    }

}
