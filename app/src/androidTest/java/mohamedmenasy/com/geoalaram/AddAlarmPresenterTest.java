package mohamedmenasy.com.geoalaram;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mohamedmenasy.com.geoalaram.ui.add.AddAlarmActivity;
import mohamedmenasy.com.geoalaram.ui.add.AddPresenter;

@RunWith(AndroidJUnit4.class)
public class AddAlarmPresenterTest {

    @Rule
    public ActivityTestRule<AddAlarmActivity> rule =
            new ActivityTestRule<>(AddAlarmActivity.class);
    private static AddPresenter presenter;

    @Test
    public void testSetup() throws Exception {

        AddAlarmActivity addAlarmActivity = rule.getActivity();

        presenter = addAlarmActivity.getPresenter();

        Assert.assertNotNull(presenter);
        Assert.assertTrue(presenter.getView() == addAlarmActivity);

    }


}
