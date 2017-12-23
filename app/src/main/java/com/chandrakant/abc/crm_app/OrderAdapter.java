package com.chandrakant.abc.crm_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.chandrakant.abc.crm_app.OrderActivity.hm;
import static com.chandrakant.abc.crm_app.OrderActivity.placeorder;
import static com.chandrakant.abc.crm_app.OrderActivity.setAmount;
import static com.chandrakant.abc.crm_app.OrderActivity.totalamountorder;

/**
 * Created by ABC on 01/17/2017.
 */


public class OrderAdapter extends BaseAdapter {

    Context mContext;
    int dr = 200;
    int dd;
    ArrayList<OrderDemo> orderDemos = new ArrayList<>();
    int r;

    int ratee;

    public OrderAdapter(Context mContext, ArrayList<OrderDemo> orderDemos) {
        this.mContext = mContext;
        this.orderDemos = orderDemos;
    }

    @Override
    public int getCount() {
        return orderDemos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.list_for_order, null);

        TextView pname = (TextView) v.findViewById(R.id.pname);
        TextView discription = (TextView) v.findViewById(R.id.discription);
        TextView specification = (TextView) v.findViewById(R.id.specification);
        TextView rate = (TextView) v.findViewById(R.id.rate);
        final TextView amount = (TextView) v.findViewById(R.id.amount);
        final EditText qty = (EditText) v.findViewById(R.id.quantity);
        ImageView imageorder = (ImageView) v.findViewById(R.id.imageorder);


        pname.setText(orderDemos.get(position).getProductname());
        discription.setText(orderDemos.get(position).getPdiscription());
        specification.setText(orderDemos.get(position).getSpecification());
        rate.setText(orderDemos.get(position).getRate());
        ratee = Integer.parseInt(rate.getText().toString());
        if (orderDemos.get(position).getImage() != null) {
            imageorder.setImageBitmap(orderDemos.get(position).getImage());
//       amount.setText(orderDemos.get(position).getAmount());
        }

        qty.setText(hm.get(orderDemos.get(position).getProductname() + position).toString());
        amount.setText(hm.get(orderDemos.get(position).getRate() + position).toString());


        qty.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (qty.length() > 0) {
                    int val = 0;


                    if (charSequence.length() > 0) {
                        ratee = Integer.parseInt(orderDemos.get(position).getRate());
                        val = Integer.parseInt(charSequence.toString());
                        r = ratee * val;


                        amount.setText("" + r);

                        hm.put(orderDemos.get(position).getProductname() + position, val);
                        hm.put(orderDemos.get(position).getRate() + position, r);

                        int q = Integer.parseInt(qty.getText().toString());
                        setAmount(position, r, q);

                    } else {

                        totalamountorder.setText("0");
                        amount.setText("00.00");
                        setAmount(position, 0, 0);

                    }


                } else {
                    hm.put(orderDemos.get(position).getProductname() + position, 0);
                    hm.put(orderDemos.get(position).getRate() + position, 0);
                    totalamountorder.setText("0");
                    amount.setText("00.00");
                    setAmount(position, 0, 0);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            ArrayList<OrderGetSet> orderGetSets = new ArrayList<OrderGetSet>();

            @Override
            public void onClick(View view) {
                for (int i = 0; i <= orderDemos.size() - 1; i++) {
                    int f = Integer.parseInt(hm.get(orderDemos.get(i).getProductname() + i).toString());

                    if (f > 0) {
                        String pname = orderDemos.get(i).getProductname();
                        String desc = orderDemos.get(i).getPdiscription();
                        String spec = orderDemos.get(i).getSpecification();
                        String prate = orderDemos.get(i).getRate();
                        String am = hm.get(orderDemos.get(i).getRate() + i).toString();
                        String qty = String.valueOf(hm.get(orderDemos.get(i).getProductname() + i).toString());
                        Bitmap img = orderDemos.get(i).getImage();

                        OrderGet.setList(pname, desc, spec, prate, qty, am, img);


                    }
                    view.getContext().startActivity(new Intent(view.getContext(), Confirm_Order.class));
                }
            }
        });

        return v;
    }
}
