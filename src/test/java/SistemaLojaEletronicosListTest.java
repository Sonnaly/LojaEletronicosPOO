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
        try{
            sistema.pesquisarProduto("0000");
        }catch(ProdutoNaoExisteException e){
            fail("O produto deveria existir no sistema")
        }
        
        //testa apagar
        sistema.apagarProduto("0000");
        try{
            sistema.pesquisarProduto("0000");
            fail("Deveria falhar pois o produto deveria ter sido apagado do sistema")
        }catch(ProdutoNaoExisteException e){
            fail("Produto apagado!")
        }

    }
}
