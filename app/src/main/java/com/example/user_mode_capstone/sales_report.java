package com.example.user_mode_capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Map;

public class sales_report extends AppCompatActivity {
   int total;
   String date = "";
    private static final String TAG = "sales_report";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
 DatabaseReference reff;

    EditText date_text, total_txt;
   Button refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);

        refresh = (Button)findViewById(R.id.refresh_id);
        date_text = (EditText)findViewById(R.id.date_id);
        total_txt = (EditText)findViewById(R.id.total_id);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:1088284420194:android:3817ae3b2f16d23e38493b") // Required for Analytics.
                .setApiKey("AIzaSyB-G0gmc2nBehEhzlzPF4UrvkqsbQUl2SE") // Required for Auth.
                .setDatabaseUrl("https://smart-shopping-cart-1fbac.firebaseio.com/") // Required for RTDB.
                .build();


        FirebaseApp.initializeApp(this /* Context */, options, "second_db");
        FirebaseApp secondApp = FirebaseApp.getInstance("second_db");


        reff= FirebaseDatabase.getInstance(secondApp).getReference().child("bills");

        date_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        sales_report.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd-mm-yyy: " + day + "-" + month + "-" + year);

               date = day + "-" + month + "-" + year;
                date_text.setText(date);
                System.out.println("hello mf " +date);
            }
        };






        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh_method(v);
            }
        });

    }

    public void refresh_method(View v)
    {



        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                total = 0;

                if(dataSnapshot.child(date).exists()) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {


                        reff = reff.child(date);


                   reff.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           for(DataSnapshot ds1 : dataSnapshot.getChildren())
                           {
                           Map<String, Object> map1 = (Map<String, Object>) ds1.getValue();
                           Object price = map1.get("total");
                           int pvalue = Integer.parseInt(String.valueOf(price));
                           total += pvalue;
                          total_txt.setText(String.valueOf(total));
                           }
                           System.out.println("hello randi "+total);

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });



                    }
                }

                else
                {
                    Toast.makeText(sales_report.this,"No Sale On the Date Selected",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}