/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.endee.kickscraper.parser;

import info.endee.kickscraper.parser.objects.KickstartWrapper;
import java.util.concurrent.Callable;

/**
 *
 * @author Devids
 */
public class ParserService implements Callable<KickstartWrapper>{
    
    String sort;
    String category;
    int threadCounter;
    int threadOffset;
    int startpage;
    int limit;
    boolean htmlParserEnabled;
    
    ParserService(String sort, String category, int threadCounter, int threadOffset, int startpage, int limit, boolean htmlParserEnabled){
        this.sort = sort;
        this.category = category;
        this.threadCounter = threadCounter;
        this.threadOffset = threadOffset;
        this.startpage = startpage;
        this.limit = limit;
        this.htmlParserEnabled = htmlParserEnabled;     
    }

    @Override
    public KickstartWrapper call() throws Exception {
        Parser parser = new Parser();
        return parser.parse(sort, category, threadCounter, threadOffset, startpage, limit, htmlParserEnabled);
    }
    
}
