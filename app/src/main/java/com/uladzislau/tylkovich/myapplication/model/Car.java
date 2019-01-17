package com.uladzislau.tylkovich.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public final class Car implements Parcelable {
    private String mId;
    private String title;
    private String mark;
    private int cost;
    private String power;
    private String doorsNumber;
    private String bodyType;
    private String seatsNumber;
    private String startRelease;
    private String endRelease;
    private String photo;

    public Car() {
        this(String.valueOf(UUID.randomUUID()));
    }

    public Car(String id) {
        mId = id;
    }

    protected Car(Parcel in) {
        mId = in.readString();
        title = in.readString();
        mark = in.readString();
        cost = in.readInt();
        power = in.readString();
        doorsNumber = in.readString();
        bodyType = in.readString();
        seatsNumber = in.readString();
        startRelease = in.readString();
        endRelease = in.readString();
        photo = in.readString();
    }

    public String getId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getDoorsNumber() {
        return doorsNumber;
    }

    public void setDoorsNumber(String doorsNumber) {
        this.doorsNumber = doorsNumber;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(String seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getStartRelease() {
        return startRelease;
    }

    public void setStartRelease(String startRelease) {
        this.startRelease = startRelease;
    }

    public String getEndRelease() {
        return endRelease;
    }

    public void setEndRelease(String endRelease) {
        this.endRelease = endRelease;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(title);
        parcel.writeString(mark);
        parcel.writeInt(cost);
        parcel.writeString(power);
        parcel.writeString(doorsNumber);
        parcel.writeString(bodyType);
        parcel.writeString(seatsNumber);
        parcel.writeString(startRelease);
        parcel.writeString(endRelease);
        parcel.writeString(photo);
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {

        @Override
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
