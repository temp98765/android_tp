package com.example.amiotj.tp0;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> data;

    public MessageAdapter(List<Message> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_messages, parent, false);
        return new ViewHolder(view);

    }

    public void setData(List<Message> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView userText;
        private final TextView contentText;

        ViewHolder(View itemView) {
            super(itemView);
            userText = itemView.findViewById(R.id.userText);
            contentText = itemView.findViewById(R.id.contentText);
        }

        void setData(Message message) {
            userText.setText(message.userName + ": ");
            contentText.setText(message.content);
        }
    }
}