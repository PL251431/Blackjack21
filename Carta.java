public class Carta {
    private String valor;
    private String naipe;

    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public int getValor() {
        if (valor.equals("J") || valor.equals("Q") || valor.equals("K")) {
            return 10;
        } else if (valor.equals("A")) {
            return 11;
        } else {
            return Integer.parseInt(valor);
        }
    }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}