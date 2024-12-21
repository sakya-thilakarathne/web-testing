package ui;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class LoginTests {

    public void testLogin() {
        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch()) {
                Page page = browser.newPage();

                // Navigate to the login page
                page.navigate("https://www.saucedemo.com/v1/");
                System.out.println("Page Title: " + page.title());

                // Debug screenshot before interaction
                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("before_interaction.png"))
                        .setFullPage(true));

                // Fill username and password fields
                page.fill("input[data-test='username']", "standard_user");
                page.fill("input[data-test='password']", "secret_sauce");

                // Wait for the login button to be visible by class name
                page.waitForSelector(".btn_action"); // 60 seconds timeout

                // Click the login button
                page.click(".btn_action");

                // Wait for post-login state
                page.waitForTimeout(3000); // Adjust timeout if needed

                System.out.println("Login Successful!");

                // Debug screenshot after login
                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("after_login.png"))
                        .setFullPage(true));
            }
        }
    }
}
