/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nivedharajaram
 */
import com.thoughtworks.selenium.DefaultSelenium;
import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import com.thoughtworks.selenium.Selenium;
import java.net.BindException;
import java.net.ServerSocket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeliniumTest {

    public void setUp() throws Exception {
        startSeleniumServer();
    }
    public static SeleniumServer server;

    public static void startSeleniumServer() throws Exception {

        try {
            ServerSocket serverSocket = new ServerSocket(RemoteControlConfiguration.DEFAULT_PORT);
            serverSocket.close();
            //Server not up, start it
            try {
                RemoteControlConfiguration rcc = new RemoteControlConfiguration();
                rcc.setPort(RemoteControlConfiguration.DEFAULT_PORT);
                server = new SeleniumServer(false, rcc);

            } catch (Exception e) {
                System.err.println("Could not create Selenium Server because of: "
                        + e.getMessage());
                e.printStackTrace();
            }
            try {
                server.start();
                System.out.println("Server started");
            } catch (Exception e) {
                System.err.println("Could not start Selenium Server because of: "
                        + e.getMessage());
                e.printStackTrace();
            }
        } catch (BindException e) {
            System.out.println("Selenium server already up, will reuse...");
        }
    }

    public static void stopSeleniumServer(Selenium selenium) {
        selenium.stop();
        if (server != null) {
            try {
                selenium.shutDownSeleniumServer();
                server.stop();

                server = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testRegitrationJspscript() throws Exception {

        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8080/opq1234/Register.jsp");

        WebElement element = driver.findElement(By.id("firstname"));

        element.sendKeys("nivedha");
        element = driver.findElement(By.id("lastname"));
        element.sendKeys("rajaram");
        element = driver.findElement(By.id("email"));
        element.sendKeys("r_nivedha@yahoo.com");
        element = driver.findElement(By.id("password"));
        element.sendKeys("shivam");
        element = driver.findElement(By.id("confirmation"));
        element.sendKeys("shivam");
        element=driver.findElement(By.id("form"));
        element.submit();

        System.out.println("Page title is: " + element.getText().contentEquals("Registration Failed"));

        driver.quit();
    }
}
