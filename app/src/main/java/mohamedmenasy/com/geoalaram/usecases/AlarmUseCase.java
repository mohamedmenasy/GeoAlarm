package mohamedmenasy.com.geoalaram.usecases;

import android.content.Context;
import android.content.IntentFilter;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;

import java.util.List;

import io.nlopez.smartlocation.OnGeofencingTransitionListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.geofencing.model.GeofenceModel;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;
import io.nlopez.smartlocation.geofencing.utils.TransitionGeofence;
import io.realm.RealmResults;
import mohamedmenasy.com.geoalaram.App;
import mohamedmenasy.com.geoalaram.data.DataRepository;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.recivers.FenceReceiver;
import mohamedmenasy.com.geoalaram.usecases.utils.AlarmSetter;
import mohamedmenasy.com.geoalaram.usecases.utils.RegisterAlarm;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MAX on 1/13/2017.
 */

public class AlarmUseCase {
    private DataRepository dataRepository;
    private CompositeSubscription mSubscriptions;
    private Context context;
    private FenceReceiver mFenceReceiver;
    private static final String FENCE_RECEIVER_ACTION = GeofencingGooglePlayServicesProvider.BROADCAST_INTENT_ACTION;

    public AlarmUseCase(Context context) {
        this.dataRepository = new DataRepository();
        this.mSubscriptions = new CompositeSubscription();
        this.context = context;
    }

    public void addAlarm(final GeoAlarm geoAlarm, final Callback<GeoAlarm> callback) {
        Subscription subscription = dataRepository.saveAlarm(geoAlarm, DataRepository.OFFLINE_REPOSITORY).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onFail();

                    }

                    @Override
                    public void onNext(Object o) {


                        if (o != null) {
                            if (geoAlarm.getCreationData() != null) {
                                setAlarm(geoAlarm, new AlarmSetter.CallBack() {
                                    @Override
                                    public void onSuccess() {
                                        callback.onSuccess(null);
                                    }

                                    @Override
                                    public void onFail() {
                                        callback.onFail();

                                    }
                                });

                            } else if (geoAlarm.getLongitude() != 0 && geoAlarm.getLatitude() != 0) {

                                setGeoFence(geoAlarm);
                                callback.onSuccess(null);
                            } else {
                                callback.onSuccess(null);
                            }
                        } else
                            callback.onFail();
                    }
                });
        mSubscriptions.add(subscription);


    }

    public void getAllAlarms(final Callback<List<GeoAlarm>> callback) {
        Subscription subscription = dataRepository.getAlarms(DataRepository.OFFLINE_REPOSITORY).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RealmResults<GeoAlarm>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onFail();

                    }

                    @Override
                    public void onNext(RealmResults<GeoAlarm> geoAlarms) {
                        if (geoAlarms != null)
                            callback.onSuccess(geoAlarms);
                        else
                            callback.onFail();
                    }
                });
        mSubscriptions.add(subscription);
    }


    public void getAllCompletedAlarms(final Callback<List<GeoAlarm>> callback) {

        Subscription subscription = dataRepository.getComplecatedAlarms(DataRepository.OFFLINE_REPOSITORY).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RealmResults<GeoAlarm>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onFail();

                    }

                    @Override
                    public void onNext(RealmResults<GeoAlarm> geoAlarms) {
                        if (geoAlarms != null)
                            callback.onSuccess(geoAlarms);
                        else
                            callback.onFail();
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void unSubscribe() {
        mSubscriptions.unsubscribe();

    }

    public void unregisterReceivers() {
        if (mFenceReceiver != null)
            context.unregisterReceiver(mFenceReceiver);

    }

    private void setGeoFence(GeoAlarm geoAlarm) {
        GeofenceModel geofenceModel = new GeofenceModel.Builder(geoAlarm.getTitle())
                .setTransition(Geofence.GEOFENCE_TRANSITION_ENTER)
                .setLatitude(geoAlarm.getLatitude())
                .setLongitude(geoAlarm.getLongitude())
                .setRadius(100)
                .setExpiration(Geofence.NEVER_EXPIRE)
                .setLoiteringDelay(0)
                .build();


        SmartLocation.with(context).geofencing()
                .add(geofenceModel)
                .start(new OnGeofencingTransitionListener() {
                    @Override
                    public void onGeofenceTransition(TransitionGeofence transitionGeofence) {
                        String s = transitionGeofence.getGeofenceModel().getRequestId();
                        Toast.makeText(context, "Alarm Set", Toast.LENGTH_LONG).show();

                    }
                });

        mFenceReceiver = new FenceReceiver();
        context.registerReceiver(mFenceReceiver, new IntentFilter(FENCE_RECEIVER_ACTION));

    }

    private void setAlarm(GeoAlarm alarm, AlarmSetter.CallBack callBack) {
        RegisterAlarm registerdAlarm = new RegisterAlarm(App.getContext());
        registerdAlarm.setAlarm(alarm, callBack);
    }

    public interface Callback<T> {
        void onSuccess(T data);

        void onFail();
    }

}
