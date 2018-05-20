package ru.spbau.mit.page;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UsersYouTrackTest {
    private final String URL = "localhost:8080";
    private final boolean REMEMBER = false;
    private final String PASSWORD = "root";
    private final String LOGIN = "root";
    private WebDriver driver;
    private UsersYouTrack users;


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
        DashboardYouTrack dashboard = track.getDashboard();
        if (dashboard != null) {
            users = dashboard.getUsers();
        }
    }

    @After
    public void destruct() {
        driver.quit();
    }

    @Test
    public void createUserTest() throws InterruptedException {
        final String FIRST_LOGIN = "user1";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user1";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        int prevRowsCount = users.getRowsCount();
        user.create();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount + 1, nextRowsCount);
        Assert.assertTrue(users.getColumnByID(1).contains(FIRST_LOGIN));
        Assert.assertTrue(users.removeUser(nextRowsCount - 1));
    }

    @Test
    public void createNotUniqueUserTest() throws InterruptedException {
        final String FIRST_LOGIN = "user1";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user1";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        user.create();
        int prevRowsCount = users.getRowsCount();
        user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        user.create();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount, nextRowsCount);
        Assert.assertTrue(users.removeUser(nextRowsCount - 1));
    }

    @Test
    public void wrongLoginTest() throws InterruptedException {
        final String FIRST_LOGIN = "user1 user1 %_";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user1";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        int prevRowsCount = users.getRowsCount();
        user.create();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount, nextRowsCount);
        Assert.assertFalse(users.getColumnByID(1).contains(FIRST_LOGIN));
    }

    @Test
    public void wrongConfirmPasswordTest() throws InterruptedException {
        final String FIRST_LOGIN = "user1";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user2";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        int prevRowsCount = users.getRowsCount();
        user.create();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount, nextRowsCount);
        Assert.assertFalse(users.getColumnByID(1).contains(FIRST_LOGIN));
    }

    @Test
    public void cancelTest() throws InterruptedException {
        final String FIRST_LOGIN = "user1";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user2";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        int prevRowsCount = users.getRowsCount();
        user.cancel();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount, nextRowsCount);
        Assert.assertFalse(users.getColumnByID(1).contains(FIRST_LOGIN));
    }

    @Test
    public void closeTest() throws InterruptedException {
        final String FIRST_LOGIN = "user1";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user2";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        int prevRowsCount = users.getRowsCount();
        user.close();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount, nextRowsCount);
        Assert.assertFalse(users.getColumnByID(1).contains(FIRST_LOGIN));
    }

    @Test
    public void createUserWithOptionalTest() throws InterruptedException {
        final String FIRST_LOGIN = "user1";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user1";
        final String FIRST_FULLNAME = "user user";
        final String FIRST_EMAIL = "user@mail.org";
        final String FIRST_JUBBER = "jubber";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        user.typeEmail(FIRST_EMAIL);
        user.typeJabber(FIRST_JUBBER);
        user.typeFullName(FIRST_FULLNAME);
        int prevRowsCount = users.getRowsCount();
        user.create();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount + 1, nextRowsCount);
        Assert.assertTrue(users.getColumnByID(1).contains(FIRST_LOGIN));
        Assert.assertTrue(users.removeUser(nextRowsCount - 1));
    }

    @Test
    public void emptyUserTest() throws InterruptedException {
        final String FIRST_LOGIN = "";
        final String FIRST_PASSWORD = "user1";
        final String FIRST_CONFIRM_PASSWORD = "user1";
        final boolean FIRST_CHANGE_PASSWORD = false;
        CreateUserYouTrack user = users.createUser();
        user.typeLogin(FIRST_LOGIN);
        user.typePassword(FIRST_PASSWORD);
        user.typeConfirmPassword(FIRST_CONFIRM_PASSWORD);
        user.typeChangePassword(FIRST_CHANGE_PASSWORD);
        int prevRowsCount = users.getRowsCount();
        user.create();
        int nextRowsCount = users.getRowsCount();
        Assert.assertEquals(prevRowsCount, nextRowsCount);
    }
}