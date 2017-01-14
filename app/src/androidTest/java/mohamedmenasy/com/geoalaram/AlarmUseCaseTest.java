package mohamedmenasy.com.geoalaram;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.usecases.AlarmUseCase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AlarmUseCaseTest {
    AlarmUseCase alarmUseCase;

    @Test
    public void add_alarm() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        GeoAlarm geoAlarm = new GeoAlarm();
        geoAlarm.setTitle("test");
        geoAlarm.setLongitude(30.0543331);
        geoAlarm.setLongitude(31.2335026);
        geoAlarm.setCreationData(new Date());

        alarmUseCase = new AlarmUseCase(appContext);
        alarmUseCase.addAlarm(geoAlarm, new AlarmUseCase.Callback<GeoAlarm>() {
            @Override
            public void onSuccess(GeoAlarm data) {
                assertNotNull(data);
            }

            @Override
            public void onFail() {
                assertTrue(false);

            }
        });
    }

    @Test
    public void get_all_alarms() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();


        alarmUseCase = new AlarmUseCase(appContext);
        alarmUseCase.getAllAlarms(new AlarmUseCase.Callback<List<GeoAlarm>>() {
            @Override
            public void onSuccess(List<GeoAlarm> data) {
                assertTrue(data.size() > 0);
            }

            @Override
            public void onFail() {
                assertTrue(false);

            }
        });
    }

    @Test
    public void get_all_completed_alarms() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        alarmUseCase = new AlarmUseCase(appContext);
        alarmUseCase.getAllCompletedAlarms(new AlarmUseCase.Callback<List<GeoAlarm>>() {
            @Override
            public void onSuccess(List<GeoAlarm> data) {
                assertTrue(data.size() > 0);
            }

            @Override
            public void onFail() {
                assertTrue(false);
            }
        });
    }

}
