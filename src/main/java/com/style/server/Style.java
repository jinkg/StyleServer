package com.style.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.style.server.model.WallpaperItem;

import java.io.File;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

/**
 * 34921
 * 2017/1/3.
 */
public class Style {
    private static Gson gson = new GsonBuilder().create();

    public static void main(String[] args) {
        staticFileLocation("/public");
        port(6060);
        get("/style", (request, response) -> getStyle());
    }

    public static final String IP = "http://www.kinglloy.com:6060";

    private static final String DATA_KEY_WALLPAPER = "wallpapers";

    private static String getStyle() {
        WallpaperItem[] itemArray = new WallpaperItem[]{
                new WallpaperItem(UUID.randomUUID().toString(),
                        "series-i-no-3.jpg",
                        "Series I, No. 3", "Georgia O'Keeffe, 1918", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "blue-02.jpg",
                        "Blue-02", "Georgia O'Keeffe, 1916", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "blue-morning-glories.jpg",
                        "Blue Morning Glories", "Georgia O'Keeffe, 1935", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "bleeding-heart.jpg",
                        "Bleeding Heart", "Georgia O'Keeffe, 1932", "kinglloy.com")
        };

        File file = new File("a.txt");

        Map<String, Object> dataMap = new HashMap<>();

        List<WallpaperItem> items = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt(itemArray.length);
            for (int j = 0; j < 3; j++) {
                int index = (random + j) % itemArray.length;
                WallpaperItem item = itemArray[index];
                item.checksum = ChecksumUtil.getChecksum("src/main/resources/public/" + item.fileName);
                if (item.checksum != null && !item.checksum.equals("")) {
                    items.add(item);
                }
            }
            if (!items.isEmpty()) {
                break;
            }
        }
        dataMap.put(DATA_KEY_WALLPAPER, items);

        System.out.println("Select wallpapers : " + items);

        return gson.toJson(dataMap);
    }
}
