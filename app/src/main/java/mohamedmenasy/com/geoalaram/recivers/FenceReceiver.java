package mohamedmenasy.com.geoalaram.recivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.NotificationCompat;

import java.util.ArrayList;

import mohamedmenasy.com.geoalaram.R;
import mohamedmenasy.com.geoalaram.ui.home.HomeActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by MAX on 1/14/2017.
 */

public class FenceReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 111;

    private void showPushNotification(Context context, String content) {
        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Geo Alarm")
                        .setContentText(content);
        Intent resultIntent = new Intent(context, HomeActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(NOTIFICATION_ID, mBuilder.build());

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ArrayList<String> geofences = intent.getStringArrayListExtra("geofences");
        Location location = intent.getParcelableExtra("location");

        showPushNotification(context, "Alarm (" + geofences.get(0) + ")(" + location.getLongitude() + ")(" + location.getLongitude() + ")");
    }
}
