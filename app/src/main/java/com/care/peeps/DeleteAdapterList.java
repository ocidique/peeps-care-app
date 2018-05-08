package com.care.peeps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by HP on 5/8/2018.
 */

public class DeleteAdapterList extends RecyclerView.Adapter<DeleteAdapterList.RoomViewHolder> {
    public List<Room_model> roomlist;
    private Context context;
    RequestOptions option;
    


    public DeleteAdapterList(Context context) {
        this.context = context;
    }


    public DeleteAdapterList(List<Room_model> roomlist){

        this.roomlist = roomlist;
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }




    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_room_single,parent,false);
        return new DeleteAdapterList.RoomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RoomViewHolder holder, int position) {

        final int pos = position;

        holder.roomname.setText(roomlist.get(position).getname());
        holder.chkSelected.setTag(roomlist.get(position));
        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Room_model model = (Room_model) cb.getTag();
                model.setSelected(cb.isChecked());
                roomlist.get(pos).setSelected(cb.isChecked());

            }
        });

        holder.status.setText(roomlist.get(position).getstatus());

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                if (holder.chkSelected.isChecked()){
                    deleteItem(v, pos);}

                else{
                    builder.setMessage("Please check the item to be deleted ?")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                        }
                                    })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {



                                }
                            });

                    builder.show();

                }


            }





        });

    }

    @Override
    public int getItemCount() {
        return roomlist.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public TextView roomname;
        public CheckBox chkSelected;
        public ImageView image;
        public TextView status;
        public ImageButton btn_delete;

        public RoomViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            roomname = (TextView) mView.findViewById(R.id.room_name);
            chkSelected = (CheckBox) mView.findViewById(R.id.chk_selected);
            //image = (ImageView) mView.findViewById(R.id.imageView);
            btn_delete = (ImageButton) mView.findViewById(R.id.btn_delete);
            status = (TextView) mView.findViewById(R.id.room_status);

        }
    }


    // confirmation dialog box to delete an unit
    private void deleteItem(View v, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        //builder.setTitle("Dlete ");
        builder.setMessage("Delete Item ?")
                .setCancelable(false)
                .setPositiveButton("CONFIRM",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {




                                notifyDataSetChanged();


                            }
                        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                    }
                });

        builder.show();
        v.getContext().startActivity(new Intent(v.getContext(),RoomSelectActivity.class));



    }

}
