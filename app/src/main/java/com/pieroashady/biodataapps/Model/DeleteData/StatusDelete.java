package com.pieroashady.biodataapps.Model.DeleteData;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatusDelete implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private String data;
    public final static Parcelable.Creator<StatusDelete> CREATOR = new Creator<StatusDelete>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatusDelete createFromParcel(Parcel in) {
            return new StatusDelete(in);
        }

        public StatusDelete[] newArray(int size) {
            return (new StatusDelete[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1408212584345168227L;

    protected StatusDelete(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StatusDelete() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

}