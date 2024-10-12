package Addtocart;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddToCart {

	public void addItems(WebDriver driver, String[] itemsNeeded) throws InterruptedException {
		int j = 0;
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		for (int i = 0; i < products.size(); i++) {
			String[] name = products.get(i).getText().split("-");
			// format to actual name
			String formattedName = name[0].trim();

			List itemsNeededList = Arrays.asList(itemsNeeded);

			if (itemsNeededList.contains(formattedName)) {
				j++;
				// click on add to cart
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (j == itemsNeeded.length) {
					break;
				}
			}
		}

	}

	public void checkout(WebDriver driver) {
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
		// WebElement codeApplied =driver.findElement(By.cssSelector("span.promoInfo"));
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.visibilityOfElementLocated((By) codeApplied)); 
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
	}

	public void postPromo(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/select")).click();
		driver.findElement(By.xpath("//option[contains(text(),'India')]")).click();
		driver.findElement(By.cssSelector("input.chkAgree")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Proceed')]")).click();

	}

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		// items to add in the cart
		String[] itemsNeeded = { "Brocolli", "Cucumber", "Beetroot" };
		// to manage browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// to open link
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		// object created for the class to call the methods
		AddToCart a = new AddToCart();
		a.addItems(driver, itemsNeeded);
		a.checkout(driver);
		a.postPromo(driver);
		System.out.println("Test Success");

		Thread.sleep(5000);
		driver.quit();

	}

}
