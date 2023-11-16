//Factory Method 
//Método para criar várias instâncias de Jogador
public class FabricaDeJogador {
    public static Jogador criarJogador(String nome) {
        return new Jogador(nome);
    }
}
