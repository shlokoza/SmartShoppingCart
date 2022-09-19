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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Delete_Item extends AppCompatActivity {

   public static EditText barcode_t;
   EditText name_t, price_t, quantity_t, location_t, weight_t;
    ImageView camara;
    Button search_delete, delete;
    String barcode;
    String  name, price, quantity, location,weight;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__item);

        barcode_t = (EditText)findViewById(R.id.barcodeid_D);
        camara = (ImageView)findViewById(R.id.camara_id);
        name_t = (EditText)findViewById(R.id.nameid_D);
        price_t = (EditText)findViewById(R.id.priceid_D);
        quantity_t = (EditText)findViewById(R.id.quantityid_D);
        location_t = (EditText)findViewById(R.id.location_delete_id);
        weight_t = (EditText)findViewById(R.id.weight_delete_id);
        search_delete = (Button)findViewById(R.id.search4delete);
        delete = (Button)findViewById(R.id.btnadd);
        reff = FirebaseDatabase.getInstance().getReference();
        barcode_t.setText(" ");
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), scanCode_del.class));


            }
        });


        search_delete.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 onClick_search_4_delete();
                                             }
                                         }
        );

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_delete();
            }
        });
    }

    public void onClick_search_4_delete()
    {
        barcode = barcode_t.getText().toString().trim();
        reff= FirebaseDatabase.getInstance().getReference().child("items");
        reff.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(barcode.length()!=0)
                    if(dataSnapshot.child(barcode).exists())
                    {
                        name = dataSnapshot.child(barcode).child("name").getValue().toString();
                        price = dataSnapshot.child(barcode).child("price").getValue().toString();
                        quantity = dataSnapshot.child(barcode).child("quantity").getValue().toString();
                        location = dataSnapshot.child(barcode).child("location").getValue().toString();
                        weight = dataSnapshot.child(barcode).child("weight").getValue().toString();
                        name_t.setText(name);
                        price_t.setText(price);
                        quantity_t.setText(quantity);
                        location_t.setText(location);
                        weight_t.setText(weight);
                        name_t.setVisibility(View.VISIBLE);
                        price_t.setVisibility(View.VISIBLE);
                        quantity_t.setVisibility(View.VISIBLE);
                        weight_t.setVisibility(View.VISIBLE);
                        location_t.setVisibility(View.VISIBLE);

                    }
                    else
                    {
                        // no barcode


                        name = "";
                        price = "";
                        quantity = "";
                        location = "";
                        weight = "";
                        name_t.setText(name);
                        price_t.setText(price);
                        quantity_t.setText(quantity);
                        weight_t.setText(weight);
                        location_t.setText(location);
                        name_t.setVisibility(View.VISIBLE);
                        price_t.setVisibility(View.VISIBLE);
                        quantity_t.setVisibility(View.VISIBLE);
                        weight_t.setVisibility(View.VISIBLE);
                        location_t.setVisibility(View.VISIBLE);


                    }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));
    }

    public void onClick_delete()
    {
        barcode = barcode_t.getText().toString().trim();
        reff= FirebaseDatabase.getInstance().getReference().child("items");
        reff.child(barcode).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Delete_Item.this,"item successfully deleted",Toast.LENGTH_SHORT).show();
                    barcode_t.setText("");
                }
                else
                {
                    Toast.makeText(Delete_Item.this,"item not successfully deleted",Toast.LENGTH_SHORT).show();

                }
            }
        });



    }




}