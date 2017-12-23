package com.chandrakant.abc.crm_app;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Saturday extends Fragment {

    public Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    AddData addv= new AddData();
    ArrayList<ListModel> liste = new ArrayList<>();
    ListModel list;
    public Saturday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_saturday, container, false);

/*
        listD=  new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar currentCal1 = Calendar.getInstance();
        currentCal1.add(Calendar.DATE, 5);
        String tomorrow = dateFormat.format(currentCal1.getTime());

        for (int i = 0; i < ListModel.getList().size(); i++) {
            String dae = ListModel.getList().get(i).getDate();
            if (tomorrow.contentEquals(ListModel.getList().get(i).getDate())) {
                String Name = ListModel.getList().get(i).getDistributorname();
                String address = ListModel.getList().get(i).getAddress();

                ListModel.setList6(R.drawable.check, Name, address, "1234567890", "");

            }
        }*/

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_viewtab6);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter= new AddDataAdapter(getContext(),ListModel.getList6());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }

}
