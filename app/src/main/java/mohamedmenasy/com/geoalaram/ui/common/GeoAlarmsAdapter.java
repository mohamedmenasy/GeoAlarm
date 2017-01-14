package mohamedmenasy.com.geoalaram.ui.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mohamedmenasy.com.geoalaram.R;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;

/**
 * Created by MAX on 1/12/2017.
 */

public class GeoAlarmsAdapter extends RecyclerView.Adapter<GeoAlarmsAdapter.ViewHolder> {
    private List<GeoAlarm> mAlaramsList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    @Override
    public GeoAlarmsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_text_item, parent, false);

        ViewHolder vh = new ViewHolder((TextView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GeoAlarmsAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(mAlaramsList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mAlaramsList.size();
    }

    public GeoAlarmsAdapter(List<GeoAlarm> mAlaramsList) {
        this.mAlaramsList = mAlaramsList;
    }
}
