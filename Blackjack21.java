import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Blackjack21 {
    private List<Jogador> jogadores;
    private Baralho baralho;
    private Map<String, Integer> vitóriasPorJogador;
    private List<ResultadoPartida> resultadosPartidas;

    public Blackjack21(List<String> nomesJogadores) {
        jogadores = new ArrayList<>();
        vitóriasPorJogador = new HashMap<>();
        resultadosPartidas = new ArrayList<>();
        for (String nome : nomesJogadores) {
            jogadores.add(FabricaDeJogador.criarJogador(nome));
            vitóriasPorJogador.put(nome, 0);
        }
        baralho = Baralho.getInstance();
        baralho.embaralhar();
    }
    public void iniciarJogo() {
        boolean continuarJogando = true;
        try (Scanner scan = new Scanner(System.in)) {
            while (continuarJogando) {
                jogarRodada();
                exibirResultadoPartidaAtual();
                continuarJogando = perguntarNovoJogo(scan);
            }
            exibirResultadosFinaisDoJogo();
        }
    }

    //Método usado para mostrar o resultado da última partida que ocorreu 
    private void exibirResultadoPartidaAtual() {
        ResultadoPartida resultadoAtual = resultadosPartidas.get(resultadosPartidas.size() - 1);
        System.out.println("\nResultado dessa partida:");
        System.out.println(" " );
        System.out.println("Tipo: " + resultadoAtual.getTipo());

        if (resultadoAtual.getTipo().equals("Vitória")) {
            System.out.print("Vencedor: ");
            List<Jogador> vencedores = resultadoAtual.getVencedores();
            for (int i = 0; i < vencedores.size(); i++) {
                System.out.print(vencedores.get(i).getNome());
                if (i < vencedores.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        Map<String, Integer> pontuacoes = resultadoAtual.getPontuações(); 
        for (String nome : pontuacoes.keySet()) {
            System.out.println(nome + ": " + pontuacoes.get(nome));
        }
        System.out.println();
    }

    //Método usado para printar na tela que houve a mudança da vez entre os jogadores
    private void mostarVezDoJogador(Jogador jogador) {
        System.out.println(" " );
        System.out.println(jogador.getNome() + " está na sua vez.");
    }

    
    public void jogarRodada() {
        for (Jogador jogador : jogadores) {
            mostarVezDoJogador(jogador);
            jogador.adicionarCartaNaMao(baralho.retirarCarta());
            jogador.adicionarCartaNaMao(baralho.retirarCarta());
            mostrarMao(jogador);
            while (jogadorQuerMaisCarta(jogador)) {
                Carta carta = baralho.retirarCarta();
                jogador.adicionarCartaNaMao(carta);
                exibirCartaTirada(jogador, carta);
                if (jogador.getValorMao() > 21) {
                    exibirEstouro(jogador);
                    break;
                }
            }
        }
        determinarOVencedorDaRodada();
        reiniciarMaos();
    }

    //Método que mostra as duas cartas iniciais de cada jogador e o somatório delas 
    private void mostrarMao (Jogador jogador) {
        System.out.print(jogador.getNome() + ", você recebeu as cartas: ");
        for (Carta carta : jogador.getMao()) {
            System.out.print(carta + " ");
        }
        System.out.println("(Total: " + jogador.getValorMao() + ")");
    }

    //Método que que pergunta se o joador deseja mais uma carta para o baralho e lê a resposta do mesmo.
    private boolean jogadorQuerMaisCarta(Jogador jogador) {
        Scanner scan = new Scanner(System.in);
        System.out.println(" " );
        System.out.println(jogador.getNome() + " deseja tirar outra carta? (S/N)");
        String escolha = scan.nextLine().trim().toUpperCase();
        return escolha.equals("S");
        
    }

    //Método usado para mostrar qual carta o jogadou tirou após fazer uma pedição
    private void exibirCartaTirada(Jogador jogador, Carta carta) {
        System.out.println(jogador.getNome() + " recebeu a carta: " + carta +"  (Total: " + jogador.getValorMao() + ")"  );
        
    }

    //Método usado para mostrar que o baralho do jogador passou da soma 21 
    private void exibirEstouro(Jogador jogador) {
        System.out.println(jogador.getNome() + " estourou! Total: " + jogador.getValorMao());
        System.out.println(" " );
    }

    //Método usado para determinar quem ganhou a partida 
    private void determinarOVencedorDaRodada() {
        int maiorValor = 0;
        List<Jogador> vencedores = new ArrayList<>();

        for (Jogador jogador : jogadores) {
            int valorMão = jogador.getValorMao();
            if (valorMão <= 21) {
                if (valorMão > maiorValor) {
                    maiorValor = valorMão;
                    vencedores.clear();
                    vencedores.add(jogador);
                } else if (valorMão == maiorValor) {
                    vencedores.add(jogador);
                }
            }
        }

        ResultadoPartida resultadoPartida = new ResultadoPartida();
        if (vencedores.isEmpty()) {
            System.out.printf(" ");
            resultadoPartida.setTipo("Empate");
            resultadoPartida.setPontuações(obterPontuações());
            resultadosPartidas.add(resultadoPartida);
        } else {
            System.out.printf(" ");
            resultadoPartida.setTipo("Vitória");
            resultadoPartida.setVencedores(vencedores);
            for (Jogador vencedor : vencedores) {
                vitóriasPorJogador.put(vencedor.getNome(), vitóriasPorJogador.get(vencedor.getNome()) + 1);
            }
            resultadoPartida.setPontuações(obterPontuações());
            resultadosPartidas.add(resultadoPartida);
        }
    }

    private Map<String, Integer> obterPontuações() {
        Map<String, Integer> pontuações = new HashMap<>();
        for (Jogador jogador : jogadores) {
            pontuações.put(jogador.getNome(), jogador.getValorMao());
        }
        return pontuações;
    }

    //Método que vai ser usado para resetar as mãos dos jogadores 
    private void reiniciarMaos() {
        for (Jogador jogador : jogadores) {
            jogador.getMao().clear();
        }
    }


    private boolean perguntarNovoJogo(Scanner scan) {
        System.out.println("Deseja continuar jogando? (S/N)");
        String escolha = scan.nextLine().trim().toUpperCase();
        return escolha.equals("S");
    }

    //Método que vai ser usado para exibir os resultados de toas as partidas anteriores
    private void exibirResultadosFinaisDoJogo() {
        System.out.println("Resultados finais dos jogos:\n");

        for (ResultadoPartida resultadoPartida : resultadosPartidas) {
            System.out.println("Tipo: " + resultadoPartida.getTipo());
            if (resultadoPartida.getTipo().equals("Vitória")) {
                System.out.print("Vencedor: ");
                List<Jogador> vencedores = resultadoPartida.getVencedores();
                for (int i = 0; i < vencedores.size(); i++) {
                    System.out.print(vencedores.get(i).getNome());
                    if (i < vencedores.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
            Map<String, Integer> pontuações = resultadoPartida.getPontuações();
            for (String nome : pontuações.keySet()) {
                System.out.println(nome + ": " + pontuações.get(nome));
            }
            System.out.println(" ");
        }
    }
}