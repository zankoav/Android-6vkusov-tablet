package com.example.alexandrzanko.tablet_6vkusov.Users;

import android.util.Log;

import com.example.alexandrzanko.tablet_6vkusov.LocalStorage;
import com.example.alexandrzanko.tablet_6vkusov.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alexandrzanko on 3/1/17.
 */

public class Register implements UserInterface {

    private final String TAG = this.getClass().getSimpleName();

    private Basket basket;

    public Register(){
        this.basket = new Basket();
    }

    @Override
    public STATUS getStatus() {
        return STATUS.REGISTER;
    }

    @Override
    public JSONObject getProfile() {
        LocalStorage store = Singleton.currentState().getStore();
        JSONObject profile = null;
        try {
            profile = new JSONObject(store.getStringValueStorage(store.APP_PROFILE));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "load profile from local storage error");
        }
        return profile;
    }

    @Override
    public Basket getBasket() {
        return basket;
    }
}
