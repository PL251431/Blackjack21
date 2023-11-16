import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Carta> mao;

    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<>();
    }

    //Método getters 
    public String getNome() {
        return nome;
    }

    public List<Carta> getMao() {
        return mao;
    }

    //Método getter que vai retornar o valor da mão e também inclue as mudanças de valores do As entre 1 e 11
    public int getValorMao() {
        int valor = 0;
        int as = 0;

        for (Carta carta : mao) {
            valor += carta.getValor();
            if (carta.getValor() == 11) {
                as++;
            }
        }

        while (valor > 21 && as > 0) {
            valor -= 10;
            as--;
        }

        return valor;
    }

    //Adiciona a carta a mão do jogador
    public void adicionarCartaNaMao(Carta carta) {
        mao.add(carta);
    }
}
