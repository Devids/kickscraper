/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.endee.kickscraper.parser;

import com.google.gson.Gson;
import info.endee.kickscraper.parser.objects.KickstartWrapper;
import info.endee.kickscraper.parser.objects.Pledge;
import info.endee.kickscraper.parser.objects.Project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Devids
 */
public class Parser {

    public KickstartWrapper getData(String page, String sort, String category, boolean htmlParserEnabled) {
        URL url = null;
        String surl = "https://www.kickstarter.com/discover/advanced?google_chrome_workaround&page=" + page + "&category_id=" + category + "&sort=" + sort;
        KickstartWrapper wrapper = null;

        try {
            url = new URL(surl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        BufferedReader in;
        try {
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
            con.addRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            con.addRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setReadTimeout(5000); //5 second
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));


            wrapper = gson.fromJson(in, KickstartWrapper.class);
            in.close();

            if (htmlParserEnabled) {
                for (Project project : wrapper.projects) {
                    Connection connection = Jsoup.connect(project.urls.web.project);
                    connection.timeout(20000);
                    Document doc = connection.get();
                    Elements badges = doc.getElementsByClass("NS-projects-reward");
                    for (Element badge : badges) {
                        try{
                        Elements h5 = badge.getElementsByTag("h5");
                        Element pledgeValue = h5.get(0).getElementsByTag("span").get(0);

                        Element backersNum = badge.getElementsByClass("backers-limits").get(0).getElementsByClass("backers-wrap").get(0).getElementsByClass("num-backers").get(0);

                        project.pledges.add(new Pledge(pledgeValue.text(), backersNum.text()));
                        } catch (IndexOutOfBoundsException e) {
                            //
                        }
                    }
                    try {
                        Element website = doc.getElementById("creator-details").getElementsByClass("links").get(0).getElementsByTag("a").get(0);

                        project.website = website.attr("href");
                    } catch (Exception e) {
                        //Logger.getLogger(Parser.class.getName()).log(Level.INFO, "Website not found: " + project.id);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return wrapper;

    }

    public KickstartWrapper parse(String sort, String category, int threadCounter, int threadOffset, int startpage, int limit, boolean htmlParserEnabled) {
        KickstartWrapper wrapper = new KickstartWrapper();
        for (int i = startpage + threadOffset; i < ((int) Math.ceil(limit / 20.0)) + 1; i += threadCounter) {
//        for (int i = 1+threadOffset; i < limit; i+=threadCounter) {
            KickstartWrapper wrapper1 = getData(String.valueOf(i), sort, category, htmlParserEnabled);
            wrapper.projects.addAll(wrapper1.projects);
            wrapper.total_hits = wrapper1.total_hits;
            System.out.println((i - 1) * 20 + "-" + i * 20 + " done");
        }
        return wrapper;
    }
}
