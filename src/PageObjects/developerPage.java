package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Utilites.base;

public class developerPage extends base {
	@FindBy(how = How.LINK_TEXT, using = "Open Source")
	public WebElement openSourceLink;
}
