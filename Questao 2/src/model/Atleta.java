// DIEGO DANIEL BORBA

package model;

import java.io.Serializable;

public class Atleta implements Serializable, Comparable<Atleta> {
    private final String nome;
    private final String cpf;
    private final float altura;
    private final float peso;
    private final Endereco endereco;

    public Atleta(String nome, String cpf, float altura, float peso, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.altura = altura;
        this.peso = peso;
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public int compareTo(Atleta other) {
        return this.cpf.compareTo(other.cpf);
    }
}