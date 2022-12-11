import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaLojaEletronicosListTest {

    @Test
    public void testaCadastroProduto() {
        SistemaLojaEletronicosList sistema = new SistemaLojaEletronicosList();
        Produto produto = new Notebook("0000", "Lenovo", "IdeaPad", "Preto", 2.600);
        //testa cadastro
        sistema.cadastrarProduto(produto);
        assertEquals("0000", produto.getCodigo());

        //testa pesquisa
        sistema.pesquisarProduto("0000");

    }
}