package org.example;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Page page;
            try (Browser browser = playwright.chromium().launch()) {
                page = browser.newPage();
            }
            page.navigate("https://www.saucedemo.com/v1/");
            System.out.println(page.title());
            //System.out.println(page.locator("text=Get Started").textContent());
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshot.png"))
                    .setFullPage(true));

        }
    }
}