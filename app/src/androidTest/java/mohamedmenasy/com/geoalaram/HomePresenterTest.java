package mohamedmenasy.com.geoalaram;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mohamedmenasy.com.geoalaram.ui.home.HomeActivity;
import mohamedmenasy.com.geoalaram.ui.home.HomePresenter;

@RunWith(AndroidJUnit4.class)
public class HomePresenterTest {
    @Rule
    public ActivityTestRule<HomeActivity> rule =
            new ActivityTestRule<>(HomeActivity.class);
    private static HomePresenter presenter;

    @Test
    public void testSetup() throws Exception {

        HomeActivity homeActivity = rule.getActivity();

        presenter = homeActivity.getPresenter();

        Assert.assertNotNull(presenter);
        Assert.assertTrue(presenter.getView() == homeActivity);

    }


}
