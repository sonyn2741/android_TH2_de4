package com.example.sqlite_demo_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.sqlite_demo_04.dao.SQLiteHelper;
import com.example.sqlite_demo_04.model.Item;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eName,eAuthor;
    private RadioButton rhoc,rtracuu;
    private CheckBox cbCNTT,cbVT,cbDT;
    private RatingBar ratingBar;
    private Button btUpdate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void initView() {
        eName= findViewById(R.id.tvName);
        eAuthor= findViewById(R.id.tvAuthor);
        rhoc= findViewById(R.id.rhoc);
        rtracuu= findViewById(R.id.rtracuu);
        cbCNTT=findViewById(R.id.cbCNTT);
        cbVT=findViewById(R.id.cbVT);
        cbDT=findViewById(R.id.cbDT);
        ratingBar=findViewById(R.id.tvRating);
        btCancel= findViewById(R.id.btCancel);
        btUpdate = findViewById(R.id.btUpdate);



    }

    @Override
    public void onClick(View view) {
        if(view==btCancel){
            finish();
        }
        if(view==btUpdate){
            String na = eName.getText().toString();
            String au= eAuthor.getText().toString();
            String ra="";
            if(rhoc.isChecked()){
                ra="study";
            }
            else if(rtracuu.isChecked()){
                ra="research";
            }
            String op="";
            if(cbCNTT.isChecked()){
                op=op+"CNTT"+",";
            }
            if(cbVT.isChecked()){
                op=op+"VT"+",";
            }
            if(cbDT.isChecked()){
                op=op+"DT"+",";
            }
            op=op.substring(0,op.length()-1);
            float dg= ratingBar.getRating();
            if(!na.isEmpty() && !ra.equals("") && !op.equals("")){
                Item i= new Item(na,au,ra,op,dg);
                SQLiteHelper db= new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
    }
}