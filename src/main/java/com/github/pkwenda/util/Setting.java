package com.github.pkwenda.util;

import us.codecraft.webmagic.Site;

public class Setting {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    public static  Site site = Site.me().setRetryTimes(3).setSleepTime(0).setTimeOut(60000 * 5);
}
