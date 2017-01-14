package mohamedmenasy.com.geoalaram.recivers;

/**
 * Created by MAX on 1/13/2017.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import mohamedmenasy.com.geoalaram.usecases.utils.RegisterAlarm;

public class AlarmReceiver extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";
    public static String CANCEL = "CANCEL";
    public static int NOTIFICATION_ID_DI;
    Notification notification;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (intent.getParcelableExtra(NOTIFICATION) != null)
            notification = intent.getParcelableExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        NOTIFICATION_ID_DI = id;

        if (intent.getBooleanExtra(CANCEL, false)) {
            RegisterAlarm.stopAlarm(context);
            notificationManager.cancel(id);
            Toast.makeText(context, "dismissed", Toast.LENGTH_SHORT).show();

        } else {
            // For our recurring task, we'll just display a message
            notificationManager.notify(id, notification);
            Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        }


    }
}
