package mrgn.testcase;

import org.testng.annotations.Test;
import mrgn.pages.FacebookSignUpPage;
import mrgn.utilities.ObjectReader;
import mrgn.utilities.TestBase;

public class MyFirstTest extends TestBase {

	@Test
	public void facebookSignUp() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		FacebookSignUpPage faSignUp = new FacebookSignUpPage(driver);
		faSignUp.facebookSignUp();

	}

}
