package mohamedmenasy.com.geoalaram.ui.completed;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.usecases.AlarmUseCase;

/**
 * Created by MAX on 1/14/2017.
 */

public class CompletedPresenter extends MvpBasePresenter<CompletedView> {
    private AlarmUseCase alarmUseCase;

    public CompletedPresenter(Context context) {
        alarmUseCase = new AlarmUseCase(context);
    }

    public void getAlarms() {
        alarmUseCase.getAllCompletedAlarms(new AlarmUseCase.Callback<List<GeoAlarm>>() {
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
