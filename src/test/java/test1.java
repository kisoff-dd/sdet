import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class test1 {

    WebDriver driver = getChromeDriver.getcromedriver();

    @BeforeTest(enabled = true)
    public void start() {
       driver.get("https://www.google.com/");
       }

    @Parameters({"arrayValues"})
    @Test(enabled = true)
    public void print(String arrayValues) {
        WebElement element = driver.findElement(By.name("q")); // Находим элемент по атрибуту name и вводим текст
        element.sendKeys("калькулятор");
        element.submit(); //нажамаем кнопку поиска
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//ожидаем полной загрузки старницы
        String[] array = arrayValues.split("");//переводим текстовый параметр теста в массив символов
        for (int i=0; i < array.length; i++){ //ищем все элементы и кликаем по ним
            WebElement element1 = driver.findElement(By.xpath("//div[text()='" + array[i] + "']"));
            element1.click();
    }}

    @AfterTest(enabled = false)
    public void close() {
          driver.close();
    }
}
