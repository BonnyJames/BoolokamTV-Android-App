package com.boolokam.boolokamtv.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoteCount implements Parcelable {

    @SerializedName("movieId")
    @Expose
    private Integer movieId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("currentVoteCount")
    @Expose
    private Integer currentVoteCount;

    @SerializedName("currentVoteInCompetition")
    @Expose
    private Integer currentVoteInCompetition;

    @SerializedName("rankInCompetition")
    @Expose
    private Integer rankInCompetition;

    @SerializedName("titleCompetition")
    @Expose
    private String titleCompetition;

    @SerializedName("descriptionCompetition")
    @Expose
    private String descriptionCompetition;

    @SerializedName("startDateCompetition")
    @Expose
    private String startDateCompetition;

    @SerializedName("endDateCompetition")
    @Expose
    private String endDateCompetition;

    @SerializedName("totalVoteCompetition")
    @Expose
    private Integer totalVoteCompetition;

    @SerializedName("mediaCompetition")
    @Expose
    private String mediaCompetition;

    protected VoteCount(Parcel in) {
        if (in.readByte() == 0) {
            movieId = null;
        } else {
            movieId = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            currentVoteCount = null;
        } else {
            currentVoteCount = in.readInt();
        }
        if (in.readByte() == 0) {
            currentVoteInCompetition = null;
        } else {
            currentVoteInCompetition = in.readInt();
        }
        if (in.readByte() == 0) {
            rankInCompetition = null;
        } else {
            rankInCompetition = in.readInt();
        }
        titleCompetition = in.readString();
        descriptionCompetition = in.readString();
        startDateCompetition = in.readString();
        endDateCompetition = in.readString();
        if (in.readByte() == 0) {
            totalVoteCompetition = null;
        } else {
            totalVoteCompetition = in.readInt();
        }
        mediaCompetition = in.readString();
    }

    public static final Creator<VoteCount> CREATOR = new Creator<VoteCount>() {
        @Override
        public VoteCount createFromParcel(Parcel in) {
            return new VoteCount(in);
        }

        @Override
        public VoteCount[] newArray(int size) {
            return new VoteCount[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (movieId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(movieId);
        }
        parcel.writeString(name);
        if (currentVoteCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currentVoteCount);
        }
        if (currentVoteInCompetition == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(currentVoteInCompetition);
        }
        if (rankInCompetition == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(rankInCompetition);
        }
        parcel.writeString(titleCompetition);
        parcel.writeString(descriptionCompetition);
        parcel.writeString(startDateCompetition);
        parcel.writeString(endDateCompetition);
        if (totalVoteCompetition == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalVoteCompetition);
        }
        parcel.writeString(mediaCompetition);
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurrentVoteCount() {
        return currentVoteCount;
    }

    public void setCurrentVoteCount(Integer currentVoteCount) {
        this.currentVoteCount = currentVoteCount;
    }

    public Integer getCurrentVoteInCompetition() {
        return currentVoteInCompetition;
    }

    public void setCurrentVoteInCompetition(Integer currentVoteInCompetition) {
        this.currentVoteInCompetition = currentVoteInCompetition;
    }

    public Integer getRankInCompetition() {
        return rankInCompetition;
    }

    public void setRankInCompetition(Integer rankInCompetition) {
        this.rankInCompetition = rankInCompetition;
    }

    public String getTitleCompetition() {
        return titleCompetition;
    }

    public void setTitleCompetition(String titleCompetition) {
        this.titleCompetition = titleCompetition;
    }

    public String getDescriptionCompetition() {
        return descriptionCompetition;
    }

    public void setDescriptionCompetition(String descriptionCompetition) {
        this.descriptionCompetition = descriptionCompetition;
    }

    public String getStartDateCompetition() {
        return startDateCompetition;
    }

    public void setStartDateCompetition(String startDateCompetition) {
        this.startDateCompetition = startDateCompetition;
    }

    public String getEndDateCompetition() {
        return endDateCompetition;
    }

    public void setEndDateCompetition(String endDateCompetition) {
        this.endDateCompetition = endDateCompetition;
    }

    public Integer getTotalVoteCompetition() {
        return totalVoteCompetition;
    }

    public void setTotalVoteCompetition(Integer totalVoteCompetition) {
        this.totalVoteCompetition = totalVoteCompetition;
    }

    public String getMediaCompetition() {
        return mediaCompetition;
    }

    public void setMediaCompetition(String mediaCompetition) {
        this.mediaCompetition = mediaCompetition;
    }

    @Override
    public String toString() {
        return "VoteCount{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", currentVoteCount=" + currentVoteCount +
                ", currentVoteInCompetition=" + currentVoteInCompetition +
                ", rankInCompetition=" + rankInCompetition +
                ", titleCompetition='" + titleCompetition + '\'' +
                ", descriptionCompetition='" + descriptionCompetition + '\'' +
                ", startDateCompetition='" + startDateCompetition + '\'' +
                ", endDateCompetition='" + endDateCompetition + '\'' +
                ", totalVoteCompetition=" + totalVoteCompetition +
                ", mediaCompetition='" + mediaCompetition + '\'' +
                '}';
    }
}
