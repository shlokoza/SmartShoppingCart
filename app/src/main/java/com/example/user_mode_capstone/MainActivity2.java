package com.example.user_mode_capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    EditText  a,b,c, txlocation, txweight;
    public static EditText barcode;
    ImageView camara;
    Button btnUpdate, searchClick;
    String barc, name, price, quantity, location, weight;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        barcode = (EditText) findViewById(R.id.barcode);
        camara = (ImageView)findViewById(R.id.camara_id);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        searchClick = (Button)findViewById(R.id.search_id1);

        a= (EditText)findViewById(R.id.name_id);
        b= (EditText)findViewById(R.id.price_id);
        c= (EditText)findViewById(R.id.quantity_id);
        txlocation = (EditText)findViewById(R.id.location_update_id);
        txweight = (EditText)findViewById(R.id.weight_update_id) ;
         barcode.setText(" ");


        btnUpdate.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateClick( v);
            }
        });

      searchClick.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              searchClick( v);
          }
      });

      camara.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(), scan_code_update.class));


          }
      });


    }



    public void searchClick (View v)
    {
        barc = barcode.getText().toString().trim();
        reff= FirebaseDatabase.getInstance().getReference().child("items");
        reff.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(barc.length()!=0)
                if(dataSnapshot.child(barc).exists())
                {
                    name = dataSnapshot.child(barc).child("name").getValue().toString();
                    price = dataSnapshot.child(barc).child("price").getValue().toString();
                    quantity = dataSnapshot.child(barc).child("quantity").getValue().toString();
                    location = dataSnapshot.child(barc).child("location").getValue().toString();
                    weight = dataSnapshot.child(barc).child("weight").getValue().toString();
                    a.setText(name);
                    b.setText(price);
                    c.setText(quantity);
                    txlocation.setText(location);
                    txweight.setText(weight);

                    a.setVisibility(View.VISIBLE);
                    b.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                    txweight.setVisibility(View.VISIBLE);
                    txlocation.setVisibility(View.VISIBLE);

                }
                else
                {
                    // no barcode


                    name = "";
                    price = "";
                    quantity = "";
                    location = "";
                    weight = "";
                    a.setText(name);
                    b.setText(price);
                    c.setText(quantity);
                    txlocation.setText(location);
                    txweight.setText(weight);
                    a.setVisibility(View.INVISIBLE);
                    b.setVisibility(View.INVISIBLE);
                    c.setVisibility(View.INVISIBLE);

                    Toast.makeText(MainActivity2.this,"item not found",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));
    }


    public void updateClick (View v)
    {
        a= (EditText)findViewById(R.id.name_id);
        b= (EditText)findViewById(R.id.price_id);
        c= (EditText)findViewById(R.id.quantity_id);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ItemModel itemModel = new ItemModel(a.getText().toString().trim(),Integer.parseInt(c.getText().toString().trim()),Double.parseDouble(b.getText().toString().trim()),Double.parseDouble(b.getText().toString().trim()), txlocation.getText().toString() );

        HashMap map = new HashMap();
        map.put(barc, itemModel);
        reff.child(barc).setValue(itemModel);

        Toast.makeText(MainActivity2.this,"Update Successful",Toast.LENGTH_SHORT).show();


    }





}