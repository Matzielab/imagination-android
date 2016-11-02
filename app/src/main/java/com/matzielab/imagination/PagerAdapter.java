package com.matzielab.imagination;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Matzie on 2016-10-25.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> slides = new ArrayList<>();
    private Context context;

    public PagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        this.prepareSlides();
    }

    private Fragment firstSlide()
    {
        BasicArtFragment slide = new BasicArtFragment();
        slide.title = StringHelper.getString(context, R.string.how_title);
        slide.description = StringHelper.getString(context, R.string.how_text);

        return slide;
    }

    private Fragment secondSlide()
    {
        BasicArtFragment slide = new BasicArtFragment();
        slide.image = R.drawable.abbey_road;
        slide.title = StringHelper.getString(context, R.string.why_title);
        slide.description = StringHelper.getString(context, R.string.why_text);

        return slide;
    }

    private void prepareSlides() {
        slides.add(new StartpageFragment());
        slides.add(this.firstSlide());
        slides.add(this.secondSlide());
    }

    @Override
    public Fragment getItem(int position) {
        return slides.get(position);
    }

    @Override
    public int getCount() {
        return slides.size();
    }
}
