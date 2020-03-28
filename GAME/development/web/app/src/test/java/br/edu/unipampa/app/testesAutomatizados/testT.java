package br.edu.unipampa.app.testesAutomatizados;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testT {

    WebDriver driver;

    @Before
    public void config(){
        WebDriverManager.chromedriver().setup();

    }

    @Test
    public void testandoSelenium(){
        driver = new ChromeDriver();
    }

    @After
    public void quit(){
        driver.quit();
    }

}
