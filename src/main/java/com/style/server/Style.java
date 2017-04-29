package com.style.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.style.server.entity.HttpRequestBody;
import com.style.server.log.LogUtil;
import com.style.server.model.WallpaperItem;
import com.style.server.parser.WallpaperSourceParser;

import java.util.*;

import static spark.Spark.*;

/**
 * 34921
 * 2017/1/3.
 */
public class Style {

  private static final String TAG = "Style";

  private static Gson gson = new GsonBuilder().create();

  private static final int MAX_WALLPAPER_RETURN = 3;

  public static void main(String[] args) {
//        staticFileLocation("/wallpapers");
    staticFiles.externalLocation("wallpapers");
    port(6060);
    post("/style", (request, response) -> {
      HttpRequestBody httpRequestBody = gson.fromJson(request.body(), HttpRequestBody.class);
      if (!ensureFacetIdValid(httpRequestBody)) {
        return "404";
      }
      LogUtil.F(TAG, httpRequestBody.toString(), httpRequestBody.getDeviceInfo());
      return getStyle();
    });
    get("/test", (request, response) -> getStyle());
  }

  public static final String IP = "http://www.kinglloy.com:6060";

  private static final String DATA_KEY_WALLPAPER = "wallpapers";

  private static String getStyle() {
    Map<String, Object> dataMap = new HashMap<>();

    List<WallpaperItem> items = WallpaperSourceParser.parseToList();

    long seed = System.nanoTime();
    Collections.shuffle(items, new Random(seed));
    items = items.size() > MAX_WALLPAPER_RETURN ? items.subList(0, MAX_WALLPAPER_RETURN) : items;

    dataMap.put(DATA_KEY_WALLPAPER, items);

    LogUtil.D(TAG, items.toString());
    System.out.println("------------");

    return gson.toJson(dataMap);
  }

  private static boolean ensureFacetIdValid(HttpRequestBody requestBody) {
    if (!requestBody.isValidBody()) {
      LogUtil.D(TAG, "Invalid facetId : " + requestBody.getFacetId());
      return false;
    }
    return true;
  }
}
