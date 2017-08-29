package com.singal.zy.normal_libs.address;

/**
 * 地址选择器 监听
 *
 * Created by li on 2017/8/29.
 */

public interface OnItemClickListener {

    /**
     * @param city 返回地址列表对应点击的对象
     * @param tabPosition 对应tab的位置
     * */
    void itemClick(AddressSelector addressSelector,CityInterface city, int tabPosition);

}
