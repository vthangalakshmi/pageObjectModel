package adminSmokeTest;

import configPack.poModelForSmoke;
import configPack.propertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class smokeTest extends BaseExtent
{
	propertyReader pReader;
    poModelForSmoke ap_obj;
    WebElement createButton;
    Boolean buttonTrue;     
	@Test(priority=0,testName="Verify the Admin Login")
	public void admin_login() throws Exception
	{
		pReader=new propertyReader();
		System.setProperty("webdriver.chrome.driver", pReader.getbrowserpath());
		test.log(Status.INFO, "Launch the Browser");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
	    driver = new ChromeDriver(options);
		test.log(Status.INFO, "Hit the admin URL");
	    driver.get(pReader.getAdminUrl());
	    test.log(Status.INFO, "Maximize the browser tab");
		driver.manage().window().maximize();
	    Thread.sleep(100);
	    String title=driver.getTitle();	
	    System.out.println(title);  
	    ap_obj=PageFactory.initElements(driver,poModelForSmoke.class);
	    test.log(Status.INFO, "Enter Username and Password");
	    ap_obj.admin_login(pReader.getUsername(), pReader.getPassword());
	    WebElement locale=driver.findElement(By.id("interface_locale"));
	    Select english=new Select(locale);
	    english.selectByValue("en_US");
	}
	@Test(priority=1,testName="Verify the Reseller Menu and Marketplace Settings tab")
	public void reseller_menu() throws Exception
	{
		test.log(Status.INFO,"Open Reseller menu");
		ap_obj.reseller_grid();
		test.log(Status.INFO, "Verify Create Reseller button is displayed or not");
		createButton=driver.findElement(By.xpath("//button[@title='Create Reseller']"));
		buttonTrue=createButton.isDisplayed();
		System.out.println("Create Reseller button is displayed and display status is "+buttonTrue);
		test.log(Status.INFO, "Open create reseller screen");
		createButton.click();
		Assert.assertTrue(driver.getTitle().contains("Create Reseller"));
		driver.navigate().back();
		test.log(Status.INFO,"View any of the reseller account");
		driver.findElement(By.xpath("//*[@id='computenext_grid_disk_table']/tbody/tr[2]/td[6]/a")).click();
		Thread.sleep(4000);
		ap_obj.mpSettings_verify();
	}
	@Test(priority=2,testName="Verify the Agent Menu")
	public void agent_menu() throws Exception
	{
		test.log(Status.INFO,"Open Agent menu");
		ap_obj.agent_grid();
		test.log(Status.INFO, "Verify Create Agent button is displayed or not");
		createButton=driver.findElement(By.xpath("//button[@title='Create Agent']"));		
		buttonTrue=createButton.isDisplayed();
		System.out.println("Create Agent button is displayed and display status is "+buttonTrue);
		test.log(Status.INFO, "Open create agent screen");
		createButton.click();
		Assert.assertTrue(driver.getTitle().contains("Create Agent"));
		driver.navigate().back();
		test.log(Status.INFO,"View any of the agent account");
		driver.findElement(By.xpath("//*[@id='computenext_grid_disk_table']/tbody/tr/td[7]/a")).click();
		Thread.sleep(2000);
	}
	@Test(priority=3,testName="Verify the Customer Menu")
	public void customer_menu() throws Exception
	{
		test.log(Status.INFO,"Open Customer menu");
		ap_obj.customer_grid();
		test.log(Status.INFO, "Verify Create Customer button is displayed or not");
		createButton=driver.findElement(By.xpath("//button[@title='Create Customer']"));		
		buttonTrue=createButton.isDisplayed();
		System.out.println("Create Customer button is displayed and display status is "+buttonTrue);
		test.log(Status.INFO, "Open create Customer screen");
		createButton.click();
		Assert.assertTrue(driver.getTitle().contains("New Account"));
		driver.navigate().back();
		test.log(Status.INFO,"View any of the Customer account");
		driver.findElement(By.xpath("//*[@id='computenext_grid_disk_table']/tbody/tr[1]/td[5]/a")).click();
		Thread.sleep(2000);
	}
	/*@Test(priority=3,testName="Verify the Loginas from User Tab of customer")
	public void verify_loginas() throws Exception
	{
		test.log(Status.INFO,"Click Users Menu");
		driver.findElement(By.xpath("/*[@id='computenext_account_adminhtml_tabs_users']/span")).click();
		
		String loginas_title=poModelForSmoke.loginas(driver);
		System.out.println("Tthe tile of Cloud Management is "+loginas_title);
	}*/
	@Test(priority=4,testName="Verify Invoices Menu")
	public void invoice_menu()
	{		
		test.log(Status.INFO, "Open Invoices menu");
		ap_obj.invoices_grid();
		Assert.assertTrue(driver.getTitle().contains("Invoice"));
		test.log(Status.INFO, "View any Invoice");
		driver.findElement(By.xpath("//*[@id='computenext_invoices_grid_table']/tbody/tr[1]/td[1]")).click();
		test.log(Status.INFO, "Verify the View PDF and Export Buttons");
		WebElement pdf=driver.findElement(By.xpath("//button[@title='View PDF']"));
		Assert.assertTrue(pdf.isDisplayed());
		WebElement export=driver.findElement(By.xpath("//button[@title='Export']"));
		Assert.assertTrue(export.isDisplayed());		
	}
	@Test(priority=5,testName="Verify Orders Menu")
	public void orders_menu()
	{		
		test.log(Status.INFO, "Open Orders menu");
		ap_obj.Orders_grid();
		Assert.assertTrue(driver.getTitle().contains("Order"));
		test.log(Status.INFO, "View any Order");
		driver.findElement(By.xpath("//*[@id='computenext_order_grid_list_table']/tbody/tr[1]/td[1]")).click();
		test.log(Status.INFO, "Verify the order details");
		String accountName=driver.findElement(By.xpath("//*[@id='computenext_orders_adminhtml_tabs_information_content']/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]")).getText();
		System.out.println("Account name of opened Order is "+accountName);	
	}
	@Test(priority=6,testName="Verify ADM Menu")
	public void adm_menu() throws Exception
	{		
		test.log(Status.INFO, "Open ADM menu");
		ap_obj.adm_grid();
		test.log(Status.INFO, "Verify Create ADM button is displayed or not");
		createButton=driver.findElement(By.xpath("//button[@title='Create New ADM']"));		
		buttonTrue=createButton.isDisplayed();
		System.out.println("Create ADM button is displayed and display status is "+buttonTrue);
		test.log(Status.INFO, "Open create ADM screen");
		createButton.click();
		Assert.assertTrue(driver.getTitle().contains("ADM"));
		driver.navigate().back();
		test.log(Status.INFO,"View any of the existing ADM");
		driver.findElement(By.xpath("//*[@id='computenext_grid_disk_table']/tbody/tr[1]/td[3]")).click();
		Thread.sleep(2000);
	}
	@Test(priority=7,testName="Verify Agent Commission Settings Menu")
	public void agentSettings_menu() throws Exception
	{		
		test.log(Status.INFO, "Open Agent Commission Settings menu");
		ap_obj.agentSettings_grid();
		test.log(Status.INFO, "Verify Create New Agent Commission button is displayed or not");
		createButton=driver.findElement(By.xpath("//button[@title='Create new Agent commission']"));		
		buttonTrue=createButton.isDisplayed();
		System.out.println("Create Create New Agent Commission button is displayed and display status is "+buttonTrue);
		test.log(Status.INFO, "Open Create New Agent Commission screen");
		createButton.click();
		Assert.assertTrue(driver.getTitle().contains("New Commission"));
	}
	@Test(priority=8,testName="Verify Admin Logout")
	public void verify_admin_logout() throws Exception
	{		
		test.log(Status.INFO, "Click Logout");
		driver.findElement(By.cssSelector("a.link-logout")).click();
		System.out.println("Loggedout Successfully");
	}
	
}
//https://www.softwaretestingmaterial.com/screenshots-extent-reports/
//http://automationtesting.in/extent-report-with-multiple-classes/
//https://www.mkyong.com/unittest/testng-tutorial-5-suite-test/