package com.style.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.style.server.entity.HttpRequestBody;
import com.style.server.log.LogUtil;
import com.style.server.model.AdvanceWallpaperItem;
import com.style.server.model.WallpaperItem;
import com.style.server.parser.AdvanceWallpaperParser;
import com.style.server.parser.WallpaperSourceParser;

import java.util.*;

import static spark.Spark.*;

/**
 * 34921
 * 2017/1/3.
 */
public class Style {

    private static final String TAG = "Style";

    private static final int PORT = 6060;

    private static Gson gson = new GsonBuilder().create();

    private static final int MAX_WALLPAPER_RETURN = 3;

    private static final int VERSION_CODE_2_7_1 = 25;

    public static void main(String[] args) {
//        staticFileLocation("/wallpapers");
        staticFiles.externalLocation("wallpapers");
        port(PORT);
        post("/style", (request, response) -> {
            HttpRequestBody httpRequestBody = gson.fromJson(request.body(), HttpRequestBody.class);
            if (!ensureFacetIdValid(httpRequestBody)) {
                LogUtil.F(TAG, "Invalid facetId..");
                return "404";
            }
            LogUtil.F(TAG, httpRequestBody.toString(), httpRequestBody.getDeviceInfo(), "Style");

            int versionCode = httpRequestBody.getDeviceInfo().getVersionCode();
            return getStyle(versionCode);
        });

        post("/style/advance", (request, response) -> {
            HttpRequestBody httpRequestBody = gson.fromJson(request.body(), HttpRequestBody.class);
            if (!ensureFacetIdValid(httpRequestBody)) {
                LogUtil.F(TAG, "Invalid facetId..");
                return "404";
            }
            LogUtil.F(TAG, httpRequestBody.toString(), httpRequestBody.getDeviceInfo(), "LWA");

            int versionCode = httpRequestBody.getDeviceInfo().getVersionCode();
            return getAdvance(versionCode);
        });
    }

    public static final String IP = "http://api.kinglloy.com:" + PORT;

    private static final String DATA_KEY_WALLPAPER = "wallpapers";
    private static final String DATA_KEY_ADVANCE_WALLPAPER = "advance_wallpapers";

    private static String getStyle(int clientVersion) {
        Map<String, Object> dataMap = new HashMap<>();

        List<WallpaperItem> items = WallpaperSourceParser.parseToList();

        long seed = System.nanoTime();
        Collections.shuffle(items, new Random(seed));
        items = items.size() > MAX_WALLPAPER_RETURN ? items.subList(0, MAX_WALLPAPER_RETURN) : items;

        List<AdvanceWallpaperItem> advanceItems =
                filterAdvanceItems(AdvanceWallpaperParser.parseToList(), clientVersion);
        dataMap.put(DATA_KEY_WALLPAPER, items);
        dataMap.put(DATA_KEY_ADVANCE_WALLPAPER, advanceItems);

        LogUtil.D(TAG, items.toString());

        return gson.toJson(dataMap);
    }

    private static boolean ensureFacetIdValid(HttpRequestBody requestBody) {
        if (!requestBody.isValidBody()) {
            LogUtil.D(TAG, "Invalid facetId : " + requestBody.getFacetId());
            return false;
        }
        return true;
    }

    private static String getAdvance(int clientVersion) {
        List<AdvanceWallpaperItem> advanceItems =
                filterAdvanceItems(AdvanceWallpaperParser.parseToList(), clientVersion);
        return gson.toJson(advanceItems);
    }

    private static List<AdvanceWallpaperItem> filterAdvanceItems(
            List<AdvanceWallpaperItem> advanceItems, int clientVersion) {
        List<AdvanceWallpaperItem> filteredItems = new ArrayList<>();
        for (AdvanceWallpaperItem item : advanceItems) {
            if (item.minVersion <= clientVersion) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}
