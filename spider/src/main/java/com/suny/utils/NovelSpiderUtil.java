package com.suny.utils;

import com.suny.enums.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 孙建荣
 * 2017/02/19 20:23
 */
public final class NovelSpiderUtil {

    private static final java.util.Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();

    static {
        init();
    }

    private NovelSpiderUtil(){

    }

    private static  void init() {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File("conf/Spider-Rule.xml"));
            Element root = document.getRootElement();    //获取根元素
            List<Element> sites = root.elements("site");   //获取site标签下的元素
            for (Element site :sites) {
                List<Element> elements = site.elements();   //获取site下的元素
                Map<String, String> stringMap = new HashMap<>();
                for (Element element : elements) {
                    String name = element.getName();   //获取小说站点名字
                    String text = element.getTextTrim();    //获取文本
                    stringMap.put(name, text);   //放入集合
                }
                CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(stringMap.get("url")), stringMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 拿到网站对应的解析规则
     * @param novelSiteEnum  对应的枚举
     * @return  解析规则
     */
    public static Map<String,String> getContext(NovelSiteEnum novelSiteEnum) {
        return CONTEXT_MAP.get(novelSiteEnum);
    }
}



























