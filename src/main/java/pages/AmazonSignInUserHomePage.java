package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AmazonSignInUserHomePage extends HomePage {

    private WebDriver driver;

    By allMenuList = By.xpath("//a[@id='nav-hamburger-menu']");
    By seeAllButton = By.xpath("//a[@class='hmenu-item hmenu-compressed-btn']");
    By videoGamesButton = By.xpath("//ul[1]/ul/li[11]/a");
    By allVideoGamesButton = By.xpath("//ul[32]/li[3]");
    By freeShippingFilter = By.xpath("//div[2]/ul/li/span/a/div[1]/label/i");
    By newConditionLink = By.xpath("//div[7]/ul[1]/span/span[1]/li/span/a");
    By sortByDropDownList= By.xpath("//span[@id = 'a-autoid-0' ]");
    By nextPageButton = By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']");
    By addToCartButton= By.xpath("//input[@id='add-to-cart-button']");
    By addToCartButtonFromHomePage= By.xpath("//span[@class='a-button a-button-primary a-button-icon']");
    By goToBasketButton = By.xpath("//span[@id='a-autoid-3']");
    By pricesBelow15K= By.xpath("//span[@class='a-price-whole' and translate(., ',', '') < 15000]");
    By cartNumberOfProducts=By.xpath("//span[@id='nav-cart-count']");




    public AmazonSignInUserHomePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public void openAllMenu() {
        driver.findElement(allMenuList).click();
    }
    public void setSeeAllButton()
    {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='hmenu-item hmenu-compressed-btn']"))).click();
    }
    public void setVideoGamesButton()
    {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(videoGamesButton)).click();
    }

    public void setAllVideoGamesButton()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(allVideoGamesButton)).click();
    }

    public void setFreeShippingFilter()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(freeShippingFilter)).click();
    }
    public void setNewConditionLink()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newConditionLink)).click();
    }
    public  void  setSortByDropDownList()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.findElement(sortByDropDownList).click();
        List<WebElement>sortByOptions=driver.findElements(By.xpath("//div[4]/div/div/ul/li"));
        for(WebElement sortOption :sortByOptions )
        {
            String optionText=sortOption.getText();
            System.out.println(optionText);
            if("Price: High to Low".equalsIgnoreCase(optionText.trim()))
            {
                sortOption.click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                break;
            }
        }

    }


    public int workAround()
    {
        int numItemsAdded = 0;
        while (true) {
            List<WebElement> ItemsPrices = driver.findElements(pricesBelow15K);
            List<String> ItemsLinks = new ArrayList<>();

            for (WebElement item : ItemsPrices) {
                WebElement productLink = item.findElement(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
                ItemsLinks.add(productLink.getAttribute("href"));
            }


            for (String itemLink : ItemsLinks) {
                driver.get(itemLink);
                driver.findElement(addToCartButton).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@id='nav-cart-count']"), String.valueOf(numItemsAdded + 1)));

                numItemsAdded++;
                driver.navigate().back();
            }

            boolean status=driver.findElement(nextPageButton).isEnabled();

            if (!status) {
                break; // No more pages, exit the loop
            } else {
                driver.findElement(nextPageButton).click(); // Navigate to the next page
            }
        }
        return numItemsAdded;
    }






    public int addProductBelow15KToCart()
    {
        int numberOfProductBelow15K=0;
        while(true)
        {
            List<WebElement>productsPrices=driver.findElements(pricesBelow15K);
            for(WebElement productPrice : productsPrices)
            {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                webDriverWait.until(ExpectedConditions.elementToBeClickable(addToCartButtonFromHomePage)).click();
                numberOfProductBelow15K++;
                System.out.println("Number Of Items Added To The Cart : " +numberOfProductBelow15K);
            }
            boolean nextButtonEnabled= webDriverWait.until(ExpectedConditions.presenceOfElementLocated(nextPageButton)).isEnabled();
            if (nextButtonEnabled)
            {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            }
            else {
                break;
            }
        }
        return numberOfProductBelow15K;
    }


    public int getCurrentNumberOfProductsAddedToTheCart()
    {
         String cartCount=webDriverWait.until(ExpectedConditions.presenceOfElementLocated(cartNumberOfProducts)).getText();
         return Integer.parseInt(cartCount);
    }


    public boolean verifyAllProductsBelow15KAddedToTheCart()
    {
        int expectedAddedProductToTheCart= addProductBelow15KToCart();
        int actualProductsInTheCart = getCurrentNumberOfProductsAddedToTheCart();
        return expectedAddedProductToTheCart==actualProductsInTheCart;
    }

    public CheckOutPage goToBasket()
    {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(goToBasketButton)).click();
        return new CheckOutPage(driver);

    }



}
