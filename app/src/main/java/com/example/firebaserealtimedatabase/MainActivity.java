package com.example.firebaserealtimedatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editname, editmarks;
    TextView txt, gotoTxt;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editname = findViewById(R.id.editName);
        editmarks = findViewById(R.id.editMarks);
        txt=findViewById(R.id.textView1);
        gotoTxt=findViewById(R.id.textViewGO);

        dbRef = FirebaseDatabase.getInstance().getReference("Students");
    }

    public void WriteData(View v){
        String name = editname.getText().toString();
        int marks = Integer.valueOf(editmarks.getText().toString());

        String uniqueId = dbRef.push().getKey();


         //step 1--------------------------
        //dbRef.child(uniqueId).child("name").setValue(name);//run twice diff names

        //step 2--------------------------
        //dbRef.child(uniqueId).child("name").setValue(name);
        //dbRef.child(uniqueId).child("marks").setValue(marks);


        //step 3--------------------------
        //dbRef.child(uniqueId).child("id").setValue(uniqueId);//option to save to SharedPreferences
        //dbRef.child(uniqueId).child("name").setValue(name);
        //dbRef.child(uniqueId).child("marks").setValue(marks);


        //step 4--------------------------POJO
        //Student s = new Student(uniqueId,name,marks);
        //dbRef.child(uniqueId).setValue(s);

    }

    public void UpdateData(View v){
        DatabaseReference updtRef;
        updtRef = FirebaseDatabase.getInstance().getReference("Students/-Lho9Q610N798c-JO_a0");

        //-----------------------1 (simple)
        //updtRef.child("Marks").setValue(555);


        //---------------------2 (full POJO)
        //Student s1 = new Student("-Lho9Q610N798c-JO_a0","AAA",555);
        //updtRef.setValue(s1);


        //---------------------3 (only few values need update)
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("name", "BBB");
        updates.put("marks", 222);
        updtRef.updateChildren(updates);
        //2 Advantages: simultaneous & no multiple firing of Listeners

    }

    public void getData(View v){
    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference("Students/-Lho9Q610N798c-JO_a0");

    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            ///-----------------------------simple
            String name = dataSnapshot.child("name").getValue(String.class);
            int marks = dataSnapshot.child("marks").getValue(Integer.class);
            String displayStr = name + "|" + marks;
            txt.setText(displayStr);


            ///-----------------------------POJO way
            //Student ss = dataSnapshot.getValue(Student.class);
            //String name = ss.getName();
            //int marks = ss.getMarks();

            //String displayStr = name + "|" + marks;
            //txt.setText(displayStr);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }

    public void Rnd(View v){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference("Students/-Lho9Q610N798c-JO_a0");

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    Log.e("CHILDREN ??", "hasChildren: " + dataSnapshot.hasChildren());
                    Log.e("CHILD COUNT", "Count: " + dataSnapshot.getChildrenCount());
                    Log.e("has CHILD ??", "is Child there?: " + dataSnapshot.hasChild("Actor"));
                    Log.e("=============", "================");
                    Log.e("CHILD COUNT", "toString: " + dataSnapshot.toString());
                    Log.e("=============", "================");
                    Log.e("GET key", "Key: " + dataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // public void goToListeners(View v){
    //     Intent i = new Intent(this,Main2Activity.class);
    //     startActivity(i);
    // }
}
