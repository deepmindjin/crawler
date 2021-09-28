package swoop.cream.control;

import org.apache.commons.validator.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Crawler {
    private static String rootUrl = "https://github.com/deepmindjin/crawler";
    private static ArrayList<Pair> urlFarm = new ArrayList<>();

    private static int cnt = 1;
    private static final int CRAWL_MAX = 100;

    private static String[] schemes = {"http","https"};
    private static UrlValidator urlValidator = new UrlValidator(schemes);

    public static void main(String[] args) throws IOException {
        urlFarm.add(new Pair(rootUrl, 1));
        int depth = 0;
        int idx = 0;
        while(cnt < CRAWL_MAX) {
            String targetUrl = urlFarm.get(idx++).str;
            Document doc = Jsoup.connect(targetUrl).get();
            System.out.println("title : " + doc.title());
            depth++;

            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String nextUrl = link.attr("href");
                if (urlValidator.isValid(nextUrl)) {
                    System.out.println("\nlink : " + nextUrl);
                    System.out.println("text : " + link.text());
                    System.out.println("depth : " + depth);
                    urlFarm.add(new Pair(link.attr("href"), depth));
                    cnt++;
                }
            }
        }
    }

    public static class Pair {
        String str;
        Integer val;

        public Pair(String str, Integer val) {
            this.str = str;
            this.val = val;
        }
    }
}
