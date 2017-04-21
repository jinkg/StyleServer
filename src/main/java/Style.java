
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

    private static final String IP = "http://www.kinglloy.com:6060";

    private static final String DATA_KEY_WALLPAPER = "wallpapers";

    private static String getStyle() {
        WallpaperItem[] itemArray = new WallpaperItem[]{
                new WallpaperItem(UUID.randomUUID().toString(),
                        IP + "/series-i-no-3.jpg",
                        "Series I, No. 3", "Georgia O'Keeffe, 1918", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        IP + "/blue-02.jpg",
                        "Blue-02", "Georgia O'Keeffe, 1916", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        IP + "/blue-morning-glories.jpg",
                        "Blue Morning Glories", "Georgia O'Keeffe, 1935", "kinglloy.com"),
                new WallpaperItem(UUID.randomUUID().toString(),
                        IP + "/bleeding-heart.jpg",
                        "Bleeding Heart", "Georgia O'Keeffe, 1932", "kinglloy.com")
        };

        Map<String, Object> dataMap = new HashMap<>();

        List<WallpaperItem> items = new ArrayList<>();

        int index = new Random().nextInt(itemArray.length);
        items.add(itemArray[index]);
//        items.add(itemArray[0]);
//        items.add(itemArray[1]);
//        items.add(itemArray[2]);

        dataMap.put(DATA_KEY_WALLPAPER, items);

        System.out.println("Select wallpaper : " + itemArray[index].imageUri);

        return new Gson().toJson(dataMap);
    }
}
