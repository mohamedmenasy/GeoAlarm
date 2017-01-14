package mohamedmenasy.com.geoalaram;

import org.junit.Test;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by MAX on 1/14/2017.
 */

public class RealmUnitTest {
    @Test
    public void add_alarm() throws Exception {
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm realm = Realm.getInstance(config);
        realm.beginTransaction();

        GeoAlarm alarm = realm.createObject(GeoAlarm.class);

        alarm.setTitle("test");
        alarm.setLongitude(30.0543331);
        alarm.setLongitude(31.2335026);
        alarm.setCreationData(new Date());

        realm.commitTransaction();
        alarm.asObservable().subscribe(new Action1<RealmObject>() {
            @Override
            public void call(RealmObject realmObject) {
                assertNotNull(realmObject);
            }
        });

    }

    @Test
    public void get_all_alarms() throws Exception {
        Realm realm = Realm.getDefaultInstance();
        Observable<RealmResults<GeoAlarm>> observable = realm.where(GeoAlarm.class).findAllSorted("creationData", Sort.ASCENDING).asObservable().unsubscribeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Action1<RealmResults<GeoAlarm>>() {
            @Override
            public void call(RealmResults<GeoAlarm> geoAlarms) {
                assertTrue(geoAlarms.size() > 0);
            }
        });
    }

    @Test
    public void get_all_completed_alarms() throws Exception {
        Realm realm = Realm.getDefaultInstance();
        Observable<RealmResults<GeoAlarm>> observable = realm.where(GeoAlarm.class).lessThan("creationData", new Date()).findAllSorted("creationData", Sort.ASCENDING).asObservable().unsubscribeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Action1<RealmResults<GeoAlarm>>() {
            @Override
            public void call(RealmResults<GeoAlarm> geoAlarms) {
                assertTrue(geoAlarms.size() > 0);
            }
        });

    }
}
