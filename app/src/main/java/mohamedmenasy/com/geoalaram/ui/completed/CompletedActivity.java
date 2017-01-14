package mohamedmenasy.com.geoalaram.ui.completed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import mohamedmenasy.com.geoalaram.R;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.ui.common.GeoAlarmsAdapter;
import mohamedmenasy.com.geoalaram.usecases.AlarmUseCase;

import static mohamedmenasy.com.geoalaram.R.id.recyclerView;

public class CompletedActivity extends MvpActivity<CompletedView, CompletedPresenter> implements CompletedView {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AlarmUseCase alarmUseCase;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        alarmUseCase = new AlarmUseCase(this);

        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getAlarms();
    }

    @NonNull
    @Override
    public CompletedPresenter createPresenter() {
        if (presenter == null) {
            presenter = new CompletedPresenter(this);
        }
        return presenter;
    }

    private void initView() {
        setTitle("Completed Alarms");
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        emptyView = (TextView) findViewById(R.id.empty_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detach();
    }

    @Override
    public void displayAlarms(List<GeoAlarm> geoAlarms) {
        mAdapter = new GeoAlarmsAdapter(geoAlarms);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        if (geoAlarms.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}
