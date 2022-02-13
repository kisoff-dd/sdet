package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mainPage extends PageFactory {

    private WebDriver driver;
    @FindBy (name = "q") //ищем поле для ввода
    public WebElement search;

    public mainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void findCalc() {
        search.sendKeys("калькулятор");
        search.submit();//нажамаем кнопку поиска
    }

    public void clickButtonCalc(String arrayValues){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);//ожидаем полной загрузки старницы
        String b = arrayValues.replaceAll(" ", "");//разбиваем пример. убираем пробелы
        Pattern p = Pattern.compile("[a-zA-Z]+|\\d|\\D");// разделяем строку на слова(sin...) и символы
        Matcher m1 = p.matcher(b);
        ArrayList<String> arrayList = new ArrayList<>();// получаем массив названий кнопок для нажатий
        int j =0;
        while (m1.find()) {
            arrayList.add(j, m1.group());
            j++;
        }
        for (int i=0; i < arrayList.size(); i++){ //ищем все элементы и кликаем по ним
            WebElement element1 = driver.findElement(By.xpath("//div[text()='" + arrayList.get(i) + "']"));
            element1.click();
    }}

    public String checkArray(String arrayValues){        //проверяем строку памяти
       //WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),'"+ arrayValues + "')]"));
        WebElement element2 = driver.findElement(By.xpath("//div[@class='XH1CIc']"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);//ожидаем полной загрузки старницы
        String checkArray = element2.getText();
        return checkArray;
    }
    public String checkExpect(String arrayValues,String expectResult){        //проверяем строку результат
        WebElement element3 = driver.findElement(By.xpath("//*[@id='cwos']"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);//ожидаем полной загрузки старницы
        String checkExpect = element3.getText();
        return checkExpect;
    }

}
