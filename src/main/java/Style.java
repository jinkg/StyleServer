
import com.google.gson.Gson;
import model.WallpaperItem;
import spark.Spark;

import java.util.*;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

/**
 * 34921
 * 2017/1/3.
 */
public class Style {
    public static void main(String[] args) {
        staticFileLocation("/public");
        port(6060);
        get("/style", (request, response) -> getStyle());

    }

    private static final String DATA_KEY_WALLPAPER = "wallpapers";

    private static String getStyle() {
        Map<String, Object> dataMap = new HashMap<>();

        List<WallpaperItem> items = new ArrayList<>();

        items.add(new WallpaperItem(UUID.randomUUID().toString(),
                "http://172.16.3.189:6060/20170102.jpg",
                "风景", "Theo van Rysselberghe, 1892", "wikiart.org"));
//        items.add(new WallpaperItem(UUID.randomUUID().toString(),
//                "http://wallpapersqq.net/wp-content/uploads/2015/12/Gintoki-Sakata-abstract.jpg",
//                "死神", "Yalin", "attribution"));
//        items.add(new WallpaperItem(UUID.randomUUID().toString(),
//                "http://labs3.kentooz.com/simplex/wp-content/uploads/2013/04/windows-vista-wallpaper-182.jpg",
//                "蓝色", "Yalin", "attribution"));

        dataMap.put(DATA_KEY_WALLPAPER, items);

        return new Gson().toJson(dataMap);
    }
}
