package com.singal.zy.optionitemview.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * 自定义 横向柱状图 数据实体类
 *
 * Created by li on 2017/8/18.
 */

public class MBarDataEntity implements Serializable {

    private List<Type> typeList;

    public List<Type> getTypeList() {
        return typeList;
    }

    public static class Type implements Serializable {
        private String typeName;//类型名称
        private int sale;//销量
        private double typeScale;//类型占比

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public double getTypeScale() {
            return typeScale;
        }

        public void setTypeScale(double typeScale) {
            this.typeScale = typeScale;
        }
    }

    public List<Type> parseData(){
        typeList = new ArrayList<>();
        Random r = new Random();
        int all=0;
        for (int i= 0;i<=6;i++){
            Type type = new Type();
            type.setSale(r.nextInt(100));
            type.setTypeName("品类" + i);
            typeList.add(type);
        }
        for (int i= 0;i<=6;i++){
            all+= typeList.get(i).getSale();
        }
        for (int i= 0;i<=6;i++){
            double typeScale = (double) typeList.get(i).getSale()/all;
            typeList.get(i).setTypeScale(typeScale);
            System.out.println("==>"+typeList.get(i).getTypeScale());
        }
        return typeList;
    }
}
