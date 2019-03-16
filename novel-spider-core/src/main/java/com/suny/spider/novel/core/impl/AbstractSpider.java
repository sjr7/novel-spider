package com.suny.spider.novel.core.impl;

import com.suny.spider.novel.core.enums.NovelSiteEnum;
import com.suny.spider.novel.core.utils.NovelSpiderHttpGet;
import com.suny.spider.novel.core.utils.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Comments:        抓取页面抽象类
 *
 * @author 孙建荣
 * @date 2017/02/20 20:07
 */
public abstract class AbstractSpider {

    /**
     * 抓取小说网站章节方法
     *
     * @param url 要抓取的url
     * @return 抓取到的内容
     * @throws Exception 抓取文档异常
     */
    protected String crwal(String url) throws Exception {

        // 自动关闭流，就不自己动手去关了
        try (CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse httpResponse = closeableHttpClient.execute(new NovelSpiderHttpGet(url))
        ) {
            //返回抓取的结果
            String result = EntityUtils.toString(httpResponse.getEntity(),
                    NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
            //传入解析的编码d
            return result;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
