package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        //Abrir o navegador
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.navegador = new ChromeDriver(options);

        //Vou maximizar a tela
        this.navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrao de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

    }

    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    @Test
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .informarNomeDoProduto("Mackbook Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor maior que 7.000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorMiorQueSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto, azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 0,01 ")
    public void testPossoAdicionarProdutosComValorDeUmCentavo() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .informarNomeDoProduto("Mackbook Pro")
                .informarValorDoProduto("300000")
                .informarCoresDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();


        Assertions.assertEquals("Produto adicionado com sucesso",  mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 7.000,00 ")
    public void testPossoAdicionarProdutosComValorDeSeteMilReais() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .informarNomeDoProduto("Mackbook Pro")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("preto, azul")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();


        Assertions.assertEquals("Produto adicionado com sucesso",  mensagemApresentada);

    }

    @AfterEach
    public void afterEach() {
        //Vou fechar o navegador
        navegador.quit();
    }
}

