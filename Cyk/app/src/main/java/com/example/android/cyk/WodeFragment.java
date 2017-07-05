package com.example.android.cyk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.cyk.Adapter.Wode_adapter;

import java.util.ArrayList;

/**
 * Created by qiao on 2017/7/3.
 */

public class WodeFragment extends Fragment {

    Context mContext;
    ListView listView;
    Wode_adapter wode_adapter;
    ArrayList<String> listString;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        listString = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            listString.add(Integer.toString(i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wode_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = getView().findViewById(R.id.id_wode_list_view);
        wode_adapter = new Wode_adapter(mContext, listString);
        listView.setAdapter(wode_adapter);
    }
}
