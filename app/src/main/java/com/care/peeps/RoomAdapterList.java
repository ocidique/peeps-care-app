package com.care.peeps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 4/19/2018.
 */

public class RoomAdapterList extends RecyclerView.Adapter<RoomAdapterList.RoomViewHolder> {
    public List<Room_model> roomlist;
    private Context context;

    public RoomAdapterList(Context context) {
        this.context = context;
    }


    public RoomAdapterList(List<Room_model> roomlist){

        this.roomlist = roomlist;

    }


    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_single_layout,parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
       holder.roomname.setText(roomlist.get(position).getname());
       holder.mView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               view.getContext().startActivity(new Intent(view.getContext(),ChatApp.class));
           }
       });

    }

    @Override
    public int getItemCount() {
        return roomlist.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView roomname;
        //public ImageView image;

        public RoomViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            roomname = (TextView) mView.findViewById(R.id.room_name);
            //image = (ImageView) mView.findViewById(R.id.imageView);

        }
    }
}
