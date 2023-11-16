import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Carta> mao;

    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarCartaNaMao(Carta carta) {
        mao.add(carta);
    }

    public List<Carta> getMao() {
        return mao;
    }

    public int getValorMao() {
        int valor = 0;
        int ases = 0;

        for (Carta carta : mao) {
            valor += carta.getValor();
            if (carta.getValor() == 11) {
                ases++;
            }
        }

        while (valor > 21 && ases > 0) {
            valor -= 10;
            ases--;
        }

        return valor;
    }
}
