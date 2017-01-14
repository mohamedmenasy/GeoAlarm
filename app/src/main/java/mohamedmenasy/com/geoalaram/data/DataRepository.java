package mohamedmenasy.com.geoalaram.data;

import io.realm.RealmResults;
import mohamedmenasy.com.geoalaram.data.local.LocalRepository;
import mohamedmenasy.com.geoalaram.data.pojo.GeoAlarm;
import mohamedmenasy.com.geoalaram.data.remote.ApiRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by MAX on 1/13/2017.
 */

public class DataRepository {
    public static final int ONLINE_REPOSITORY = 1;
    public static final int OFFLINE_REPOSITORY = 2;

    private ApiRepository apiRepository;
    private LocalRepository localRepository;

    public DataRepository() {
        this.apiRepository = new ApiRepository();
        this.localRepository = new LocalRepository();
    }

    public Repository getRepository(int repositoryType) {
        switch (repositoryType) {
            case ONLINE_REPOSITORY:
                return apiRepository;
            case OFFLINE_REPOSITORY:
                return localRepository;
            default:
                return null;
        }
    }

    public Observable<RealmResults<GeoAlarm>> getAlarms(int repositoryType) {
        return getRepository(repositoryType).getAlarms().subscribeOn(AndroidSchedulers.mainThread());
    }

    public Observable<RealmResults<GeoAlarm>> getComplecatedAlarms(int repositoryType) {
        return getRepository(repositoryType).getAllCompletedAlarms().subscribeOn(AndroidSchedulers.mainThread());
    }

    public Observable saveAlarm(GeoAlarm geoAlarm, int repositoryType) {
        return getRepository(repositoryType).saveAlarm(geoAlarm).subscribeOn(AndroidSchedulers.mainThread());
    }

}
