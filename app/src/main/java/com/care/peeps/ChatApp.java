package com.care.peeps;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ChatApp extends AppCompatActivity {

    private EditText messagetext;
    private DocumentReference documentreference;
    private RecyclerView mMessageList;
    private FirebaseUser mcurretUser;
    private DocumentReference getUsername;
    public static final String  SenderName = "from";
    public static final String  cDate = "createdAt";
    public static final String  content = "msg";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_app);
        messagetext = (EditText) findViewById(R.id.editmess);
        documentreference = FirebaseFirestore.getInstance().document("rooms/Linxvw0qrN6CxznrGTyc/messages/JT5bBkOBT0D6tsjvmIpm");


        mMessageList = (RecyclerView) findViewById(R.id.messRec);
        mMessageList.setHasFixedSize(true);



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
        });*/
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
        });





    }


}
