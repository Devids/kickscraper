/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.endee.kickscraper.parser.objects;

/**
 *
 * @author Devids
 */
public class Pledge {
    public String value;
    public String backersNum;

    public Pledge() {
    }

    public Pledge(String value, String backersNum) {
        this.value = value;
        this.backersNum = backersNum;
    }
    
    
}
