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
//
//public class CadastrarItemDePautaTest {
//
//    WebDriver driver;
//
//    @Before
//    public void config(){
//        WebDriverManager.chromedriver().setup();
//
//    }
//
//    //#1
//
////    @Test
////    public void cadastraUmItemDePautaComoPadrao(){
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/itens-de-pauta.jsf?reuniao=1");
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////        inputDescricao.sendKeys("Teste");
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////        inputRelator.sendKeys("Teste");
////
////        WebElement encaminhamentoPadrao = driver.findElement(By.id("form:j_idt42"));
////        encaminhamentoPadrao.click();
////
////        WebElement encaminhar = driver.findElement(By.id("form:j_idt68"));
////        encaminhar.click();
////    }
////
////    //#2
////    @Test
////    public void cadastraUmItemDePautaComoCustomizado(){
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/itens-de-pauta.jsf?reuniao=1");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////        inputDescricao.sendKeys("Teste");
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////        inputRelator.sendKeys("Teste");
////
////        WebElement encaminhamentoCustomizado = driver.findElement(By.id("form:j_idt46"));
////        encaminhamentoCustomizado.click();
////
////        WebElement encaminhar = driver.findElement(By.id("form:j_idt68"));
////        encaminhar.click();
////    }
////
////    //#3
////    @Test
////    public void naoDeveCadastrarUmEncaminhamentoPadraoVazio(){
////
////        driver.get("http://localhost:8888/item-de-pauta.jsf");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////
////        WebElement encaminhamentoPadrao = driver.findElement(By.id("form:j_idt42"));
////        encaminhamentoPadrao.click();
////
////        WebElement encaminhar = driver.findElement(By.id("form:j_idt68"));
////        encaminhar.click();
////    }
////
////    //#4
////    @Test
////    public void naoDeveCadastrarUmEncaminhamentoCustomizadoVazio(){
////
////        driver.get("http://localhost:8888/item-de-pauta.jsf");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////
////        WebElement encaminhamentoCustomizado = driver.findElement(By.id("form:j_idt46"));
////        encaminhamentoCustomizado.click();
////
////        WebElement encaminhar = driver.findElement(By.id("form:j_idt68"));
////        encaminhar.click();
////    }
////    //#5
////    @Test
////    public void naoDeveCadastrarSeOCampoDescricaoEstiverVazio(){
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/itens-de-pauta.jsf?reuniao=1");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////        inputRelator.sendKeys("Teste");
////
////        WebElement encaminhamentoPadrao = driver.findElement(By.id("form:j_idt42"));
////        encaminhamentoPadrao.click();
////
////        WebElement encaminhar = driver.findElement(By.id("form:j_idt68"));
////        encaminhar.click();
////
////    }
////    //#6
////    @Test
////    public void naoDeveCadastrarSeOCampoRelatorEstiverVazio(){
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/itens-de-pauta.jsf?reuniao=1");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////        inputDescricao.sendKeys("Teste");
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////
////        WebElement encaminhamentoPadrao = driver.findElement(By.id("form:j_idt42"));
////        encaminhamentoPadrao.click();
////
////        WebElement encaminhar = driver.findElement(By.id("form:j_idt68"));
////        encaminhar.click();
////
////    }
////
////    //#7
////    @Test
////    public void deveCancelarOEnvioDoEncaminhamento(){
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/itens-de-pauta.jsf?reuniao=1");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////        inputDescricao.sendKeys("Teste");
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////        inputRelator.sendKeys("Teste");
////
////        WebElement encaminhamentoPadrao = driver.findElement(By.id("form:j_idt42"));
////        encaminhamentoPadrao.click();
////
////        WebElement cancelar = driver.findElement(By.id("form:j_idt70"));
////        cancelar.click();
////
////    }
////
////    //#8
////    @Test
////    public void aTelaDeveSerAtualizadaAposCancelarUmEncaminhamento() {
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/itens-de-pauta.jsf?reuniao=1");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////        inputDescricao.sendKeys("Teste");
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////        inputRelator.sendKeys("Teste");
////
////        WebElement encaminhamentoPadrao = driver.findElement(By.id("form:j_idt42"));
////        encaminhamentoPadrao.click();
////
////        WebElement cancelar = driver.findElement(By.id("form:j_idt70"));
////        cancelar.click();
////
////        Assert.assertTrue(inputDescricao == null);
////        Assert.assertTrue(inputRelator == null);
////
////    }
////
////    //#9
////    @Test
////    public void aTelaDeveSerAtualizadaAposUmEncaminhamentoSerEnviado(){
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/itens-de-pauta.jsf?reuniao=1");
////
////        Assert.assertEquals("Reuniões", driver.getTitle());
////
////        WebElement inputDescricao = driver.findElement(By.id("input_form:j_idt27"));
////        inputDescricao.click();
////        inputDescricao.sendKeys("Teste");
////
////        WebElement inputRelator = driver.findElement(By.id("input_form:j_idt34"));
////        inputRelator.click();
////        inputRelator.sendKeys("Teste");
////
////        WebElement encaminhamentoPadrao = driver.findElement(By.id("form:j_idt42"));
////        encaminhamentoPadrao.click();
////
////        WebElement encaminhar = driver.findElement(By.id("form:j_idt68"));
////        encaminhar.click();
////
////        Assert.assertTrue(inputDescricao == null);
////        Assert.assertTrue(inputRelator == null);
////    }
//
//    @After
//    public void quit(){
//        driver.quit();
//    }
//
//}
