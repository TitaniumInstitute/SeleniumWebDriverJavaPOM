package com.ti.pages;

import org.openqa.selenium.By;

public class LoginPage extends MainPage{

    private By txtUser = By.id("user_login");

    private By txtPassword = By.name("pwd");

    private By chkRememberMe = By.cssSelector("#rememberme");

    private By btnLogin = By.xpath("//input[contains(@value, 'Log')]");

    private void setUserName(String userName){
        driver.findElement(txtUser).clear();
        driver.findElement(txtUser).sendKeys(userName);
    }

    private void setPassword(String password){
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }

    private void checkRememberMe(){
        driver.findElement(chkRememberMe).click();
    }

    private void clickLogin(){
        driver.findElement(btnLogin).click();
    }

    public void login(String userName, String password){
        setUserName(userName);
        setPassword(password);
        checkRememberMe();
        clickLogin();
        preLoading();
    }

}
