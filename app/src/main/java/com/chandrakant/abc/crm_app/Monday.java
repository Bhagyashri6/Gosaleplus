package com.chandrakant.abc.crm_app;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Monday extends Fragment {

    public Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LayoutManager layoutManager;



    public Monday() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_monday, container, false);
        // Inflate the layout for this fragment

       /* listD=  new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

       String day = sdf.format(date);
*/
       /* for(int i = 0; i< ListModel.getList().size(); i++) {
            String dae=ListModel.getList().get(i).getDate();
            if (day.contentEquals(ListModel.getList().get(i).getDate())) {
              String Name=ListModel.getList().get(i).getDistributorname();
                String address=ListModel.getList().get(i).getAddress();

                ListModel.setList1(R.drawable.check,Name,address,"1234567890","");

            }
        }*/

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_viewtab1);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter= new AddDataAdapter(getContext(),ListModel.getList1());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

       return v;




    }

}
