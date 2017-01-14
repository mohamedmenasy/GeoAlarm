package mohamedmenasy.com.geoalaram.ui.add;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by MAX on 1/13/2017.
 */

public interface AddView extends MvpView {

    void showDateTimeDialog();

    void showMap();

    boolean validate();

    void showError(String errMsg);

    void close();
}
