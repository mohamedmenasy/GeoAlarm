package mohamedmenasy.com.geoalaram;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import io.realm.RealmResults;
import mohamedmenasy.com.geoalaram.data.local.LocalRepository;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import rx.Observable;
import rx.functions.Action1;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by MAX on 1/14/2017.
 */

public class LocalRepostoryTest {
    @Rule
    LocalRepository localRepository = new LocalRepository();

    @Test
    public void add_alarm() throws Exception {

        GeoAlarm alarm = new GeoAlarm();
        alarm.setTitle("test");
        alarm.setLongitude(30.0543331);
        alarm.setLongitude(31.2335026);
        alarm.setCreationData(new Date());
        Observable observable = localRepository.saveAlarm(alarm);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                assertNotNull(o);
            }
        });

    }

    @Test
    public void get_all_alarms() throws Exception {
        Observable<RealmResults<GeoAlarm>> observable = localRepository.getAlarms();
        observable.subscribe(new Action1<RealmResults<GeoAlarm>>() {
            @Override
            public void call(RealmResults<GeoAlarm> geoAlarms) {
                assertNotNull(geoAlarms);
                assertTrue(geoAlarms.size() > 0);

            }
        });
    }

    @Test
    public void get_all_completed_alarms() throws Exception {
        Observable<RealmResults<GeoAlarm>> observable = localRepository.getAllCompletedAlarms();
        observable.subscribe(new Action1<RealmResults<GeoAlarm>>() {
            @Override
            public void call(RealmResults<GeoAlarm> geoAlarms) {
                assertNotNull(geoAlarms);
                assertTrue(geoAlarms.size() > 0);

            }
        });
    }
}
