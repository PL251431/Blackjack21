package Exception;
//Exceção para quando o usuario inserir um nome vazio 
public class ExcecaoPorNomeInvalido extends Exception {
    public ExcecaoPorNomeInvalido(String mensagem) {
        super(mensagem);
    }
}