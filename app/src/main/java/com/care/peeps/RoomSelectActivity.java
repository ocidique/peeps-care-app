package com.care.peeps;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        //toolbar = (Toolbar) findViewById(R.id.room_toolbar);
        //setActionBar(toolbar);
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
}
