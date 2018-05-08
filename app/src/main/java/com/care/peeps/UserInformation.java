package com.care.peeps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UserInformation extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore database;
    EditText user_Display_name;
    EditText phone;
    EditText role;
    Button add_info;
    public static final String  authId = "auth_id";
    public static final String  userName = "name";
    public static final String  phone_number= "phone_number";
    public static final String  Userrole = "role";
    String autid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        auth = FirebaseAuth.getInstance();
        autid = auth.getCurrentUser().getUid();
        FirebaseUser user = auth.getCurrentUser();
        Log.d("userId",user.getUid().toString());
        user_Display_name = (EditText) findViewById(R.id.DisplayName);
        phone = (EditText) findViewById(R.id.phone_number);
        role = (EditText) findViewById(R.id.role);
        add_info = (Button) findViewById(R.id.userInformation);


       add_info.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String userDN = user_Display_name.getText().toString();
               String ph = phone.getText().toString();
               String ro = role.getText().toString();

               Map<String, String> userinfoMap=new HashMap<>();
               userinfoMap.put(authId,autid);
               userinfoMap.put(userName,userDN);
               userinfoMap.put(phone_number,ph);
               userinfoMap.put(Userrole,ro);
               database.collection("users").document(autid).set(userinfoMap, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void v) {
                       Toast.makeText(UserInformation.this,"message added",Toast.LENGTH_LONG).show();
                       startActivity(new Intent(UserInformation.this, RoomSelectActivity.class));

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       String error = e.getMessage();
                       Toast.makeText(UserInformation.this,"Error :" + error,Toast.LENGTH_LONG).show();
                   }
               });

           }
       });



    }
}
