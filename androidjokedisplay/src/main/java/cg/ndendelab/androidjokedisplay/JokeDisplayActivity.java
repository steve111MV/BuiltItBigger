package cg.ndendelab.androidjokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "cg.stevendende.joke";

    TextView mJokeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);


        mJokeTv = findViewById(R.id.jokeTv);

        if(getIntent() != null && getIntent().getStringExtra(EXTRA_JOKE)!=null){
            mJokeTv.setText(getIntent().getStringExtra(EXTRA_JOKE));
        }
    }
}
