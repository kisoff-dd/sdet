import io.netty.util.internal.StringUtil;
import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test1 {

    WebDriver driver = getChromeDriver.getcromedriver();

    @BeforeTest(enabled = true)
    public void start() {
       driver.get("https://www.google.com/");
       }

    @Parameters({"arrayValues","expect"})
    @Test(enabled = true)
    public void print(String arrayValues,String expect) {
        try {
            WebElement element = driver.findElement(By.name("q")); // Находим элемент по атрибуту name и вводим текст
            element.sendKeys("калькулятор");
            element.submit(); //нажамаем кнопку поиска
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);//ожидаем полной загрузки старницы
            // запасной вариант ,переводим текстовый параметр теста в массив символов если пример разделить пробелами
            /*String[] array = arrayValues.split(" ");
               for (int i=0; i < array.length; i++){ //ищем все элементы и кликаем по ним
                    WebElement element1 = driver.findElement(By.xpath("//div[text()='" + array[i] + "']"));
                    element1.click();
                 } */
            // разбиваем пример если он без пробелов
            Pattern p = Pattern.compile("[a-zA-Z]+|\\d|\\D");
            Matcher m1 = p.matcher(arrayValues);
            ArrayList<String> arrayList = new ArrayList<>();
            int j =0;
            while (m1.find()) {
                arrayList.add(j, m1.group());
                j++;
            }
            for (int i=0; i < arrayList.size(); i++){ //ищем все элементы и кликаем по ним
                        WebElement element1 = driver.findElement(By.xpath("//div[text()='" + arrayList.get(i) + "']"));
                        element1.click();
                     }
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);//ожидаем полной загрузки старницы
            //проверяем строку памяти
            WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),' " + arrayValues + "')]"));
            //проверяем строку результат
            WebElement element3 = driver.findElement(By.xpath("//*[@id='cwos']"));
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);//ожидаем полной загрузки старницы
          //  System.out.println(element2.getText()+"  "+ element3.getText());
            Assert.assertEquals(element2.getText(),arrayValues);// сравниваем результат с входными данными
            Assert.assertEquals(element3.getText(),"11");
        } catch (Exception e) {
           // System.out.println("что-то гдето поламалось " + e.getMessage());
        }
    }


    @AfterTest(enabled = true)
    public void close() {
          driver.close();
    }
}
