package PageObjects;

import org.openqa.selenium.support.PageFactory;

import Utilites.base;

public class managePages extends base 
{
	public static void init()
	{
		sup = PageFactory.initElements(driver, PageObjects.signUpPage.class);
		dp = PageFactory.initElements(driver, PageObjects.developerPage.class);
	}
}
