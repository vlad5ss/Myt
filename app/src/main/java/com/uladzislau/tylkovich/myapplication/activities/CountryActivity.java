package com.uladzislau.tylkovich.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.uladzislau.tylkovich.myapplication.R;
import com.uladzislau.tylkovich.myapplication.database.DatabaseHelperMethods;
import com.uladzislau.tylkovich.myapplication.utils.ToastShowing;


public final class CountryActivity extends AppCompatActivity {
    private EditText editText;
    private DatabaseHelperMethods databaseHelperMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        databaseHelperMethods = new DatabaseHelperMethods(CountryActivity.this);
        editText = findViewById(R.id.input_country_edit_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.car_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_confirm_new_car:
                String nameCountry = editText.getText().toString();
                if (!nameCountry.equals("")) {
                    databaseHelperMethods.insertCountry(nameCountry);
                    Intent intent = new Intent();
                    intent.putExtra("ID_COUNTRY", databaseHelperMethods.getIdCountryFromName(nameCountry));
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastShowing.postToastMessage(CountryActivity.this, "Please, input country");
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
