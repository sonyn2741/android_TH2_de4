package com.example.sqlite_demo_04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;

import com.example.sqlite_demo_04.dao.SQLiteHelper;
import com.example.sqlite_demo_04.model.Item;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eName,eAuthor;
    private RadioButton rhoc,rtracuu;
    private CheckBox cbCNTT,cbVT,cbDT;
    private RatingBar ratingBar;
    private Button btUpdate,btBack,btRemove;

    private Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btBack.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        Intent intent=getIntent();
        item= (Item) intent.getSerializableExtra("item");
        eName.setText(item.getName());
        eAuthor.setText(item.getAuthor());
        if(item.getRange().equalsIgnoreCase("study".trim())){
            rhoc.setChecked(true);
        }
        else{
            rtracuu.setChecked(true);
        }
        String [] its= item.getOop().split(",");
        for(String it:its) {
            if (it.equalsIgnoreCase("CNTT".trim())) {
                cbCNTT.setChecked(true);
            }
            if (it.equalsIgnoreCase("VT".trim())) {
                cbVT.setChecked(true);
            }
            if (it.equalsIgnoreCase("DT".trim())) {
                cbDT.setChecked(true);
            }
        }
        ratingBar.setRating(item.getRating());
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
        btBack= findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btRemove);
        btUpdate = findViewById(R.id.btUpdate);
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db= new SQLiteHelper(this);
        if(view==btBack){
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
                Item i= new Item(item.getId(),na,au,ra,op,dg);
                db.updateItem(i);
                finish();
            }
        }
        if(view== btRemove){
            int id=item.getId();
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa!");
            builder.setTitle("Ban co chac muon xoa "+item.getName()+" khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.deleteItem(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }
}