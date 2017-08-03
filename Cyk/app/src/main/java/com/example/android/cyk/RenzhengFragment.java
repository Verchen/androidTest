package com.example.android.cyk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.cyk.Adapter.Renzheng_adapter;

/**
 * Created by qiao on 2017/7/3.
 */

public class RenzhengFragment extends Fragment implements Renzheng_adapter.Callback, AdapterView.OnItemClickListener {

    ListView listView;
    Renzheng_adapter adapter;
    Context mContext;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        sharedPreferences = mContext.getSharedPreferences("jyd", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.renzheng_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.id_renzheng_listview);
        adapter = new Renzheng_adapter(mContext, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void click(int i) {
        if (sharedPreferences.getString("userId", "").isEmpty()){
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            return;
        }
        switch (i){
            case 0:
                Intent intent = new Intent(mContext, ContactsActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(mContext, BankCardActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(mContext, PhoneShimingActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(mContext, ShenfenRenzhengActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0 || i == 2){
            return;
        }
        if (sharedPreferences.getString("userId", "").isEmpty()){
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            return;
        }
        switch (i){
            case 3:
                Toast.makeText(mContext, "芝麻信用分", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(mContext, "京东白条", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(mContext, "手机账单", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(mContext, "信用卡账单", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
