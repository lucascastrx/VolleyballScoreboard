/**
 * @author Lucas Castro
 */
public class Equipe {

    private String nome;
    private int setsGanhos;

    public Equipe(){}

    public Equipe(String nome){
        this.nome = nome;
        this.setsGanhos = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSetsGanhos() {
        return setsGanhos;
    }

    public void increaseSetsGanhos() {
        this.setsGanhos++;
    }

    public void decreaseSetsGanhos(){
        this.setsGanhos--;
    }
}
