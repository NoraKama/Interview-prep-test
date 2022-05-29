package tests;


import base.BaseTest;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.Test;

import java.util.List;


public class HomeTest extends BaseTest {


    @Test(testName = "IN-1 - Test Title", description = "Validating title on home page")
    public void test01() {
        //signing in as a user
        getDriver().findElement(By.name("email")).sendKeys("test@yahoo.com");
        getDriver().findElement(By.name("password")).sendKeys("test123");
        getDriver().findElement(By.xpath("//div[@class='container']/button")).click();
        Assert.assertEquals(getDriver().getTitle(), "Interview App");
    }

    @Test(testName = "IN-2 - Test Sign Out button", description = "Testing visibility of the Sign out button")
    public void test02() {
        //signing in as a user
        getDriver().findElement(By.name("email")).sendKeys("test@yahoo.com");
        getDriver().findElement(By.name("password")).sendKeys("test123");
        getDriver().findElement(By.xpath("//div[@class='container']/button")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//nav//a/u[text()='Sign out']")).isEnabled());
    }

    @Test(testName = "IN-2 - Test Manage Access button", description = "Testing button is not visible")
    public void test03() {
        //signing in as a user
        getDriver().findElement(By.name("email")).sendKeys("test@yahoo.com");
        getDriver().findElement(By.name("password")).sendKeys("test123");
        getDriver().findElement(By.xpath("//div[@class='container']/button")).click();
        List<WebElement> elementList = getDriver().findElements(By.xpath("//*[text()='Manage Access']"));
        Assert.assertEquals(elementList.size(), 0);
    }

    @Test(testName = "IN-3 - Default dashboards", description = "Validate 3 dashboards are present")
    public void test04() {
        //signing in as a user
        getDriver().findElement(By.name("email")).sendKeys("test@yahoo.com");
        getDriver().findElement(By.name("password")).sendKeys("test123");
        getDriver().findElement(By.xpath("//div[@class='container']/button")).click();
        String[] data = {"All Topics", "Coding", "Soft skills"};
        for (String text : data) {
            Assert.assertTrue(getDriver().findElement(
                    By.xpath("//form[@class='form-inline']//button[text()='" + text + "']")).isEnabled());
        }
    }

    @Test(testName = "IN-4 - Statement Do's section", description = "Verify user can add a statement in Do's section")
    public void test05() {
        //signing in as a user
        getDriver().findElement(By.name("email")).sendKeys("test@yahoo.com");
        getDriver().findElement(By.name("password")).sendKeys("test123");
        getDriver().findElement(By.xpath("//div[@class='container']/button")).click();
        //Adding a statement in Do's section
        getDriver().findElement(By.xpath("//button[text()='Add do ']")).click();
        getDriver().findElement(By.id("inputArea1")).sendKeys("IN-4 Do section test");

        //capturing initial size of the statements right before adding a new statement
        List<WebElement> initialList = getDriver().findElements(By.xpath("(//div[@class='anyClass'])[1]/div"));

        //clicking to add the statement
        getDriver().findElement(By.xpath("//button[text()='Enter']")).click();

        //waiting for the list to be bigger than initial size
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("(//div[@class='anyClass'])[1]/div"), initialList.size()));

        String actualText = getDriver().findElement(
                By.xpath("(//div[@class='anyClass'])[1]/div[last()]/div[contains(@class, 'col-md-7')]")).getText();
        String expectedTest = "IN-4 Do section test";

        Assert.assertEquals(actualText, expectedTest);
    }

    @Test(testName = "IN-4 - Statement Dont's section", description = "Verify user can add a statement in Dont's section")
    public void test06() {
        //signing in as a user
        getDriver().findElement(By.name("email")).sendKeys("test@yahoo.com");
        getDriver().findElement(By.name("password")).sendKeys("test123");
        getDriver().findElement(By.xpath("//div[@class='container']/button")).click();
        String statement = "IN-4 Don't section test";

        //Adding a statement in Do's section
        getDriver().findElement(By.xpath("//button[text()=\"Add don't \"]")).click();
        getDriver().findElement(By.id("inputArea2")).sendKeys(statement);

        //capturing initial size of the statements right before adding a new statement
        List<WebElement> initialList = getDriver().findElements(By.xpath("(//div[@class='anyClass'])[2]/div"));

        //clicking to add the statement
        getDriver().findElement(By.xpath("//button[text()='Enter']")).click();

        //waiting for the list to be bigger than initial size
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("(//div[@class='anyClass'])[2]/div"), initialList.size()));

        String actualText = getDriver().findElement(
                By.xpath("(//div[@class='anyClass'])[2]/div[last()]/div[contains(@class, 'col-md-7')]")).getText();

        Assert.assertEquals(actualText, statement);
    }
}