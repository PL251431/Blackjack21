import java.util.ArrayList;
import java.util.List;

//File feita usando o padrão de projeto Singleton
public class Baralho {
    private static Baralho instance;
    private List<Carta> cartas;

    // Define-se aqui quais são todos os naipes e valores dentro do baralho do jogo
    private Baralho() {
        cartas = new ArrayList<>();
        String[] naipes = {"ESPADAS", "PAUS", "COPAS", "OURO"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String naipe : naipes) {
            for (String valor : valores) {
                cartas.add(new Carta(valor, naipe));
            }
        }
    }

    //Método para criar uma instância sozinha 
    public static Baralho getInstance() {
        if (instance == null) {
            instance = new Baralho();
        }
        return instance;
    }

    //Método para embaralhar o baralho
    public void embaralhar() {
        for (int i = cartas.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Carta temp = cartas.get(i);
            cartas.set(i, cartas.get(j));
            cartas.set(j, temp);
        }
    }

    //Método para retirar uma carta
    public Carta retirarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.remove(0);
    }
}
