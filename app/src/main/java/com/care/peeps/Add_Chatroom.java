package com.care.peeps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Add_Chatroom extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    EditText room_status;
    EditText room_name;
    public static final String  RoomName = "name";
    public static final String  Status = "status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__chatroom);
        mFirestore = FirebaseFirestore.getInstance();





    }

    public void onButtonClicked(View view) {
        room_name = (EditText) findViewById(R.id.aRoom_name);
        room_status = (EditText) findViewById(R.id.aRoom_name);

        String room_name_s = room_name.getText().toString();
        String room_status_s = room_status.getText().toString();

        Map<String, String> roomMap=new HashMap<>();
        roomMap.put(RoomName,room_name_s);
        roomMap.put(Status,room_status_s);

        mFirestore.collection("rooms").document(room_name_s).set(roomMap,SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void v) {
               Toast.makeText(Add_Chatroom.this,"message added",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error = e.getMessage();
                Toast.makeText(Add_Chatroom.this,"Error :" + error,Toast.LENGTH_LONG).show();
            }
        });
        this.finish();

    }
}
