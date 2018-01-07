package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cg.ndendelab.androidjokedisplay.JokeDisplayActivity;
import cg.ndendelab.javajoke.JokeTeller;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.CallbackInterface {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        View v = root.findViewById(R.id.tellJokeButton);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make((View) v.getParent(), "loading joke ...", Snackbar.LENGTH_SHORT).show();

                Toast.makeText(getActivity(), JokeTeller.getRandomJoke(),Toast.LENGTH_LONG).show();

                tellJoke();
            }
        });
        return root;
    }


    public void tellJoke() {
        new EndpointsAsyncTask(MainActivityFragment.this){}.execute();
    }

    @Override
    public void onJokeLoaded(String joke) {
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.EXTRA_JOKE, joke);
        startActivity(intent);
    }
}
