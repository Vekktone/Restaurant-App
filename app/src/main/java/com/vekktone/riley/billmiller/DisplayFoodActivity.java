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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class DisplayFoodActivity extends AppCompatActivity {

    private List<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain a reference to the product catalog
        mProductList = ShoppingCartHelper.getSA(getResources());

        // Create the list
        ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        listViewCatalog.setAdapter(new ProductAdapter(mProductList, getLayoutInflater(), false));

        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),SADetailsActivity.class);
                productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

        Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
        viewShoppingCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
                startActivity(viewShoppingCartIntent);
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
