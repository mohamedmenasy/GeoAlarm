package mohamedmenasy.com.geoalaram;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mohamedmenasy.com.geoalaram.ui.completed.CompletedActivity;
import mohamedmenasy.com.geoalaram.ui.completed.CompletedPresenter;

@RunWith(AndroidJUnit4.class)
public class CompletedPresenterTest {
    @Rule
    public ActivityTestRule<CompletedActivity> rule =
            new ActivityTestRule<>(CompletedActivity.class);
    private static CompletedPresenter presenter;

    @Test
    public void testSetup() throws Exception {

        CompletedActivity completedActivity = rule.getActivity();

        presenter = completedActivity.getPresenter();

        Assert.assertNotNull(presenter);
        Assert.assertTrue(presenter.getView() == completedActivity);

    }


}
