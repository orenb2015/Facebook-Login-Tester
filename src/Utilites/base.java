package Utilites;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class base {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
			.format(Calendar.getInstance().getTime());

	public static PageObjects.signUpPage sup;
	public static PageObjects.developerPage dp;

	public static String getData(String nodeName) throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File("Configuration.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}

	public static void initBrowser(String browserType) throws ParserConfigurationException, SAXException, IOException {
		switch (browserType.toLowerCase()) {
		case "firefox":
			driver = initFFDriver();
			break;
		case "ie":
			driver = initIEDriver();
			break;
		case "chrome":
			driver = initChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.get(getData("URL"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException {
		System.setProperty("webdriver.gecko.driver", getData("FFDriverPath"));
		WebDriver driverff = new FirefoxDriver();
		return driverff;
	}

	public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException {
		System.setProperty("webdriver.ie.driver", getData("IEDriverPath"));
		WebDriver driverie = new InternetExplorerDriver();
		return driverie;
	}

	public static WebDriver initChromeDriver() throws ParserConfigurationException, SAXException, IOException {
		System.setProperty("webdriver.chrome.driver", getData("ChromeDrivePath"));
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static void InstanceReport() throws ParserConfigurationException, SAXException, IOException {
		extent = new ExtentReports(getData("ReportFilePath") + getData("ReportFileName") + timeStamp + ".html", true);
	}

	public static void initReportTest(String testName, String testDescription) {
		test = extent.startTest(testName, testDescription);
	}

	public static void finalizeReportTest() {
		extent.endTest(test);
	}

	public static void finalizeExtentReport() {
		extent.flush();
		extent.close();
	}

	public static String takeSS() throws IOException, ParserConfigurationException, SAXException {
		String SSpath = getData("ReportFilePath") + "screenshot_" + getRandomNumber() + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(SSpath));
		return SSpath;
	}

	public static int getRandomNumber() {
		Random rand = new Random();
		// int n = rand.nextInt(999999) + 1000;
		return rand.nextInt(999999) + 1000;
	}

	@Rule
	public TestName name = new TestName();

	@BeforeClass
	public static void startSession() throws ParserConfigurationException, SAXException, IOException {
		initBrowser(getData("BrowserType"));
		InstanceReport();
		PageObjects.managePages.init();
	}

	@AfterClass
	public static void closeSession() {
		finalizeExtentReport();
		driver.quit();
	}

	@After
	public void doAfterTest() {
		finalizeReportTest();
	}

	@Before
	public void doBeforeTest() {
		String[] split = name.getMethodName().split("_");
		initReportTest(split[0], split[1]);
	}
}
