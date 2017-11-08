package com.style.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.style.server.entity.HttpRequestBody;
import com.style.server.log.LogUtil;
import com.style.server.model.LiveWallpaperItem;
import com.style.server.model.StyleWallpaperItem;
import com.style.server.model.VideoWallpaperItem;
import com.style.server.parser.LiveWallpaperParser;
import com.style.server.parser.StyleWallpaperSourceParser;
import com.style.server.parser.VideoWallpaperParser;

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
            return getLiveWallpapers(versionCode);
        });

        post("/style/style_wallpaper", (request, response) -> {
            HttpRequestBody httpRequestBody = gson.fromJson(request.body(), HttpRequestBody.class);
            if (!ensureFacetIdValid(httpRequestBody)) {
                LogUtil.F(TAG, "Invalid facetId..");
                return "404";
            }
            LogUtil.F(TAG, httpRequestBody.toString(), httpRequestBody.getDeviceInfo(), "LWA");

            int versionCode = httpRequestBody.getDeviceInfo().getVersionCode();
            return getStyleWallpapers(versionCode);
        });

        post("/style/video_wallpaper", (request, response) -> {
            HttpRequestBody httpRequestBody = gson.fromJson(request.body(), HttpRequestBody.class);
            if (!ensureFacetIdValid(httpRequestBody)) {
                LogUtil.F(TAG, "Invalid facetId..");
                return "404";
            }
            LogUtil.F(TAG, httpRequestBody.toString(), httpRequestBody.getDeviceInfo(), "LWA");

            int versionCode = httpRequestBody.getDeviceInfo().getVersionCode();
            return getVideoWallpapers(versionCode);
        });

        post("/style/lwa", (request, response) -> {
            HttpRequestBody httpRequestBody = gson.fromJson(request.body(), HttpRequestBody.class);
            if (!ensureFacetIdValid(httpRequestBody)) {
                LogUtil.F(TAG, "Invalid facetId..");
                return "404";
            }
            LogUtil.F(TAG, httpRequestBody.toString(), httpRequestBody.getDeviceInfo(), "LWA");

            int versionCode = httpRequestBody.getDeviceInfo().getVersionCode();
            return getLWA(versionCode);
        });


    }

    public static final String IP = "http://api.kinglloy.com:" + PORT;

    private static final String DATA_KEY_WALLPAPER = "wallpapers";
    private static final String DATA_KEY_ADVANCE_WALLPAPER = "advance_wallpapers";

    private static final String DATA_KEY_LIVE_WALLPAPER = "live_wallpapers";
    private static final String DATA_KEY_STYLE_WALLPAPER = "style_wallpapers";
    private static final String DATA_KEY_VIDEO_WALLPAPER = "video_wallpapers";

    static String getStyle(int clientVersion) {
        Map<String, Object> dataMap = new HashMap<>();

        List<StyleWallpaperItem> items = StyleWallpaperSourceParser.parseToList();

        long seed = System.nanoTime();
        Collections.shuffle(items, new Random(seed));
        items = items.size() > MAX_WALLPAPER_RETURN ? items.subList(0, MAX_WALLPAPER_RETURN) : items;

        List<LiveWallpaperItem> advanceItems =
                filterAdvanceItems(LiveWallpaperParser.parseToList(), clientVersion);
        dataMap.put(DATA_KEY_WALLPAPER, items);
        dataMap.put(DATA_KEY_ADVANCE_WALLPAPER, advanceItems);

        LogUtil.D(TAG, items.toString());

        return gson.toJson(dataMap);
    }

    private static String LWAStyleSourceFile = "./style_wallpapers.txt";

    static String getLWA(int clientVersion) {
        Map<String, Object> dataMap = new HashMap<>();

        List<StyleWallpaperItem> styleItems = StyleWallpaperSourceParser.parseToList(LWAStyleSourceFile);
        List<VideoWallpaperItem> videoItems = VideoWallpaperParser.parseToList();
        List<LiveWallpaperItem> liveItems =
                filterAdvanceItems(LiveWallpaperParser.parseToList(), clientVersion);
        dataMap.put(DATA_KEY_LIVE_WALLPAPER, liveItems);
        dataMap.put(DATA_KEY_STYLE_WALLPAPER, styleItems);
        dataMap.put(DATA_KEY_VIDEO_WALLPAPER, videoItems);

        return gson.toJson(dataMap);
    }

    private static boolean ensureFacetIdValid(HttpRequestBody requestBody) {
        if (!requestBody.isValidBody()) {
            LogUtil.D(TAG, "Invalid facetId : " + requestBody.getFacetId());
            return false;
        }
        return true;
    }

    static String getLiveWallpapers(int clientVersion) {
        List<LiveWallpaperItem> advanceItems =
                filterAdvanceItems(LiveWallpaperParser.parseToList(), clientVersion);
        return gson.toJson(advanceItems);
    }

    static String getStyleWallpapers(int clientVersion) {
        List<StyleWallpaperItem> items = StyleWallpaperSourceParser.parseToList(LWAStyleSourceFile);
        return gson.toJson(items);
    }

    static String getVideoWallpapers(int clientVersion) {
        List<VideoWallpaperItem> items = VideoWallpaperParser.parseToList();
        return gson.toJson(items);
    }

    private static List<LiveWallpaperItem> filterAdvanceItems(
            List<LiveWallpaperItem> advanceItems, int clientVersion) {
        List<LiveWallpaperItem> filteredItems = new ArrayList<>();
        for (LiveWallpaperItem item : advanceItems) {
            if (item.minVersion <= clientVersion) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}
