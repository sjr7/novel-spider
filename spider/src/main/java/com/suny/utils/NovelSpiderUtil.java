package com.suny.utils;

import com.suny.enums.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;

/**
 * Comments:    小说爬虫的一个工具类，对常用的方法进行封装
 * Author:      孙建荣
 * Create Date: 2017/02/19 20:23
 */
public final class NovelSpiderUtil {

    private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();

    static {
        init();
    }

    private NovelSpiderUtil() {

    }

    /**
     * 初始化方法
     */
    private static void init() {
        // 使用SAX进行解析
        SAXReader saxReader = new SAXReader();
        try {

            Document document = saxReader.read(new File("conf/Spider-Rule.xml"));     //解析的配置文件
            Element root = document.getRootElement();    //获取根元素
            List<Element> sites = root.elements("site");   //获取site标签下的元素
            for (Element site : sites) {
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
     *
     * @param novelSiteEnum 对应的枚举
     * @return 解析规则
     */
    public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
        return CONTEXT_MAP.get(novelSiteEnum);
    }


    /**
     * 合并分段下载的小说章节到一个文件里面
     *
     * @param path           要合并小说txt文件的路径
     * @param mergeToFile    合并的文件
     * @param deleteThisFile 是否要删除分段的小说章节txt文件
     */
    public static void multiFileMerge(String path,
                                      String mergeToFile,
                                      boolean deleteThisFile) {
        //  如果没有传入合并的文件名，则默认为merge.txt
        mergeToFile = mergeToFile == null ? path + "/merge.txt " : mergeToFile;
        File[] files = new File(path).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File pathname, String name) {
                return name.endsWith(".txt");
            }
        });

        // 对分段的小说.txt问价进行排序，然后按照排序的序列号进行合并文件
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
                int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
                if (o1Index > o2Index) {
                    return 1;
                } else if (o1Index == o2Index) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        PrintWriter printWriter = null;
        try {
            // 把合并的文件输出到一个位置，并传入文件编码
            printWriter = new PrintWriter(new File(mergeToFile), "UTF-8");
            for (File file : files) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    printWriter.print(line);
                }
                bufferedReader.close();

                // 如果传入的deleteThisFile为true的话就在合并文件后删除分段的文件
                if (deleteThisFile) {
                    file.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }

    }


}



























