package model;

/**
 * 34921
 * 2017/1/3.
 */
public class WallpaperItem {
    public String wallpaperId;
    public String imageUri;
    public String title;
    public String byline;
    public String attribution;

    public WallpaperItem(String wallpaperId, String imageUri, String title, String byline, String attribution) {
        this.wallpaperId = wallpaperId;
        this.imageUri = imageUri;
        this.title = title;
        this.byline = byline;
        this.attribution = attribution;
    }
}
