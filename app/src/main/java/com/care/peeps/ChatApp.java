package com.care.peeps;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatApp extends AppCompatActivity  {

    private EditText messagetext;
    private RecyclerView mMessageList;
    private FirebaseFirestore mFirestore;
    private List<Message_model> mMessagemodel;
    private MessageAdapterList messageAdapterList;
    public static final String  SenderName = "from";
    public static final String  cDate = "createdAt";
    public static final String  content = "msg";
    String currentDateTimeString;
    String username;
    String userID;
    String msgcontent;
    private FirebaseAuth auth;
    String roomname;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    NavigationView navigationView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_app);
        //messagetext = (EditText) findViewById(R.id.editmessage);
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Intent Chatroom= getIntent();

       // mDrawerLayout =(DrawerLayout) findViewById(R.id.drawlaychat);
        //mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        //mDrawerLayout.addDrawerListener(mToogle);
        //mToogle.syncState();

        //navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        Bundle b = Chatroom.getExtras();
        roomname =(String) b.get("roomname");
        //Log.d("name_of_room",roomname);



        Toolbar toolbar = findViewById(R.id.main_toolbar);
        //toolbar.setTitle(R.string.app_name);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        mFirestore = FirebaseFirestore.getInstance();

        mMessagemodel  = new ArrayList<>();
        messageAdapterList = new MessageAdapterList(mMessagemodel);
        mMessageList = (RecyclerView) findViewById(R.id.editmessage);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(layoutManager);

        mMessageList.setAdapter(messageAdapterList);
        auth = FirebaseAuth.getInstance();
        username = auth.getCurrentUser().getEmail();
        userID= auth.getCurrentUser().getUid();

       // Log.d("username" , username);
        //Log.d("ACroom", roomname);
        //Log.d("UserID", userID);


        mFirestore.collection("rooms").document(roomname).collection("messages").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for(DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED){

                        Message_model messages = doc.getDocument().toObject(Message_model.class);
                        String message = doc.getDocument().getString("msg");
                        String user = doc.getDocument().getString("from");
                        String date = doc.getDocument().getString("createdAt");
                        mMessagemodel.add(messages);


                    }
                }
                messageAdapterList.notifyDataSetChanged();
                mMessageList.post(new Runnable() {
                    @Override
                    public void run() {
                        // Call smooth scroll
                        mMessageList.smoothScrollToPosition(messageAdapterList.getItemCount());
                    }
                });

            }
        });




    }
    public void onButtonClicked(View view) {


        messagetext = (EditText) findViewById(R.id.messagetext);
        msgcontent = messagetext.getText().toString();
        Map<String, String> messageMap=new HashMap<>();
        messageMap.put(cDate,currentDateTimeString);
        messageMap.put(SenderName,username);
        messageMap.put(content,msgcontent);

        mFirestore.collection("rooms").document(roomname).collection("messages").add(messageMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(ChatApp.this,"message added",Toast.LENGTH_LONG).show();
                messagetext.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error = e.getMessage();
                Toast.makeText(ChatApp.this,"Error :" + error,Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.croom:
                //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Intent intent = new Intent(MainActivity.this, ChatApp.class);
                //intent.putExtra("CurrentUser",user.toString());
                startActivity(new Intent(this, RoomSelectActivity.class));
                return true;


            case R.id.Add_members:
                //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Intent intent = new Intent(MainActivity.this, ChatApp.class);
                //intent.putExtra("CurrentUser",user.toString());
                startActivity(new Intent(this, RoomSelectActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


   /* @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
    int id = item.getItemId();
        if (id == R.id.home){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        return true;

    }
        if (id == R.id.croom){
        startActivity(new Intent(getApplicationContext(), RoomSelectActivity.class));
        return true;

    }
        if(id == R.id.List_Members){
        startActivity(new Intent(getApplicationContext(), MemberSelectActivity.class));
        return true;
    }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

}*/
}
