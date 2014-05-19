/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nivedharajaram
 */
import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

public class SeliniumTest extends SeleneseTestCase {

    public void setUp() throws Exception {
        setUp("http://localhost:8080/opq1234/", "*.jsp");
    }

    public void testRegitrationJspscript() throws Exception {

        selenium.waitForPageToLoad("30000");
        selenium.type("name=firstname", "nivedha");
        selenium.type("name=lastname", "rajaram");
        selenium.type("name=email", "r_nivedha@yahoo.com");
        selenium.type("name=password", "shivam");
        selenium.type("name=confirmation", "shivam1");
        selenium.click("name=submit");
        selenium.waitForPageToLoad("100000");
        assertTrue(selenium.getBodyText().contentEquals("Registration Failed"));

    }

}
