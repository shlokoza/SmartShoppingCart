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

public class Inventory extends AppCompatActivity {
    Button search;
    EditText  name_e, price_e, quantity_e, location_e, weight_e;
    public static EditText barcode;
    ImageView camara;
    String barc, name, price, quantity, location, weight;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        search = (Button)findViewById(R.id.search_inv_id);
        barcode = (EditText) findViewById(R.id.barcode);
        name_e= (EditText)findViewById(R.id.name_id);
        camara = (ImageView)findViewById(R.id.camara_id);
        price_e= (EditText)findViewById(R.id.price_id);
       quantity_e= (EditText)findViewById(R.id.quantity_id);
       location_e = (EditText)findViewById(R.id.location_inv_id);
       weight_e = (EditText)findViewById(R.id.weight_inv_id);
       barcode.setText(" ");
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClick( v);
            }
        });

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), scan_code_inv.class));


            }
        });
    }

    public void searchClick(View v)
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

                        name_e.setText(name);
                        price_e.setText(price);
                        quantity_e.setText(quantity);
                        location_e.setText(location);
                        weight_e.setText(weight);
                        name_e.setVisibility(View.VISIBLE);
                        price_e.setVisibility(View.VISIBLE);
                        quantity_e.setVisibility(View.VISIBLE);
                        weight_e.setVisibility(View.VISIBLE);
                        location_e.setVisibility(View.VISIBLE);

                    }
                    else
                    {
                        // no barcode


                        name = "";
                        price = "";
                        quantity = "";
                        location = "";
                        weight = "";
                        name_e.setText(name);
                        price_e.setText(price);
                        quantity_e.setText(quantity);
                        location_e.setText(location);
                        weight_e.setText(weight);
                        name_e.setVisibility(View.INVISIBLE);
                        price_e.setVisibility(View.INVISIBLE);
                        quantity_e.setVisibility(View.INVISIBLE);
                        weight_e.setVisibility(View.INVISIBLE);
                        location_e.setVisibility(View.INVISIBLE);

                        Toast.makeText(Inventory.this,"item not found",Toast.LENGTH_SHORT).show();
                    }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));
    }
}