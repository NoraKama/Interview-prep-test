package tests;


import base.BaseTest;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.Test;


public class DashboardTest extends BaseTest {


    @Test(testName = "Enter new question button")
    public void test08() {

        //opening coding dashboard
        getDriver().findElement(By.xpath("//form[@class='form-inline']//button[text()='Coding']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//button[text()='Enter new question ']")).isEnabled());
    }
}

