package mohamedmenasy.com.geoalaram.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import mohamedmenasy.com.geoalaram.R;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.ui.common.GeoAlarmsAdapter;
import mohamedmenasy.com.geoalaram.ui.add.AddAlarmActivity;
import mohamedmenasy.com.geoalaram.ui.completed.CompletedActivity;

public class HomeActivity extends MvpActivity<HomeView, HomePresenter> implements HomeView {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<GeoAlarm> dataGeoAlarms;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, AddAlarmActivity.class));
            }
        });
        dataGeoAlarms = new ArrayList<>();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        emptyView = (TextView) findViewById(R.id.empty_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.getAlarms();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAlarms();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        if (presenter == null) {
            presenter = new HomePresenter(this);
        }
        return presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_colmpleted_alarms) {
            startActivity(new Intent(this, CompletedActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkEmptyRecyclerView() {

        if (dataGeoAlarms.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayAlarms(List<GeoAlarm> geoAlarms) {
        dataGeoAlarms.clear();
        for (GeoAlarm geoAlarm : geoAlarms) {
            dataGeoAlarms.add(geoAlarm);
        }

        checkEmptyRecyclerView();
        mAdapter = new GeoAlarmsAdapter(dataGeoAlarms);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }
}
