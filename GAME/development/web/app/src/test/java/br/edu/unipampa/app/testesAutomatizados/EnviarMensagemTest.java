//package br.edu.unipampa.app.testesAutomatizados;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.text.ParseException;
//
//import static org.apache.xalan.lib.ExsltDatetime.dateTime;
//import static org.apache.xalan.lib.ExsltDatetime.time;
//
///**
// * Created by Esther Favero on 11/05/2018.
// */
//public class EnviarMensagemTest {
//
//    static WebDriver driver;
//
//    @Before
//    public void config(){
//        WebDriverManager.chromedriver().setup();
//
//    }
//
//    @After
//    public void quit(){
//        driver.quit();
//    }
//
//    // #1
//    @Test
//    public void verificaSeAMensagemFoiEnviadaComEncaminhamentoPadraoTest(){
//
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        WebDriverWait encaminhamentoPadrao = new WebDriverWait(driver,100);
//        encaminhamentoPadrao.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt42"))).click();
//
//
//        WebDriverWait encaminhar = new WebDriverWait(driver,100);
//        encaminhar.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt82"))).click();
//
//        WebDriverWait confirma = new WebDriverWait(driver,100);
//        confirma.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt76"))).click();
//
//
//        driver.get("http:localhost:8181/itens-de-pauta/stream");
//
//        WebDriverWait id = new WebDriverWait(driver,100);
//        id.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
//
//        Assert.assertEquals("id", id);
//
//    }
//
//    // #2
//    @Test
//    public void verificaSeAMensagemFoiEnviadaComEncaminhamentoCustomizadoTest(){
//
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        WebDriverWait encaminhamentoCustomizado = new WebDriverWait(driver,100);
//        encaminhamentoCustomizado.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt46"))).click();
//
//        WebDriverWait preenche = new WebDriverWait(driver,100);
//        preenche.until(ExpectedConditions.visibilityOfElementLocated(By.id("input_form:input-opt"))).sendKeys("ABC");
//
//        WebDriverWait add = new WebDriverWait(driver,100);
//        add.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt62"))).sendKeys("ABC");
//
//
//        WebDriverWait encaminhar = new WebDriverWait(driver,100);
//        encaminhar.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt82"))).click();
//
//        WebDriverWait confirma = new WebDriverWait(driver,100);
//        confirma.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt76"))).click();
//
//
//        driver.get("http:localhost:8181/itens-de-pauta/stream");
//
//        WebDriverWait id = new WebDriverWait(driver,100);
//        id.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
//
//        Assert.assertEquals("id", id);
//
//    }
//
//    // #3
//    @Test
//    public void verificaSeAMensagemFoiEnviadaComEncaminhamentoCustomizadoComSegundoTurnoTest(){
//
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        WebDriverWait encaminhamentoCustomizado = new WebDriverWait(driver,100);
//        encaminhamentoCustomizado.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt46"))).click();
//
//        WebDriverWait preenche = new WebDriverWait(driver,100);
//        preenche.until(ExpectedConditions.visibilityOfElementLocated(By.id("input_form:input-opt"))).sendKeys("ABC");
//
//        WebDriverWait add = new WebDriverWait(driver,300);
//        add.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt62"))).click();
//
//        WebDriverWait segTurno = new WebDriverWait(driver,300);
//        segTurno.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:segundo-turno"))).click();
//
//        WebDriverWait encaminhar = new WebDriverWait(driver,100);
//        encaminhar.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt82"))).click();
//
//        WebDriverWait confirma = new WebDriverWait(driver,100);
//        confirma.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt76"))).click();
//
//
//        driver.get("http:localhost:8181/itens-de-pauta/stream");
//
//        WebDriverWait id = new WebDriverWait(driver,100);
//        id.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
//
//        Assert.assertEquals("id", id);
//
//    }
//
//    // #4
//    @Test
//    public void verificaSeAMensagemPossuiRelatorTest(){
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        WebDriverWait encaminhamentoPadrao = new WebDriverWait(driver,100);
//        encaminhamentoPadrao.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt42"))).click();
//
//
//        WebDriverWait encaminhar = new WebDriverWait(driver,100);
//        encaminhar.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt82"))).click();
//
//        WebDriverWait confirma = new WebDriverWait(driver,100);
//        confirma.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt76"))).click();
//
//
//        driver.get("http:localhost:8181/itens-de-pauta/stream");
//
//        WebDriverWait relator = new WebDriverWait(driver,100);
//        relator.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
//
//        Assert.assertEquals("relator", relator);
//    }
//
//    // #5
//    @Test
//    public void verificaSeAMensagemPossuiEncaminhamentosTest(){
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        WebDriverWait encaminhamentoPadrao = new WebDriverWait(driver,100);
//        encaminhamentoPadrao.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt42"))).click();
//
//
//        WebDriverWait encaminhar = new WebDriverWait(driver,100);
//        encaminhar.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt82"))).click();
//
//        WebDriverWait confirma = new WebDriverWait(driver,100);
//        confirma.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt76"))).click();
//
//
//        driver.get("http:localhost:8181/itens-de-pauta/stream");
//
//        WebDriverWait encaminhamentos = new WebDriverWait(driver,100);
//        encaminhamentos.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
//
//        Assert.assertEquals("Favorável", encaminhamentos);
//    }
//
//    // #6
//    @Test
//    public void verificaSeAOpcaoParaSegundoTurnoÉFalseNoEncaminhamentoPadraoTest(){
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
//
//        WebDriverWait encaminhamentoPadrao = new WebDriverWait(driver,100);
//        encaminhamentoPadrao.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt42"))).click();
//
//
//        WebDriverWait encaminhar = new WebDriverWait(driver,100);
//        encaminhar.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt82"))).click();
//
//        WebDriverWait confirma = new WebDriverWait(driver,100);
//        confirma.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:j_idt76"))).click();
//
//
//        driver.get("http:localhost:8181/itens-de-pauta/stream");
//
//        WebDriverWait segTurno = new WebDriverWait(driver,100);
//        segTurno.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
//
//        Assert.assertEquals("false", segTurno);
//    }
//
//    @After
//    public void close(){
//        driver.close();
//    }
//}
