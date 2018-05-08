package com.care.peeps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class MemberSelectActivity extends AppCompatActivity {
    private FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_select);
        /*mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("rooms").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED){




                        Room_model rooms = doc.getDocument().toObject(Room_model.class);
                        //Log.d("room", rooms.getname());
                        //Log.d("room", rooms.getImage());
                        room_modelList.add(rooms);
                        roomAdapterList.notifyDataSetChanged();

                    }
                }
            }
        });*/

    }
}
