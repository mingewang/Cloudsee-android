
package com.jovetech.product;

import android.content.res.Resources;

import com.jovetech.CloudSee.temp.R;

public class MyDeviceTab implements ITabItem {

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return Resources.getSystem().getStringArray(R.array.array_tab)[0];
    }

    @Override
    public int getDrawable() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setMessage(String count) {
        // TODO Auto-generated method stub

    }

    @Override
    public char getTag() {
        // TODO Auto-generated method stub
        return 'a';
    }

    @Override
    public int getLayoutType() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
