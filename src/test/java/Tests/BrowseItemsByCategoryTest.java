package Tests;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class BrowseItemsByCategoryTest extends TestBase{

    @Test(description = "Go to men's clothing category",priority = 40)
    public void goToMenClothingJeansCategory(){
       int count = 0;
       int maxTries = 3;
       while(count != maxTries){
           try{
               amazonHomePage.clickOnMenJeansCategory();
               break;
           } catch (NoSuchElementException e){
               count++;
           }
       }
        Assert.assertTrue(amazonHomePage.jeansWasSelected(),"Not in Jeans page");
    }

    @Test(description = "Sort page by price low to high", priority = 50)
    public void sortPageByPriceLowToHigh(){
        Select selectSort = new Select(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("s-result-sort-select")))));
        String pageSourceBeforeSorting = driver.getPageSource();
        selectSort.selectByValue("price-asc-rank");
        Assert.assertFalse(pageSourceBeforeSorting.equals(driver.getPageSource()),"Page didn't change after selecting sort option");
    }

    @Test(description = "Validate that items are sorted", priority = 60)
    public void validateThatItemsAreSorted(){
        List<WebElement> pricesElements = driver.findElements(By.cssSelector("span.rush-component.s-latency-cf-section  span:nth-child(1) > span.a-offscreen"));
        pricesElements.remove(0);//this is sponsored by amazon, it doesn't get ordered (always stays on top)
        ArrayList<Float> pricesList = new ArrayList<Float>();
        pricesElements.stream().forEach(pe -> {
            String peTextBeautified = pe.getAttribute("innerHTML").replace("ILS&nbsp;","");
            Float peFloat = Float.parseFloat(peTextBeautified);
            pricesList.add(peFloat);
        });
        Assert.assertTrue(Ordering.natural().isOrdered(pricesList));
    }

    @Test(description = "Filter items by eligible for free shipping", priority = 70)
    public void filterItemsByEligibleForFreeShipping(){
        String pageSourceBeforeFiltering = driver.getPageSource();
        driver.findElement(By.xpath("//*[@id=\"p_n_is_free_shipping/10236242011\"]/span/a/div/label/i")).click();
        Assert.assertFalse(pageSourceBeforeFiltering.equals(driver.getPageSource()),"Page didn't change after choosing our brand filter");
    }

    @Test(description = "Validate items were filtered", priority = 80)
    public void validateItemsWereFiltered(){
        List<WebElement> items = driver.findElements(By.cssSelector("h2 > a > span"));
        List<WebElement> freeShippingElements = driver.findElements(By.cssSelector("span[data-component-type=\"s-search-results\"] span.a-color-base.a-text-bold"));
        //if size of items is less than the size of amazon branded items then it must not pass( a bug was found on amazon)
        Assert.assertTrue(items.size() == freeShippingElements.size(),"Not all items were amazon branded");

    }
}
