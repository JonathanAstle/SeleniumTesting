package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void checkboxTest1() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        Thread.sleep(800);

        WebElement successCheckbox = driver.findElement(By.id("isAgeSelected"));
        successCheckbox.click();
        Thread.sleep(300);

        WebElement successMessage = driver.findElement(By.id("txtAge"));
        assertTrue(successMessage.getText().equals("Success - Check box is checked"));
    }

    @Test
    public void checkboxTest2() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        Thread.sleep(800);

        WebElement checkAllButton = driver.findElement(By.id("check1"));
        WebElement check1 = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[1]/label/input"));
        WebElement check2 = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[2]/label/input"));
        WebElement check3 = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[3]/label/input"));
        WebElement check4 = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[4]/label/input"));
        WebElement areChecked = driver.findElement(By.id("isChkd"));

        checkAllButton.click();
        Thread.sleep(600);

        assertTrue(areChecked.getAttribute("value").equals("true"));

        check1.click();
        Thread.sleep(100);

        assertTrue(checkAllButton.getAttribute("value").equals("Check All"));
        Thread.sleep(1000);
    }

    @Test
    public void radioButtonTest1() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
        Thread.sleep(800);

        WebElement maleButton = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[1]/div[2]/label[1]/input"));
        WebElement femaleButton = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[1]/div[2]/label[2]/input"));
        WebElement getCheckedValueButton = driver.findElement(By.id("buttoncheck"));
        WebElement text = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[1]/div[2]/p[3]"));

        maleButton.click();
        Thread.sleep(100);
        getCheckedValueButton.click();
        Thread.sleep(100);

        assertTrue(text.getText().equals("Radio button 'Male' is checked"));

        femaleButton.click();
        Thread.sleep(100);
        getCheckedValueButton.click();
        Thread.sleep(100);

        assertTrue(text.getText().equals("Radio button 'Female' is checked"));
        Thread.sleep(600);
    }

    @Test
    public void radioButtonTest2() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
        Thread.sleep(800);

        WebElement maleButton = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[1]/label[1]/input"));
        WebElement femaleButton = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[1]/label[2]/input"));
        WebElement age0To5 = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[2]/label[1]/input"));
        WebElement age5To15 = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[2]/label[2]/input"));
        WebElement age15To50 = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[2]/label[3]/input"));

        WebElement getValuesButton = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/button"));
        WebElement text = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/p[2]"));

        List<WebElement> maleOrFemale = new ArrayList<WebElement>();
        maleOrFemale.add(maleButton);
        maleOrFemale.add(femaleButton);

        List<WebElement> ageRanges = new ArrayList<WebElement>();
        ageRanges.add(age0To5);
        ageRanges.add(age5To15);
        ageRanges.add(age15To50);

        for (WebElement gender: maleOrFemale) {
            for (WebElement ages: ageRanges) {
                gender.click();
                ages.click();
                Thread.sleep(500);

                getValuesButton.click();
                Thread.sleep(500);

                if (gender.equals(maleButton)) { //if gender is male
                    assertTrue(text.getText().contains("Sex : Male"));
                    if (ages.equals(age0To5)) {
                        assertTrue(text.getText().contains("Age group: 0 - 5"));
                    } else if (ages.equals(age5To15)) {
                        assertTrue(text.getText().contains("Age group: 5 - 15"));
                    } else {
                        assertTrue(text.getText().contains("Age group: 15 - 50"));
                    }
                } else { //gender is female
                    assertTrue(text.getText().contains("Sex : Female"));
                    if (ages.equals(age0To5)) {
                        assertTrue(text.getText().contains("Age group: 0 - 5"));
                    } else if (ages.equals(age5To15)) {
                        assertTrue(text.getText().contains("Age group: 5 - 15"));
                    } else {
                        assertTrue(text.getText().contains("Age group: 15 - 50"));
                    }
                }
            }
        }
    }
}
