package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.VideoWallpaperItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.style.server.Style.IP;

/**
 * jinyalin
 * on 2017/7/28.
 */
public class VideoWallpaperParser {
    private static final String RES_HOST = IP;

    private static final String WALLPAPER_SOURCE_FILE = "./video_wallpapers.txt";
    private static final String WALLPAPER_ROOT_DIR = "./wallpapers";
    private static final String WALLPAPER_SOURCE_DIR = "/video/files/";
    private static final String WALLPAPER_ICON_DIR = "/video/icons/";
    private static final int FIELD_COUNT = 6;

    private static final long CACHE_VALID_TIMEOUT = 2 * 60 * 60 * 1000L;

    private static final List<VideoWallpaperItem> mCachedWallpaper = new ArrayList<>();
    private static long lastRefreshCacheTime;

    private static boolean maybeInvalidCache() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastRefreshCacheTime > CACHE_VALID_TIMEOUT;
    }

    public static synchronized List<VideoWallpaperItem> parseToList() {
        if (!maybeInvalidCache()) {
            return mCachedWallpaper;
        }

        List<VideoWallpaperItem> wallpaperItems = new ArrayList<>();
        ArrayList<String[]> itemsInfo = FileParser.parseFile(WALLPAPER_SOURCE_FILE);
        for (String[] fields : itemsInfo) {
            VideoWallpaperItem item = parseFields(fields);
            if (item != null) {
                wallpaperItems.add(item);
            }
        }

        if (!itemsInfo.isEmpty()) {
            mCachedWallpaper.clear();
            mCachedWallpaper.addAll(wallpaperItems);
            lastRefreshCacheTime = System.currentTimeMillis();
        }
        return wallpaperItems;
    }

    private static VideoWallpaperItem parseFields(String[] wallpaperFields) {
        if (wallpaperFields != null && wallpaperFields.length == FIELD_COUNT) {
            VideoWallpaperItem item = new VideoWallpaperItem();
            String filename = wallpaperFields[1].trim();
            item.name = wallpaperFields[0].trim();
            item.videoUri = RES_HOST + WALLPAPER_SOURCE_DIR + filename;
            item.iconUri = RES_HOST + WALLPAPER_ICON_DIR + wallpaperFields[2].trim();
            item.author = wallpaperFields[3].trim();
            item.pro = Integer.parseInt(wallpaperFields[4].trim()) == 1;
            item.byline = wallpaperFields[5].trim();
            item.wallpaperId = UUID.randomUUID().toString();
            String componentFile = WALLPAPER_ROOT_DIR + WALLPAPER_SOURCE_DIR + filename;
            item.size = new File(componentFile).length();

            item.checksum = ChecksumUtil.getChecksum(componentFile);

            if (item.checksum != null && item.checksum.length() > 0) {
                return item;
            }
        }
        return null;
    }
}
