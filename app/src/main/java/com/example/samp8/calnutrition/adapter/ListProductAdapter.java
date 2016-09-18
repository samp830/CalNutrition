package com.example.samp8.calnutrition.adapter;

/**
 * Created by samp8 on 9/17/2016.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.samp8.calnutrition.R;
import com.example.samp8.calnutrition.model.Product;

import java.util.List;

/**
 * Created by NgocTri on 11/7/2015.
 */
public class ListProductAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product> mProductList;

    public ListProductAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getNumber();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView Name = (TextView)v.findViewById(R.id.product_name);
        TextView Calorie = (TextView)v.findViewById(R.id.product_calorie);
        TextView Protein = (TextView)v.findViewById(R.id.product_protein);
        TextView Fat = (TextView)v.findViewById(R.id.product_fat);
        TextView Carbs = (TextView)v.findViewById(R.id.product_carbs);

        Name.setText(mProductList.get(position).getName());
        Calorie.setText(String.valueOf(mProductList.get(position).getCalorie()));
        Protein.setText(mProductList.get(position).getProtein());
        Fat.setText(mProductList.get(position).getFat());
        Carbs.setText(mProductList.get(position).getCarbs());

        v.setTag( mProductList.get(position).getNumber());

        return v;
    }

    public void updateList(List<Product> lstItem) {
        mProductList.clear();
        mProductList.addAll(lstItem);
        this.notifyDataSetChanged();
    }
}
