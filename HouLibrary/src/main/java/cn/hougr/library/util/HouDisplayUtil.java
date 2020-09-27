package cn.hougr.library.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by hougr(houxiabing) on 2020/9/26.
 */
public class HouDisplayUtil {
    public static int dp2px (float dp) {
        Resources resources = AppGlobals.INSTANCE.get().getResources();
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static int sp2px (float sp) {
        Resources resources = AppGlobals.INSTANCE.get().getResources();
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.getDisplayMetrics());
    }
}
