package ru.spbau.mit.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.mit.element.TableElement;

import java.util.List;

public class UsersYouTrack {
    private final By tableLocator = By.xpath("//div[@class='jt-panel-content']//div[2]//table//tbody");
    private final By buttonLocator = By.id("id_l.U.createNewUser");
    private WebDriver driver;
    private TableElement table;

    public UsersYouTrack(WebDriver driver) {
        this.driver = driver;
        if (!this.driver.getCurrentUrl().endsWith("users")) {
            throw new IllegalStateException("This is not the users page");
        }
        table = new TableElement(driver.findElement(tableLocator));
    }

    public int getRowsCount() {
        table.setElement(driver.findElement(tableLocator));
        return table.getRowsCount();
    }

    public CreateUserYouTrack createUser() throws InterruptedException {
        driver.findElement(buttonLocator).click();
        (new WebDriverWait(driver, 2)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("id_l.U.cr.createUserDialog")).getCssValue("display").equals("block");
            }
        });
        return new CreateUserYouTrack(driver);
    }

    public List<String> getColumnByID(int id) {
        table.setElement(driver.findElement(tableLocator));
        switch (id) {
            case 1: return table.getUserNames();
            case 2: return table.getFullName();
            case 3: return table.getEmailAndJabber();
            case 4: return table.getGroups();
            case 5: return table.getLastAccess();
            default: throw new IllegalStateException("Wrong id: " + String.valueOf(id));
        }
    }

    public boolean removeUser(int index) throws InterruptedException {
        table.setElement(driver.findElement(tableLocator));
        boolean out = table.removeAt(index);
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        return out;
    }
}
