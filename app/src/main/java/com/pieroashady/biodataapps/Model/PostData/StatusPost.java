package com.pieroashady.biodataapps.Model.PostData;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatusPost implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<StatusPost> CREATOR = new Creator<StatusPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatusPost createFromParcel(Parcel in) {
            return new StatusPost(in);
        }

        public StatusPost[] newArray(int size) {
            return (new StatusPost[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2661860357389059173L;

    protected StatusPost(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public StatusPost() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

}