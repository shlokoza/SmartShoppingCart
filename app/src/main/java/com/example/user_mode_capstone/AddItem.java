package com.example.user_mode_capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {
    String barcode, name, location;
    Double price, weight;
    int quantity;
    EditText txname, txprice, txquantity, txlocation, txweight;
    public static EditText txbarcode;
    ImageView camara;
    FirebaseDatabase rootnode;
    DatabaseReference reff;
    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        txbarcode = (EditText)findViewById(R.id.barcodeid_D);
        txname = (EditText)findViewById(R.id.nameid_D);
        txprice = (EditText)findViewById(R.id.priceid_D);
        txquantity = (EditText)findViewById(R.id.quantityid_D);
        camara = (ImageView)findViewById(R.id.camara_id);
        btnadd = (Button)findViewById(R.id.btnadd);
        txlocation = (EditText)findViewById(R.id.location_add_id);
        txweight = (EditText)findViewById(R.id.weight_add_id);
        txbarcode.setText(" ");
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickadd(v);
            }
        });

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), scan_code_add.class));


            }
        });


    }

    public void onClickadd( View v)
    {
        barcode = txbarcode.getText().toString().trim();
        name = txname.getText().toString().trim();
        price = Double.parseDouble(txprice.getText().toString());
        quantity = Integer.parseInt(txquantity.getText().toString());
        location = txlocation.getText().toString().trim();
        weight = Double.parseDouble(txweight.getText().toString());
        rootnode = FirebaseDatabase.getInstance();
        reff =  FirebaseDatabase.getInstance().getReference().child("items").child(barcode);
        reff.setValue(new ItemModel(name,quantity,price, weight,location));

        Toast.makeText(AddItem.this, "Item Added Successfully", Toast.LENGTH_SHORT).show();

    }
}