package com.example;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import com.example.MyCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import com.example.Teacher;
public class Controller {
    public static void main(String[] args) throws Exception{

        List<HashMap<String,Object>> webPageMapList =crawlerConfigAndRun();
        Vector<Teacher> teachers =new Vector<>();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new ParseHtmlThread(teachers,webPageMapList.get(0)));
        executor.submit(new ParseHtmlThread(teachers,webPageMapList.get(1)));
        executor.submit(new ParseHtmlThread(teachers,webPageMapList.get(2)));

        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) {
                break;
            }
            Thread.sleep(200);
        }


        Iterator<Teacher> it=teachers.iterator();
        while(it.hasNext()){
            if(it.next().name.equals("")){
                it.remove();
            }
        }

        String path="D:/Desktop/data";
        FileOperate.excelWrite(path,teachers);

//        Vector<Teacher> teachers=FileOperate.excelRead(path);

        MatchRsArea matcher=new MatchRsArea(teachers);
        matcher.match("人工智能");
        matcher.match("分布式");
        matcher.match("大数据");
        matcher.match("云计算");
    }

    public static List crawlerConfigAndRun(){
        CrawlConfig config = new CrawlConfig();

        // Set the folder where intermediate crawl data is stored (e.g. list of urls that are extracted from previously
        // fetched pages and need to be crawled later).
        config.setCrawlStorageFolder("/tmp/crawler4j/");

        // Be polite: Make sure that we don't send more than 1 request per second (1000 milliseconds between requests).
        // Otherwise it may overload the target servers.
        config.setPolitenessDelay(1000);

        // You can set the maximum crawl depth here. The default value is -1 for unlimited depth.
        config.setMaxDepthOfCrawling(1);

        // You can set the maximum number of pages to crawl. The default value is -1 for unlimited number of pages.
        config.setMaxPagesToFetch(1000);

        // Should binary data should also be crawled? example: the contents of pdf, or the metadata of images etc
        config.setIncludeBinaryContentInCrawling(false);

        // Do you need to set a proxy? If so, you can use:
        // config.setProxyHost("proxyserver.example.com");
        // config.setProxyPort(8080);

        // If your proxy also needs authentication:
        // config.setProxyUsername(username); config.getProxyPassword(password);

        // This config parameter can be used to set your crawl to be resumable
        // (meaning that you can resume the crawl from a previously
        // interrupted/crashed crawl). Note: if you enable resuming feature and
        // want to start a fresh crawl, you need to delete the contents of
        // rootFolder manually.
        config.setResumableCrawling(false);

        // Set this to true if you want crawling to stop whenever an unexpected error
        // occurs. You'll probably want this set to true when you first start testing
        // your crawler, and then set to false once you're ready to let the crawler run
        // for a long time.
//        config.setHaltOnError(true);

        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        config.setIncludeHttpsPages(true);
        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages

        controller.addSeed("https://www.thss.tsinghua.edu.cn/szdw/jsml.htm");
        controller.addSeed("http://www.cs.hust.edu.cn/szdw/jsml/axmpyszmlb.htm#");
        controller.addSeed("https://www.eecs.mit.edu/role/faculty-cs/");


        // Number of threads to use during crawling. Increasing this typically makes crawling faster. But crawling
        // speed depends on many other factors as well. You can experiment with this to figure out what number of
        // threads works best for you.
        int numberOfCrawlers = 3;


        // The factory which creates instances of crawlers.
        CrawlController.WebCrawlerFactory<MyCrawler> factory = () -> new MyCrawler();

        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);
        return controller.getCrawlersLocalData();

    }
}
