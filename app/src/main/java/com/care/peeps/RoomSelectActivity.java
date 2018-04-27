package com.care.peeps;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RoomSelectActivity extends AppCompatActivity {
   // private DocumentReference documentreference = FirebaseFirestore.getInstance().collection("rooms").document("");
    //TextView rooms;

    private Toolbar toolbar;
    private RecyclerView mroomlist;
    private FirebaseFirestore mFirestore;
    private List<Room_model> room_modelList;
    private RoomAdapterList roomAdapterList;
   // private DatabaseReference mroomdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        //toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        //getActionBar().setTitle("ALL Rooms");
        //getActionBar().setDisplayHomeAsUpEnabled(true);

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

                       Room_model rooms = doc.getDocument().toObject(Room_model.class);
                       room_modelList.add(rooms);
                       roomAdapterList.notifyDataSetChanged();

                   }
                }
            }
        });

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.Add_Chat_Rooms:
                //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Intent intent = new Intent(MainActivity.this, ChatApp.class);
                //intent.putExtra("CurrentUser",user.toString());
                startActivity(new Intent(this, RoomSelectActivity.class));

            case R.id.Delete_ChatRooms:
                //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Intent intent = new Intent(MainActivity.this, ChatApp.class);
                //intent.putExtra("CurrentUser",user.toString());
                startActivity(new Intent(this, RoomSelectActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
