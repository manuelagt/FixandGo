package com.example.manue.fixandgo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private List<Request> mRequests;
    final private RequestClickListener mRequestClickListener;
    private Context mContext;

    public interface RequestClickListener{
        void reminderOnClick(int i);
    }

    public RequestAdapter(List<Request> mRequests, RequestClickListener mRequestClickListener) {
        this.mRequests = mRequests;
        this.mRequestClickListener = mRequestClickListener;
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.request_post, parent, false);

        // Return a new holder instance
        RequestAdapter.ViewHolder viewHolder = new RequestAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ViewHolder holder, int position) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy / MM / dd ");
        String currentDate = format.format(calendar.getTime());
        Request request = mRequests.get(position);
        holder.mRequestTitle.setText(request.getRequestTitle());
        holder.mRequestDescription.setText(request.getRequestDescription());
        holder.mRequestName.setText(request.getRequestName());
        holder.mRequestEmail.setText(request.getRequestEmail());
        holder.mDate.setText(currentDate);
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mRequestTitle;
        public TextView mRequestDescription;
        public TextView mRequestName;
        public TextView mRequestEmail;
        public TextView mDate;

        public ViewHolder(View itemView) {

            super(itemView);
            mRequestTitle= itemView.findViewById(R.id.request_title);
            mRequestDescription = itemView.findViewById(R.id.request_description);
            mRequestName = itemView.findViewById(R.id.request_name);
            mRequestEmail = itemView.findViewById(R.id.request_email);
            mDate = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mRequestClickListener.reminderOnClick(clickedPosition);
        }

    }

    public void swapList (List<Request> newList) {


        mRequests = newList;

        if (newList != null) {

            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();

        }

    }


}

