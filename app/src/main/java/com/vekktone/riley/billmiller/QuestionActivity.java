package com.vekktone.riley.billmiller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class QuestionActivity extends AppCompatActivity {

    private Button mYesButton;
    private Button mNoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mYesButton = (Button) findViewById(R.id.yes_button);
        final Intent tDayMenu = new Intent(this, tDayMenu.class);
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(tDayMenu);
            }
        });

        final Intent normMenu = new Intent(this, DisplayFoodActivity.class);
        mNoButton = (Button) findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(normMenu);
            }
        });
    }

    public void showYesAlert(View view) {
        final Intent tDayMenu = new Intent(this, tDayMenu.class);
        startActivity(tDayMenu);
    }

    public void showNoAlert(View view) {
        final Intent normMenu = new Intent(this, DisplayFoodActivity.class);
        startActivity(normMenu);

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
        final Intent storeLoc = new Intent(this, MapsActivity.class);
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
    /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    } */
