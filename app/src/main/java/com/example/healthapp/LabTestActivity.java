package com.example.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private  String[][] packages =
            {
                    {"Package 1 : Full Body Checkup" , "" , "" , "" , "999"},
                    {"Package 2 : Blood Sugar Check" , "" , "" , "" , "299"},
                    {"Package 3 : COVID-19 Antibody" , "" , "" , "" , "399"},
                    {"Package 4 : Thyroid Check" , "" , "" , "" , "599"},
                    {"Package 5 : Immunity Check" , "" , "" , "" , "799"},
            };
    private String[] package_details = {
            "Blood Sugar Test\n" + "Complete Hemogram\n" + "HbA1c\n" + "Iron Studies\n" + "Kidney Function Test\n" + "LDH Lactate Dehydrogenase\n" + "Lipid Profile\n" + "Liver Function Test" ,
            "Blood Sugar Test" ,
            "COVID-19 Antibody" ,
            "Thyroid Profile(T3 , T4 & TSH)" ,
            "Complete Hemogram\n" + "CRP\n" + "Iron Studies\n" + "Kindey Function Test\n" + "Vitamin D Hydroxy\n" + "Liver Function Test\n" + "Lipid Profile"
    };


    HashMap<String , String> item;
    ArrayList list ;
    SimpleAdapter sa ;
    Button btnGoToCart , btnBack ;
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.buttonLTGoToCart);
        btnBack = findViewById(R.id.buttonLTBack);
        listView = findViewById(R.id.listViewLT);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this , HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0 ; i < packages.length ; i ++){
            item = new HashMap<String , String>();
            item.put("line1" , packages[i][0]);
            item.put("line2" , packages[i][1]);
            item.put("line3" , packages[i][2]);
            item.put("line4" , packages[i][3]);
            item.put("line5" , "Total Cost : " + packages[i][4] +"BDT");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] {"line1" , "line2" , "line3" , "line4" , "line5"},
                new int[] {R.id.line_a ,R.id.line_b ,R.id.line_c ,R.id.line_d ,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this , LabTestDetailsActivity.class);
                it.putExtra("text1" , packages[i][0]);
                it.putExtra("text2" , package_details[i]);
                it.putExtra("text3" , packages[i][4]);
                startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}