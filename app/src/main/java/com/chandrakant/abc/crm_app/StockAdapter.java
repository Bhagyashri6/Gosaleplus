package com.chandrakant.abc.crm_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 12/29/2016.
 */

public class StockAdapter extends BaseAdapter
{

    Context context;
    List<StockList> stockList;
    List<StockList> orig;

    public StockAdapter(Context context, List<StockList> stockList) {
        this.context = context;
        this.stockList = stockList;
    }

    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int position) {
        return  stockList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View v=View.inflate(context,R.layout.stock_list,null);

        TextView itemName=(TextView)v.findViewById(R.id.itemnameStock);
        EditText editStock =(EditText) v.findViewById(R.id.edit_queryStock);

            itemName.setText(stockList.get(position).getItemName());

        return v;
    }
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<StockList> results = new ArrayList<>();
                if (orig == null)
                    orig = stockList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final StockList g : orig) {
                            if (g.getItemName().toLowerCase()
                                    .contains(constraint.toString()) || g.getItemName().toLowerCase().contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                stockList = (ArrayList<StockList>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
