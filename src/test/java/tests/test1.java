package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tools.getChromeDriver;
import pages.mainPage;

public class test1 {
    public static WebDriver driver = getChromeDriver.getcromedriver();

    @BeforeTest(enabled = true)
    public void start() {
       driver.get("https://www.google.com/");
    }

    @Parameters({"arrayValues","expectMemory" ,"expectResult"})
    @Test(enabled = true)
    public static void print(String arrayValues, String expectMemory, String expectResult) {
        try {
            mainPage page= new mainPage(driver);
            page.findCalc();
            page.clickButtonCalc(arrayValues);
            Assert.assertEquals(page.checkArray(arrayValues),expectMemory);
            Assert.assertEquals(page.checkExpect(arrayValues,expectResult),expectResult);
            //Thread.sleep(1000);
            } catch (Exception e) {
            System.out.println("что-то гдето поламалось " + e.getMessage());
        }
    }

    @AfterTest(enabled = true)
    public void close() {        driver.close();}
}
