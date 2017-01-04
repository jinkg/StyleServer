
import com.google.gson.Gson;
import model.WallpaperItem;

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
        WallpaperItem[] itemArray = new WallpaperItem[]{
                new WallpaperItem(UUID.randomUUID().toString(),
                        "http://172.16.3.189:6060/20170102.jpg",
                        "风景", "Theo van Rysselberghe, 1892", "wikiart.org"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "http://172.16.3.189:6060/20170103.jpg",
                        "风景", "Theo van Rysselberghe, 1892", "wikiart.org"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        "http://172.16.3.189:6060/20170104.jpg",
                        "风景", "Theo van Rysselberghe, 1892", "wikiart.org")
        };

        Map<String, Object> dataMap = new HashMap<>();

        List<WallpaperItem> items = new ArrayList<>();

        int index = new Random().nextInt(itemArray.length);
        items.add(itemArray[index]);

        dataMap.put(DATA_KEY_WALLPAPER, items);

        return new Gson().toJson(dataMap);
    }
}
