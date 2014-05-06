/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.endee.kickscraper.parser.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Devids
 */
public class Project {
    public int id;
    public String name;
    public String blurb;
    public double goal;
    public double pledged;
    public String state;
    public String slug;
    public boolean disable_communication;
    public String country;
    public String currency;
    public String currency_symbol;
    public boolean currency_trailing_code;
    public long deadline;
    public long state_changed_at;
    public long created_at;
    public long launched_at;
    public int backers_count;
    public Photo photo;
    public Creator creator;
    public Location location;
    public Category category;
    public Urls urls;
    public List<Pledge> pledges = new ArrayList<>();
    public String website = "";
    
}
