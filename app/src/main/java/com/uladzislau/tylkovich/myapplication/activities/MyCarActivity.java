package com.uladzislau.tylkovich.myapplication.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;



import com.squareup.picasso.Picasso;
import com.uladzislau.tylkovich.myapplication.R;
import com.uladzislau.tylkovich.myapplication.database.DatabaseHelperMethods;
import com.uladzislau.tylkovich.myapplication.model.Car;
import com.uladzislau.tylkovich.myapplication.utils.StringUtils;
import com.uladzislau.tylkovich.myapplication.utils.ToastShowing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class MyCarActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int REQUEST_PHOTO = 1;
    private static final int REQUEST_COUNTRY = 2;
    private EditText titleEditText;
    private EditText markEditText;
    private EditText costEditText;
    private EditText powerEditText;
    private EditText doorsNumberEditText;
    private EditText bodyTypeEditText;
    private EditText seatsNumberEditText;
    private EditText startReleaseEditText;
    private EditText endReleaseEditText;
    private DatabaseHelperMethods databaseHelperMethods;
    private ImageView mPhotoView;
    private Car car = new Car();
    private Car carForUpdating;
    private boolean isSetPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        titleEditText = findViewById(R.id.title_model_edit_text);
        markEditText = findViewById(R.id.mark_model_edit_text);
        costEditText = findViewById(R.id.cost_model_edit_text);
        powerEditText = findViewById(R.id.power_model_edit_text);
        doorsNumberEditText = findViewById(R.id.doors_number_model_edit_text);
        bodyTypeEditText = findViewById(R.id.body_type_model_edit_text);
        seatsNumberEditText = findViewById(R.id.seats_number_model_edit_text);
        startReleaseEditText = findViewById(R.id.start_release_model_edit_text);
        endReleaseEditText = findViewById(R.id.end_release_model_edit_text);

        databaseHelperMethods = new DatabaseHelperMethods(this);
        ImageButton mPhotoButton = findViewById(R.id.crime_camera);

        final Intent captureImage = new Intent();
        captureImage.setType("image/*");
        captureImage.setAction(Intent.ACTION_GET_CONTENT);

        mPhotoView = (ImageView) findViewById(R.id.crime_photo);

        carForUpdating = (Car) getIntent().getParcelableExtra("Car");
        if (carForUpdating != null) {
            titleEditText.setText(carForUpdating.getTitle());
            markEditText.setText(databaseHelperMethods.getNameMarkFromId(carForUpdating.getMark()));
            costEditText.setText(Integer.toString(carForUpdating.getCost()));
            powerEditText.setText(carForUpdating.getPower());
            doorsNumberEditText.setText(carForUpdating.getDoorsNumber());
            bodyTypeEditText.setText(carForUpdating.getBodyType());
            seatsNumberEditText.setText(carForUpdating.getSeatsNumber());
            startReleaseEditText.setText(carForUpdating.getStartRelease());
            endReleaseEditText.setText(carForUpdating.getEndRelease());

            if (StringUtils.foo(carForUpdating.getPhoto(), "jpg")) {
                InputStream is = getClass().getClassLoader().getResourceAsStream(carForUpdating.getPhoto());
                Bitmap bm = BitmapFactory.decodeStream(is);
                mPhotoView.setImageBitmap(bm);
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (!carForUpdating.getPhoto().equals(" ")) {
                Picasso.with(this)
                        .load(carForUpdating.getPhoto())
                        .fit()
                        .into(mPhotoView);
            }
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted()) {
                    startActivityPhotoPickerIntent();
                }
            }
        });
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
                String mark = markEditText.getText().toString();
                if (titleEditText.getText().toString().equals("")) {
                    ToastShowing.postToastMessage(MyCarActivity.this, getString(R.string.input_title));
                } else if (markEditText.getText().toString().equals("")) {
                    ToastShowing.postToastMessage(MyCarActivity.this, getString(R.string.input_mark));
                } else if (costEditText.getText().toString().equals("") || !TextUtils.isDigitsOnly(costEditText.getText().toString())) {
                    ToastShowing.postToastMessage(MyCarActivity.this, getString(R.string.incorrect_input_cost));
                } else {
                    if (!databaseHelperMethods.isMarkExist(markEditText.getText().toString())) {
                        Intent intent = new Intent(MyCarActivity.this, CountryActivity.class);
                        startActivityForResult(intent, REQUEST_COUNTRY);
                    } else {
                        car.setTitle(titleEditText.getText().toString());
                        car.setMark(String.valueOf(databaseHelperMethods.getIdMarkFromName(mark)));
                        car.setCost(Integer.parseInt(costEditText.getText().toString()));
                        car.setPower(powerEditText.getText().toString());
                        car.setDoorsNumber(doorsNumberEditText.getText().toString());
                        car.setBodyType(bodyTypeEditText.getText().toString());
                        car.setSeatsNumber(seatsNumberEditText.getText().toString());
                        car.setStartRelease(startReleaseEditText.getText().toString());
                        car.setEndRelease(endReleaseEditText.getText().toString());

                        if (!isSetPhoto && carForUpdating != null) {
                            if(carForUpdating.getPhoto()!=null){
                                car.setPhoto(carForUpdating.getPhoto());
                            }
                        }
                        databaseHelperMethods.insertCarModel(car);
                        if (carForUpdating != null) {
                            databaseHelperMethods.deleteCarModel(carForUpdating);
                        }
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_PHOTO) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap selectedImages = BitmapFactory.decodeStream(imageStream);
            car.setPhoto(selectedImage.toString());
            isSetPhoto = true;
            mPhotoView.setImageBitmap(selectedImages);
        }
        if (requestCode == REQUEST_COUNTRY) {
            int idCountry = data.getIntExtra("ID_COUNTRY", 0);
            databaseHelperMethods.insertMark(markEditText.getText().toString(), idCountry);
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivityPhotoPickerIntent();
        }
    }

    private void startActivityPhotoPickerIntent() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_PHOTO);
    }

}
