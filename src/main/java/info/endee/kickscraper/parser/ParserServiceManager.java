/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.endee.kickscraper.parser;

import info.endee.kickscraper.parser.objects.KickstartWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Devids
 */
public class ParserServiceManager {

    public static KickstartWrapper run(String sort, String category, int threadCounter, int offset, int forcedLimit, boolean htmlParserEnabled) {
        Parser parser = new Parser();
        int startpage = offset == 0 ? 1 : 1 + (int) Math.floor((offset-1) / 20.0);
        KickstartWrapper wrapper = parser.parse(sort, category, 1, 0, startpage, startpage*20, htmlParserEnabled);
        ExecutorService service = Executors.newFixedThreadPool(threadCounter);

        ArrayList<Callable<KickstartWrapper>> callableList = new ArrayList<>();
        int limit = forcedLimit == 0 ? wrapper.total_hits : (forcedLimit > wrapper.total_hits ? wrapper.total_hits : forcedLimit);

        for (int i = 0; i < threadCounter; i++) {
            callableList.add(new ParserService(sort, category, threadCounter, i + 1, startpage, limit, htmlParserEnabled));
        }

        try {
            List<Future<KickstartWrapper>> futureList = service.invokeAll(callableList);
            KickstartWrapper tempWrapper;
            for (Future<KickstartWrapper> future : futureList) {
                tempWrapper = future.get();
                wrapper.projects.addAll(tempWrapper.projects);
            }
        } catch (final InterruptedException ex) {
            ex.printStackTrace();
        } catch (final ExecutionException ex) {
            ex.printStackTrace();
        }
        service.shutdownNow();

        return wrapper;
    }
}
