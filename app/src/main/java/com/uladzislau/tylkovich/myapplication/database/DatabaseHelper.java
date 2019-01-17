package com.uladzislau.tylkovich.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "carBase.db";

    public static final String TABLE_MANUFACTURERS = "manufacturers";
    public static final String COLUMN_ID_COUNTRY_MANUFACTURER = "_id_manufacturer";
    public static final String COLUMN_NAME_COUNTRY_MANUFACTURER = "name_country_manufacturer";

    public static final String TABLE_CAR_MARKS = "car_marks";
    public static final String COLUMN_ID_CAR_MARK = "_id_car_mark";
    public static final String COLUMN_NAME_CAR_MARK = "name_car_mark";
    public static final String COLUMN_ID_COUNTRY_CAR_MARK = "_id_country_car_mark";

    public static final String TABLE_CAR_MODELS = "car_models";
    public static final String COLUMN_ID_CAR_MODEL = "_id_car_model";
    public static final String COLUMN_NAME_CAR_MODEL = "name_car_model";
    public static final String COLUMN_ID_CAR_MODEL_MARK = "_id_car_model_mark";
    public static final String COLUMN_COST_CAR_MODEL = "cost_car_model";
    public static final String COLUMN_POWER_CAR_MODEL = "power_car_model";
    public static final String COLUMN_DOORS_NUMBER_CAR_MODEL = "doors_number_car_model";
    public static final String COLUMN_BODY_TYPE_CAR_MODEL = "body_type_car_model";
    public static final String COLUMN_SEATS_NUMBER_CAR_MODEL = "seats_number_car_model";
    public static final String COLUMN_RELEASE_START_CAR_MODEL = "release_start_car_model";
    public static final String COLUMN_RELEASE_END_CAR_MODEL = "release_end_car_model";
    public static final String COLUMN_PHOTO_CAR_MODEL = "photo_car_model";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MANUFACTURERS
                + " (" + COLUMN_ID_COUNTRY_MANUFACTURER
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_COUNTRY_MANUFACTURER + " TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CAR_MARKS
                + " (" + COLUMN_ID_CAR_MARK
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_CAR_MARK + " TEXT, "
                + COLUMN_ID_COUNTRY_CAR_MARK + " INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CAR_MODELS
                + " (" + COLUMN_ID_CAR_MODEL
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_CAR_MODEL + " TEXT, "
                + COLUMN_ID_CAR_MODEL_MARK + " TEXT, "
                + COLUMN_COST_CAR_MODEL + " INTEGER, "
                + COLUMN_POWER_CAR_MODEL + " TEXT, "
                + COLUMN_DOORS_NUMBER_CAR_MODEL + " INTEGER, "
                + COLUMN_BODY_TYPE_CAR_MODEL + " TEXT, "
                + COLUMN_SEATS_NUMBER_CAR_MODEL + " INTEGER, "
                + COLUMN_RELEASE_START_CAR_MODEL + " TEXT, "
                + COLUMN_RELEASE_END_CAR_MODEL + " TEXT, "
                + COLUMN_PHOTO_CAR_MODEL + " TEXT);");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MANUFACTURERS + " (" + COLUMN_ID_COUNTRY_MANUFACTURER + ", " +
                COLUMN_NAME_COUNTRY_MANUFACTURER + ") VALUES ('1', 'USA'),('2','Japan'), ('3', 'Germany');");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CAR_MARKS + " (" + COLUMN_ID_CAR_MARK + ", " +
                COLUMN_NAME_CAR_MARK + "," + COLUMN_ID_COUNTRY_CAR_MARK + ") VALUES ('1', 'Ford', '1')," +
                "('2', 'Toyota', '2'),('3', 'Nissan', '2'),('4', 'BMW', '3'),('5', 'Mercedes-Benz', '3')," +
                "('6', 'Volkswagen', '3')," + "('7', 'Audi', '3');");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CAR_MODELS + " (" + COLUMN_NAME_CAR_MODEL + ", " +
                COLUMN_ID_CAR_MODEL_MARK + "," + COLUMN_COST_CAR_MODEL + "," + COLUMN_POWER_CAR_MODEL + "," +
                COLUMN_DOORS_NUMBER_CAR_MODEL + "," + COLUMN_BODY_TYPE_CAR_MODEL + "," + COLUMN_SEATS_NUMBER_CAR_MODEL + "," +
                COLUMN_RELEASE_START_CAR_MODEL + "," + COLUMN_RELEASE_END_CAR_MODEL + "," +
                COLUMN_PHOTO_CAR_MODEL + ") VALUES " +
                "('Volkswagen', '6', '2500', '200 Hp', '4', 'universal','5','1999', '-', 'res/drawable/volf.jpg')," +
                "('BMV X6', '4', '28000', '526 Hp', '4', 'Sedan','5','2017', '-', 'res/drawable/bmw.jpg')," +
                "('Audi A8', '7', '13000', '526 Hp', '4', 'universal','5','2003', '2018', 'res/drawable/audi.jpg')," +
                "('Ford escape', '1', '13000', '230 Hp', '4', 'Hatchback','5','2014', '-', 'res/drawable/ford.jpg')," +
                "('land rover', '1', '20000', '300 Hp', '4', 'Hatchback','5','2016', '-', 'res/drawable/rover.jpg')," +
                "('Toyota Carolla', '2', '8000', '135 Hp', '4', 'Sedan','5','2006', '-', 'res/drawable/toyota.jpg')," +
                "('Nissan Quashi', '3', '9000', '200 Hp', '4', 'Hatchback','5','2008', '-', 'res/drawable/nissan.jpg')," +
                "('Mercedes CL550', '5', '50000', '500 Hp', '2', 'universal','3','2015', '-', 'res/drawable/mersedes.jpg');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
