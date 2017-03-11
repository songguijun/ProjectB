package gift.songguijun.lanou.com.projectb.subscibe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

import gift.songguijun.lanou.com.projectb.R;

/**
 * Created by dllo on 17/3/2.
 */

/**
 * 地图
 */
public class MapActivity extends AppCompatActivity implements AMapLocationListener, LocationSource {
    private MapView mapView ;
    private AMap aMap;
    private AMapLocationClient mLocationClient;
    private LocationSource.OnLocationChangedListener mListener;
    public AMapLocationClientOption mapLocationClientOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();}


        //UiSettings 主要是对地图上的控件的管理，比如指南针、logo位置（不能隐藏）.....
        UiSettings settings =  aMap.getUiSettings();

        //设置了定位的监听,这里要实现LocationSource接口
        aMap.setLocationSource(this);

        // 是否显示定位按钮（据我所知不能更改，知道的麻烦告我一声）
        settings.setMyLocationButtonEnabled(true);
        //添加指南针
        settings.setCompassEnabled(true);

        aMap.getCameraPosition(); //方法可以获取地图的旋转角度


        //管理缩放控件
        settings.setZoomControlsEnabled(true);
        //设置logo位置，左下，底部居中，右下（据我所知不能隐藏，知道的麻烦告我一声）
        settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
        //设置显示地图的默认比例尺
        settings.setScaleControlsEnabled(true);
        //每像素代表几米
        //float scale = aMap.getScalePerPixel();
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);


        //开始定位
        location();
    }
    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mLocationClient==null){
            //初始化AMapLocationClient，并绑定监听
            mLocationClient = new AMapLocationClient(getApplicationContext());

            //初始化定位参数
            mapLocationClientOption = new AMapLocationClientOption();
            //设置定位精度
            mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //是否返回地址信息
            mapLocationClientOption.setNeedAddress(true);
            //是否只定位一次
            mapLocationClientOption.setOnceLocation(false);
            //设置是否强制刷新WIFI，默认为强制刷新
            mapLocationClientOption.setWifiActiveScan(true);
            //是否允许模拟位置
            mapLocationClientOption.setMockEnable(false);
            //定位时间间隔
            mapLocationClientOption.setInterval(2000);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mapLocationClientOption);
            //绑定监听
            mLocationClient.setLocationListener(this);
            //开启定位
            mLocationClient.startLocation();
        }

    }



    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                mListener.onLocationChanged(aMapLocation);
                StringBuilder stringBuilder = new StringBuilder();
                int type = aMapLocation.getLocationType();
                String address = aMapLocation.getAddress();
                stringBuilder.append(type + address);
                // Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                aMapLocation.getAccuracy();//获取精度信息
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                aMapLocation.getAoiName();//获取当前定位点的AOI信息
                //获取定位时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);


                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                Boolean isFirstLoc = false ;
                if (isFirstLoc) {

                    //设置缩放级别（缩放级别为4-20级）
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());

                    Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                }

            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("地图错误","定位失败, 错误码:" + aMapLocation.getErrorCode() + ", 错误信息:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient!=null){
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }
}
