package com.example.firebaserealtimedatabase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class Main2Activity extends AppCompatActivity {
    DatabaseReference readRef, marksRef, nameRef, childRefs, findRefs;

    TextView txtName, txtMarks;
    EditText editName, editMarks;
    Button btnName,btnMarks, btnChildListener;

    ValueEventListener listener;
    ChildEventListener listener2;
    ChildEventListener listener3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtName= findViewById(R.id.textView0);
        txtMarks= findViewById(R.id.textView1);
        editName= findViewById(R.id.editText0);
        editMarks= findViewById(R.id.editText1);
        btnName = findViewById(R.id.btnName);
        btnMarks = findViewById(R.id.btnMarks);
        btnChildListener = findViewById(R.id.btnChildListener);
        nameRef = FirebaseDatabase.getInstance().getReference("Students/-LiOyAE2yAqSbZDybEmy/name");
        marksRef = FirebaseDatabase.getInstance().getReference("Students/-LiOyAE2yAqSbZDybEmy/marks");
    }
    @Override
    protected void onStart() {
        super.onStart();
        //=======================setting up our listener
        readRef = FirebaseDatabase.getInstance().getReference("Students/-LiOyAE2yAqSbZDybEmy");
        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                int marks = dataSnapshot.child("marks").getValue(Integer.class);
                txtName.setText(name);
                txtMarks.setText(String.valueOf(marks));
                Log.e("LISTENER FIRES", name + "|" + marks);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //readRef.addValueEventListener(listener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        //readRef.removeEventListener(listener);
        //childRefs.removeEventListener(listener2);
        //childRefs.removeEventListener(listener3);
    }
    public void enterName(View v){
        String str = editName.getText().toString();
        nameRef.setValue(str);
    }
    public void enterMarks(View v){
        String str = editMarks.getText().toString();
        int i = Integer.valueOf(str);
        marksRef.setValue(i);
    }
    public void LogChildListenerValues(View v){
        childRefs = FirebaseDatabase.getInstance().getReference("Students");
        listener2 = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("ChildListener","ChildAdded - " + dataSnapshot.getKey());
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("ChildListener","DataChaged - " + dataSnapshot.getKey());
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.e("ChildListener","Child Removed - " + dataSnapshot.getKey());
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        childRefs.addChildEventListener(listener2);
    }
    public void findStudentMarks(View v){
        findRefs = FirebaseDatabase.getInstance().getReference("Students");
        listener3 = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("name").getValue(String.class);
                int marks = dataSnapshot.child("marks").getValue(Integer.class);
                String displayStr = name + "|" + marks;
                Log.e("ChildListener",displayStr);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        findRefs.orderByChild("name").equalTo("qqq999").addChildEventListener(listener3);
    }
    public void DeleteValue(View v){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference("Students/-Lzjkjdsf00");
        delRef.child("marks").setValue(null);
    }
    public void RemoveNode(View v){
        DatabaseReference removeRef = FirebaseDatabase.getInstance().getReference("Students/-ZZZ001122");
        removeRef.removeValue();
    }
}
/*
listener = new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String name = dataSnapshot.child("name").getValue(String.class);
        int marks = dataSnapshot.child("marks").getValue(Integer.class);
        txtName.setText(name);
        txtMarks.setText(String.valueOf(marks));
        Log.e("LISTENER FIRES", name + "|" + marks);
        }
@Override
public void onCancelled(@NonNull DatabaseError databaseError) {
        }
        };
        readRef.addValueEventListener(listener);*/

