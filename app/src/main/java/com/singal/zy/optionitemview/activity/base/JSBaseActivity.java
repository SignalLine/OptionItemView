package com.singal.zy.optionitemview.activity.base;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.singal.zy.optionitemview.app.App;

import java.util.List;

/**
 * Created by li on 2017/7/10.
 */

public class JSBaseActivity extends AppCompatActivity{

    private LocationManager mLocationManager;
    private LocationListener locationListener;
    private Menu menu;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        return true;
    }

    public void setMenu(String title, final Runnable r) {
        if (menu != null) {
            menu.clear();
            MenuItem item = menu.add(Menu.NONE, 0, Menu.NONE, title);
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    r.run();
                    return true;
                }
            });
            MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    public Location getLocation(LocationListener listener) {
        locationListener = listener;
        mLocationManager = (LocationManager) App.getInstance().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        String locationProvider = null;
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }
        Location location = null;


        if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(JSBaseActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                || PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(JSBaseActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)){
            location = mLocationManager.getLastKnownLocation(locationProvider);
            mLocationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
        }else {
            ActivityCompat.requestPermissions(JSBaseActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0x11);
        }


        return location;
    }


    @Override
    protected void onDestroy() {
        if (mLocationManager != null && locationListener != null) {
            mLocationManager.removeUpdates(locationListener);
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0x11:

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //
                    Toast.makeText(this, "获取到权限", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
