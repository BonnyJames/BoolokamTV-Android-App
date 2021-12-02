package com.boolokam.boolokamtv.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Leaderboard implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("startDate")
    @Expose
    private String startDate;

    @SerializedName("endDate")
    @Expose
    private String endDate;

    @SerializedName("nbrVotes")
    @Expose
    private Integer nbrVotes;

    @SerializedName("media")
    @Expose
    private String media;

    @SerializedName("topMovies")
    @Expose
    private List<LeaderBoardMovie> topMovies;

    protected Leaderboard(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        description = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        if (in.readByte() == 0) {
            nbrVotes = null;
        } else {
            nbrVotes = in.readInt();
        }
        media = in.readString();
        topMovies = in.createTypedArrayList(LeaderBoardMovie.CREATOR);
    }

    public static final Creator<Leaderboard> CREATOR = new Creator<Leaderboard>() {
        @Override
        public Leaderboard createFromParcel(Parcel in) {
            return new Leaderboard(in);
        }

        @Override
        public Leaderboard[] newArray(int size) {
            return new Leaderboard[size];
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
        parcel.writeString(description);
        parcel.writeString(startDate);
        parcel.writeString(endDate);
        if (nbrVotes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(nbrVotes);
        }
        parcel.writeString(media);
        parcel.writeTypedList(topMovies);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getNbrVotes() {
        return nbrVotes;
    }

    public void setNbrVotes(Integer nbrVotes) {
        this.nbrVotes = nbrVotes;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public List<LeaderBoardMovie> getTopMovies() {
        return topMovies;
    }

    public void setTopMovies(List<LeaderBoardMovie> topMovies) {
        this.topMovies = topMovies;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", nbrVotes=" + nbrVotes +
                ", media='" + media + '\'' +
                ", topMovies=" + topMovies +
                '}';
    }
}
