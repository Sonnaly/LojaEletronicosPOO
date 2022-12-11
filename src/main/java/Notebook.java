public class Notebook extends Produto {
    public static final String TIPO_NOTEBOOK = "Notebook";

    public Notebook(String codigo, String marca, String modelo, String cor, Double preco) {

        super(codigo, marca, modelo, cor, preco);

    }

    public Notebook() {
        this("", "", "", "", 0.0);
    }

    @Override
    public String getTipo() {
        return TIPO_NOTEBOOK;
    }
}
