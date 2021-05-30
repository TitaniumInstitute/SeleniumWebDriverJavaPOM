package com.ti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

public class StudentsPage extends MainPage{
    private By navStudents = By.linkText("Students");

    private By rdnGender = By.name("s_gender");

    private By txtFirstName = By.id("firstname");

    private By txtLastName = By.id("lastname");

    private By dtpDateOfBirth = By.id("Dob");

    private By tdSelectDay = By.xpath("//td[contains(@class,'day')]");

    private By txtCurrentAddress = By.id("current_address");

    private By txtEmailAddress = By.id("Email");

    private By txtUserName = By.name("Username");

    private By txtPassword = By.name("Password");

    private By txtConfirmPass = By.id("ConfirmPassword");

    private By txtRollNumber = By.id("Rollno");

    private By trStudentsRows = By.xpath("//tr[contains(@role,'row')]");

    public void clickStudents(){
        driver.findElement(navStudents).click();
        preLoading();
    }

    private void selectGender(String gender){
        for (WebElement optGender:driver.findElements(rdnGender)) {
            if (optGender.getAttribute("value").equals(gender)){
                optGender.click();
                break;
            }
        }
    }

    private void setFirstName(String firstName){
        driver.findElement(txtFirstName).clear();
        driver.findElement(txtFirstName).sendKeys(firstName);
    }

    private void setLastName(String lastName){
        driver.findElement(txtLastName).clear();
        driver.findElement(txtLastName).sendKeys(lastName);
    }

    private void setDayOfBirth(String selectedDay){
        driver.findElement(dtpDateOfBirth).click();

        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(tdSelectDay)));
        for (WebElement day:driver.findElements(tdSelectDay)) {
            if (day.getText().equals(selectedDay)){
                day.click();
                break;
            }
        }
    }

    private void setCurrentAddress(String currentAddress){
        driver.findElement(txtCurrentAddress).clear();
        driver.findElement(txtCurrentAddress).sendKeys(currentAddress);
    }

    public void studentPersonalDetails(String ... detail){
        selectGender(detail[0]);
        setFirstName(detail[1]);
        setLastName(detail[2]);
        setDayOfBirth(detail[3]);
        setCurrentAddress(detail[4]);
    }

    private void setEmailAddress(String email){
        driver.findElement(txtEmailAddress).clear();
        driver.findElement(txtEmailAddress).sendKeys(email);
    }

    private void setUserName(String userName){
        driver.findElement(txtUserName).clear();
        driver.findElement(txtUserName).sendKeys(userName);
    }

    private void setPassword(String password){
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }

    private void setConfirmPassword(String confPass){
        driver.findElement(txtConfirmPass).clear();
        driver.findElement(txtConfirmPass).sendKeys(confPass);
    }

    public void accountInformation(Map<String,String> accountInfo){
        setEmailAddress(accountInfo.get("email"));
        setUserName(accountInfo.get("user"));
        setPassword(accountInfo.get("password"));
        setConfirmPassword(accountInfo.get("password"));
    }

    public void schoolDetails(String rollNumber){
        driver.findElement(txtRollNumber).clear();
        driver.findElement(txtRollNumber).sendKeys(rollNumber);
        driver.findElement(txtRollNumber).submit();
        preLoading();
    }

    public void validateStudentIsAdded(String name){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(trStudentsRows)));
        }catch (TimeoutException te){
            preLoading();
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(trStudentsRows)));
        }

        WebElement newStudentRow = driver.findElements(trStudentsRows).get(driver.findElements(trStudentsRows).size()-1);
        Assert.assertTrue(newStudentRow.getText().contains(name));
    }

    public void deleteStudent(){
        deleteRow();
        confirmationWindow();
    }
}
