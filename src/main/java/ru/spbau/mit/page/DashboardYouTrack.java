package ru.spbau.mit.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public final class DashboardYouTrack {
    private WebDriver driver;
    private By menuLocator = By.xpath("//div[@class='ring-menu__right']//a[2]");
    private By userLocator = By.xpath("//div[@class='ring-dropdown__i']//a[2]");

    public DashboardYouTrack(WebDriver driver) {
        this.driver = driver;
        if (!this.driver.getCurrentUrl().endsWith("dashboard")) {
            throw new IllegalStateException("This is not the dashboard page");
        }
    }

    public UsersYouTrack getUsers() {
        driver.findElement(menuLocator).click();
        driver.findElement(userLocator).click();
        return new UsersYouTrack(driver);
//        try {
//            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//        } finally {
//            if (driver.getCurrentUrl().endsWith("/users")) {
//                return new UsersYouTrack(driver);
//            }
//            return null;
//        }
//        try {
//            (new WebDriverWait(driver, 2)).until(new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver d) {
//                    return d.getCurrentUrl().endsWith("/users");
//                }
//            });
//        } finally {
//            if (driver.getCurrentUrl().endsWith("/users")) {
//                return new UsersYouTrack(driver);
//            }
//            return null;
//        }
    }
}
