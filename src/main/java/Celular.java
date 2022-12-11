public class Celular extends Produto {
    public static final String TIPO_CELULAR = "Celular";

    public Celular(String codigo, String marca, String modelo, String cor, Double preco) {

        super(codigo, marca, modelo, cor, preco);

    }

    public Celular() {
        this("", "", "", "", 0.0);
    }

    @Override
    public String getTipo() {
        return TIPO_CELULAR;
    }
}
