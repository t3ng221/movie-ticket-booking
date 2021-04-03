package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText nameText,idText,courseText,sectionText ;
    EditText searchiD;
    TextView nameview, sectionview,courseview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idText=findViewById(R.id.idEditTextid);
        nameText=findViewById(R.id.nameEditTextid);
        courseText=findViewById(R.id.courseEditTextid);
        sectionText=findViewById(R.id.sectionEditTextid);
        searchiD=findViewById(R.id.searchTextiD);
        nameview=findViewById(R.id.idoutput);
        courseview=findViewById(R.id.courseoutput);
        sectionview=findViewById(R.id.sectionoutput);
    }

    public void Saveprocess(View view) {
        String idData,nameData,courseData,sectiondata;
                idData =idText.getText().toString();
                nameData =nameText.getText().toString();
                courseData =courseText.getText().toString();
                sectiondata =sectionText.getText().toString();
                insertionClass data=new insertionClass(nameData,courseData,sectiondata);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Students");
        myRef.child(idData).setValue(data);
        Toast.makeText(this, "Data successfully saved", Toast.LENGTH_SHORT).show();
        idText.setText("");
        nameText.setText("");
        courseText.setText("");
        sectionText.setText("");



    }

    public void Showthedata(View view) {

        String searchdata = searchiD.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Students").child(searchdata);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String n,c,s;
                n=snapshot.child("name").getValue().toString();
                c=snapshot.child("course").getValue().toString();
                s=snapshot.child("section").getValue().toString();
                nameview .setText(""+n);
                courseview .setText(""+c);
                sectionview .setText(""+s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}