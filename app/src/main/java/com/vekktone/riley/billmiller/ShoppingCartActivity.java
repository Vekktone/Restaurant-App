package com.vekktone.riley.billmiller;

import java.text.DecimalFormat;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShoppingCartActivity extends Activity {


    private List<Product> mCartList;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);


        mCartList = ShoppingCartHelper.getCartList();

        // Make sure to clear the selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }

        // Create the list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        Button removeButton = (Button) findViewById(R.id.ButtonRemoveFromCart);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Loop through and remove all the products that are selected
                // Loop backwards so that the remove works correctly
                for(int i=mCartList.size()-1; i>=0; i--) {

                    ShoppingCartHelper.setQuantity(mCartList.get(i), 0);
                    mCartList.remove(i);
                    onResume();
                }
                mProductAdapter.notifyDataSetChanged();
            }
        });


        Button proceed = (Button) findViewById(R.id.Proceed);
        final Intent emailActivity = new Intent(this, EmailActivity.class);
        emailActivity.putExtra("mStringArray", myStringArray);
        emailActivity.putExtra("mIntArray", myIntArray);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                startActivity(emailActivity);
            }
        });

    }
    double[] myIntArray = new double[1000];
    String[] myStringArray = new String[1000];

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if(mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }

        double subTotal = 0;
        for(Product p : mCartList) {
            int quantity = ShoppingCartHelper.getProductQuantity(p);
            subTotal += p.price * quantity;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
        productPriceTextView.setText("Subtotal: $" + df.format(subTotal));



        for (int i=0;i<mCartList.size();i++){
            myIntArray[i] = mCartList.get(i).price;
            myStringArray[i] = mCartList.get(i).title;

            //System.out.println(myStringArray[i]);
            //System.out.println(df.format(myIntArray[i]));
        }
    }

}
