package com.care.peeps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RoomSelectActivity extends AppCompatActivity {
    private DocumentReference documentreference = FirebaseFirestore.getInstance().collection("rooms").document("");
    TextView rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);
        rooms = (TextView) findViewById(R.id.rooms);

        documentreference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                  String Chatroomname = documentSnapshot.getString("name");
                  rooms.setText(Chatroomname);


                }
            }
        });
    }
}
