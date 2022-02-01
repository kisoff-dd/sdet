import org.openqa.selenium.chrome.ChromeDriver;

public class getChromeDriver {
    public static ChromeDriver getcromedriver() {
        System.setProperty("webdriver.chrome.driver", "F:\\java\\sdet\\chromedriver\\chromedriver.exe");
        return new ChromeDriver();
    }
}
