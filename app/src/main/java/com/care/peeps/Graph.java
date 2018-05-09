package com.care.peeps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {



    float values[] = {3,4,5};
    String mention[] = {"Health","Mood","Apetite"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        //toolbar.setTitle(R.string.app_name);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        
        setupPieChart();
    }

    private void setupPieChart() {
        List<PieEntry> Pieentries = new ArrayList<>();
        for(int i = 0; i<values.length;i++){
        Pieentries.add(new PieEntry(values[i],mention[i]));

            PieDataSet DataSet = new PieDataSet(Pieentries,"Routine Check");
            DataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            PieData data = new PieData(DataSet);

            PieChart chart = (PieChart) findViewById(R.id.piechart);

            chart.setData(data);
            chart.animateY(1000);
            chart.invalidate();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.graph_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
