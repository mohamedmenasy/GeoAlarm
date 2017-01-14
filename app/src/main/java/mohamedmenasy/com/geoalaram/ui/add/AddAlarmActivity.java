package mohamedmenasy.com.geoalaram.ui.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mohamedmenasy.com.geoalaram.R;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.ui.map.MapActivity;

public class AddAlarmActivity extends MvpActivity<AddView, AddPresenter> implements AddView, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    @BindView(R.id.alarmTitleET)
    BootstrapEditText alarmTitleText;

    @BindView(R.id.addDateBTN)
    BootstrapButton addDateButton;

    @BindView(R.id.addLocationBTN)
    BootstrapButton addLocationButton;

    @BindView(R.id.saveBTN)
    BootstrapButton saveButton;

    private Calendar alarmCalendar;
    private static final int MAP_ACTIVITY_RESULT = 100;
    private GeoAlarm geoAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);
        alarmCalendar = Calendar.getInstance();
        geoAlarm = new GeoAlarm();
        initView();
    }

    private void initView() {
        addDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addDateButtonClicked();
            }
        });

        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addLocationButtonClicked();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.saveDate(geoAlarm);
            }
        });
    }

    @NonNull
    @Override
    public AddPresenter createPresenter() {
        if (presenter == null)
            presenter = new AddPresenter(this);
        return presenter;
    }


    @Override
    public void showDateTimeDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                AddAlarmActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void showMap() {
        startActivityForResult(new Intent(this, MapActivity.class), MAP_ACTIVITY_RESULT);
    }

    @Override
    public boolean validate() {
        geoAlarm.setTitle(alarmTitleText.getText().toString().trim());
        if (!alarmTitleText.getText().toString().isEmpty()) {
            if (geoAlarm != null) {
                if (geoAlarm.getCreationData() != null || geoAlarm.getLatitude() != 0.0 || geoAlarm.getLongitude() != 0.0) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public void showError(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        alarmCalendar.set(year, monthOfYear, dayOfMonth);
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                AddAlarmActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), false
        );
        tpd.show(getFragmentManager(), "Select Date and Time");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        alarmCalendar.set(alarmCalendar.get(Calendar.YEAR), alarmCalendar.get(Calendar.MONTH), alarmCalendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute, second);
        geoAlarm.setCreationData(alarmCalendar.getTime());
        String dateFormatted = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(alarmCalendar.getTime());
        addDateButton.setText(dateFormatted);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MAP_ACTIVITY_RESULT) {
            if (resultCode == RESULT_OK) {
                LatLng location = data.getExtras().getParcelable("location");
                NumberFormat formatter = new DecimalFormat("#0.00000");
                geoAlarm.setLatitude(location.latitude);
                geoAlarm.setLongitude(location.longitude);

                addLocationButton.setText("Location : " + formatter.format(location.latitude) + "," + formatter.format(location.longitude));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        presenter.unregisterReceiver();
        super.onStop();
    }

    @Override
    protected void onPause() {
        //presenter.detach();
        super.onPause();
    }
}
