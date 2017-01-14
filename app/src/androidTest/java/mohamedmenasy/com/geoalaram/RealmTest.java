package mohamedmenasy.com.geoalaram;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RealmTest {

    @Test
    public void add_alarm() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm realm = Realm.getInstance(config);
        realm.beginTransaction();

        GeoAlarm alarm = realm.createObject(GeoAlarm.class);

        alarm.setTitle("test");
        alarm.setLongitude(30.0543331);
        alarm.setLongitude(31.2335026);
        alarm.setCreationData(new Date());

        realm.commitTransaction();

        Assert.assertNotNull(alarm);

    }

    @Test
    public void get_all_alarms() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm realm = Realm.getInstance(config);

        RealmResults<GeoAlarm> realmResults = realm.where(GeoAlarm.class).findAllSorted("creationData", Sort.ASCENDING);
        assertTrue(realmResults.size() > 0);
    }

    @Test
    public void get_all_completed_alarms() throws Exception {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<GeoAlarm> realmResults = realm.where(GeoAlarm.class).lessThan("creationData", new Date()).findAllSorted("creationData", Sort.ASCENDING);
        assertTrue(realmResults.size() > 0);

    }

}
