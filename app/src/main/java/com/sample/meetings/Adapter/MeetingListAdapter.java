package com.sample.meetings.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.meetings.Model.MeetingDetailsResponse;
import com.sample.meetings.R;

import java.util.ArrayList;

/**
 * Created by himanshu on 5/12/17.
 */

public class MeetingListAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<MeetingDetailsResponse> meetingList;

    public MeetingListAdapter(Context context, ArrayList<MeetingDetailsResponse> meetingList) {
        this.context = context;
        this.meetingList = meetingList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_list_row, parent, false);
        return new MeetingListAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MeetingDetailsResponse response = meetingList.get(position);

        ((viewHolder) holder).txtStartTime.setText(response.getStartTime());
        ((viewHolder) holder).txtEndTime.setText(response.getEndTime());
        ((viewHolder) holder).txtDescription.setText(response.getDescription());
    }

    @Override
    public int getItemCount() {
        return meetingList.size();
    }

    private class viewHolder extends RecyclerView.ViewHolder {

        TextView txtStartTime, txtEndTime, txtDescription;

        viewHolder(View itemView) {
            super(itemView);
            txtStartTime = (TextView) itemView.findViewById(R.id.txtStartTime);
            txtEndTime = (TextView) itemView.findViewById(R.id.txtEndTime);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
        }
    }

}
