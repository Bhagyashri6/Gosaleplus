package com.chandrakant.abc.crm_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Stock_Collection extends AppCompatActivity {

    ListView listView;
    SearchView searchView;
    List<StockList> stockList;

    TextView itemName;
    EditText editqty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__collection);

        itemName = (TextView) findViewById(R.id.tvstockAmount);
        listView = (ListView) findViewById(R.id.stock_list);
        searchView = (SearchView) findViewById(R.id.search1);
        stockList = new ArrayList<>();

        stockList.add(new StockList("fiber", "1"));
        stockList.add(new StockList("metal", "2"));
        stockList.add(new StockList("coper", "3s"));

        final StockAdapter adapter = new StockAdapter(getApplicationContext(), stockList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    listView.clearTextFilter();
                } else {
                    adapter.getFilter().filter(s);
                }

                return true;
            }
        });

    }

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
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
