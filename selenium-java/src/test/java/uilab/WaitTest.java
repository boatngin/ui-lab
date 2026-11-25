package uilab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class WaitTest extends BaseTest {

    static final String PAGE =
            "https://www.selenium.dev/selenium/web/dynamic.html";

    // Goal: clicking "Add a box!" adds a red box to the page.
    @Test
    void addABox() {
        driver.get(PAGE);
        driver.findElement(By.id("adder")).click();
        WebElement box = driver.findElement(By.id("box0"));
        assertEquals("redbox", box.getDomAttribute("class"));
    }
}
