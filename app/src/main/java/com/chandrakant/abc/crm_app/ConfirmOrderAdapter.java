package com.chandrakant.abc.crm_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ABC on 02/21/2017.
 */

public class ConfirmOrderAdapter extends BaseAdapter {
   private Context mContext;
ArrayList<OrderGetSet> orderGetSets = new ArrayList<>();

    public ConfirmOrderAdapter(Context mContext, ArrayList<OrderGetSet> orderGetSets) {
        this.mContext = mContext;
        this.orderGetSets = orderGetSets;
    }

    @Override
    public int getCount() {
        return orderGetSets.size();
    }

    @Override
    public Object getItem(int position) {
        return  orderGetSets.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext,R.layout.order_confirm_layout,null);
        TextView pname1= (TextView)v.findViewById(R.id.pname1);
        TextView discription1= (TextView)v.findViewById(R.id.discription1);
        TextView specification1= (TextView)v.findViewById(R.id.specification1);
        TextView rate1= (TextView)v.findViewById(R.id.rate1);
        final TextView amount1=(TextView)v.findViewById(R.id.amount1);
        final TextView qty1 = (TextView) v.findViewById(R.id.quantity1);
        final ImageView imageorder1 = (ImageView) v.findViewById(R.id.imageorder1);




        pname1.setText( orderGetSets.get(position).getProductname());
        discription1.setText(orderGetSets.get(position).getPdiscription());
        specification1.setText(orderGetSets.get(position).getSpecification());
        rate1.setText(orderGetSets.get(position).getRate());
        amount1.setText(orderGetSets.get(position).getAmount());
        qty1.setText(orderGetSets.get(position).getQty());
        if(orderGetSets.get(position).getImg()!=null) {
            imageorder1.setImageBitmap(orderGetSets.get(position).getImg());
//       amount.setText(orderDemos.get(position).getAmount());
        }

        return v;
    }
}
