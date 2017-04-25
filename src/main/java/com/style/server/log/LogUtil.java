package com.style.server.log;

import java.util.Calendar;
import java.util.Locale;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class LogUtil {

    public static void D(String tag, String msg) {
        String procInfo = getProcessInfo();

        String time = getCurrentTime();
        System.out.println(time + " " + procInfo + " " + tag + " \t" + msg);
    }

    private static String getProcessInfo() {
        return " Thread id: " + Thread.currentThread().getId() + " ";
    }

    private static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        return String.format(Locale.getDefault(), "%d-%02d-%02d %02d:%02d:%02d.%03d",
                c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND),
                c.get(Calendar.MILLISECOND));
    }
}
