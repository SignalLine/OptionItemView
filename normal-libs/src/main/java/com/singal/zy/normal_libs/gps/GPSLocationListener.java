package com.singal.zy.normal_libs.gps;

import android.location.Location;
import android.os.Bundle;

/**
 * GPS 供外部实现的接口
 *
 * Created by li on 2017/4/7.
 */

public interface GPSLocationListener {

    /**
     * 位置信息发生改变时候被调用
     *
     * @param location
     */
    void UpdateLocation(Location location);

    /**
     * provider定位源类型变化时候被调用
     *
     * @param provider
     * @param status
     * @param extras
     */
    void UpdateStatus(String provider, int status, Bundle extras);

    /**
     * GPS状态发生改变时候被调用  GPS手动启动关闭，不在服务 占用  不可用
     *
     * @param gpsStatus
     */
    void UpdateGPSProviderStatus(int gpsStatus);


}
