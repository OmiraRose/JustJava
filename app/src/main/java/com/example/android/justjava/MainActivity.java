package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean hasWhippedCream;
    boolean hasChocolate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Invalid Quantity";
        int duration = Toast.LENGTH_SHORT;
        quantity++;
        if(quantity >101){
            Toast.makeText(context,text,duration).show();
        } else {
            display(quantity);
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Invalid Quantity";
        int duration = Toast.LENGTH_SHORT;
        quantity--;
        if(quantity <= 0){
            Toast.makeText(context,text,duration).show();
        }else {
            display(quantity);
        }

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whippedCream);
        hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox =  findViewById(R.id.chocolate);
        hasChocolate = chocolateCheckBox.isChecked();

        EditText name = findViewById(R.id.cxName);
        String customerName = name.getText().toString();
        int price = calculatePrice();
        String priceMessage = createOrderSummary(customerName,hasWhippedCream, hasChocolate,price);
        String subject = "Just Java Order for "+customerName;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: omirarose@hotmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView summaryTextView = findViewById(R.id.order_summary_text_view);
        summaryTextView.setText(message);
    }
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price =   quantity * 5;
        if (hasWhippedCream&&hasChocolate){
            price = (8)*quantity;
            return price;
        } else if (hasWhippedCream){
            price = (quantity) + (quantity * 5);
            return price;
        } else if (hasChocolate){
            price = (quantity*2) +(quantity*5);
            return price;
        } else {
            return price;
        }

    }
    private String createOrderSummary(String customerName, boolean hasWhippedCream, boolean hasChocolate,int price){
        if (hasChocolate&&hasWhippedCream){
            String summaryMessage = getString(R.string.javaName,customerName)+"\n";
            summaryMessage += getString(R.string.javaToppings)+""+getString(R.string.whipped_cream)+"&"+""+getString(R.string.chocolate)+"\n";
            summaryMessage += getString(R.string.javaQuantity)+""+ quantity+"\n";
            summaryMessage += getString(R.string.javaPrice)+ price+"\n";
            summaryMessage += getString(R.string.thankYou);
            return summaryMessage;
        }
        else if(hasWhippedCream) {
            String summaryMessage = getString(R.string.javaName,customerName)+"\n";
            summaryMessage += getString(R.string.javaToppings)+""+ getString(R.string.whipped_cream)+"\n";
            summaryMessage += getString(R.string.javaQuantity)+""+ quantity+"\n";
            summaryMessage += getString(R.string.javaPrice)+ price+"\n";
            summaryMessage += getString(R.string.thankYou);
            return summaryMessage;
        } else if (hasChocolate){
            String summaryMessage = getString(R.string.javaName,customerName)+"\n";
            summaryMessage += getString(R.string.javaToppings)+""+getString(R.string.chocolate)+"\n";
            summaryMessage += getString(R.string.javaQuantity)+""+ quantity+"\n";
            summaryMessage += getString(R.string.javaPrice)+ price+"\n";
            summaryMessage += getString(R.string.thankYou);
            return summaryMessage;
        } else {
            String summaryMessage = getString(R.string.javaName,customerName)+"\n";
            summaryMessage += getString(R.string.javaToppings)+""+getString(R.string.none)+"\n";
            summaryMessage += getString(R.string.javaQuantity)+""+ quantity+"\n";
            summaryMessage += getString(R.string.javaPrice) + price+"\n";
            summaryMessage += getString(R.string.thankYou);
            return summaryMessage;
        }
    }

}

