package com.example.omer.hw4;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FrontPageFragment.OnFragmentInteractionListener {
    Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, FrontPageFragment.newInstance(0)).commit();

        if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
            Log.d("SavedInstance", "is not null");
        }
        else {
            mContent = FrontPageFragment.newInstance(0);
            Log.d("SavedInstance", "is null");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mContent).commit();

    }

    @Override
    public void onFragmentInteraction(int num) {
        if (num == 2) {
            Intent intent = new Intent(this, Task1Activity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.fancy_animation, R.anim.card_flip_left_out);
        }
        else {
            mContent = FrontPageFragment.newInstance(num);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mContent).addToBackStack(null).commit();
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mContent.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "mContent", mContent);
        }
    }


}
