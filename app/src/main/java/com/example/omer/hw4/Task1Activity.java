package com.example.omer.hw4;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class Task1Activity extends AppCompatActivity implements RecyclerViewFragment.OnFragmentInteractionListener{

    Fragment mContent;
    MovieData mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        }
        else {
            mData = new MovieData();
            mContent = RecyclerViewFragment.newInstance(-1);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.recycleContainer, mContent).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(int num) {
        mContent = RecyclerViewFragment.newInstance((num));
        getSupportFragmentManager().beginTransaction().replace(R.id.recycleContainer, mContent).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mContent.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "mContent", mContent);
            //outState.putInt("currentFrag", );
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        mContent = getSupportFragmentManager().getFragment(outState, "mContent");
        //outState.getInt()
    }
}
