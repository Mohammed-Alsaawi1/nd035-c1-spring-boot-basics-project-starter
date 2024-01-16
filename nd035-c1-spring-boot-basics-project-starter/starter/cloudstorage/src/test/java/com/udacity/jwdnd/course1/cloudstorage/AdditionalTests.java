package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdditionalTests {


    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }


    @Test
    @Order(2)
    private void checkAccess(){


    }
    @Test
    @Order(1)
    public void ASignup(){
            doMockSignUp("q","q","q","q");

    }
    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/

    private void doMockSignUp(String firstName,String lastName,String userName,String password){
        // Create a dummy account for logging in later.

        // Visit the sign-up page.
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        driver.get("http://localhost:" + this.port + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill out credentials
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        WebElement inputLastName = driver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.click();
        inputUsername.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.click();
        inputPassword.sendKeys(password);

        // Attempt to sign up.
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
        WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
        buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
        Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
    }



    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doLogIn(String userName, String password)
    {
        // Log in to our dummy account.
        driver.get("http://localhost:" + this.port + "/login");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement loginUserName = driver.findElement(By.id("inputUsername"));
        loginUserName.click();
        loginUserName.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement loginPassword = driver.findElement(By.id("inputPassword"));
        loginPassword.click();
        loginPassword.sendKeys(password);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        webDriverWait.until(ExpectedConditions.titleContains("Home"));

    }
public void doLogout(){
//    doMockSignUp("q","q","q","q");
    doLogIn("q","q");
    driver.get("http://localhost:" + this.port + "/home");
    WebDriverWait webDriverWait = new WebDriverWait(driver, 2);


    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/form/button")));
    WebElement loginUserName = driver.findElement(By.xpath("/html/body/div/div[1]/form/button"));
    loginUserName.click();

    Assertions.assertEquals("http://localhost:" + this.port + "/login?logout", driver.getCurrentUrl());
    driver.get("http://localhost:" + this.port + "/home");
}
    @Test
    public void LoginAndLogoutTest(){
//        doMockSignUp("q","q","q","q");
        doLogIn("q","q");
        doLogout();
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

    }
    @Test
    public void NoteTest(){
//        doMockSignUp("q","q","q","q");
        doLogIn("q","q");
        driver.get("http://localhost:" + this.port + "/home");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement NotePage = driver.findElement(By.id("nav-notes-tab"));
         NotePage.click();

        WebDriverWait webDriverWait1 = new WebDriverWait(driver, 2);
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddNote")));
        WebElement NotePage1 = driver.findElement(By.id("AddNote"));
        NotePage1.click();

        WebDriverWait webDriverWait3 = new WebDriverWait(driver, 2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        WebElement noteTitle = driver.findElement(By.id("note-title"));
        noteTitle.click();
        noteTitle.sendKeys("AAA");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
        WebElement noteDescription= driver.findElement(By.id("note-description"));
        noteDescription.click();
        noteDescription.sendKeys("BBB");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("savenote")));
        WebElement SaveNote= driver.findElement(By.id("savenote"));
        SaveNote.click();

        Assertions.assertEquals("http://localhost:" + this.port + "/note", driver.getCurrentUrl());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok")));
        WebElement toHome= driver.findElement(By.id("ok"));
        toHome.click();
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());


        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement NotePage2 = driver.findElement(By.id("nav-notes-tab"));
        NotePage2.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editid")));
        WebElement toEdit= driver.findElement(By.id("editid"));
        toEdit.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        WebElement noteTitle3= driver.findElement(By.id("note-title"));
        noteTitle3.click();
        noteTitle3.sendKeys("CCC");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
        WebElement noteDescription5 = driver.findElement(By.id("note-description"));
        noteDescription5.click();
        noteDescription5.sendKeys("DDD");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("savenote")));
        WebElement savenote3 = driver.findElement(By.id("savenote"));
        savenote3.click();

        Assertions.assertEquals("http://localhost:" + this.port + "/note", driver.getCurrentUrl());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok")));
        WebElement toHome2= driver.findElement(By.id("ok"));
        toHome2.click();
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());


        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement NotePage3 = driver.findElement(By.id("nav-notes-tab"));
        NotePage3.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-note")));
        WebElement todelete = driver.findElement(By.id("delete-note"));
        todelete.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement NotePage4 = driver.findElement(By.id("nav-notes-tab"));
        NotePage4.click();
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

    }
    @Test
    public void credentialTest(){
//        doMockSignUp("q","q","q","q");
        doLogIn("q","q");
        driver.get("http://localhost:" + this.port + "/home");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
        WebElement CredentialPage = driver.findElement(By.id("nav-credentials-tab"));
        CredentialPage.click();

        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addcredential")));
        WebElement AddCredentialpage = driver.findElement(By.id("addcredential"));
        AddCredentialpage.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        WebElement URL = driver.findElement(By.id("credential-url"));
        URL.click();
        URL.sendKeys("AAA");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        WebElement UserName = driver.findElement(By.id("credential-username"));
        UserName.click();
        UserName.sendKeys("AAA");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
        WebElement Password = driver.findElement(By.id("credential-password"));
        Password.click();
        Password.sendKeys("AAA");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("savecredential")));
        WebElement savecredential = driver.findElement(By.id("savecredential"));
        savecredential.click();

        Assertions.assertEquals("http://localhost:" + this.port + "/credentials", driver.getCurrentUrl());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok")));
        WebElement toHome1= driver.findElement(By.id("ok"));
        toHome1.click();
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
        WebElement CredentialPage1 = driver.findElement(By.id("nav-credentials-tab"));
        CredentialPage1.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editcredentials")));
        WebElement editCredential = driver.findElement(By.id("editcredentials"));
        editCredential.click();


        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        WebElement url1 = driver.findElement(By.id("credential-url"));
        url1.click();
        url1.sendKeys("BBB");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        WebElement USERNAME = driver.findElement(By.id("credential-username"));
        USERNAME.click();
        USERNAME.sendKeys("BBB");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
        WebElement PASSWORD = driver.findElement(By.id("credential-password"));
        PASSWORD.click();
        PASSWORD.sendKeys("BBB");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("savecredential")));
        WebElement savecredential1 = driver.findElement(By.id("savecredential"));
        savecredential1.click();

        Assertions.assertEquals("http://localhost:" + this.port + "/credentials", driver.getCurrentUrl());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok")));
        WebElement toHome2= driver.findElement(By.id("ok"));
        toHome2.click();
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
        WebElement CredentialPage2 = driver.findElement(By.id("nav-credentials-tab"));
        CredentialPage2.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-credentials")));
        WebElement deleteCredential = driver.findElement(By.id("delete-credentials"));
        deleteCredential.click();

        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());



    }



}
