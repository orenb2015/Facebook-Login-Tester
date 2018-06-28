package Utilites;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

public class commonOps extends base {

	public static void verifyMessageExists(WebElement elem, String expectedValue)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		try {
			String actual = elem.getText();
			assertEquals(expectedValue, actual);
			System.out.println("Value Exists!");
			test.log(LogStatus.PASS, "Value Exists !");
		} catch (Exception e) {
			System.out.println("Element Not Exists !" + e.getMessage());
			test.log(LogStatus.FAIL,
					"Element Not exists ! , see Screen Shot :" + e.getMessage() + test.addScreenCapture(takeSS()));
			fail("Element Not Exists !");
		} catch (AssertionError ea) {
			System.out.println("Assert Failed " + ea.getMessage());
			test.log(LogStatus.FAIL, "Assert Failed: " + ea.getMessage() + test.addScreenCapture(takeSS()));
			fail("Assert Failed !");

		}

	}

	public static void verifyElementExists(WebElement elem, String elementName)
			throws IOException, ParserConfigurationException, SAXException {
		try {
			elem.isDisplayed();
			System.out.println("Element " + elementName + " Exists!");
			test.log(LogStatus.PASS, "Element " + elementName + " Exists!");
		} catch (Exception e) {
			System.out.println("Element " + elementName + " NOT Exists !");
			test.log(LogStatus.FAIL,
					"Element " + elementName + " NOT Exists! , see Screen Shot: " + test.addScreenCapture(takeSS()));
			fail("Element NOT Exists !");
		}
	}

}
