package com.care.peeps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.care.peeps.R.layout.singlemessagelayout;

/**
 * Created by HP on 4/27/2018.
 */

public class MessageAdapterList extends RecyclerView.Adapter<MessageAdapterList.MessageViewHolder> {
    public List<Message_model> messageslist;
    private Context context;


    public MessageAdapterList(Context context) {
        this.context = context;
    }


    public MessageAdapterList(List<Message_model> messageslist){

        this.messageslist = messageslist;

    }



    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(singlemessagelayout,parent,false);
        return new MessageAdapterList.MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        //Log.d("check","i am in");
        holder.username.setText(messageslist.get(position).getfrom());
        holder.content.setText(messageslist.get(position).getmsg());
        holder.timestamp.setText(messageslist.get(position).getcreatedAt());
    }

    @Override
    public int getItemCount() {
        return messageslist.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public TextView username;
        public TextView content;
        public TextView timestamp;


        public MessageViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            username = (TextView) mView.findViewById(R.id.username);
            content = (TextView) mView.findViewById(R.id.actualmessage);
            timestamp = (TextView) mView.findViewById(R.id.timpstamp);



        }
    }
}
