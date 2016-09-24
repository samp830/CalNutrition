package com.example.samp8.calnutrition.database;

/**
 * Created by samp8 on 9/17/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.samp8.calnutrition.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "breakfast.sqlite";
    public static String DBLOCATION = null;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 14);
        this.DBLOCATION = "/data/data/" + context.getPackageName() + "/" + "databases/";
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Product> getListProduct() {
        Product product = null;
        List<Product> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM PRODUCT ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(3),
                    cursor.getInt(4));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }

    public Product getProductById(int number) {
        Product product = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM PRODUCT WHERE NUMBER = ?", new String[]{String.valueOf(number)});
        cursor.moveToFirst();
        product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(3),
                cursor.getInt(4));
        //Only 1 result
        cursor.close();
        closeDatabase();
        return product;
    }
    public long updateProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUMBER", product.getId());
        contentValues.put("NAME", product.getName());
        contentValues.put("CALORIES", product.getCalorie());
        contentValues.put("PROTEIN", product.getProtein());
        contentValues.put("FAT", product.getFat());
        contentValues.put("CARBS", product.getCarbs());

        String[] whereArgs = {Integer.toString(product.getId())};
        openDatabase();
        long returnValue = mDatabase.update("PRODUCT", contentValues, "NUMBER=?", whereArgs);
        closeDatabase();
        return returnValue;
    }

    public long addProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUMBER", product.getId());
        contentValues.put("NAME", product.getName());
        contentValues.put("CALORIES", product.getCalorie());
        contentValues.put("PROTEIN", product.getProtein());
        contentValues.put("FAT", product.getFat());
        contentValues.put("CARBS", product.getCarbs());
        openDatabase();
        long returnValue = mDatabase.insert("PRODUCT", null, contentValues);
        closeDatabase();
        return returnValue;
    }
    public boolean deleteProductById(int number) {
        openDatabase();
        int result = mDatabase.delete("PRODUCT",  "NUMBER =?", new String[]{String.valueOf(number)});
        closeDatabase();
        return result !=0;
    }
}