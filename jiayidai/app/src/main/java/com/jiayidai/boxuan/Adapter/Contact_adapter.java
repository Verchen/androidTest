package com.jiayidai.boxuan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jiayidai.boxuan.Model.ContactResp;
import com.jiayidai.boxuan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao on 2017/7/11.
 */

public class Contact_adapter extends BaseAdapter {

    private Context mContact;
    private LayoutInflater inflater;
    private List<ContactResp.ContactModel> datasource = new ArrayList<>();

    public Contact_adapter(Context context){
        this.mContact = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setDatasource(List<ContactResp.ContactModel> datasource) {
        this.datasource = datasource;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datasource.size();
    }

    @Override
    public Object getItem(int i) {
        return datasource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ContactResp.ContactModel contactModel = datasource.get(i);

        view = inflater.inflate(R.layout.contact_item_layout, viewGroup, false);
        TextView name = view.findViewById(R.id.id_contact_name_tv);
        TextView relation = view.findViewById(R.id.id_contact_relation_tv);
        TextView phone = view.findViewById(R.id.id_contact_phone_tv);

        name.setText(contactModel.getName());
        relation.setText(contactModel.getRelationId());
        phone.setText("手机号码："+contactModel.getTel());
        return view;
    }
}
