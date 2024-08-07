// DIEGO DANIEL BORBA

package model.passageiro;

public class Estudante extends Passageiro {
    private String escola;

    public Estudante(String nome, int idade, String escola) {
        super(nome, idade);
        this.escola = escola;
    }

    public float getTarifa() {
        return tarifaInteira / 2f;
    }

    @Override
    public String toString() {
        return super.toString() + " - "
                + "Escola: " + escola;
    }
}
