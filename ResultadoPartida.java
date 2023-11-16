import java.util.List;
import java.util.Map;

public class ResultadoPartida {
    private String tipo;
    private Map<String, Integer> pontuações;
    private List<Jogador> vencedor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
public Map<String, Integer> getPontuações() {
        return pontuações;
    }

    public void setPontuações(Map<String, Integer> pontuações) {
        this.pontuações = pontuações;
    }

    public List<Jogador> getVencedores() {
        return vencedor;
    }

    public void setVencedores(List<Jogador> vencedores) {
        this.vencedor = vencedores;
    }
}
