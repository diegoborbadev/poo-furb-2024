// DIEGO DANIEL BORBA

package model.passageiro;

public class Passageiro {
    private String nome;
    private int idade;
    protected final float tarifaInteira = 5f;

    public Passageiro(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public float getTarifa() {
        return tarifaInteira;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - "
                + "Idade: " + idade;
    }
}