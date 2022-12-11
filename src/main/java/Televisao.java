public class Televisao extends Produto {

    public static final String TIPO_TELEVISAO = "Televisão";

    public Televisao(String codigo, String marca, String modelo, String cor, Double preco){

        super(codigo, marca, modelo, cor, preco);

    }

    public Televisao(){
        this("", "", "", "", 0.0);
    }

    @Override
    public String getTipo() {
        return TIPO_TELEVISAO;
    }
}