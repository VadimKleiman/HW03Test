package ru.spbau.mit.page;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class DashboardYouTrackTest {
    private final String PASSWORD = "root";
    private final String LOGIN = "root";
    private final String URL = "localhost:8080";
    private final boolean REMEMBER = false;
    private WebDriver driver;
    private DashboardYouTrack dashboard;

    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get(URL);
        LoginYouTrack track = new LoginYouTrack(driver);
        track.typeUsername(LOGIN);
        track.typePassword(PASSWORD);
        track.typeRemember(REMEMBER);
        dashboard = track.getDashboard();
    }

    @After
    public void destruct() {
        driver.quit();
    }

    @Test
    public void createUsersPageTest() {
        Assert.assertNotNull(dashboard);
        Assert.assertNotNull(dashboard.getUsers());
    }
}