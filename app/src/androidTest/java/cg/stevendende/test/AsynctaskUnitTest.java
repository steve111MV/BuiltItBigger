package cg.stevendende.test;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cg.ndendelab.javajoke.JokeTeller;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by stevendende on 09/01/2018.
 */

@RunWith(AndroidJUnit4.class)
public class AsynctaskUnitTest implements EndpointsAsyncTask.CallbackInterface {


    final CountDownLatch mSignal = new CountDownLatch(1);

    @Override
    public void onJokeLoaded(String joke) {

        //Check that the Endpoint loads the joke
        assertTrue( joke != null );
        mSignal.countDown();
    }

    @Test
    public void testJokeLoader() {
        new EndpointsAsyncTask(this).execute();
        try {
            boolean testSucceded = mSignal.await(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            fail("test failed ");
        }
    }
}
