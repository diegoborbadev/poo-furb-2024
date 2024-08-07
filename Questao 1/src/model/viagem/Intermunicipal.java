// DIEGO DANIEL BORBA

package model.viagem;

import java.util.Date;

public class Intermunicipal extends Viagem {

    public Intermunicipal(String placaOnibus, String nomeMotorista, Date dataViagem) {
        super(placaOnibus, nomeMotorista, dataViagem);
    }

    @Override
    public float getValorTotal() {
        return passageiros.stream().map(passageiro -> passageiro.getTarifa() + 3.15f).reduce(0f, Float::sum);
    }

    @Override
    public int getLimitePassageiros() {
        return 45;
    }
}