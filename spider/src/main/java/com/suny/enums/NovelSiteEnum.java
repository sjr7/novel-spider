package com.suny.enums;

/**
 * Comments:    单独创建一个枚举类，限定已经被支持的小说网站
 * Author:          孙建荣
 * Create Date:     2017/02/19 20:25
 */
public enum NovelSiteEnum {
    DingDianXiaoShuo(1, "23wx.com"),           //顶点小说
    BiQuGe(2, "biquge.tw"),                      //笔趣阁
    KanShuZhong(3, "kanshuzhong.com"),          //看书中
    Bxwx(4, "bxwx9.org");                       //笔下文学

    private int id;             //  枚举对象对应的id
    private String url;         // 枚举对象对应的url地址

    private NovelSiteEnum(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *  通过枚举id返回url地址
     * @param id  枚举id
     * @return
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
     *  通过url返回一个枚举对象
     * @param url   支持的小说网站url地址
     * @return
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

