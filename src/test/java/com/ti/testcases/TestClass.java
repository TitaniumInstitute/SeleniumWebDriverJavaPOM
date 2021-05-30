package com.ti.testcases;

import org.testng.annotations.Test;

public class TestClass extends BaseTest{

    @Test(priority = 1)
    void loginWithRightCredentials(){
        loginPage.login(userCredentials.get("username"), userCredentials.get("userpass"));
        loginPage.verySchoolName();
    }

    @Test(priority = 2)
    void createNewStudent(){
        studentsPage.clickStudents();
        studentsPage.clickCreateNew();
        studentsPage.studentPersonalDetails(studentPersonalDetails);
        studentsPage.accountInformation(studentAccountInfo);
        studentsPage.schoolDetails("015");
        studentsPage.validateStudentIsAdded(studentPersonalDetails[1]);
    }

}
