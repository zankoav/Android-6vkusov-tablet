package com.example.alexandrzanko.tablet_6vkusov;

import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexandrzanko.tablet_6vkusov.Utilites.JsonLoader.JsonHelperLoad;
import com.example.alexandrzanko.tablet_6vkusov.Utilites.JsonLoader.LoadJson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity implements LoadJson{

    private final String TAG = this.getClass().getSimpleName();
    private EditText email,password, confirmPassword, firstName, promoCode;
    private CheckBox checkBoxLicense, checkBoxNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        addToolBarToScreen();
        initFields();

    }

    @Override
    public void loadComplete(JSONObject obj, String sessionName) {

    }

    public void registerPressed(View view) {

        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        String confirmPassword = this.confirmPassword.getText().toString().trim();
        String firstName = this.firstName.getText().toString().trim();
        String promoCode = this.promoCode.getText().toString().trim();
        int news = this.checkBoxNews.isChecked() ? 1 :2;
        int license = this.checkBoxLicense.isChecked() ? 1 :2;

    }

    private void sendHashToTheServer(String email, String password, String firstName, String promoCode, int news){
        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("password", password);
            params.put("name", firstName);
            if(promoCode != null){
                params.put("promo", promoCode);
            }
            params.put("news", news);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),this.getResources().getString(R.string.error_server), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        (new JsonHelperLoad(this.getResources().getString(R.string.api_registration),params,this, TAG)).execute();
    }

    private void addToolBarToScreen() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.reg_title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initFields(){
        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        confirmPassword = (EditText)findViewById(R.id.et_confirm_password);
        firstName = (EditText)findViewById(R.id.et_first_name);
        promoCode = (EditText)findViewById(R.id.et_promo);
        checkBoxLicense = (CheckBox)findViewById(R.id.check_box_license);
        checkBoxNews = (CheckBox)findViewById(R.id.check_box_news);
    }


}
