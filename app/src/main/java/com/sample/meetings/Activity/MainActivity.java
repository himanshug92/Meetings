package com.sample.meetings.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sample.meetings.Fragment.MeetingListFragment;
import com.sample.meetings.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MeetingListFragment fragment = new MeetingListFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, "MeetingListFragment").commit();

        setContentView(R.layout.activity_main);
    }
}
