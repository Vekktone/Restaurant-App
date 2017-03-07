package com.vekktone.riley.billmiller;

import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static List<Product> sanAntonio;
    private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<Product, ShoppingCartEntry>();

    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<Product>();
            catalog.add(new Product("Turkey Hen", res.getDrawable(R.drawable.caterimage),
                    "9 - 11 lbs. (raw weight) Turkey Hen (cooked and chilled) with 1/2 gallon Cornbread Dressing (ready to be baked) and 1 quart Giblet Gravy (ready to be heated) ... $42.99", 42.99));
            catalog.add(new Product("Turkey Tom", res.getDrawable(R.drawable.cast_ic_notification_connecting),
                    "17 - 19 lbs. (raw weight) Turkey Tom (cooked and chilled) with 1 gallon Cornbread Dressing (ready to be baked) and 1/2 gallon Giblet Gravy (ready to be heated) ... $74.99", 74.99));
            catalog.add(new Product("Honey Glazed Spiral Cut Ham", res.getDrawable(R.drawable.cast_ic_notification_connecting),
                    "Sold by the half (approximately 7 - 10 lbs.) ... $49.99", 49.99));
            catalog.add(new Product("Small Party Pack- Complete Meal for 10", res.getDrawable(R.drawable.cast_ic_mini_controller_closed_caption),
                    "Turkey Hen, HOT AND CARVED - or - 3.5 lbs. Sliced Turkey Breast (HOT), 2 quarts cornbread dressing, 1 quart giblet gravy, " +
                            "2 quarts green beans OR 2 quarts mashed potatoes, 1 dozen dinner rolls, 1 9-inch fresh baked pie, 1 bucket iced tea, plates, cups and set ups for 10" +
                            "(Only $8.50 per person + tax) ... $85.00", 85.00));
            catalog.add(new Product("Large Party Pack- Complete Meal for 20", res.getDrawable(R.drawable.ic_audiotrack),
                    "Turkey Tom - or - 1/2 Honey-glazed spiral cut ham, HOT AND CARVED - or - 7 lbs. Sliced Turkey Breast (HOT), 4 quarts cornbread dressing, 2 quarts giblet gravy, 4 quarts green beans OR 4 quarts mashed potatoes, 2 dozen dinner rolls, " +
                            "2 9-inch fresh baked pies, 2 buckets iced tea, plates, cups and set ups for 20\n(Only $7.50 per person + tax) ... $150.00", 150.00));
            catalog.add(new Product("Fresh Baked, 9-inch Pies", res.getDrawable(R.drawable.ic_audiotrack),
                    "pie", 9.49));
        }

        return catalog;
    }

    public static List<Product> getSA(Resources res){
        if(sanAntonio == null) {
            sanAntonio = new Vector<Product>();
            sanAntonio.add(new Product("Poorboy", res.getDrawable(R.drawable.caterimage),
                    "yummy...", 7.89));
        }

        return sanAntonio;
    }

    public static void setQuantity(Product product, int quantity) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null) {
                removeProduct(product);
                curEntry.setQuantity(quantity);
            }
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Product product) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        if(curEntry != null) {
            return curEntry.getQuantity();
        }

        return 0;
    }

    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }


}
