package com.sample.meetings.Webservices;

import com.sample.meetings.Model.MeetingDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by himanshu on 5/12/17.
 */

public interface ApiInterface {

    @GET("schedule")
    Call<List<MeetingDetailsResponse>> getMeetingList(@Query("date") String date);

}