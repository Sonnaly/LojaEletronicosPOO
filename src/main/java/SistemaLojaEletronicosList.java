import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class SistemaLojaEletronicosList implements SistemaLojaEletronicos {


    private List<Produto> produto;
    private GravadorDeDados gravador = new GravadorDeDados("Produtos.txt");
    public SistemaLojaEletronicosList() {
        this(new LinkedList<>());
    }

    public SistemaLojaEletronicosList(List<Produto> produto) {
        this.produto = produto;
    }

    @Override
    public boolean cadastrarProduto(Produto p) {
        if (this.produto.contains(p)) {
            return false;
        }

        else {
            this.produto.add(p);
            return true;
        }
    }

    @Override
    public String pesquisarProduto(String codigo) {
        for (Produto p : this.produto) {
            if (p.getCodigo().equals(codigo)) {
                return ("O produto é um " + p.getTipo() + ", de modelo " + p.getModelo() + " , da marca " + p.getMarca()
                        + " , de cor " + p.getCor() + " , que custa " + p.getPreco() + ".");
            }
        }
        return "Produto não encontrado";
    }

    @Override
    public void apagarProduto(String codigo) throws ProdutoNaoExisteException {
        for (Produto p : this.produto) {
            if (p.getCodigo().equals(codigo)) {
                produto.remove(p);
                return;
            }
        }
        throw new ProdutoNaoExisteException("Este produto não esta cadastrado. Verifique o código");
    }

    @Override
    public void atualizarProduto(String codigo, Produto prod) {
        for (Produto p : this.produto) {
            if (p.getCodigo().equals(codigo)) {
                produto.set(produto.indexOf(p), prod);
            }
        }
    }

    @Override
    public ArrayList<Produto> pesquisarListaDeProdutos(String tipo) {
        ArrayList<Produto> pesquisaProdutos = new ArrayList<>();
        for (Produto p : produto) {
            if (p.getTipo().equals(tipo)) {
                pesquisaProdutos.add(p);
            }
        }
        return pesquisaProdutos;
    }

    public void salvarDados() throws IOException {
        List<String> texto = new LinkedList<>();
        for (Produto p: this.produto){
            String linha = p.getTipo() + "#" + p.getCodigo() + "#" + p.getMarca() + "#" + p.getModelo() + "#" + p.getCor() + "#" + p.getPreco();
            texto.add(linha);
        }
        gravador.gravaTextoEmArquivo(texto);
    }

    public void recuperarDados() throws IOException {

        List<String> textoLido = gravador.recuperaTextoDeArquivo();

        for (String linha: textoLido){
            String [] elementosLinha = linha.split("#");

            Produto p = null;

            String tipo = elementosLinha[0];

            if (tipo.equals("Celular")){
                p = new Celular();
            } else if (tipo.equals("Notebook")){
                p = new Notebook();
            } else if (tipo.equals("Televisão")){
                p = new Televisao();
            }

            p.setCodigo(elementosLinha[1]);
            p.setMarca(elementosLinha[2]);
            p.setModelo(elementosLinha[3]);
            p.setCor(elementosLinha[4]);
            Double prec = Double.parseDouble(elementosLinha[5]);
            p.setPreco(prec);

            this.produto.add(p);
        }
    }
}