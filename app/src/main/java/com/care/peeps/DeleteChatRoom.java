package com.care.peeps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_chat_room);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

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

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {


                        Room_model rooms = doc.getDocument().toObject(Room_model.class);
                        room_modelList.add(rooms);


                    }
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deleteroom,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.chroom:
                startActivity(new Intent(this, RoomSelectActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}

