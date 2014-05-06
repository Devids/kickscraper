/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.endee.kickscraper;

import info.endee.kickscraper.exporter.CsvExporter;
import info.endee.kickscraper.parser.objects.KickstartWrapper;
import info.endee.kickscraper.parser.ParserServiceManager;
import java.util.Date;

/**
 *
 * @author Devids
 */
public class KickScraper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sort = null;
        String category = null;
        int threadCounter = 1;
        boolean htmlParserEnabled = false;
        int offset = 0;
        int forcedLimit = 0;
        if (args.length >= 4) {
            sort = args[0];
            category = args[1];
            try {
                threadCounter = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Argument 3 must be an integer");
                System.exit(1);
            }
            try {
                htmlParserEnabled = (Integer.parseInt(args[3])==1?true:false);
            } catch (NumberFormatException e) {
                System.err.println("Argument 4 must be an integer (Enable html parsing 1, disable 0)");
                System.exit(1);
            }
            if (args.length > 4) {
                try {
                    offset = Integer.parseInt(args[4]);
                } catch (NumberFormatException e) {
                    System.err.println("Argument 5 must be an integer");
                    System.exit(1);
                }
            }
            if (args.length > 5) {
                try {
                    forcedLimit = Integer.parseInt(args[5]);
                } catch (NumberFormatException e) {
                    System.err.println("Argument 6 must be an integer");
                    System.exit(1);
                }
            }
        } else {
            System.err.println("Usage: kickstcraper.jar sort category_id threadnumber htmlparsing limit");
                    System.exit(1);
        }
        long start = new Date().getTime();
        KickstartWrapper wrapper = ParserServiceManager.run(sort, category, threadCounter, offset, forcedLimit, htmlParserEnabled);

        CsvExporter csvExporter = new CsvExporter();
        csvExporter.export(wrapper);
        System.out.println("Running time: " + (new Date().getTime() - start) + " msec");
    }
}
