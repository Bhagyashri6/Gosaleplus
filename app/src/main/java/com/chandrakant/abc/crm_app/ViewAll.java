package com.chandrakant.abc.crm_app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class ViewAll extends AppCompatActivity {

    private List<AddData> mListDemo;
    private List<AddData> orig;

    public Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    LinearLayout sort,sort1,viewtext,filter,sorting,ascdsc,all;

    CheckBox datecheck,namecheck,addresscheck;
    TextView currentdate;

    EditText searchtext;

        RelativeLayout r;
    RadioButton bydate,bycompany,bycity,asc,dsc;
Date dt = new Date();
    int dialog_id=0;
    int  year_x, month_x, day_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);


        sort=(LinearLayout)findViewById(R.id.sort);
        sort1=(LinearLayout)findViewById(R.id.sort1);
        filter=(LinearLayout)findViewById(R.id.filter);
        viewtext=(LinearLayout)findViewById(R.id.viewtext);
        sorting=(LinearLayout)findViewById(R.id.sorting);
        ascdsc=(LinearLayout)findViewById(R.id.ascdsc);
        all=(LinearLayout)findViewById(R.id.all);

        datecheck=(CheckBox)findViewById(R.id.datecheck);
        namecheck=(CheckBox)findViewById(R.id.namecheck);
        addresscheck=(CheckBox)findViewById(R.id.addresscheck);
        currentdate=(TextView)findViewById(R.id.currentdate);
        searchtext=(EditText) findViewById(R.id.searchtext);



        bydate=(RadioButton) findViewById(R.id.bydate);
        bycompany=(RadioButton) findViewById(R.id.bycompany);
        bycity=(RadioButton) findViewById(R.id.bycity);
        asc=(RadioButton) findViewById(R.id.asc);
        dsc=(RadioButton) findViewById(R.id.dsc);









        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewtab1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new AddDataAdapter(getApplicationContext(),ListModel.list);


        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

/*sort.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_BUTTON_PRESS:
                sort1.setVisibility(View.VISIBLE);
                return true;

            case MotionEvent.ACTION_BUTTON_RELEASE:
                sort1.setVisibility(View.GONE);
                return true;

        }
        return false;
    }
});*/
        sort.setOnClickListener(new View.OnClickListener() {
            int flag=0;

            @Override
            public void onClick(View view) {

                    if(flag==0)
                    {
                        all.setVisibility(View.VISIBLE);
                        sort1.setVisibility(View.GONE);
                        sorting.setVisibility(View.VISIBLE);

                        bydate.setOnClickListener(new View.OnClickListener() {
                            int flag=0;
                            @Override
                            public void onClick(View view) {
                                if(flag==0)
                                {

//                                    ascdsc.setVisibility(View.VISIBLE);


                                    flag=1;
                                }
                                else
                                {
//                                    ascdsc.setVisibility(View.GONE);

                                    flag=0;
                                }
                            }
                        });

                        bycompany.setOnClickListener(new View.OnClickListener() {
                            int flag=0;
                            @Override
                            public void onClick(View view) {
                                if(flag==0)
                                {

//                                    ascdsc.setVisibility(View.VISIBLE);
                                    sortcomapny("asc");
                                    flag=1;
                                }
                                else
                                {
//                                    ascdsc.setVisibility(View.GONE);
                                    sortcomapny("desc");
                                    flag=0;
                                }
                            }
                        });
                        bycity.setOnClickListener(new View.OnClickListener() {
                            int flag=0;
                            @Override
                            public void onClick(View view) {
                                if(flag==0)
                                {

//                                    ascdsc.setVisibility(View.VISIBLE);
                                    sortcity("asc");
                                    flag=1;
                                }
                                else
                                {
//                                    ascdsc.setVisibility(View.GONE);
                                    sortcity("desc");
                                    flag=0;
                                }
                            }
                        });

                        flag=1;
                    }
               else
                {
                    sorting.setVisibility(View.GONE);
                    bydate.setChecked(false);
                    bycompany.setChecked(false);
                    bycity.setChecked(false);
                    ascdsc.setVisibility(View.GONE);
                    all.setVisibility(View.GONE);
                    flag=0;

                }
            }
        });

    filter.setOnClickListener(new View.OnClickListener() {
        int flag=0;
        @Override
        public void onClick(View view) {
            if(flag==0)
            {
                all.setVisibility(View.VISIBLE);
                sorting.setVisibility(View.GONE);
                sort1.setVisibility(View.VISIBLE);

                flag=1;
            }
            else
            {
                sort1.setVisibility(View.GONE);

                datecheck.setChecked(false);
                namecheck.setChecked(false);
                addresscheck.setChecked(false);

                flag=0;
            }

        }
    });


        datecheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            boolean flag=true;

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



                if(flag==true)
                {
                                         datecheck.setChecked(true);
                    currentdate.setVisibility(View.VISIBLE);
                    currentdate.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            flag=true;
                            showDialog(dialog_id);

                            return true;

                        }

                    });
                    flag=false;
                }
                else {
                    datecheck.setChecked(false);
                    currentdate.setVisibility(View.GONE);
                    all.setVisibility(View.GONE);
                    flag=true;
                }
            }
        });


        namecheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(namecheck.isChecked())
                {
                    viewtext.setVisibility(View.VISIBLE);


                searchtext.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        ListModel.getListsearch().clear();
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {




                        charSequence=charSequence.toString().toLowerCase();
                        ListModel.getListsearch().clear();
                for(int p=0;p<ListModel.getList().size();p++)
                {
                    String text=    ListModel.getList().get(p).getDistributorname().toLowerCase();
                    if(text.contains(charSequence))
                    {
                        String Name = ListModel.getList().get(p).getDistributorname();
                        String address = ListModel.getList().get(p).getAddress();
                        ListModel.setListsearch(R.drawable.check, Name, address, "1234567890", "");

                    }
                }
                        adapter = new AddDataAdapter(getApplicationContext(),ListModel.listsearch);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                }
                else {
                    viewtext.setVisibility(View.GONE);
                    searchtext.setText("");
                }
            }
        });

        addresscheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(addresscheck.isChecked())
                {
                    viewtext.setVisibility(View.VISIBLE);


                    searchtext.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            ListModel.getListsearch().clear();
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            charSequence=charSequence.toString().toLowerCase();
                            ListModel.getListsearch().clear();
                            for(int p=0;p<ListModel.getList().size();p++)
                            {
                                String text=    ListModel.getList().get(p).getAddress().toLowerCase();
                                if(text.contains(charSequence))
                                {
                                    String Name = ListModel.getList().get(p).getDistributorname();
                                    String address = ListModel.getList().get(p).getAddress();
                                    String contact= ListModel.getList().get(p).getContact();
                                    ListModel.setListsearch(R.drawable.check, Name, address,contact, "");

                                }
                            }
                            adapter = new AddDataAdapter(getApplicationContext(),ListModel.listsearch);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });





                }
                else {
                    viewtext.setVisibility(View.GONE);
                    searchtext.setText("");
                }
            }
        });



Calendar calendar = Calendar.getInstance();

        year_x=calendar.get(calendar.YEAR);
        month_x=calendar.get(calendar.MONTH);
        day_x=calendar.get(calendar.DAY_OF_MONTH);


    }


    public void showDialogOfDate(){

        /*currentdate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                showDialog(dialog_id);
                return true;
            }
        });*/


    }

    protected Dialog onCreateDialog(int id){
        if (id==dialog_id) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(ViewAll.this, dtPickerClickListner, year_x, month_x, day_x);
//            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return datePickerDialog;
        }

       return null;
    }
    private DatePickerDialog.OnDateSetListener dtPickerClickListner
            = new DatePickerDialog.OnDateSetListener(){

        int flag = 0;
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy");
            String currentDateTimeString = sdf.format(dt);


            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;

            String d = day_x + ".0" + month_x + "." + year_x;
            ;
           /* if(month_x<=9){
                d=day_x+ ".0"+ month_x + "."+year_x;
            }else {
                d = day_x + "." + month_x + "." + year_x;
            }*/
            currentdate.setText(d);

            currentdate.setVisibility(View.VISIBLE);
            String dateadd = currentdate.getText().toString();
            ListModel.getListsearch().clear();


            if (flag == 0) {

                for (int i = 0; i < ListModel.list.size(); i++) {
                    String addDate = ListModel.getList().get(i).getDate();
                    String Name = ListModel.getList().get(i).getDistributorname();
                    String address = ListModel.getList().get(i).getAddress();

                    if (addDate.equals(dateadd)) {

                        ListModel.setListsearch(R.drawable.check, Name, address, "1234567890", "");
                    }
                }

                adapter = new AddDataAdapter(getApplicationContext(),ListModel.listsearch);
                recyclerView.setAdapter(adapter);
               datecheck.setChecked(true);
                flag=1;
            }
            else {
                datecheck.setChecked(false);
                flag=0;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_outlet,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.home)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
        }


        if (id==R.id.logout)
        {
            maneDialog();


        }
        if(id== android.R.id.home) {
            ListModel.list1.clear();
            ListModel.list2.clear();
            ListModel.list3.clear();
            ListModel.list4.clear();
            ListModel.list5.clear();
            ListModel.list6.clear();
            ListModel.list7.clear();
            ListModel.listback1.clear();
            ListModel.listback2.clear();
            ListModel.listback3.clear();
            ListModel.listback4.clear();
            ListModel.listback5.clear();
            ListModel.listback6.clear();
            ListModel.listback7.clear();
            ListModel.list.clear();
            Intent intent = new Intent(getApplicationContext(), TodaysPJP.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void maneDialog()
    {

        MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                .setTitle("Alert!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setDescription("Do you want to Logout. \n")
                .withIconAnimation(true)
                .setPositiveText("Exit")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Log.d("MaterialStyledDialogs", "Do something!");
                        SharedPreferences loginPref=getSharedPreferences("loginprefs",MODE_PRIVATE);
                        SharedPreferences.Editor loginEditor=loginPref.edit();
                        loginEditor.remove("logged");
                        loginEditor.commit();


                        Intent  intent=new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finish();

                    }}) .setNegativeText("Cancel") .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Log.d("MaterialStyledDialogs", "Do something!");
                        dialog.dismiss();

                    }})
                .show();

    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected Filter.FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<AddData> results = new ArrayList<>();
                if (orig == null)
                    orig = mListDemo;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final AddData g : orig) {
                            if (g.getDate().toLowerCase()
                                    .contains(constraint.toString())|| g.getDate().toLowerCase().contains(constraint.toString()))
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
                mListDemo = (ArrayList<AddData>) results.values;
                adapter.notifyDataSetChanged();
            }
        };





    }

    public void sortcomapny(final String name)
    {
        Comparator<AddData> comp = new Comparator<AddData>() {
            @Override
            public int compare(AddData lhs, AddData rhs) {

                String first = String.valueOf(lhs.getDistributorname().toLowerCase());
                String secound = String.valueOf(rhs.getDistributorname().toLowerCase());


                if (name == "asc") {
                    if (first == secound) {
                        return 0;
                    }
                    if (first == null) {
                        return -1;
                    }
                    if (secound == null) {
                        return 1;
                    }

                }
                return first.compareTo(secound);
            }


        };
        Collections.sort(ListModel.list,comp);
        adapter = new AddDataAdapter(getApplicationContext(),ListModel.list);
        adapter.notifyDataSetChanged();
    }



    public void sortcity(final String name)
    {
        Comparator<AddData> comp = new Comparator<AddData>() {
            @Override
            public int compare(AddData lhs, AddData rhs) {

                String first = String.valueOf(lhs.getAddress().toLowerCase());
                String secound = String.valueOf(rhs.getAddress().toLowerCase());


                if (name == "asc") {
                    if (first == secound) {
                        return 0;
                    }
                    if (first == null) {
                        return -1;
                    }
                    if (secound == null) {
                        return 1;
                    }

                }
                return first.compareTo(secound);
            }


        };
        Collections.sort(ListModel.list,comp);
        adapter = new AddDataAdapter(getApplicationContext(),ListModel.list);
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {

    }
}
