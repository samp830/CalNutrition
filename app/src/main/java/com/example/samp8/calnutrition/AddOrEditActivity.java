package com.example.samp8.calnutrition;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samp8.calnutrition.database.DatabaseHelper;
import com.example.samp8.calnutrition.model.Product;

/**
 * Created by samp8 on 9/17/2016.
 */
public class AddOrEditActivity extends Activity {
    private TextView tvAddOrEdit;
    private EditText txtId;
    private EditText txtName;
    private EditText txtCalorie;
    private EditText txtProtein;
    private EditText txtFat;
    private EditText txtCarbs;

    private Button btnAddOrEdit;
    private DatabaseHelper dbHelper;
    private String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit);
        tvAddOrEdit = (TextView)findViewById(R.id.tvAddOrEdit);
        txtId = (EditText)findViewById(R.id.txtId);
        txtName = (EditText)findViewById(R.id.txtName);
        txtCalorie= (EditText)findViewById(R.id.txtCalorie);
        txtProtein = (EditText)findViewById(R.id.txtProtein);
        txtFat = (EditText)findViewById(R.id.txtFat);
        txtCarbs = (EditText)findViewById(R.id.txtCarbs);
        btnAddOrEdit = (Button)findViewById(R.id.btnAddOrEdit);
        //Get action pass by main activity
        action =  getIntent().getExtras().getString("Action");
        tvAddOrEdit.setText(action);
        btnAddOrEdit.setText(action);
        dbHelper = new DatabaseHelper(getApplicationContext());
        txtId.setEnabled(true);

        if("Edit".equals(action)){
            int productId = getIntent().getExtras().getInt("Id");
            Product product = dbHelper.getProductById(productId);
            txtId.setText(String.valueOf(product.getId()));
            txtId.setEnabled(false);
            txtName.setText(product.getName());
            txtCalorie.setText(String.valueOf(product.getCalorie()));
            txtProtein.setText(String.valueOf(product.getProtein()));
            txtFat.setText(String.valueOf(product.getFat()));
            txtCarbs.setText(String.valueOf(product.getCarbs()));
        }
        btnAddOrEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product(
                        Integer.parseInt(txtId.getText().toString()),
                        txtName.getText().toString(),
                        Integer.parseInt(txtCalorie.getText().toString()),
                        Integer.parseInt(txtProtein.getText().toString()),
                        Integer.parseInt(txtFat.getText().toString()),
                        Integer.parseInt(txtCarbs.getText().toString())
                );
                if("Edit".equals(action)) {
                    long result = dbHelper.updateProduct(product);
                    if(result>0){
                        Toast.makeText(getApplicationContext(),"Updated", Toast.LENGTH_SHORT).show();
                        //back to main activity
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"Update failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    long result = dbHelper.addProduct(product);
                    if(result>0){
                        Toast.makeText(getApplicationContext(),"Added", Toast.LENGTH_SHORT).show();
                        //back to main activity
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"Add failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}