package com.zwg.teseapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConnectivityManager connectivityManager;




/*
        TextView tv1= (TextView) findViewById(R.id.jj);
        TextView tv2= (TextView) findViewById(R.id.jj2);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWithPkgAndCls("com.zwg.newalldemo","com.zwg.newalldemo.MainActivity");
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWithPkgAndCls("com.zwg.newalldemo","com.zwg.newalldemo.Main2Activity");
            }
        });*/


    }










    private void toggleMobileData(Context context, boolean enabled) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //ConnectivityManager类
        Class<?> connectivityManagerClass = null;
        //ConnectivityManager类中的字段
        Field connectivityManagerField = null;


        //IConnectivityManager接口
        Class<?> iConnectivityManagerClass = null;
        //IConnectivityManager接口的对象
        Object iConnectivityManagerObject = null;
        //IConnectivityManager接口的对象的方法
        Method setMobileDataEnabledMethod = null;

        try {
            //取得ConnectivityManager类
            connectivityManagerClass = Class.forName(connectivityManager.getClass().getName());
            //取得ConnectivityManager类中的字段mService
            connectivityManagerField = connectivityManagerClass.getDeclaredField("mService");
            //取消访问私有字段的合法性检查
            //该方法来自java.lang.reflect.AccessibleObject
            connectivityManagerField.setAccessible(true);


            //实例化mService
            //该get()方法来自java.lang.reflect.Field
            //一定要注意该get()方法的参数:
            //它是mService所属类的对象
            //完整例子请参见:
            //http://blog.csdn.net/lfdfhl/article/details/13509839
            iConnectivityManagerObject = connectivityManagerField.get(connectivityManager);
            //得到mService所属接口的Class
            iConnectivityManagerClass = Class.forName(iConnectivityManagerObject.getClass().getName());
            //取得IConnectivityManager接口中的setMobileDataEnabled(boolean)方法
            //该方法来自java.lang.Class.getDeclaredMethod
            setMobileDataEnabledMethod =
                    iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            //取消访问私有方法的合法性检查
            //该方法来自java.lang.reflect.AccessibleObject
            setMobileDataEnabledMethod.setAccessible(true);
            //调用setMobileDataEnabled方法
            setMobileDataEnabledMethod.invoke(iConnectivityManagerObject,enabled);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }







    /**
     * 根据app包名自动对应的app和Activity
     * context注意要传applicationContext
     */
    public  void launchWithPkgAndCls (String pkg, String cls) {
        try {
            Intent intent=new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(new ComponentName(pkg, cls));
            startActivity(intent);

        }catch (Exception e){
            e.printStackTrace();
            Log.e("=========",e.toString());
        }

    }
}
