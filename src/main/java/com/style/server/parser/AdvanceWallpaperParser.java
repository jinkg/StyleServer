package com.style.server.parser;

import com.style.server.ChecksumUtil;
import com.style.server.model.AdvanceWallpaperItem;

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

    public static List<AdvanceWallpaperItem> parseToList() {
        List<AdvanceWallpaperItem> wallpapers = new ArrayList<>();

        AdvanceWallpaperItem cubeItem = new AdvanceWallpaperItem();
        cubeItem.name = "旋转立方";
        cubeItem.wallpaperId = UUID.randomUUID().toString();
        cubeItem.link = "www.kinglloy.com/";
        cubeItem.author = "Yalin";
        cubeItem.iconUrl = RES_HOST + "/advance/icons/cube.png";
        cubeItem.downloadUrl = RES_HOST + "/advance/components/cube.component";
        cubeItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/cube.component");
        cubeItem.providerName = "com.yalin.wallpaper.cube.ProviderImpl";

        AdvanceWallpaperItem rainbowItem = new AdvanceWallpaperItem();
        rainbowItem.name = "Rainbow";
        rainbowItem.wallpaperId = UUID.randomUUID().toString();
        rainbowItem.link = "kinglloy.com";
        rainbowItem.author = "Yalin";
        rainbowItem.iconUrl = RES_HOST + "/advance/icons/rainbow.png";
        rainbowItem.downloadUrl = RES_HOST + "/advance/components/rainbow.component";
        rainbowItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/rainbow.component");
        rainbowItem.providerName = "com.yalin.wallpaper.rainbow.ProviderImpl";

        AdvanceWallpaperItem limitlessItem = new AdvanceWallpaperItem();
        limitlessItem.name = "Limitless";
        limitlessItem.wallpaperId = UUID.randomUUID().toString();
        limitlessItem.link = "kinglloy.com";
        limitlessItem.author = "Alexander Fedora";
        limitlessItem.iconUrl = RES_HOST + "/advance/icons/limitless.png";
        limitlessItem.downloadUrl = RES_HOST + "/advance/components/limitless.component";
        limitlessItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/limitless.component");
        limitlessItem.providerName = "com.yalin.wallpaper.limitless.ProviderImpl";

        AdvanceWallpaperItem blurredItem = new AdvanceWallpaperItem();
        blurredItem.name = "Blurred Lines";
        blurredItem.wallpaperId = UUID.randomUUID().toString();
        blurredItem.link = "kinglloy.com";
        blurredItem.author = "Alexander Fedora";
        blurredItem.iconUrl = RES_HOST + "/advance/icons/blurred_line.png";
        blurredItem.downloadUrl = RES_HOST + "/advance/components/blurred_line.component";
        blurredItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/blurred_line.component");
        blurredItem.providerName = "com.yalin.wallpaper.blurred_line.ProviderImpl";

        AdvanceWallpaperItem sunItem = new AdvanceWallpaperItem();
        sunItem.name = "Sun";
        sunItem.wallpaperId = UUID.randomUUID().toString();
        sunItem.link = "kinglloy.com";
        sunItem.author = "Alexander Fedora";
        sunItem.iconUrl = RES_HOST + "/advance/icons/sun.png";
        sunItem.downloadUrl = RES_HOST + "/advance/components/sun.component";
        sunItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/sun.component");
        sunItem.providerName = "com.yalin.wallpaper.sun.ProviderImpl";

        AdvanceWallpaperItem flowerItem = new AdvanceWallpaperItem();
        flowerItem.name = "Flower";
        flowerItem.wallpaperId = UUID.randomUUID().toString();
        flowerItem.link = "kinglloy.com";
        flowerItem.author = "Harism";
        flowerItem.iconUrl = RES_HOST + "/advance/icons/flower.png";
        flowerItem.downloadUrl = RES_HOST + "/advance/components/flower.component";
        flowerItem.checkSum = ChecksumUtil.getChecksum("./wallpapers/advance/components/flower.component");
        flowerItem.providerName = "com.yalin.wallpaper.flower.ProviderImpl";

        wallpapers.add(cubeItem);
        wallpapers.add(rainbowItem);
        wallpapers.add(limitlessItem);
        wallpapers.add(blurredItem);
        wallpapers.add(sunItem);
        wallpapers.add(flowerItem);

        return wallpapers;
    }
}
