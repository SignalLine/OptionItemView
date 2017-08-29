package com.singal.zy.optionitemview.model;

import com.singal.zy.normal_libs.address.CityInterface;

/**
 *
 * 城市选择 实体类
 *
 * Created by li on 2017/8/29.
 */

public class City implements CityInterface {
    private String Name;
    private String id;
    private String Grade;
    private String IsMunicipality;

    public String getIsMunicipality() {
        return IsMunicipality;
    }

    public void setIsMunicipality(String isMunicipality) {
        IsMunicipality = isMunicipality;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    @Override
    public String getCityName() {
        return Name;
    }
}
