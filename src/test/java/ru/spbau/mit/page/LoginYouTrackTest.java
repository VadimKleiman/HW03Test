package ru.spbau.mit.page;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.spbau.mit.page.LoginYouTrack;

import java.util.concurrent.TimeUnit;

public class LoginYouTrackTest {
    private final String URL = "localhost:8080";
    private final boolean REMEMBER = false;
    private WebDriver driver;

    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @After
    public void destruct() {
        driver.quit();
    }

    @Test
    public void correctTest() {
        final String PASSWORD = "root";
        final String LOGIN = "root";
        LoginYouTrack track = new LoginYouTrack(driver);
        track.typeUsername(LOGIN);
        track.typePassword(PASSWORD);
        track.typeRemember(REMEMBER);
        Assert.assertNotNull(track.getDashboard());
    }

    @Test
    public void incorrectPasswordTest() {
        final String PASSWORD = "123";
        final String LOGIN = "root";
        LoginYouTrack track = new LoginYouTrack(driver);
        track.typeUsername(LOGIN);
        track.typePassword(PASSWORD);
        track.typeRemember(REMEMBER);
        Assert.assertNull( track.getDashboard());
    }

    @Test
    public void incorrectLoginAndPasswordTest() {
        final String PASSWORD = "123";
        final String LOGIN = "123";
        LoginYouTrack track = new LoginYouTrack(driver);
        track.typeUsername(LOGIN);
        track.typePassword(PASSWORD);
        track.typeRemember(REMEMBER);
        Assert.assertNull( track.getDashboard());
    }
}