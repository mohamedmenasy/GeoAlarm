package mohamedmenasy.com.geoalaram.usecases.utils;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import mohamedmenasy.com.geoalaram.R;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.recivers.AlarmReceiver;

/**
 * Created by MAX on 1/13/2017.
 */

public class RegisterAlarm implements AlarmSetter {

    Context context;

    public RegisterAlarm(Context context) {
        this.context = context;
          /* Retrieve a PendingIntent that will perform a broadcast */


    }


    @Override
    public void setAlarm(GeoAlarm alarm, CallBack callback) {
//        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        int interval = 8000;
//        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarm.getCreationData().getTime(), interval, pendingIntent);
//
        scheduleNotification(getNotification(alarm.getTitle()), 30000, alarm.getCreationData().getTime());
        Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
        callback.onSuccess();
    }

    private void scheduleNotification(Notification notification, int delay, long notificationTime) {

        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);



        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, delay,notificationTime, pendingIntent);
    }

    private Notification getNotification(String content) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmSound == null) {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if (alarmSound == null) {
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }

        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setSound(alarmSound);
        builder.setAutoCancel(false);

        Intent cancelIntent = new Intent(context, AlarmReceiver.class);
        cancelIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
        cancelIntent.putExtra(AlarmReceiver.CANCEL, true);
        PendingIntent contentIntent = PendingIntent.getBroadcast(context, 89, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.mipmap.dismiss, "DISMISS", contentIntent);
        return builder.build();
    }

    public static void stopAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
