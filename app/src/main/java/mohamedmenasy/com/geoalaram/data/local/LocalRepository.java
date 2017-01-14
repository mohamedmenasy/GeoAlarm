package mohamedmenasy.com.geoalaram.data.local;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import mohamedmenasy.com.geoalaram.data.Repository;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by MAX on 1/13/2017.
 */
public class LocalRepository implements Repository {

    @Override
    public Observable getAlarms() {
        Realm realm = Realm.getDefaultInstance();
        Observable<RealmResults<GeoAlarm>> observable = realm.where(GeoAlarm.class).findAllSorted("creationData", Sort.ASCENDING).asObservable().unsubscribeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public Observable getAllCompletedAlarms() {
        Realm realm = Realm.getDefaultInstance();
        Observable<RealmResults<GeoAlarm>> observable = realm.where(GeoAlarm.class).lessThan("creationData", new Date()).findAllSorted("creationData", Sort.ASCENDING).asObservable().unsubscribeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public Observable saveAlarm(final GeoAlarm geoAlarm) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        GeoAlarm alarm = realm.createObject(GeoAlarm.class);
        alarm.setTitle(geoAlarm.getTitle());
        alarm.setLatitude(geoAlarm.getLatitude());
        alarm.setLongitude(geoAlarm.getLongitude());
        alarm.setCreationData(geoAlarm.getCreationData());
        alarm.setCompleteDate(geoAlarm.getCompleteDate());
        realm.commitTransaction();
        return alarm.asObservable().unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
