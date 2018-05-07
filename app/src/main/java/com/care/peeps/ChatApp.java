package com.care.peeps;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
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

public class ChatApp extends AppCompatActivity {

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
    String msgcontent;
    private FirebaseAuth auth;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_app);
        //messagetext = (EditText) findViewById(R.id.editmessage);
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        //toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        mFirestore = FirebaseFirestore.getInstance();

        mMessagemodel  = new ArrayList<>();
        messageAdapterList = new MessageAdapterList(mMessagemodel);
        mMessageList = (RecyclerView) findViewById(R.id.editmessage);
        mMessageList.setHasFixedSize(true);
        mMessageList.setLayoutManager(new LinearLayoutManager(this));
        mMessageList.setAdapter(messageAdapterList);
        auth = FirebaseAuth.getInstance();
        username = auth.getCurrentUser().getUid();
        Log.d("username" , username);


        mFirestore.collection("rooms").document("Linxvw0qrN6CxznrGTyc").collection("messages").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for(DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED){

                        Message_model messages = doc.getDocument().toObject(Message_model.class);
                        String message = doc.getDocument().getString("msg");
                        String user = doc.getDocument().getString("from");
                        String date = doc.getDocument().getString("createdAt");
                        mMessagemodel.add(messages);
                        Log.d("Message", message);
                        Log.d("user", user);
                        Log.d("date", date);

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
        //Bundle extras = getIntent().getExtras();
        //String value = extras.getString("CurrentUser");
        //getUsername = FirebaseFirestore.getInstance().document("users/CurrentUser");
        /*getUsername.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    cUser = documentSnapshot.getString("name");



                }
            }
        });
        String cUser = "qDSWt4zmRMJMWon2NrJ1";
        final String getcontent = messagetext.getText().toString().trim();
        Map<String,Object> datatosave= new HashMap<String,Object>();
        datatosave.put(SenderName,cUser);
        datatosave.put(content,getcontent);
        datatosave.put(cDate,"9:15");
        documentreference.set(datatosave).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Data Saved Succesfully!",
                            Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Data donot Saved Successfully!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });*/

        messagetext = (EditText) findViewById(R.id.messagetext);
        msgcontent = messagetext.getText().toString();
        Map<String, String> messageMap=new HashMap<>();
        messageMap.put(cDate,currentDateTimeString);
        messageMap.put(SenderName,username);
        messageMap.put(content,msgcontent);

        mFirestore.collection("rooms").document("Linxvw0qrN6CxznrGTyc").collection("messages").add(messageMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(ChatApp.this,"message added",Toast.LENGTH_LONG).show();
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
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.Chat_Rooms:
                //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Intent intent = new Intent(MainActivity.this, ChatApp.class);
                //intent.putExtra("CurrentUser",user.toString());
                startActivity(new Intent(this, RoomSelectActivity.class));

            case R.id.List_Members:
                //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Intent intent = new Intent(MainActivity.this, ChatApp.class);
                //intent.putExtra("CurrentUser",user.toString());
                startActivity(new Intent(this, MemberSelectActivity.class));

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


}
