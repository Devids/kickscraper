/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.endee.kickscraper.exporter;

import au.com.bytecode.opencsv.CSVWriter;
import info.endee.kickscraper.parser.objects.KickstartWrapper;
import info.endee.kickscraper.parser.objects.Pledge;
import info.endee.kickscraper.parser.objects.Project;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devids
 */
public class CsvExporter {

    public void export(KickstartWrapper wrapper) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("export.csv"), ';');
            String[] header = {"name", "blurb", "goal", "pledged", "state", "slug", "country", "currency", "deadline", "created_at", "launched_at", "state_changed_at", "creator_name", "creator_url", "location_name", "location_country", "location_state", "category", "url", "website"};
            writer.writeNext(header);

            for (Project project : wrapper.projects) {
                ArrayList<String> aList = new ArrayList<>();
                aList.add(project.name);
                aList.add(project.blurb);
                aList.add(String.valueOf(project.goal));
                aList.add(String.valueOf(project.pledged));
                aList.add(project.state);
                aList.add(project.slug);
                aList.add(project.country);
                aList.add(project.currency);
                aList.add(String.valueOf(project.deadline));
                aList.add(String.valueOf(project.created_at));
                aList.add(String.valueOf(project.launched_at));
                aList.add(String.valueOf(project.state_changed_at));
                aList.add(project.creator.name);
                aList.add(project.creator.urls.web.user);
                if (project.location != null) {
                    aList.add(project.location.name);
                    aList.add(project.location.country);
                    aList.add(project.location.state);
                } else {
                    aList.add("");
                    aList.add("");
                    aList.add("");
                }
                aList.add(project.category.name);
                aList.add(project.urls.web.project);
                aList.add(project.website);

                for (Pledge pledge : project.pledges) {
                    aList.add(pledge.value);
                    aList.add(pledge.backersNum);
                }

                writer.writeNext(aList.toArray(new String[1]));
            }

            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CsvExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
