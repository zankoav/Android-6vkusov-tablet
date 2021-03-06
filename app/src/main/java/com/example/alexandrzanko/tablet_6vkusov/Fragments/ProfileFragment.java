package com.example.alexandrzanko.tablet_6vkusov.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.alexandrzanko.tablet_6vkusov.Adapters.PromoAdapter;
import com.example.alexandrzanko.tablet_6vkusov.Models.Promo;
import com.example.alexandrzanko.tablet_6vkusov.R;
import com.example.alexandrzanko.tablet_6vkusov.Utilites.JsonLoader.LoadJson;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alexandrzanko on 5/2/17.
 */

public class ProfileFragment extends Fragment implements LoadJson {

    private String slug;
    private PromoAdapter adapter;
    private ArrayList<Promo> promos;

    private final String TAG = this.getClass().getSimpleName();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_comments_layout, container, false);
        return rootView;

    }

    static ListView listView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void loadComplete(JSONObject obj, String sessionName) {

    }
}
