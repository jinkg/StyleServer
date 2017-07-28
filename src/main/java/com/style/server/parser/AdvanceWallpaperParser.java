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
        item.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/cube.component");
        item.providerName = "com.yalin.component.ProviderImpl";
        wallpapers.add(item);

        return wallpapers;
    }
}
