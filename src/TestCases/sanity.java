package TestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

import Utilites.base;
import Utilites.commonOps;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class sanity extends base {
	@Test
	public void Test1_RegistrationTooShortPassword()
			throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		try {
			String firstName = getData("Firstname");
			sup.firstName.sendKeys(firstName);
			test.log(LogStatus.INFO, "First name sent: " + firstName);

			String lastName = getData("Lastname");
			sup.lastName.sendKeys(lastName);
			test.log(LogStatus.INFO, "Last name sent: " + lastName);

			String email = getData("Email");
			sup.phoneOrEmail.sendKeys(email);
			test.log(LogStatus.INFO, "Email sent: " + email);

			sup.phoneOrEmailConfirmation.sendKeys(getData("Email"));
			test.log(LogStatus.INFO, "Email confirmation sent");

			String password = getData("Password");
			sup.newPassword.sendKeys(password);
			test.log(LogStatus.INFO, "Password sent: " + password);

			Select birthDaySelect = new Select(sup.birthdayDay);
			birthDaySelect.selectByValue(getData("BirthdayDay"));
			Select birthMonthSelect = new Select(sup.birthdayMonth);
			birthMonthSelect.selectByValue(getData("BirthdayMonth"));
			Select birthYearSelect = new Select(sup.birthdayYear);
			birthYearSelect.selectByValue(getData("BirthdayYear"));
			test.log(LogStatus.INFO, "birthday sent: " + getData("BirthdayDay") + "/" + getData("BirthdayMonth") + "/"
					+ getData("BirthdayYear"));

			sup.maleCheckBox.click();
			sup.registrationButton.click();

			Thread.sleep(10000);
			assertEquals("Your password must be at least 6 characters long. Please try another.",
					sup.regerror.getText());

			test.log(LogStatus.PASS, "Registration failed as expected - too short password");

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"exception occured! , see Screen Shot :" + e.getMessage() + test.addScreenCapture(takeSS()));
			fail("Exception: " + e.toString());
		} catch (AssertionError ea) {
			test.log(LogStatus.FAIL, "Assert Failed: " + ea.getMessage() + test.addScreenCapture(takeSS()));
			fail("Assert Failed!");
		}
	}

	@Test
	public void Test2_RegistrationVerifyLinks() throws IOException, ParserConfigurationException, SAXException {
		try {
			commonOps.verifyElementExists(sup.footerAbout, "About");
			commonOps.verifyElementExists(sup.footerDevelopers, "Developers");
			commonOps.verifyElementExists(sup.footerPlaces, "Places");
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"exception occured! , see Screen Shot :" + e.getMessage() + test.addScreenCapture(takeSS()));
			fail("Exception: " + e.toString());
		} catch (AssertionError ea) {
			test.log(LogStatus.FAIL, "Assert Failed: " + ea.getMessage() + test.addScreenCapture(takeSS()));
			fail("Assert Failed!");
		}
	}

	@Test
	public void Test3_DevelopersVerifyMessengerPlatform() throws IOException, ParserConfigurationException, SAXException {
		try {
			sup.footerDevelopers.click();
			Thread.sleep(5000);
			commonOps.verifyElementExists(dp.openSourceLink, "Open Source Link");
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"exception occured! , see Screen Shot :" + e.getMessage() + test.addScreenCapture(takeSS()));
			fail("Exception: " + e.toString());
		} catch (AssertionError ea) {
			test.log(LogStatus.FAIL, "Assert Failed: " + ea.getMessage() + test.addScreenCapture(takeSS()));
			fail("Assert Failed!");
		}
	}
}
