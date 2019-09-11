package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.Assert.assertTrue;

public class GoogleSeleniumTest {

    private ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\seleniumtesting\\src\\test\\java\\resources\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void searchTest() throws InterruptedException {
        driver.get("http://google.com");
        Thread.sleep(1000);         //so I can see that it opened

        WebElement searchField = driver.findElement(By.name("q"));      // q is the name of the text input
        assertTrue(searchField.isDisplayed());
        searchField.sendKeys("funny cat pictures");
        Thread.sleep(1000);

        WebElement submitButton = driver.findElement(By.name("btnK"));
        submitButton.click();
        Thread.sleep(1000);

        WebElement linkToBiggerPicture = driver.findElementByLinkText("Images for funny cat");
        linkToBiggerPicture.click();
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,3000)");        // the first argument is in horizontal direction
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,-1000)");       // minus sign scrolls up
        Thread.sleep(1000);
        js.executeScript("scroll(0, 4200)");       // scrolls TO position 3200, NOT by an additional 3200
        Thread.sleep(1000);
    }

    @Test
    public void myTestExercises1and2() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        Thread.sleep(800);

        //first exercise on this page
        WebElement textInput = driver.findElement(By.id("user-message"));
        assertTrue(textInput.isDisplayed());
        textInput.sendKeys("hello world!");
        Thread.sleep(500);

        WebElement showMessageButton = driver.findElement(By.xpath("//*[@id=\"get-input\"]/button"));
        showMessageButton.click();
        Thread.sleep(500);

        WebElement displayedText = driver.findElement(By.id("display"));
        assertTrue(displayedText.getText().equals("hello world!"));
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");

        //second exercise on this page
        WebElement firstInput = driver.findElement(By.id("sum1"));
        WebElement secondInput = driver.findElement(By.id("sum2"));
        firstInput.sendKeys("3");
        secondInput.sendKeys("5");
        Thread.sleep(400);

        WebElement getTotalButton = driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button"));
        getTotalButton.click();
        Thread.sleep(1000);

        WebElement displayedTotal = driver.findElement(By.id("displayvalue"));
        assertTrue(displayedTotal.getText().equals("8"));
        Thread.sleep(1000);
    }
}
