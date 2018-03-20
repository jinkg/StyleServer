package com.style.server;

/**
 * jinyalin
 * on 2017/11/3.
 */
public class Test {
    public static void main(String[] args) {
        String live = Style.getLiveWallpapers(25);
        String style = Style.getStyleWallpapers(25);
        String video = Style.getVideoWallpapers(25);
        String hd = Style.getHDWallpapers(25);

        String lwa = Style.getLWA(25);
        String style1 = Style.getStyle(25);

        System.out.println("-");
    }
}
