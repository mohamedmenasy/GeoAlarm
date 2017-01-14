package mohamedmenasy.com.geoalaram.ui.add;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.usecases.AlarmUseCase;

/**
 * Created by MAX on 1/13/2017.
 */

public class AddPresenter extends MvpBasePresenter<AddView> {
    private AlarmUseCase mAlarmUseCase;

    public AddPresenter(Context context) {
        mAlarmUseCase = new AlarmUseCase(context);
    }

    public void addDateButtonClicked() {
        getView().showDateTimeDialog();
    }

    public void addLocationButtonClicked() {
        getView().showMap();

    }

    public void saveDate(final GeoAlarm geoAlarm) {
        if (getView().validate()) {
            mAlarmUseCase.addAlarm(geoAlarm, new AlarmUseCase.Callback<GeoAlarm>() {
                @Override
                public void onSuccess(GeoAlarm geoAlarms) {
                    if (geoAlarm != null)
                        if (getView() != null)
                            getView().close();
                }

                @Override
                public void onFail() {
                    getView().showError("An error has been occurred");

                }
            });
        } else {
            getView().showError("Please set alarm title, and it's date ot location");
        }
    }


    public void unregisterReceiver() {
        mAlarmUseCase.unregisterReceivers();
    }

    void detach() {
        mAlarmUseCase.unSubscribe();
    }

}
