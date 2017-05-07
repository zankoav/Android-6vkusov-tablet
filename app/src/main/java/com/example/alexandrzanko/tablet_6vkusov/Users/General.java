package com.example.alexandrzanko.tablet_6vkusov.Users;

import android.util.Log;

import com.example.alexandrzanko.tablet_6vkusov.LocalStorage;
import com.example.alexandrzanko.tablet_6vkusov.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alexandrzanko on 3/1/17.
 */

public class General implements UserInterface {

    private Basket basket;

    public General(){
        this.basket = new Basket();
    }

    @Override
    public STATUS getStatus() {
        return STATUS.GENERAL;
    }

    @Override
    public JSONObject getProfile() {
        return null;
    }

    @Override
    public Basket getBasket() {
        return basket;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public void setPoints(int points) {

    }
}
