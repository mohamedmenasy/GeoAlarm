package mohamedmenasy.com.geoalaram.data.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by MAX on 1/12/2017.
 */

public class GeoAlarm extends RealmObject implements Parcelable {
    private Date creationData;
    private Date completeDate;
    private String title;
    private double latitude;
    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getCreationData() {
        return creationData;
    }

    public void setCreationData(Date creationData) {
        this.creationData = creationData;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.creationData != null ? this.creationData.getTime() : -1);
        dest.writeLong(this.completeDate != null ? this.completeDate.getTime() : -1);
        dest.writeString(this.title);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    public GeoAlarm() {
    }

    protected GeoAlarm(Parcel in) {
        long tmpCreationData = in.readLong();
        this.creationData = tmpCreationData == -1 ? null : new Date(tmpCreationData);
        long tmpCompleteDate = in.readLong();
        this.completeDate = tmpCompleteDate == -1 ? null : new Date(tmpCompleteDate);
        this.title = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<GeoAlarm> CREATOR = new Parcelable.Creator<GeoAlarm>() {
        @Override
        public GeoAlarm createFromParcel(Parcel source) {
            return new GeoAlarm(source);
        }

        @Override
        public GeoAlarm[] newArray(int size) {
            return new GeoAlarm[size];
        }
    };
}
