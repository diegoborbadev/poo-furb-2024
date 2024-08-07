// DIEGO DANIEL BORBA

package model;

import java.io.Serializable;

public class Endereco implements Serializable {
    private final String rua;
    private final String numero;
    private final String complemento;
    private final String cep;
    private final String cidade;
    private final String estado;

    public Endereco(String rua, String numero, String complemento, String cep, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }
}