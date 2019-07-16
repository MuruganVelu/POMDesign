package mrgn.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import mrgn.pageobjects.OR_FacebookSignUp;
import mrgn.utilities.ExcelDataExtraction;
import mrgn.utilities.LoggerHelper;
import mrgn.utilities.ResourceHelper;

public class FacebookSignUpPage extends OR_FacebookSignUp {

	protected WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(FacebookSignUpPage.class);
	public static final String TESTDATAPATH = ResourceHelper.getResourcePath("/src/main/resources/testData/TestData.xlsx");
	public static final String SHEETNAME = "FacebooSignup";

	public FacebookSignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void facebookSignUp() {

		
		
		
		setText(firstname, FacebookSignUpPage.data(1, "FirstName"));
		setText(lastname, FacebookSignUpPage.data(1, "LastName"));
		setText(email, FacebookSignUpPage.data(1, "Email"));
		selectUsingVisibleText(selectDay, FacebookSignUpPage.data(1, "Day"));
		selectUsingVisibleText(selectMonth, FacebookSignUpPage.data(1, "Month"));
		selectUsingVisibleText(selectYear, FacebookSignUpPage.data(1, "Year"));

	}

	public static String data(int index, String key) {

		String result = ExcelDataExtraction.getData(TESTDATAPATH, SHEETNAME, index, key);

		return result;
	}

}
