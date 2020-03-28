package br.edu.unipampa.app.testesAutomatizados;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Secretário {

    WebDriver driver;

    @Before
    public void config() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void OSecretarioDevePoderOrdenarItensDePauta() throws InterruptedException {

        this.driver.get("http://localhost:8080/dashboard.xhtml");

        Thread.sleep(4000);

        this.driver.findElement(By.xpath("//*[@id=\"j_idt13\"]/div/a[1]/div/div")).click();

        Thread.sleep(2000);

        this.driver.findElement(By.xpath("//*[@id=\"form:opt-lista:0:j_idt23\"]")).click();

        Thread.sleep(2000);

        this.driver.findElement(By.id("form:j_idt56")).click();

        this.driver.findElement(By.id("form:descricao")).sendKeys("Apresentação de RP");
        this.driver.findElement(By.id("form:relator")).sendKeys("Igor");

        Thread.sleep(6000);

        this.driver.findElement(By.id("form:btnSalvar")).click();

        Thread.sleep(5000);

        WebElement elementoBase = driver.findElement(By.xpath("//*[@id=\"form:itens-lista_data\"]/tr[1]"));

        WebElement elementoAlvo = driver.findElement(By.xpath("//*[@id=\"form:itens-lista_data\"]/tr[2]"));

        Actions action = new Actions(driver);

        action.clickAndHold(elementoBase).moveToElement(elementoAlvo).release().build().perform();

        Thread.sleep(4000);

        elementoBase.click();

        Thread.sleep(4000);

        this.driver.findElement(By.xpath("//*[@id=\"form:j_idt57\"]")).click();

        Thread.sleep(4000);

        this.driver.findElement(By.id("form:descricao-editar")).clear();
        this.driver.findElement(By.id("form:descricao-editar")).sendKeys("Editado");
        this.driver.findElement(By.id("form:relator-editar")).clear();
        this.driver.findElement(By.id("form:relator-editar")).sendKeys("editado");



        this.driver.findElement(By.xpath("//*[@id=\"form:btnEditar\"]/span")).click();

        Thread.sleep(6000);

        this.driver.findElement(By.xpath("//*[@id=\"form:delete-btn\"]")).click();

        Thread.sleep(5000);
        this.driver.findElement(By.xpath("//*[@id=\"form:j_idt33\"]/span")).click();
        Thread.sleep(8000);
    }

    @After
    public void quit() throws InterruptedException {
        Thread.sleep(2000);
        this.driver.quit();
    }


}
