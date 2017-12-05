package com.sample.meetings.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by himanshu on 5/12/17.
 */

public class MeetingDetailsResponse {

    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String EndTime;
    @SerializedName("description")
    private String description;
    @SerializedName("participants")
    private ArrayList<String> participants = new ArrayList<>();

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }
}