package adminSmokeTest;


import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import configPack.poModelForSmoke;
import configPack.propertyReader;

public class marketplaceSmokeTest extends BaseExtent
{
	
		propertyReader pReader;
	    poModelForSmoke mp_obj;
	  	@Test(priority=0,testName="Verify the MP login and Workload page")
		public void marketplace_login() throws Exception
		{
			pReader=new propertyReader();
			System.setProperty("webdriver.chrome.driver", pReader.getbrowserpath());
			test.log(Status.INFO, "Launch the Browser");
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
		    driver = new ChromeDriver(options);
		    test.log(Status.INFO, "Hit the Marketplace URL");
		    driver.get(pReader.getMarketplaceUrl());
		    test.log(Status.INFO, "Maximize the Browser Tab");
			driver.manage().window().maximize();
		    Thread.sleep(1000);
		    String title=driver.getTitle();	
		    System.out.println(title);  
		    test.log(Status.INFO, "Enter the EmailID and Password");
		    mp_obj=PageFactory.initElements(driver,poModelForSmoke.class);
			mp_obj.mp_login(pReader.getMarketplaceEmailid(), pReader.getMarketplacePassword());
		}
		@Test(priority=1,testName="Verify the Product details page")
		public void verify_product_details() throws Exception
		{
			test.log(Status.INFO,"Go to Product Category");
			mp_obj.product_details();
			test.log(Status.INFO, "Product has been added to the cart");
			
		}
		@Test(priority=2,testName="Verify the workload page")
		public void verify_workload() throws Exception
		{
			test.log(Status.INFO,"Click Configure");
			mp_obj.configure_workload();
			Thread.sleep(6000);
			test.log(Status.INFO, "Verify the price is displaying or not");
			WebElement price=driver.findElement(By.xpath("//div[@id='pricing']/span/h5"));
			System.out.println("The price display status is "+price.isDisplayed()+" and the price is "+price.getText());
		}
		@Test(priority=3,testName="Verify product delete")
		public void verify_product_delete() throws Exception
		{
			
			test.log(Status.INFO, "Verify Delete Bin of product");
			
			driver.findElement(By.xpath("//*[@id='remove-WLE']/i")).click();;
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("html/body/div[4]/div/div")).isDisplayed())
			{
			
				driver.findElement(By.xpath("html/body/div[4]/div/div/div[2]/button[2]")).click();
				Thread.sleep(10000);
			}
			
		}
		@Test(priority=4,testName="Verify logout")
		public void verify_logout() throws Exception
		{
			test.log(Status.INFO, "Go to Account Name header");
		    mp_obj.logout();
			Thread.sleep(4000);
			test.log(Status.INFO, "Loggedout Successfully");
			System.out.println("The title after login is "+driver.getTitle());
		}
		
}
