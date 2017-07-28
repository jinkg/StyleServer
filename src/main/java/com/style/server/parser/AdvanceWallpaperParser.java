package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.AdvanceWallpaperItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * jinyalin
 * on 2017/7/28.
 */
public class AdvanceWallpaperParser {
    public static List<AdvanceWallpaperItem> parseToList() {
        List<AdvanceWallpaperItem> wallpapers = new ArrayList<>();

        AdvanceWallpaperItem item = new AdvanceWallpaperItem();
        item.name = "旋转立方";
        item.wallpaperId = UUID.randomUUID().toString();
        item.link = "http://www.kinglloy.com/";
        item.author = "Yalin";
        item.iconUrl = "http://172.22.158.81:6060/advance/icons/wallpaper_demo.gif";
        item.downloadUrl = "http://172.22.158.81:6060/advance/components/cube.component";
        item.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/cube.component");
        item.providerName = "com.yalin.component1.ProviderImpl";

        AdvanceWallpaperItem item2 = new AdvanceWallpaperItem();
        item2.name = "绿色草地";
        item2.wallpaperId = UUID.randomUUID().toString();
        item2.link = "http://www.kinglloy.com/";
        item2.author = "Yalin";
        item2.iconUrl = "http://172.22.158.81:6060/advance/icons/wallpaper_demo.gif";
        item2.downloadUrl = "http://172.22.158.81:6060/advance/components/blue.component";
        item2.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/blue.component");
        item2.providerName = "com.yalin.component2.ProviderImpl";

        wallpapers.add(item);
        wallpapers.add(item2);

        return wallpapers;
    }
}
