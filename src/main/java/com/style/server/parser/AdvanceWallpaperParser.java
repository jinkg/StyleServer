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
    private static final String RES_HOST = "http://172.22.158.80:6060";

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
        cubeItem.providerName = "com.yalin.wallpaper.cube.ProviderImpl";

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

        AdvanceWallpaperItem dripplerItem = new AdvanceWallpaperItem();
        dripplerItem.name = "Drippler";
        dripplerItem.wallpaperId = UUID.randomUUID().toString();
        dripplerItem.link = "kinglloy.com";
        dripplerItem.author = "Yalin";
        dripplerItem.iconUrl = RES_HOST + "/advance/icons/rainbow.png";
        dripplerItem.downloadUrl = RES_HOST + "/advance/components/drippler.component";
        dripplerItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/drippler.component");
        dripplerItem.providerName = "com.yalin.wallpaper.drippler.ProviderImpl";

        AdvanceWallpaperItem limitlessItem = new AdvanceWallpaperItem();
        limitlessItem.name = "Limitless";
        limitlessItem.wallpaperId = UUID.randomUUID().toString();
        limitlessItem.link = "kinglloy.com";
        limitlessItem.author = "Alexander Fedora";
        limitlessItem.iconUrl = RES_HOST + "/advance/icons/limitless.png";
        limitlessItem.downloadUrl = RES_HOST + "/advance/components/limitless.component";
        limitlessItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/limitless.component");
        limitlessItem.providerName = "com.yalin.wallpaper.limitless.ProviderImpl";

        wallpapers.add(cubeItem);
        wallpapers.add(blueItem);
        wallpapers.add(pointItem);
        wallpapers.add(rainbowItem);
        wallpapers.add(dripplerItem);
        wallpapers.add(limitlessItem);

        return wallpapers;
    }
}
