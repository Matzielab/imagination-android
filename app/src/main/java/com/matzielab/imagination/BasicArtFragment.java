package com.matzielab.imagination;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Matzie on 2016-10-25.
 */

public class BasicArtFragment extends Fragment{

    private LayoutInflater layoutInflater;
    private ViewGroup viewGroupContainer;

    public int layout = R.layout.basic_art;
    public int image = R.drawable.squiggles;
    public String title = "title";
    public String description = "description";

    private void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    private void setViewGroupContainer(ViewGroup container) {
        this.viewGroupContainer = container;
    }

    private View render() {
        View rootView = layoutInflater.inflate(this.layout, this.viewGroupContainer, false);
        ImageView image = (ImageView) rootView.findViewById(R.id.basic_image);
        TextView title = (TextView) rootView.findViewById(R.id.basic_title);
        TextView description = (TextView) rootView.findViewById(R.id.basic_description);

        image.setImageResource(this.image);
        title.setText(this.title);
        description.setText(this.description);

        return rootView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.setLayoutInflater(inflater);
        this.setViewGroupContainer(container);

        return this.render();
    }
}
