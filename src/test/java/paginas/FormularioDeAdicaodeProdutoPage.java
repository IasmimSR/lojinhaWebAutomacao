package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaodeProdutoPage {
    private WebDriver navegador;

    public FormularioDeAdicaodeProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioDeAdicaodeProdutoPage informarNomeDoProduto(String proutoNome) {
        navegador.findElement(By.id("produtonome")).sendKeys(proutoNome);

        return this;
    }

    public FormularioDeAdicaodeProdutoPage informarValorDoProduto(String produtoValor) {
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);

        return this;
    }

    public FormularioDeAdicaodeProdutoPage informarCoresDoProduto(String produtoCores) {
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro() {
        navegador.findElement(By.cssSelector("button[type=submit]")).click();

        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso() {
        navegador.findElement(By.cssSelector("button[type=submit]")).click();

        return new FormularioDeEdicaoDeProdutoPage(navegador);
    }
}
