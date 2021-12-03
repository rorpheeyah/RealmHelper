package com.rorpheeyah.realmhelper.pet;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pet extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    private String name;
    private boolean gender;
    private double distance;
    private long createdDate;
    private String behavior;
    private String imgUrl;
    private long dob;
    private String story;
    private String color;
    private double weight;
    private int ownerId;

    public Pet(int id, String name, boolean gender, String behavior, String imgUrl) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.behavior = behavior;
        this.imgUrl = imgUrl;
    }

    public Pet(){
        // require empty constructor
    }

    protected Pet(Parcel in) {
        id = in.readInt();
        name = in.readString();
        gender = in.readByte() != 0;
        distance = in.readDouble();
        createdDate = in.readLong();
        behavior = in.readString();
        imgUrl = in.readString();
        dob = in.readLong();
        story = in.readString();
        color = in.readString();
        weight = in.readDouble();
        ownerId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (gender ? 1 : 0));
        dest.writeDouble(distance);
        dest.writeLong(createdDate);
        dest.writeString(behavior);
        dest.writeString(imgUrl);
        dest.writeLong(dob);
        dest.writeString(story);
        dest.writeString(color);
        dest.writeDouble(weight);
        dest.writeInt(ownerId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", distance=" + distance +
                ", createdDate=" + createdDate +
                ", behavior='" + behavior + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", dob=" + dob +
                ", story='" + story + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", ownerId=" + ownerId +
                '}';
    }
}
