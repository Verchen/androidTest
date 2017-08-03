package com.example.android.cyk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cyk.Model.UserInfroResp;
import com.example.android.cyk.R;

/**
 * Created by qiao on 2017/7/5.
 */

public class Wode_adapter extends BaseAdapter {

    Context mContext;
    private UserInfroResp.UserModel userModel = new UserInfroResp.UserModel();

    public Wode_adapter (Context context){
        this.mContext=context;
    }

    public void setUserModel(UserInfroResp.UserModel userModel) {
        this.userModel = userModel;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        if (position == 5) {
            return 3;
        }else {
            return 2;
        }

    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        int type = getItemViewType(i);

        if (type == 1){
            view = inflater.inflate(R.layout.wode_touxiang_layout, viewGroup, false);
            TextView name = view.findViewById(R.id.id_wode_name_tv);
            if (userModel.getTel().isEmpty()){
                name.setText("未登录");
            }else {
                name.setText(userModel.getTel());
            }
            return view;
        }
        if (type == 2){
            view = inflater.inflate(R.layout.wo_menu_item_layout, viewGroup, false);
            switch (i){
                case 1:
                {
                    ImageView imageView = view.findViewById(R.id.id_wo_menu_img);
                    imageView.setImageResource(R.drawable.backpack);
                    TextView textView = view.findViewById(R.id.id_wo_menu_tv);
                    textView.setText("我的信息");
                    break;
                }

                case 2:
                {
                    ImageView imageView = view.findViewById(R.id.id_wo_menu_img);
                    imageView.setImageResource(R.drawable.backpack);
                    TextView textView = view.findViewById(R.id.id_wo_menu_tv);
                    textView.setText("账单");
                    break;
                }
                case 3:
                {
                    ImageView imageView = view.findViewById(R.id.id_wo_menu_img);
                    imageView.setImageResource(R.drawable.password_icon);
                    TextView textView = view.findViewById(R.id.id_wo_menu_tv);
                    textView.setText("借款记录");
                    break;
                }
                case 4:
                {
                    ImageView imageView = view.findViewById(R.id.id_wo_menu_img);
                    imageView.setImageResource(R.drawable.password_icon);
                    TextView textView = view.findViewById(R.id.id_wo_menu_tv);
                    textView.setText("更改密码");
                    break;
                }
            }
            return view;
        }else {
            view = inflater.inflate(R.layout.wode_tuichu_item_layout, viewGroup, false);
            TextView login = view.findViewById(R.id.id_wo_login_tv);
            if (userModel.getTel().isEmpty()){
                login.setText("登录");
            }else {
                login.setText("退出登录");
            }
            return view;
        }
    }
}
