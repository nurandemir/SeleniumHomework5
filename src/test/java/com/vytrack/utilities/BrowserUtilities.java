package com.vytrack.utilities;


import java.io.File;
import java.io.IOException;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtilities {
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    /**
     * @param elements represents collection of WebElements
     * @return collection of strings
     */
    public static List<String> getTextFromWebElements(List<WebElement> elements) {
        List<String> textValues = new ArrayList<>();
        for (WebElement element : elements) {
            if (!element.getText().isEmpty()) {
                textValues.add(element.getText());
            }
        }
        return textValues;

        //BrowserUtils.getTextFromWebElements(columnNames) ==>
// this method takes the text of every single webElement and puts it into collection of strings
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Scroll to element using JavaScript
     *
     * @param element
     */
    public static void scrollTo(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * @param name screenshot name
     * @return path to the screenshot
     */
    public static String getScreenshot(String name) { // get screenshot, and report
        //adding date and time to screenshot name
        // name = LocalDateTime.now() + "_" + name;
        name=new Date().toString()+"_"+name;

        //where we gonna store a screenshot
        String path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
        //user.dir->returns location of project   |   folder store test result +
        System.out.println("Screenshot is here: " + path);

        //since our reference type is a WebDriver
        //we cannot see methods from TakesScreenshot interface that's why do casting
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();

        //take screenshot of web browser, and save it as a file
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);//it takes screenshot and returns it as File object

        File destination = new File(path); //where screenshot will be saved
        try {
            //copy file to the previously specified location
            FileUtils.copyFile(source, destination);//FileUtils is a class in Java,general file manipulation utilies
            //gets 2 arguments your file and where to save it
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

        // copy file to the previously specified location
        // FileUtils class comes from java, to work with the files, general file manipulation properties
        // It stores methods to work with file
        // takes 2 argument,where is your file and where to save it
        //  FileUtils.copyFile(source,destination);
    }
}






