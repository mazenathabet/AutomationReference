package Common.web;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DriverActions {

    public WebDriver driver;
    public WebDriverWait wait;

    public DriverActions(WebDriver driver) {
        this.driver = driver;
    }

    protected void openUrl(String Url) {
        driver.get(Url);
    }

    protected void clickOn(WebElement webElement, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    protected void clickOn(By by, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        scrollToElement(by);
        driver.findElement(by).click();
    }

    protected void sendKeysToElement(String value, WebElement webElement, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(value);
    }

    protected void sendKeysToElement(String value, By by) {
        scrollToElement(by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(value);
    }

    protected void sendKeyChords(String value, Keys keys, WebElement webElement, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(value, keys);
    }

    protected void sendKeyChords(String value, Keys keys, By by, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        scrollToElement(by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(value, keys);
    }

    protected void sendKeysWithoutClear(String value, WebElement webElement, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(value);
    }

    protected void sendKeysWithoutClear(String value, By by) {
        scrollToElement(by);
        driver.findElement(by).sendKeys(value);
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    protected void switchToIframe(int index, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        driver.switchTo().frame(index);
    }

    protected void switchToIframe(String idOrName, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
        driver.switchTo().frame(idOrName);
    }

    protected void switchToIframe(WebElement webElement, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
        driver.switchTo().frame(webElement);
    }

    protected void switchToIframe(By by, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
        driver.switchTo().frame(driver.findElement(by));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void selectByIndex(By by, int index, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        final Select dropdown = new Select(driver.findElement(by));
        dropdown.selectByIndex(index);
    }

    protected void selectByVisibleText(By by, String visibleText, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        final Select dropdown = new Select(driver.findElement(by));
        dropdown.selectByVisibleText(visibleText);
    }

    protected void selectByValue(By by, String value, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        final Select dropdown = new Select(driver.findElement(by));
        dropdown.selectByValue(value);
    }


    protected String getElementAttribute(By by, String attribute) {
        return driver.findElement(by).getAttribute(attribute);
    }

    /**
     * Check if an alert is present in the window handles and return a boolean if it
     * exists and not if not
     *
     * @return Boolean with tru if alert exist, false if not
     */
    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    /**
     * If an alert is present (exist), switch to it and accept it
     */
    protected void acceptAlert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
    }

    /**
     * If an alert is present (exist), switch to it and close it
     */
    protected void closeAlert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().dismiss();
        }
    }

    /**
     * If a prompt alert is present (exist), switch to it , type text and accept it
     */
    protected void sendTextToPromptAlertAndAccept(String text) {
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            alert.accept();
        }
    }

    /**
     * Scrolls the page until the given element is visible
     *
     * @param by From the element we want to operate
     */
    protected void scrollToElement(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    /**
     * positive pixels means scrolling down, negative means up
     *
     * @param pixels -> number of pixels to scroll
     */
    protected void scrollPage(int pixels) {
        ((JavascriptExecutor) driver).executeScript("scroll(0, " + pixels + ");");
    }

    protected void scrollPageBy(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + pixels + ");");
    }

    /**
     * To get all the page inner Texts
     */
    protected String getPageInnerText() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String pageText = js.executeScript("return document.documentElement.innerText;").toString();
        return pageText;
    }

    /**
     * To Draw a border around the element
     */
    protected void drawBorder(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**
     * To Highlight the element with specific color
     */
    protected void flash(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgColor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,200,0)", element);
            changeColor(bgColor, element);
        }
    }

    protected void changeColor(String color, WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
    }

    /**
     * Refresh the page with JavaScriptExecutor
     */
    protected void refreshBrowserByJS() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("history.go(0)");
    }

    /**
     * Get Page title using JavaScriptExecutor
     */
    protected String getTitleByJS() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String title = js.executeScript("return document.title;").toString();
        return title;
    }

    /**
     * Set Calendar's Date to specific date using JSE
     */
    protected void selectDateByJS(WebElement element, String dateVal) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].setAttribute('value','" + dateVal + "');", element);

    }

    protected void dragElementOverElement(WebElement firstElement, WebElement targetElement) {
        Actions action = new Actions(driver);
        action.dragAndDrop(firstElement, targetElement).perform();
    }

    protected void clearField(By by) {
        driver.findElement(by).clear();
    }

    /**
     * Read an alert containing text
     *
     * @return innerText of the alert element.
     */
    protected String readAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    protected String getElementAttribute(WebElement webElement, String attribute) {
        return webElement.getAttribute(attribute);
    }

    protected String getElementText(By by, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    protected String getElementText(WebElement webElement, int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Delete all cookies from actual browser
     */
    protected void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * Look for if a element is present or not in the frame/window and return a
     * true/false answer
     *
     * @param by Element to look for
     * @return true if the element is located, false if the element
     */
    protected boolean elementExists(By by) {
        return driver.findElements(by).size() > 0;
    }

    /**
     * Perform an action to double-click an element
     *
     * @param element Where we want to click
     */
    protected void doubleClickWebElement(WebElement element, int seconds) {
        Actions action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        action.doubleClick(element).build().perform();
    }

    /**
     * Perform an action to double-click an element
     *
     * @param by is a locator expression
     */
    protected void doubleClickBy(By by, int seconds) {
        Actions action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        action.doubleClick(driver.findElement(by)).build().perform();
    }

    /**
     * Test the state of visibility of an element and return a boolean, true if
     * visible, false if not
     *
     * @param by from the desired element
     * @return return true if visible, false if not
     */
    protected boolean isElementDisplayed(By by) {
        if (elementExists(by)) {
            WebElement we = driver.findElement(by);
            return we.isDisplayed();
        } else {
            return false;
        }
    }

    /**
     * Test the element is selected or not  of an element and return a boolean, true if
     *
     * @param by from the desired element
     * @return return true if visible, false if not
     */
    protected boolean isSelected(By by) {
        if (elementExists(by)) {
            WebElement we = driver.findElement(by);
            return we.isSelected();
        } else {
            return false;
        }
    }

    /**
     * Test the element is enabled or not  of an element and return a boolean, true if
     *
     * @param by from the desired element
     * @return return true if visible, false if not
     */
    protected boolean isEnabled(By by) {
        if (elementExists(by)) {
            WebElement we = driver.findElement(by);
            return we.isEnabled();
        } else {
            return false;
        }
    }

    /**
     * Check from a list got from the locator if each one of the elements
     * contains a certain text/feature/property. Returns true if it does, false if
     * not
     *
     * @param by      from the list of items we want to check
     * @param element element we want to check if it's inside in every item
     * @return return true if the element exisit in every item from the list, false
     * if not
     */
    protected boolean searchElementInEveryRow(By by, String element) {
        List<WebElement> elements = driver.findElements(by);
        for (WebElement e : elements) {
            if (!e.getText().toUpperCase().contains(element.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Move to the element located by the by and then press the right click button
     * inside this element
     *
     * @param by from the element to use
     */
    protected void rightClick(By by) {
        WebElement we = driver.findElement(by);
        scrollToElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(we);
        action.contextClick(we).build().perform();
    }

    /**
     * Returns the quantity of elements that are specified by the locator supplied
     *
     * @param by that specifies or not some elements
     * @return number of elements located bt locator
     */
    protected int getElementsSize(By by) {
        return driver.findElements(by).size();
    }

    protected void takeScreenShotOfElement(By by) {
        File image = driver.findElement(by).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(image, new File("src/ScreenShots/elementImage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void takePageScreenshotAsFile() {
        File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Random random = new Random();
            Date date = new Date();
            FileUtils.copyFile(image, new File("src/ScreenShots/pageImage_" + random.nextInt()+date.getTime()+ ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
