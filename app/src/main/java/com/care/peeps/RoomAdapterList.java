package com.care.peeps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by HP on 4/19/2018.
 */

public class RoomAdapterList extends RecyclerView.Adapter<RoomAdapterList.RoomViewHolder> {
    public List<Room_model> roomlist;
    private Context context;
    RequestOptions option;

    public RoomAdapterList(Context context) {
        this.context = context;
    }


    public RoomAdapterList(List<Room_model> roomlist){

        this.roomlist = roomlist;
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }


    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_single_layout,parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RoomViewHolder holder, final int position) {
       holder.roomname.setText(roomlist.get(position).getname());
       holder.status.setText(roomlist.get(position).getstatus());
        //Glide.with(context).load(roomlist.get(position).getImage()).apply(option).into(holder.image);

        Log.d("Aroom", roomlist.get(position).getRoomid());
        holder.mView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               view.getContext().startActivity(new Intent(view.getContext(),ChatApp.class).putExtra("roomname",roomlist.get(position).getRoomid()));

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
        public ImageView image;
        public TextView status;


        public RoomViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            roomname = (TextView) mView.findViewById(R.id.room_name);
            //image = (ImageView) mView.findViewById(R.id.imageView);
            status = (TextView) mView.findViewById(R.id.room_status);

        }
    }
}
