package mrgn.library;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import mrgn.utilities.LoggerHelper;
import mrgn.utilities.TestBase;

public class FunctionalLibrary {
	protected WebDriver driver;

	private Logger log = LoggerHelper.getLogger(FunctionalLibrary.class);

	public FunctionalLibrary(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		TestBase.logExtentReport("Common function object created...");

	}

	public WebElement getElement(WebElement element) {

		return element;

	}

	public void setText(WebElement element, String values) {

		try {
			if (!element.equals(null) && !values.equals(null)) {
				element.clear();
				element.sendKeys(values);
				log.info("entering .." + values);
				TestBase.logExtentReport("Entering .." + values);
				System.out.println("Element found" + element);
			} else {

				System.out.println("Element not found" + element);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void selectDropDown(WebElement element, int index) {

		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("DropdownValue entering .." + index);
		TestBase.logExtentReport("DropdownValue Selected .." + index);

	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("selectUsingValue and value is: " + value);
		select.selectByValue(value);
		TestBase.logExtentReport("DropdownValue Selected .." + value);

	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("selectUsingIndex and index is: " + index);
		select.selectByIndex(index);
		TestBase.logExtentReport("DropdownValue Selected .." + index);

	}

	public void selectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("selectUsingVisibleText and visibleText is: " + visibleText);
		select.selectByVisibleText(visibleText);
		TestBase.logExtentReport("DropdownValue Selected .." + visibleText);

	}

	public void deSelectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("deSelectUsingValue and value is: " + value);
		select.deselectByValue(value);
	}

	public void deSelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("deSelectUsingIndex and index is: " + index);
		select.deselectByIndex(index);
	}

	public void deSelectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("deselectByVisibleText and visibleText is: " + visibleText);
		select.deselectByVisibleText(visibleText);
	}

	public List<String> getAllDropDownData(WebElement element) {
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for (WebElement ele : elementList) {
			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}

	public void clearText(WebElement element) {

		element.clear();
	}

	/**
	 * This is ImplicitWait method
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit Wait has been set to: " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);

	}

	/**
	 * This will help us to get WebDriverWait object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * This method will make sure element is visible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
			int pollingEveryInMiliSec) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}

	public void explicitWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");
	}

	/**
	 * This method will make sure invisibilityOf element
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisibile now");
		return status;
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched");
	}

	/**
	 * This method will give is fluentWait object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}

	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public void pageLoadTime(long timeout, TimeUnit unit) {
		log.info("waiting for page to load for : " + unit + " seconds");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}

	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("switched to :" + frameIndex + " frame");
	}

	/**
	 * this method will switchToFrame based on frame name
	 * 
	 * @param frameName
	 */
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to :" + frameName + " frame");
	}

	/**
	 * this method will switchToFrame based on frame WebElement
	 * 
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("switched to frame " + element.toString());
	}

	public void switchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();
	}

	/**
	 * This method will switch to child window based on index
	 * 
	 * @param index
	 */
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				log.info("switched to : " + index + " window");
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	/**
	 * This method will close all tabbed window and switched to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainwindow = driver.getWindowHandle();

		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainwindow)) {
				driver.close();
			}
		}
		log.info("switched to main window");
		driver.switchTo().window(mainwindow);
	}

	/**
	 * This method will do browser back navigation
	 */
	public void navigateBack() {
		log.info("navigating back");
		driver.navigate().back();
	}

	/**
	 * This method will do browser forward navigation
	 */
	public void navigateForward() {
		log.info("navigating forward");
		driver.navigate().forward();
	}

	public Alert getAlert() {
		log.info("alert test: " + driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		getAlert().accept();
		log.info("accept Alert is done...");
	}

	public void dismissAlert() {
		getAlert().dismiss();
		log.info("dismiss Alert is done...");
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info("alert text: " + text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("alert is present");
			return true;
		} catch (NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void dismissAlertIfPresent() {
		if (isAlertPresent()) {
			dismissAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void acceptPrompt(String text) {
		if (isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("alert text: " + text);
		}
	}

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is Displayed.." + element.getText());
			TestBase.logExtentReport("element is Displayed.." + element.getText());
			return true;
		} catch (Exception e) {
			log.error("element is not Displayed..", e.getCause());
			TestBase.logExtentReport("element is not Displayed.." + e.getMessage());
			return false;
		}
	}

	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is present.." + element.getText());
			TestBase.logExtentReport("element is present.." + element.getText());
			return false;
		} catch (Exception e) {
			log.error("element is not present..");
			return true;
		}
	}

	public String readValueFromElement(WebElement element) {
		if (null == element) {
			log.info("WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("element text is .." + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	public String getText(WebElement element) {
		if (null == element) {
			log.info("WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("element text is .." + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	public String getTitle() {
		log.info("page title is: " + driver.getTitle());
		return driver.getTitle();
	}

}
