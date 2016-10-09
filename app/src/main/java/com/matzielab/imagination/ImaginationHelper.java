package com.matzielab.imagination;


/**
 * Created by Matzie on 16-09-06.
 */
public class ImaginationHelper {

    static float makeBetween1And0(float number) {
        if(number > 1) {
            number = 1;
        } else if(number < 0) {
            number = 0;
        }

        return number;
    }

}
