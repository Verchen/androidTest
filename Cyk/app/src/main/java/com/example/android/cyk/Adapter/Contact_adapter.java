package com.example.android.cyk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.cyk.R;

/**
 * Created by qiao on 2017/7/11.
 */

public class Contact_adapter extends BaseAdapter {

    private Context mContact;
    private LayoutInflater inflater;

    public Contact_adapter(Context context){
        this.mContact = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return "";
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.contact_item_layout, viewGroup, false);
        return view;
    }
}
