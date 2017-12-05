package com.sample.meetings.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.meetings.Adapter.MeetingListAdapter;
import com.sample.meetings.Model.MeetingDetailsResponse;
import com.sample.meetings.R;
import com.sample.meetings.Webservices.ApiClient;
import com.sample.meetings.Webservices.ApiInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingListFragment extends Fragment {

    private TextView txtPrev, txtDate, txtNext;
    private ImageView imgPrev, imgNext;
    private RecyclerView meetingsRecyclerView;
    private Button btnScheduleMeeting;
    private TextView txtNoResult;

    private ArrayList<MeetingDetailsResponse> meetingResponseList = new ArrayList<>();
    private MeetingListAdapter adapter;

    private String startingDate = "7/8/2015";

    Calendar calendar = Calendar.getInstance();

    public MeetingListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        setRetainInstance(true);

        txtPrev = (TextView) rootView.findViewById(R.id.txtPrev);
        txtDate = (TextView) rootView.findViewById(R.id.txtDate);
        txtNext = (TextView) rootView.findViewById(R.id.txtNext);
        imgPrev = (ImageView) rootView.findViewById(R.id.imgPrev);
        imgNext = (ImageView) rootView.findViewById(R.id.imgNext);
        meetingsRecyclerView = (RecyclerView) rootView.findViewById(R.id.meetingsRecyclerView);
        btnScheduleMeeting = (Button) rootView.findViewById(R.id.btnScheduleMeeting);
        txtNoResult = (TextView) rootView.findViewById(R.id.txtNoResult);

        txtDate.setText(startingDate);
        calendar.clear();
        calendar.set(2015, 8, 7);

        meetingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MeetingListAdapter(getActivity(), meetingResponseList);
        meetingsRecyclerView.setAdapter(adapter);

        txtPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrevClick();
            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrevClick();
            }
        });

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });

        btnScheduleMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().add(R.id.fragment_container, new NewMeetingFragment())
                        .addToBackStack("NewMeetingFragment").commit();
            }
        });

        getMeetingList(startingDate);
        return rootView;
    }

    private void getMeetingList(String date) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<List<MeetingDetailsResponse>> call = apiService.getMeetingList(date);

        call.enqueue(new Callback<List<MeetingDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<MeetingDetailsResponse>> call, Response<List<MeetingDetailsResponse>> response) {
                List<MeetingDetailsResponse> meetingsResponses = response.body();

                meetingResponseList.clear();
                meetingResponseList.addAll(meetingsResponses);
                adapter.notifyDataSetChanged();

                if (meetingsResponses.isEmpty()) {
                    meetingsRecyclerView.setVisibility(View.GONE);
                    txtNoResult.setVisibility(View.VISIBLE);
                } else {
                    meetingsRecyclerView.setVisibility(View.VISIBLE);
                    txtNoResult.setVisibility(View.GONE);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<MeetingDetailsResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_LONG).show();
                Log.e("serviceError", t.toString());
            }
        });

    }

    private void onNextClick() {
        calendar.add(Calendar.DATE, 1);

        String date = String.valueOf(calendar.get(Calendar.DATE)) + "/" + String.valueOf(calendar.get(Calendar.MONTH)) + "/" +
                String.valueOf(calendar.get(Calendar.YEAR));

        txtDate.setText(date);
        getMeetingList(date);
    }

    private void onPrevClick() {
        calendar.add(Calendar.DATE, -1);

        String date = String.valueOf(calendar.get(Calendar.DATE)) + "/" + String.valueOf(calendar.get(Calendar.MONTH)) + "/" +
                String.valueOf(calendar.get(Calendar.YEAR));

        txtDate.setText(date);
        getMeetingList(date);
    }

}