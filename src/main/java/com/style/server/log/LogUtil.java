package com.style.server.log;

import com.style.server.entity.DeviceInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Locale;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class LogUtil {

    private static final String DEFAULT_FILE_NAME = "logs/stat_log.txt";
    private static final String LOG_DIR = "logs";

    public static void D(String tag, String msg) {
        String procInfo = getProcessInfo();

        String time = getCurrentTime();
        System.out.println(time + " " + procInfo + " " + tag + " \t" + msg);
    }

    public static void F(String tag, String msg) {
        internalWriteLog(new File(DEFAULT_FILE_NAME), tag, msg);
    }

    public static void F(String tag, String msg, DeviceInfo deviceInfo) {
        File file = generateLogFile(deviceInfo.getModel(), deviceInfo.getAndroidId());
        if (file != null) {
            internalWriteLog(file, tag, msg);
        }
    }

    private static synchronized File generateLogFile(String model, String androidId) {
        Calendar c = Calendar.getInstance();
        String dir = String.format(Locale.getDefault(), "%d-%02d-%02d",
                c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        File dirFile = new File(LOG_DIR + File.separator + dir);
        //noinspection ResultOfMethodCallIgnored
        dirFile.mkdirs();
        String fileName = model + "_" + androidId;
        return new File(dirFile, fileName);
    }

    private static synchronized void internalWriteLog(File file, String tag, String msg) {
        try {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file, true)));

            String time = getCurrentTime();
            bw.write(time + " " + tag + " \t" + msg + "\r\n");

            bw.close();
        } catch (Exception e) {
            // ignore
            e.printStackTrace();
        }
    }

    private static String getProcessInfo() {
        return " Thread id: " + Thread.currentThread().getId() + " ";
    }

    private static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        return String.format(Locale.CHINA, "%d-%02d-%02d %02d:%02d:%02d.%03d",
                c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND),
                c.get(Calendar.MILLISECOND));
    }
}
