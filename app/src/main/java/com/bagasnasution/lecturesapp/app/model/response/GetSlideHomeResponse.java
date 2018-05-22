package com.bagasnasution.lecturesapp.app.model.response;

import com.bagasnasution.lecturesapp.app.model.SlideHomeModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bagas on 5/7/2018.
 */

public class GetSlideHomeResponse extends ResponseDefault{
    @SerializedName("data")
    @Expose
    private List<SlideHomeModel> listSlideHome = null;

    public List<SlideHomeModel> getListSlideHome() {
        return listSlideHome;
    }

    public void setListSlideHome(List<SlideHomeModel> listSlideHome) {
        this.listSlideHome = listSlideHome;
    }
}
