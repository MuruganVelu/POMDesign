package mrgn.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mrgn.library.FunctionalLibrary;

public class OR_FacebookSignUp extends FunctionalLibrary {

	protected WebDriver driver;
	
	@FindBy(xpath = "//input[contains(@name,'firstname')]")
	public WebElement firstname;
	@FindBy(xpath = "//input[contains(@name,'lastname')]")
	public WebElement lastname;
	@FindBy(xpath = "//input[contains(@name,'reg_email__')]")
	public WebElement email;
	@FindBy(xpath = "//input[contains(@name,'reg_passwd__')and @type=\"password\"]")
	public WebElement password;
	
	@FindBy(xpath = "//select[@id=\"day\"]")
	public WebElement selectDay;
	@FindBy(xpath = "//select[@id=\"month\"]")
	public WebElement selectMonth;
	@FindBy(xpath = "//select[@id=\"year\"]")
	public WebElement selectYear;
	
	public OR_FacebookSignUp(WebDriver driver) {
		super(driver);
		this.driver = driver;
    	PageFactory.initElements(driver, this);
}

	

	
}
