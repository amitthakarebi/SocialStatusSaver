package com.amitthakare.socialstatussaver.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.amitthakare.socialstatussaver.fragment.TweatFrag;
import com.amitthakare.socialstatussaver.fragment.LikeeFrag;
import com.amitthakare.socialstatussaver.fragment.WABusFrag;
import com.amitthakare.socialstatussaver.fragment.WAppFrag;
import com.amitthakare.socialstatussaver.fragment.FBFrag;
import com.amitthakare.socialstatussaver.fragment.InsFrag;
import com.amitthakare.socialstatussaver.fragment.TikFrag;

public class GalleryPagerAdapter extends FragmentPagerAdapter {


    public GalleryPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new WAppFrag();
        }else if (position == 1) {
            return new WABusFrag();
        } else if (position == 2){
            return new InsFrag();
        }  else if (position == 3){
            return new TikFrag();
        }else if (position == 4){
            return new LikeeFrag();
        }else if (position == 5){
            return new FBFrag();
        }else {
            return new TweatFrag();
        }
    }

    @Override
    public int getCount() {
        return 7;
    }


}
