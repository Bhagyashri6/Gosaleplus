package com.chandrakant.abc.crm_app;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PieFragmentOne extends Fragment {
    private static String TAG="MainActivity3";

    private float[] yData={25.3f,10.6f,44.32f,46.01f,16.89f,23.9f};
    private String[] xData={"Mitch","jessica","Mane","Deepak","Chandrakant","Bhushan"};

    PieChart pieChart;


    public PieFragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pie_one, container, false);

        pieChart = (PieChart) v.findViewById(R.id.piechart);

        pieChart.setContentDescription("Salary in Thousands $");

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Super cool Text");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);


        addDataSet();


        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {


                Log.d(TAG, "OnVAlueSElected" + e.toString());
                Log.d(TAG, "OnVAlueSElected" + h.toString());

                int pos1 = e.toString().indexOf("y:");

                String sales = e.toString().substring(pos1);
                String sale = sales.substring(3);
                for (int i = 0; i < yData.length; i++) {
                    if (yData[i] == Float.parseFloat(sale)) {
                        pos1 = i;
                        break;
                    }
                }
                String employee = xData[pos1];
                Toast.makeText(container.getContext(), "Employee :" + employee + "\n" + "Sales :" + sales, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        return v;
    }



    private void addDataSet() {

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i=0;i<yData.length;i++)
        {
            yEntrys.add(new PieEntry(yData[i],i));
        }


        for(int i=0;i<xData.length;i++)
        {
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys,"Sales");

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);


        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);


        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }

}
