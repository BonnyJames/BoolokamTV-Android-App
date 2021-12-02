package com.boolokam.boolokamtv.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastVote  implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("sublabel")
    @Expose
    private String sublabel;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("rating")
    @Expose
    private float rating;

    @SerializedName("duration")
    @Expose
    private int duration;

    @SerializedName("downloadas")
    @Expose
    private String downloadas;


    @SerializedName("imdb")
    @Expose
    private String imdb;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("playas")
    @Expose
    private String playas;

    @SerializedName("classification")
    @Expose
    private String classification;

    @SerializedName("VoteCountNbr")
    @Expose
    private int VoteCountNbr;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    @SerializedName("sources")
    @Expose
    private List<Source> sources;

    @SerializedName("message")
    @Expose
    private String message;


    protected CastVote(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        label = in.readString();
        sublabel = in.readString();
        type = in.readString();
        description = in.readString();
        year = in.readString();
        rating = in.readInt();
        duration = in.readInt();
        downloadas = in.readString();
        imdb = in.readString();
        comment = in.readString();
        playas = in.readString();
        classification = in.readString();
        VoteCountNbr = in.readInt();
        image = in.readString();
        message = in.readString();
        genres = in.createTypedArrayList(Genre.CREATOR);
        sources = in.createTypedArrayList(Source.CREATOR);
    }

    public static final Creator<CastVote> CREATOR = new Creator<CastVote>() {
        @Override
        public CastVote createFromParcel(Parcel in) {
            return new CastVote(in);
        }

        @Override
        public CastVote[] newArray(int size) {
            return new CastVote[size];
        }
    };

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSublabel() {
        return sublabel;
    }

    public void setSublabel(String sublabel) {
        this.sublabel = sublabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDownloadas() {
        return downloadas;
    }

    public void setDownloadas(String downloadas) {
        this.downloadas = downloadas;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPlayas() {
        return playas;
    }

    public void setPlayas(String playas) {
        this.playas = playas;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getVoteCountNbr() {
        return VoteCountNbr;
    }

    public void setVoteCountNbr(int voteCountNbr) {
        VoteCountNbr = voteCountNbr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

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
        parcel.writeString(label);
        parcel.writeString(sublabel);
        parcel.writeString(type);
        parcel.writeString(description);
        parcel.writeString(year);
        parcel.writeFloat(rating);
        parcel.writeInt(duration);
        parcel.writeString(downloadas);
        parcel.writeString(imdb);
        parcel.writeString(comment);
        parcel.writeString(playas);
        parcel.writeString(classification);
        parcel.writeInt(VoteCountNbr);
        parcel.writeString(image);
        parcel.writeString(message);
        parcel.writeTypedList(genres);
        parcel.writeTypedList(sources);
    }


    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CastVote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", sublabel='" + sublabel + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                ", duration=" + duration +
                ", downloadas='" + downloadas + '\'' +
                ", imdb='" + imdb + '\'' +
                ", comment='" + comment + '\'' +
                ", playas='" + playas + '\'' +
                ", classification='" + classification + '\'' +
                ", VoteCountNbr=" + VoteCountNbr +
                ", image='" + image + '\'' +
                ", genres=" + genres +
                ", sources=" + sources +
                ", message='" + message + '\'' +
                '}';
    }
}
