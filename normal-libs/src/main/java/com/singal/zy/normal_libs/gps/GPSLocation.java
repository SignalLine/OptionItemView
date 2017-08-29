package com.singal.zy.normal_libs.gps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;

/**
 *
 * 实现LocationListener的子类 同时实现自己的接口调用
 *
 *
 * Created by li on 2017/4/7.
 */

public class GPSLocation implements LocationListener {

    private GPSLocationListener mGPSLocationListener;

    public GPSLocation(GPSLocationListener gpsLocationListener){
        this.mGPSLocationListener = gpsLocationListener;
    }


    @Override
    public void onLocationChanged(Location location) {
        if(location != null){
            mGPSLocationListener.UpdateLocation(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        mGPSLocationListener.UpdateStatus(provider, status, extras);
        switch (status) {
            case LocationProvider.AVAILABLE:
                mGPSLocationListener.UpdateGPSProviderStatus(GPSProviderStatus.GPS_AVAILABLE);
                break;
            case LocationProvider.OUT_OF_SERVICE:
                mGPSLocationListener.UpdateGPSProviderStatus(GPSProviderStatus.GPS_OUT_OF_SERVICE);
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                mGPSLocationListener.UpdateGPSProviderStatus(GPSProviderStatus.GPS_TEMPORARILY_UNAVAILABLE);
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        mGPSLocationListener.UpdateGPSProviderStatus(GPSProviderStatus.GPS_ENABLED);
    }

    @Override
    public void onProviderDisabled(String provider) {
        mGPSLocationListener.UpdateGPSProviderStatus(GPSProviderStatus.GPS_DISABLED);
    }
}
