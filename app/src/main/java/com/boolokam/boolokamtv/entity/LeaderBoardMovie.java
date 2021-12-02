package com.boolokam.boolokamtv.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderBoardMovie implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("nbrVotes")
    @Expose
    private Integer nbrVotes;

    protected LeaderBoardMovie(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        image = in.readString();
        if (in.readByte() == 0) {
            nbrVotes = null;
        } else {
            nbrVotes = in.readInt();
        }
    }

    public static final Creator<LeaderBoardMovie> CREATOR = new Creator<LeaderBoardMovie>() {
        @Override
        public LeaderBoardMovie createFromParcel(Parcel in) {
            return new LeaderBoardMovie(in);
        }

        @Override
        public LeaderBoardMovie[] newArray(int size) {
            return new LeaderBoardMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(title);
        parcel.writeString(image);
        if (nbrVotes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(nbrVotes);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNbrVotes() {
        return nbrVotes;
    }

    public void setNbrVotes(Integer nbrVotes) {
        this.nbrVotes = nbrVotes;
    }

    @Override
    public String toString() {
        return "LeaderBoardMovie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", nbrVotes=" + nbrVotes +
                '}';
    }
}
