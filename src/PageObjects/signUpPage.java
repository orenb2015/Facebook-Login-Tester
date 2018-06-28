package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Utilites.base;

public class signUpPage extends base 
{
	@FindBy(how = How.NAME , using = "firstname")
	public WebElement firstName ;
	
	@FindBy(how = How.NAME , using = "lastname")
	public WebElement lastName ;

	@FindBy(how = How.NAME , using = "reg_email__")
	public WebElement phoneOrEmail ;
	
	@FindBy(how = How.NAME , using = "reg_email_confirmation__")
	public WebElement phoneOrEmailConfirmation ;

	@FindBy(how = How.NAME , using = "reg_passwd__")
	public WebElement newPassword ;


	@FindBy(how = How.NAME , using = "birthday_month")
	public WebElement birthdayMonth ;
	

	@FindBy(how = How.NAME , using = "birthday_day")
	public WebElement birthdayDay ;

	@FindBy(how = How.NAME , using = "birthday_year")
	public WebElement birthdayYear ;
	
	@FindBy(how = How.XPATH , using = "//input[@name='sex' and @value='2']")
	public WebElement maleCheckBox ;
	
	@FindBy(how = How.XPATH , using = "//input[@name='sex' and @value='1']")
	public WebElement femaleCheckBox ;
	
	@FindBy(how = How.NAME , using = "websubmit")
	public WebElement registrationButton ;
	
	@FindBy(how = How.ID , using = "reg_error_inner")
	public WebElement regerror ;
	
	/**
	 * footers
	 */
	@FindBy(how = How.LINK_TEXT , using = "Developers")
	public WebElement footerDevelopers ;

	@FindBy(how = How.LINK_TEXT , using = "About")
	public WebElement footerAbout ;

	@FindBy(how = How.LINK_TEXT , using = "Places")
	public WebElement footerPlaces ;
	
	public signUpPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
}
