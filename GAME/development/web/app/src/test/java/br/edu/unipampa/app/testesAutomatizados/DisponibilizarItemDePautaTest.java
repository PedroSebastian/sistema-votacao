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
//import java.text.ParseException;
//
//import static org.apache.xalan.lib.ExsltDatetime.time;
//
//public class DisponibilizarItemDePautaTest {
//
//    static WebDriver driver;
//
//    @Before
//    public void config(){
//        WebDriverManager.chromedriver().setup();
//
//    }
//
////    //#1
////    @Test
////    public void selecionarUmaPautaParaSegundoTurno(){
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement checkbox = driver.findElement(By.id("form:segundo-turno"));
////        checkbox.click();
////        Assert.assertTrue(checkbox.isSelected() == true);
////
////    }
////    //#2
////    @Test
////    public void encaminharUmaPautaPadraoParaSegundoTurno(){
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement checkbox = driver.findElement(By.id("form:segundo-turno"));
////        checkbox.click();
////        Assert.assertTrue(checkbox.isSelected() == true);
////
////        WebElement encaminhamentoPadrão = driver.findElement(By.id("form:j_idt48"));
////        encaminhamentoPadrão.click();
////
////        WebElement encaminhar = driver.findElement((By.id("form:j_idt75")));
////        encaminhar.click();
////    }
////    //#3
////    @Test
////    public void encaminharUmaPautaCustomizadaParaSegundoTurno(){
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement checkbox = driver.findElement(By.id("form:segundo-turno"));
////        checkbox.click();
////        Assert.assertTrue(checkbox.isSelected() == true);
////
////        WebElement encaminhamentoCustomizado = driver.findElement(By.id("form:j_idt52"));
////        encaminhamentoCustomizado.click();
////
////        WebElement encaminhar = driver.findElement((By.id("form:j_idt75")));
////        encaminhar.click();
////
////    }
////    //#4
////    @Test
////    public void cancelarUmItem() {
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement checkbox = driver.findElement(By.id("form:segundo-turno"));
////        checkbox.click();
////        Assert.assertTrue(checkbox.isSelected() == true);
////
////        WebElement encaminhamentoCustomizado = driver.findElement(By.id("form:j_idt52"));
////        encaminhamentoCustomizado.click();
////
////        WebElement cancelar = driver.findElement((By.id("form:j_idt77")));
////        cancelar.click();
////    }
////
////    //#5
////    @Test
////    public void naoDeveDisponibilizarUmItemSemSelecionarOTipoDeEncaminhamento() throws ParseException {
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement encaminhar = driver.findElement((By.id("form:j_idt75")));
////        encaminhar.click();
////
////        time("2000");
////
////        WebElement message = driver.findElement(By.xpath("//*[@id=\"form:growl_container\"]/div"));
////        Assert.assertFalse(message.getText() =="Item de Pauta disponibilizado com sucesso.");
////    }
////
////    //#6
////    @Test
////    public void deveApresentarMensagemDeErroAoEncaminharUmItemSemTipoDeEncaminhamento() throws ParseException {
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement encaminhar = driver.findElement((By.id("form:j_idt75")));
////        encaminhar.click();
////        time("2000");
////        WebElement message = driver.findElement(By.xpath(" //*[@id=\"form:growl_container\"]"));
////        Assert.assertTrue(message.getText() =="Você deve selecionar os Tipos de Encaminhamento.");
////
////    }
////
////    //#7
////    @Test
////    public void deveApresentarMensagemDeConfirmacaoAoEncaminharUmItemSemTipoDeEncaminhamento() throws ParseException {
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement encaminhar = driver.findElement((By.id("form:j_idt75")));
////        encaminhar.click();
////        time("2000");
////        WebElement message = driver.findElement(By.xpath(" //*[@id=\"form:growl_container\"]"));
////        Assert.assertTrue(message.getText() =="Item de Pauta disponibilizado com sucesso.");
////
////    }
////
////    //#8
////    @Test
////    public void osCamposQueSaoObrigatoriosDevemApresentarEstaObrigatoriedade() throws ParseException {
////
////        driver = new ChromeDriver();
////        driver.get("http://localhost:8181/encaminhamentos.jsf?item=1");
////        WebElement encaminhar = driver.findElement((By.id("form:j_idt75")));
////        time("2000");
////        WebElement corpoDoConteudo = driver.findElement(By.id("form:j_idt12"));
////
////        Assert.assertTrue(corpoDoConteudo.getText() == "* Campos obrigatórios");
////        Assert.assertTrue(corpoDoConteudo.getText() == "Votação com Segundo Turno? *");
////        Assert.assertTrue(corpoDoConteudo.getText() == "Tipos de Encaminhamento *");
////    }
//
//
//    @After
//    public void quit(){
//        driver.quit();
//    }
//
//
//
//
//
//}
