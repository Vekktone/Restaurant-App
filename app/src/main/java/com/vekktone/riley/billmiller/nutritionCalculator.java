package com.vekktone.riley.billmiller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class nutritionCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //Options Menu Code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.select_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        final Intent foodMenu = new Intent(this, DisplayFoodActivity.class);
        final Intent tDayMenu = new Intent(this, tDayMenu.class);
        final Intent nutritionCalculator = new Intent(this, nutritionCalculator.class);
        final Intent storeLoc = new Intent(this, storeLoc.class);
        if (id == R.id.reg_menu) {

            //do something
            startActivity(foodMenu);
            return true;
        }
        if (id == R.id.tDay_Menu) {

            //do something
            startActivity(tDayMenu);
            return true;
        }
        if (id == R.id.nutrition) {

            //do something
            startActivity(nutritionCalculator);
            return true;
        }
        if (id == R.id.store_locator) {

            //do something
            startActivity(storeLoc);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
