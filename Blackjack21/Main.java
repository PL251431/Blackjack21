import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exception.ExcecaoPorNomeInvalido;

public class Main {
    public static void main(String[] args) {
        List<String> nomesJogadores = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("SEJA BEM VINDO AO JOGO DE CARTAS 21!");
        System.out.println("==============================================");

        try {
            obterNomesJogadores(nomesJogadores, scan);
        } catch (ExcecaoPorNomeInvalido e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        Blackjack21 jogo = new Blackjack21(nomesJogadores);
        jogo.iniciarJogo();
        scan.close();
    }

    // 
    private static void obterNomesJogadores(List<String> nomesJogadores, Scanner scan) throws ExcecaoPorNomeInvalido {
        for (int i = 1; i <= 2; i++) {
            System.out.print("INSIRA O NOME DO PLAYER " + i + ": ");
            String nome = scan.nextLine().trim();
            
            if (nome.isEmpty()) {
                throw new ExcecaoPorNomeInvalido("Nome não pode ser vazio.");
            }

            nomesJogadores.add(nome);
        }
    }
}
