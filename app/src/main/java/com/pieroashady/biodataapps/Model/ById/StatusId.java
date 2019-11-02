package com.pieroashady.biodataapps.Model.ById;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatusId implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<StatusId> CREATOR = new Creator<StatusId>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatusId createFromParcel(Parcel in) {
            return new StatusId(in);
        }

        public StatusId[] newArray(int size) {
            return (new StatusId[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1414753264062684006L;

    protected StatusId(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public StatusId() {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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