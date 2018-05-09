package com.care.peeps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DeleteChatRoom extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CheckBox chk_select_all;
    private Button btn_delete_all;
    private Toolbar toolbar;
    private RecyclerView mroomlist;
    private FirebaseFirestore mFirestore;
    private List<Room_model> room_modelList;
    private DeleteAdapterList roomAdapterList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_chat_room);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
//        getActionBar().setTitle("ALL Rooms");
        //      getActionBar().setDisplayHomeAsUpEnabled(true);

        //mroomdb = FirebaseDatabase.getInstance().getReference().child("Room");
        room_modelList = new ArrayList<>();
        roomAdapterList = new DeleteAdapterList(room_modelList);

        mroomlist = (RecyclerView) findViewById(R.id.recyclerView);
        mroomlist.setHasFixedSize(true);
        mroomlist.setLayoutManager(new LinearLayoutManager(this));
        mroomlist.setAdapter(roomAdapterList);
        chk_select_all = (CheckBox) findViewById(R.id.chk_selected);


        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("rooms").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED){




                        Room_model rooms = doc.getDocument().toObject(Room_model.class);
                        room_modelList.add(rooms);


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

/*
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

            //case R.id.:
                //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Intent intent = new Intent(MainActivity.this, ChatApp.class);
                //intent.putExtra("CurrentUser",user.toString());
                //startActivity(new Intent(this, RoomSelectActivity.class));

               // return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/
}
