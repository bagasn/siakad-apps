package com.bagasnasution.lecturesapp.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Batavianet on 10/12/2017.
 */

public class ResponseDefault implements Serializable {
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Message")
    @Expose
    private String message;
}
