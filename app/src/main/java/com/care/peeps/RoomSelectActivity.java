package com.care.peeps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
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

    public TextView rooms;
    private Toolbar toolbar;
    private RecyclerView mroomlist;
    private FirebaseFirestore mFirestore;
    private List<Room_model> room_modelList;
    private RoomAdapterList roomAdapterList;
    private DatabaseReference mroomdb;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);

        String url = "https://firebasestorage.googleapis.com/v0/b/peeps-care-app.appspot.com/o/room.png?alt=media&token=516d1785-507f-4e7f-8217-1b83d54b7b42";
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

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
                       Log.d("room", rooms.getstatus());
                       //Log.d("room", rooms.getImage());
                       room_modelList.add(rooms);
                       roomAdapterList.notifyDataSetChanged();

                   }
                }
            }
        });


    }



    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.Add_Chat_Rooms:
                startActivity(new Intent(this, Add_Chatroom.class));
                return true;
            case R.id.Delete_ChatRooms:
                startActivity(new Intent(this, DeleteChatRoom.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.room_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
