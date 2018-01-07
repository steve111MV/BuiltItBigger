package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import cg.stevendende.backend.myApi.MyApi;
import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private CallbackInterface mCallback;

    /**
     * Interface definition for a callback to be invoked when reviews are loaded.
     */
    interface CallbackInterface {
        void onJokeLoaded(String joke);
    }

    public EndpointsAsyncTask(CallbackInterface mCallback){
        this.mCallback = mCallback;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();

            try {
                Log.e("doInBackground", String.valueOf(myApiService.tellJoke()));
                return myApiService.tellJoke().execute().getData();

            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {

            try {
                return myApiService.tellJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);

        mCallback.onJokeLoaded(joke);
    }
}