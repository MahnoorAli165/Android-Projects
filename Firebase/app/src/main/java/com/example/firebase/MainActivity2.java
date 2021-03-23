package com.example.firebase;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    EditText makeT, modelT, engineT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        makeT = findViewById(R.id.makeT);
        modelT = findViewById(R.id.modelT);
        engineT = findViewById(R.id.engineT);
    }
    public  void submit(View v)
    {
        if (TextUtils.isEmpty(makeT.getText().toString()))
        {
            makeT.setError("This field is required!!!");
            return;
        }
        if (TextUtils.isEmpty(modelT.getText().toString()))
        {
            modelT.setError("This field is required!!!");
            return;
        }
        if (TextUtils.isEmpty(engineT.getText().toString()))
        {
            engineT.setError("This field is required!!!");
            return;
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Cars").push();
        reference.child("Make").setValue(makeT.getText().toString());
        reference.child("Model").setValue(modelT.getText().toString());
        reference.child("Engine").setValue(engineT.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(MainActivity2.this, "Success: Data saved", Toast.LENGTH_SHORT).show();
                }
                if (!task.isSuccessful())
                {
                    Toast.makeText(MainActivity2.this, "Error saving data", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void show(View v)
    {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity2.this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Cars");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s="";
                if(dataSnapshot.getChildrenCount()>0)
                {
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        s+= "Make: " + ds.child("Make").getValue()+"\n";
                        s+= "Model: " + ds.child("Model").getValue()+"\n";
                        s+= "Engine: " + ds.child("Engine").getValue()+"\n";

                    }
                    alertDialog.setTitle("Records: Cars");
                    alertDialog.setMessage(s);
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();
                }
                else {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Nothing to show!!!");
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}