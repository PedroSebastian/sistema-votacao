package br.edu.unipampa.app.testesAutomatizados;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReuniaoTest {

    static WebDriver driver;

    @Before
    public void config(){
        WebDriverManager.chromedriver().setup();

    }

    @After
    public void quit(){
        driver.quit();
    }

    @Test
    public void verificaSeUmaReuniaoEstaDisponivel(){
        driver = new ChromeDriver();
        driver.get("http://localhost:8181/reunioes.jsf");
        WebElement reuniao = driver.findElement(By.className("ui-datalist-item"));
        Assert.assertTrue(reuniao.isDisplayed());
    }

//    @Test
//    public void clicarEmUmaReuniao(){
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/reunioes.jsf");
//        WebElement reuniao = driver.findElement(By.className("ui-datalist-item"));
//        reuniao.click();
//        WebElement listaDeItens = driver.findElement(By.id("form:opt-lista_list"));
//        Assert.assertTrue(listaDeItens.isDisplayed());
//    }

//    @Test
//    public void entrarEmUmaReuniaoEncaminharUmItem() throws InterruptedException {
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/reunioes.jsf");
//        WebElement reuniao = driver.findElement(By.className("ui-datalist-item"));
//        reuniao.click();
//        WebElement listaDeItens = driver.findElement(By.id("form:opt-lista_list"));
//        listaDeItens.click();
//        Thread.sleep(1000);
//        WebElement selecionarPadrao = driver.findElement(By.id("form:j_idt42"));
//        selecionarPadrao.click();
//        driver.findElement(By.id("form:j_idt82")).click();
//        Thread.sleep(1000);
//        WebElement confirmacao = driver.findElement(By.id("form:j_idt76"));
//        confirmacao.click();
//        Thread.sleep(5000);
//        WebElement sucesso = driver.findElement(By.className("ui-growl-message"));
//        Assert.assertTrue(sucesso.isDisplayed());
//    }

    @Test
    public void verificaADisponibilidadeVariasReunioes(){

        driver = new ChromeDriver();
        driver.get("http://localhost:8181/reunioes.jsf");
        for(int i = 0; i < 5; i++){
            WebElement primeiroItem = driver.findElement(By.xpath("//*[@id=\"form:opt-lista:"+i+":j_idt21\"]"));
            Assert.assertTrue(primeiroItem.isDisplayed());
        }
    }
}
