package com.matzielab.imagination;

import android.content.Context;

/**
 * Created by Matzie on 2016-11-02.
 */

public class StringHelper {

    static String getString(Context context, int stringResource) {
        return context.getResources().getString(stringResource);
    }
}
