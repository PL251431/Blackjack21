import java.util.Arrays;
import java.util.List;

public class TesteBlackjack {
    public static void main(String[] args) {
        List<String> nomesJogadores = Arrays.asList("Jogador1", "Jogador2");

        Blackjack21 jogo = new Blackjack21(nomesJogadores);
        jogo.iniciarJogo();
    }
}

