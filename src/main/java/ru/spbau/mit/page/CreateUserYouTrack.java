package ru.spbau.mit.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.mit.element.CheckBoxElement;
import ru.spbau.mit.element.InputElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateUserYouTrack {
    private WebElement element;
    private WebDriver driver;
    private String url;
    private final By createUserLocator = By.id("id_l.U.cr.createUserDialog");
    private final By loginLocator = By.id("id_l.U.cr.login");
    private final By passwordLocator = By.id("id_l.U.cr.password");
    private final By confirmPasswordLocator = By.id("id_l.U.cr.confirmPassword");
    private final By changePasswordLocator = By.id("id_l.U.cr.forcePasswordChange");
    private final By fullNameLocator = By.id("id_l.U.cr.fullName");
    private final By emailLocator = By.id("id_l.U.cr.email");
    private final By jabberLocator = By.id("id_l.U.cr.jabber");
    private final By createLocator = By.id("id_l.U.cr.createUserOk");
    private final By cancelLocator = By.id("id_l.U.cr.createUserCancel");
    private final By closeLocator = By.id("id_l.U.cr.closeCreateUserDlg");
    private InputElement login;
    private InputElement password;
    private InputElement confirmPassword;
    private CheckBoxElement changePassword;
    private InputElement fullName;
    private InputElement email;
    private InputElement jabber;

    public CreateUserYouTrack(WebDriver driver) {
        this.driver = driver;
        this.url = driver.getCurrentUrl();
        this.element = driver.findElement(createUserLocator);
        login = new InputElement(element.findElement(loginLocator));
        password = new InputElement(element.findElement(passwordLocator));
        confirmPassword = new InputElement(element.findElement(confirmPasswordLocator));
        changePassword = new CheckBoxElement(element.findElement(changePasswordLocator));
        fullName = new InputElement(element.findElement(fullNameLocator));
        email = new InputElement(element.findElement(emailLocator));
        jabber = new InputElement(element.findElement(jabberLocator));
    }

    public void typeLogin(String login) {
        this.element = driver.findElement(createUserLocator);
        this.login.setElement(element.findElement(loginLocator));
        this.login.setValue(login);
    }

    public void typePassword(String password) {
        this.element = driver.findElement(createUserLocator);
        this.password.setElement(element.findElement(passwordLocator));
        this.password.setValue(password);
    }

    public void typeConfirmPassword(String confirmPassword) {
        this.element = driver.findElement(createUserLocator);
        this.confirmPassword.setElement(element.findElement(confirmPasswordLocator));
        this.confirmPassword.setValue(confirmPassword);
    }

    public void typeChangePassword(boolean change) {
        this.element = driver.findElement(createUserLocator);
        changePassword.setElement(element.findElement(changePasswordLocator));
        this.changePassword.selected(change);
    }

    public void typeFullName(String fullName) {
        this.element = driver.findElement(createUserLocator);
        this.fullName.setElement(element.findElement(fullNameLocator));
        this.fullName.setValue(fullName);
    }

    public void typeEmail(String email) {
        this.element = driver.findElement(createUserLocator);
        this.email.setElement(element.findElement(emailLocator));
        this.email.setValue(email);
    }

    public void typeJabber(String jabber) {
        this.element = driver.findElement(createUserLocator);
        this.jabber.setElement(element.findElement(jabberLocator));
        this.jabber.setValue(jabber);
    }

    public void create() throws InterruptedException {
        this.element = driver.findElement(createUserLocator);
        this.element.findElement(createLocator).click();
        this.driver.get(this.url);
        Thread.sleep(3000);
        this.driver.navigate().refresh();
    }

    public void cancel() {
        this.element = driver.findElement(createUserLocator);
        this.element.findElement(cancelLocator).click();
    }

    public void close() {
        this.element = driver.findElement(createUserLocator);
        this.element.findElement(closeLocator).click();
    }
}
