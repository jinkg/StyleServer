package com.style.server.parser;

import com.style.server.model.AdvanceWallpaperItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * jinyalin
 * on 2017/8/3.
 */
class FileParser {
    private static final String FILED_SEPARATOR = "\\|";

    static ArrayList<String[]> parseFile(String filePath) {
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        ArrayList<String[]> results = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            bufferedReader = new BufferedReader(isr);
            // comment line
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine();
                 line != null && line.length() > 0;
                 line = bufferedReader.readLine()) {
                String[] wallpaperFields = line.split(FILED_SEPARATOR);
                results.add(wallpaperFields);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception ignored) {
            }
        }
        return results;
    }
}
