package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.apache.http.Header;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    private Map<String,Page> map;

    public MyCrawler() {
        this.map = new HashMap<>();
    }

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of image extensions.
        if (IMAGE_EXTENSIONS.matcher(href).matches()) {

            return false;
        }

        // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
        return href.startsWith("https://www.thss.tsinghua.edu.cn/faculty/")||
                href.startsWith("http://faculty.hust.edu.cn/")||
                href.startsWith("https://www.eecs.mit.edu/people/");
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        if (page.getParseData() instanceof HtmlParseData) {

            String url = page.getWebURL().getURL();
            System.out.println("----------------------------------------------------");
            System.out.println(url);
            System.out.println("----------------------------------------------------");
            page.setContentData(null);
            this.map.put(url, page);

        }
    }
    @Override
    public Object getMyLocalData() {
        return map;
    }
}