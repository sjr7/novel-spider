package com.suny.spider.novel.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 单独创建一个枚举类，限定已经被支持的小说网站
 *
 * @author sunjianrong
 * @date 2017/02/19 20:25
 */
@AllArgsConstructor
@Getter
public enum NovelSiteEnum {
    /**
     * 顶点小说
     */
    DingDianXiaoShuo(1, "23wx.com"),
    /**
     * 笔趣阁
     */
    BiQuGe(2, "biquge.tw"),
    /**
     * 看书中
     */
    KanShuZhong(3, "kanshuzhong.com"),
    /**
     * 笔下文学
     */
    Bxwx(4, "bxwx9.org");

    /**
     * 枚举对象对应的id
     */
    private int id;
    /**
     * 枚举对象对应的url地址
     */
    private String url;


    /**
     * 通过枚举id返回url地址
     *
     * @param id 枚举id
     * @return url 地址
     */
    public static NovelSiteEnum getEnumById(int id) {
        switch (id) {
            case 1:
                return DingDianXiaoShuo;
            case 2:
                return BiQuGe;
            default:
                throw new RuntimeException("id=" + id + "is not support");

        }
    }


    /**
     * 通过url返回一个枚举对象
     *
     * @param url 支持的小说网站url地址
     * @return 枚举
     */
    public static NovelSiteEnum getEnumByUrl(String url) {
        for (NovelSiteEnum novelSiteEnum : values()) {
            if (url.contains(novelSiteEnum.url)) {
                return novelSiteEnum;
            }
        }
        throw new RuntimeException(" url=" + url + "  is not support");
    }
}

