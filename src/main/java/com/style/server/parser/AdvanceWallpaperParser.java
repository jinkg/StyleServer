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
    private static final String RES_HOST = "http://172.22.158.86:6060";

    public static List<AdvanceWallpaperItem> parseToList() {
        List<AdvanceWallpaperItem> wallpapers = new ArrayList<>();

        AdvanceWallpaperItem cubeItem = new AdvanceWallpaperItem();
        cubeItem.name = "旋转立方";
        cubeItem.wallpaperId = UUID.randomUUID().toString();
        cubeItem.link = "http://www.kinglloy.com/";
        cubeItem.author = "Yalin";
        cubeItem.iconUrl = RES_HOST + "/advance/icons/cube.png";
        cubeItem.downloadUrl = RES_HOST + "/advance/components/cube.component";
        cubeItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/cube.component");
        cubeItem.providerName = "com.yalin.component1.ProviderImpl";

        AdvanceWallpaperItem blueItem = new AdvanceWallpaperItem();
        blueItem.name = "绿色草地";
        blueItem.wallpaperId = UUID.randomUUID().toString();
        blueItem.link = "http://www.kinglloy.com/";
        blueItem.author = "Yalin";
        blueItem.iconUrl = RES_HOST + "/advance/icons/the_blue.png";
        blueItem.downloadUrl = RES_HOST + "/advance/components/blue.component";
        blueItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/blue.component");
        blueItem.providerName = "com.yalin.component2.ProviderImpl";

        AdvanceWallpaperItem pointItem = new AdvanceWallpaperItem();
        pointItem.name = "点点点";
        pointItem.wallpaperId = UUID.randomUUID().toString();
        pointItem.link = "http://www.kinglloy.com/";
        pointItem.author = "Yalin";
        pointItem.iconUrl = RES_HOST + "/advance/icons/the_blue.png";
        pointItem.downloadUrl = RES_HOST + "/advance/components/point.component";
        pointItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/point.component");
        pointItem.providerName = "com.yalin.component.point.ProviderImpl";

        AdvanceWallpaperItem rainbowItem = new AdvanceWallpaperItem();
        rainbowItem.name = "Rainbow";
        rainbowItem.wallpaperId = UUID.randomUUID().toString();
        rainbowItem.link = "kinglloy.com";
        rainbowItem.author = "Yalin";
        rainbowItem.iconUrl = RES_HOST + "/advance/icons/rainbow.png";
        rainbowItem.downloadUrl = RES_HOST + "/advance/components/rainbow.component";
        rainbowItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/rainbow.component");
        rainbowItem.providerName = "com.yalin.wallpaper.rainbow.ProviderImpl";

        wallpapers.add(cubeItem);
        wallpapers.add(blueItem);
        wallpapers.add(pointItem);
        wallpapers.add(rainbowItem);

        return wallpapers;
    }
}
