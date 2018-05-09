package com.care.peeps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RoomSelectActivity extends AppCompatActivity {
  // private DocumentReference documentreference = FirebaseFirestore.getInstance().collection("rooms").document("");
   TextView rooms;


    private Toolbar toolbar;
    private RecyclerView mroomlist;
    private FirebaseFirestore mFirestore;
    private List<Room_model> room_modelList;
    private RoomAdapterList roomAdapterList;
    private DatabaseReference mroomdb;
    //private RelativeLayout relativeLayout1;
    //private RelativeLayout relativeLayout2;
    //private RelativeLayout relativeLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);

        String url = "https://firebasestorage.googleapis.com/v0/b/peeps-care-app.appspot.com/o/room.png?alt=media&token=516d1785-507f-4e7f-8217-1b83d54b7b42";
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.app_name);
        //setSupportActionBar(toolbar);
//        getActionBar().setTitle("ALL Rooms");
  //      getActionBar().setDisplayHomeAsUpEnabled(true);

        //mroomdb = FirebaseDatabase.getInstance().getReference().child("Room");
        room_modelList = new ArrayList<>();
        roomAdapterList = new RoomAdapterList(room_modelList);

        mroomlist = (RecyclerView) findViewById(R.id.recyclerView);
        mroomlist.setHasFixedSize(true);
        mroomlist.setLayoutManager(new LinearLayoutManager(this));
        mroomlist.setAdapter(roomAdapterList);


        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("rooms").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                   if (doc.getType() == DocumentChange.Type.ADDED){


                       Log.d("roomid", doc.getDocument().getId());

                       Room_model rooms = doc.getDocument().toObject(Room_model.class);
                       rooms.setRoomid(doc.getDocument().getId());
                       Log.d("room", rooms.getRoomid());
                       //Log.d("room", rooms.getImage());
                       room_modelList.add(rooms);
                       roomAdapterList.notifyDataSetChanged();

                   }
                }
            }
        });


        /*relativeLayout1 = (RelativeLayout) findViewById(R.id.Layout1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.Layout2);
        relativeLayout3 = (RelativeLayout) findViewById(R.id.Layout3);

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RoomSelectActivity.this, ChatApp.class));

            }
        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomSelectActivity.this, ChatApp.class));
            }
        });

        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RoomSelectActivity.this, ChatApp.class));
            }
        });*/


    }

    /*@Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Room_model,RoomViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Room_model, RoomViewHolder>() {

            @Override
            protected void onBindViewHolder(@NonNull RoomViewHolder holder, int position, @NonNull Room_model model) {


            }

            @Override
            public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

        };
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public RoomViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.room_menu, menu);
        return true;
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.Add_Chat_Rooms:
                startActivity(new Intent(this, Add_Chatroom.class));
                return true;
            case R.id.Delete_ChatRooms:
                startActivity(new Intent(this, DeleteChatRoom.class));

                return true;
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}
