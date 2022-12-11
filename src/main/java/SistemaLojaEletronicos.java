import java.io.IOException;
import java.util.ArrayList;

public interface SistemaLojaEletronicos {

    boolean cadastrarProduto(Produto p);

    String pesquisarProduto(String codigo);

    void apagarProduto(String codigo) throws ProdutoNaoExisteException;

    void atualizarProduto(String codigo, Produto prod);

    ArrayList<Produto> pesquisarListaDeProdutos(String tipo);
    void recuperarDados() throws IOException;
    void salvarDados() throws IOException;
}