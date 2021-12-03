package com.rorpheeyah.realmhelper.pet;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    private String name;
    private boolean gender;
    private long createdDate;
    private String imgUrl;
    private long dob;
    private String occupation;

    public User(){
        // require empty constructor
    }

    public User(int id, String name, boolean gender, long createdDate, String imgUrl, long dob, String occupation) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.createdDate = createdDate;
        this.imgUrl = imgUrl;
        this.dob = dob;
        this.occupation = occupation;
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        gender = in.readByte() != 0;
        createdDate = in.readLong();
        imgUrl = in.readString();
        dob = in.readLong();
        occupation = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (gender ? 1 : 0));
        dest.writeLong(createdDate);
        dest.writeString(imgUrl);
        dest.writeLong(dob);
        dest.writeString(occupation);
    }
}
