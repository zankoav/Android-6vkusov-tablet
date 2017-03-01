package com.example.alexandrzanko.tablet_6vkusov.Users;

import org.json.JSONObject;

/**
 * Created by alexandrzanko on 3/1/17.
 */

public class Register implements UserInterface {

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
        return null;
    }

    @Override
    public Basket getBasket() {
        return basket;
    }
}
