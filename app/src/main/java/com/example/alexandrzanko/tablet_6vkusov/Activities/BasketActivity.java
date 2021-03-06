package com.example.alexandrzanko.tablet_6vkusov.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexandrzanko.tablet_6vkusov.Adapters.BasketAdapter;
import com.example.alexandrzanko.tablet_6vkusov.R;
import com.example.alexandrzanko.tablet_6vkusov.Singleton;
import com.example.alexandrzanko.tablet_6vkusov.Users.Basket;

public class BasketActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private ListView lv;

    public BasketAdapter getAdapter() {
        return adapter;
    }
    private BasketAdapter adapter;

    private Button btnSender;
    private TextView tvPrice, tvDeliveryPrice, tvTotalPrice, tvPoints ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        addToolBarToScreen();
        lv = (ListView)findViewById(R.id.listView_basket);
        adapter = new BasketAdapter(this, Singleton.currentState().getUser().getBasket().getProductItems());
        lv.setAdapter(adapter);
        initViews();

    }

    private void initViews() {
        btnSender = (Button)findViewById(R.id.button);
        tvPrice = (TextView)findViewById(R.id.checkPriceR);
        tvDeliveryPrice = (TextView)findViewById(R.id.checkDeliveryPriceRub);
        tvTotalPrice= (TextView)findViewById(R.id.checkTotalPriceR);
        tvPoints= (TextView)findViewById(R.id.checkTotalPointsR);
        checkOutUpdateView();
    }

    private void addToolBarToScreen() {
        toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
        toolbar.setTitle(R.string.basket);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }

        );
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void orderButtonPressed(View view) {
        Intent intent = new Intent(BasketActivity.this, CheckOutActivity.class);
        startActivity(intent);
    }

    public void checkOutUpdateView(){
        Basket basket = Singleton.currentState().getUser().getBasket();
        double price = basket.getPrice();
        double deliveryPrice = basket.getDeliveryPrice();
        double totalPrice = price - deliveryPrice;
        int points = basket.getPoints();
        double minimumPrice = basket.getMinimumPrice();

        tvPrice.setText(round(price,2) + "");
        tvDeliveryPrice.setText((deliveryPrice > 0 ? round(deliveryPrice,2) : "бесплатно") + "");
        tvTotalPrice.setText(round(totalPrice,2) + "");
        tvPoints.setText(points + "");

        if (minimumPrice > totalPrice){
            btnSender.setEnabled(false);
            btnSender.setBackgroundResource(R.drawable.shape_corner);
            btnSender.setText("Минимальная стоимость заказа " + round(minimumPrice,2) + " руб.");
        }else{
            btnSender.setEnabled(true);
            btnSender.setBackgroundResource(R.drawable.shape_corner_green);
            btnSender.setText("Оформить заказ");
        }
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
