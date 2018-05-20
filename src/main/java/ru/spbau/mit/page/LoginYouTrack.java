package ru.spbau.mit.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.mit.element.CheckBoxElement;
import ru.spbau.mit.element.InputElement;

public final class LoginYouTrack {
    private final By loginLocator = By.id("id_l.L.login");
    private final By passwordLocator = By.id("id_l.L.password");
    private final By rememberLocator = By.id("id_l.L.rememberMeLabel");
    private final By buttonLocator = By.id("id_l.L.loginButton");
    private InputElement login;
    private InputElement password;
    private CheckBoxElement remember;
    private WebDriver driver;

    public LoginYouTrack(WebDriver driver) {
        this.driver = driver;
        if (!this.driver.getCurrentUrl().endsWith("login")) {
            throw new IllegalStateException("This is not the login page");
        }
        login = new InputElement(driver.findElement(loginLocator));
        password = new InputElement(driver.findElement(passwordLocator));
        remember = new CheckBoxElement(driver.findElement(rememberLocator));
    }

    public void typeUsername(String username) {
        login.setElement(driver.findElement(loginLocator));
        login.setValue(username);
    }

    public void typePassword(String password) {
        this.password.setElement(driver.findElement(passwordLocator));
        this.password.setValue(password);
    }

    public void typeRemember(boolean remember) {
        this.remember.setElement(driver.findElement(rememberLocator));
        this.remember.selected(remember);
    }

    public DashboardYouTrack getDashboard() {
        WebElement button = driver.findElement(buttonLocator);
        button.click();
        try {
            (new WebDriverWait(driver, 2)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getCurrentUrl().endsWith("/dashboard");
                }
            });
        } finally {
            if (driver.getCurrentUrl().endsWith("/dashboard")) {
                return new DashboardYouTrack(driver);
            }
            return null;
        }
    }

}
