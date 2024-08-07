// DIEGO DANIEL BORBA

package model.passageiro;

public class Idoso extends Passageiro {
    private String rg;

    public Idoso(String nome, int idade, String rg) {
        if(idade < 60) {
            throw new IllegalArgumentException("Idade inválida para idoso");
        }
        super(nome, idade);
        this.rg = rg;
    }

    public float getTarifa() {
        return 0f;
    }

    @Override
    public String toString() {
        return super.toString() + " - "
                + "RG: " + rg;
    }
}
