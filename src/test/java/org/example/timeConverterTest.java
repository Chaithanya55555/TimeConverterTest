package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class timeConverterTest {
    public static void main(String[] args)throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://qatimeconverter.ccbp.tech/");
        driver.manage().window().maximize();

        //Test the conversion of hours and minutes to seconds
        WebElement hours = driver.findElement(By.xpath("(//input[@type='text'])[position() = 1]"));
        hours.sendKeys("2");
        WebElement seconds = driver.findElement(By.xpath("(//input[@type='text'])[position() = 2]"));
        seconds.sendKeys("30");
        WebElement convert = driver.findElement(By.xpath("//button[contains(@id , 'convert')]"));
        convert.click();
        WebElement retrieveseconds = driver.findElement(By.xpath("//p[starts-with(@class , 'converted')]"));
        String getsec = retrieveseconds.getText();
        String expextedsecondsCase1 = "9000s";
        if (getsec.equals(expextedsecondsCase1)){
            System.out.println("Conversion successful");
        }else {
            System.out.println("Conversion failed");
            System.out.println(expextedsecondsCase1);
            System.out.println(getsec);
        }

        Thread.sleep(3000);

        //Test the error case at hours
        hours.clear();
        seconds.clear();
        convert.click();
        WebElement error = driver.findElement(By.xpath("//p[starts-with(@class , 'error')]"));
        String message = error.getText();
        String alert = "Please enter a valid number of hours.";
        if (message.equals(alert)){
            System.out.println("Expected Error Message Shown");
        }else {
            System.out.println("Unexpected Error Message Shown");
        }

        //Test the error case at minutes
        hours.sendKeys("1");
        convert.click();
        WebElement error2 = driver.findElement(By.xpath("//p[starts-with(@class , 'error')]"));
        String message2 = error2.getText();
        String expectedMessage = "Please enter a valid number of minutes.";
        if (message2.equals(expectedMessage)){
            System.out.println("Expected Error Message Shown");
        }else{
            System.out.println("Unexpected Error Message Shown");
        }

        Thread.sleep(3000);

        //Test the conversion of hours and minutes to seconds
        seconds.sendKeys("15");
        convert.click();
        String messa = retrieveseconds.getText();
        String seconds2 = "4500s";
        if (messa.equals(seconds2)){
            System.out.println("Conversion Successful");
        }else {
            System.out.println("Conversion Failed");
        }
        Thread.sleep(3000);

        //Close the browser window.
        driver.quit();

    }
}
