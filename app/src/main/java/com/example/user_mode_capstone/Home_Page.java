package com.example.user_mode_capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home_Page extends AppCompatActivity {
    TextView logout;
    ImageView add, update,delete, inventory,salesreport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        logout = (TextView)findViewById(R.id.logout_id);
        add = (ImageView)findViewById(R.id.add_id_clk);
        update = (ImageView)findViewById(R.id.update_id_clk);
        delete = (ImageView)findViewById(R.id.delete_id_clk);
        inventory = (ImageView)findViewById(R.id.inv_id_clk);
        salesreport = (ImageView)findViewById(R.id.sales_report_id) ;
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout( v);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_method(v);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_method(v);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_method(v);
            }
        });

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inv_method(v);
            }
        });

        salesreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sales_method(v);
            }
        });
    }



    public void logout(View v)
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void add_method(View v)
    {
        startActivity(new Intent(getApplicationContext(), AddItem.class));
    }

    public void update_method(View v)
    {
        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
    }

    public void delete_method(View v)
    {
        startActivity(new Intent(getApplicationContext(), Delete_Item.class));
    }

    public void inv_method(View v)
    {
        startActivity(new Intent(getApplicationContext(), Inventory.class));
    }

    public void sales_method(View v)
    {
        startActivity(new Intent(getApplicationContext(), sales_report.class));
    }
}