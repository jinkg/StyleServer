package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.LiveWallpaperItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.style.server.Style.IP;

/**
 * jinyalin
 * on 2017/7/28.
 */
public class AdvanceWallpaperParser {
    private static final String RES_HOST = IP;

    private static final String COMPONENT_SOURCE_FILE = "./component.txt";
    private static final String COMPONENT_ROOT_DIR = "./wallpapers";
    private static final String COMPONENT_SOURCE_DIR = "/advance/components/";
    private static final String COMPONENT_ICON_DIR = "/advance/icons/";
    private static final int FIELD_COUNT = 8;

    private static final long CACHE_VALID_TIMEOUT = 2 * 60 * 60 * 1000L;

    private static final List<LiveWallpaperItem> mCachedWallpaper = new ArrayList<>();
    private static long lastRefreshCacheTime;

    private static boolean maybeInvalidCache() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastRefreshCacheTime > CACHE_VALID_TIMEOUT;
    }

    public static synchronized List<LiveWallpaperItem> parseToList() {
        if (!maybeInvalidCache()) {
            return mCachedWallpaper;
        }

        List<LiveWallpaperItem> wallpaperItems = new ArrayList<>();
        ArrayList<String[]> itemsInfo = FileParser.parseFile(COMPONENT_SOURCE_FILE);
        for (String[] fields : itemsInfo) {
            LiveWallpaperItem item = parseFields(fields);
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

    private static LiveWallpaperItem parseFields(String[] wallpaperFields) {
        if (wallpaperFields != null && wallpaperFields.length == FIELD_COUNT) {
            LiveWallpaperItem item = new LiveWallpaperItem();
            String filename = wallpaperFields[1].trim();
            item.name = wallpaperFields[0].trim();
            item.downloadUrl = RES_HOST + COMPONENT_SOURCE_DIR + filename;
            item.iconUrl = RES_HOST + COMPONENT_ICON_DIR + wallpaperFields[2].trim();
            item.providerName = wallpaperFields[3].trim();
            item.author = wallpaperFields[4].trim();
            item.lazyDownload = Integer.parseInt(wallpaperFields[5].trim()) == 1;
            item.needAd = Integer.parseInt(wallpaperFields[6].trim()) == 1;
            item.wallpaperId = UUID.randomUUID().toString();
            item.link = "kinglloy.com";
            item.minVersion = Integer.parseInt(wallpaperFields[7].trim());
            String componentFile = COMPONENT_ROOT_DIR + COMPONENT_SOURCE_DIR + filename;
            item.size = new File(componentFile).length();

            item.checkSum = ChecksumUtil.getChecksum(componentFile);

            if (item.checkSum != null && item.checkSum.length() > 0) {
                return item;
            }
        }
        return null;
    }
}
