// DIEGO DANIEL BORBA

package model.viagem;

import model.passageiro.Passageiro;

import java.util.Date;

public class Municipal extends Viagem {

    public Municipal(String placaOnibus, String nomeMotorista, Date dataViagem) {
        super(placaOnibus, nomeMotorista, dataViagem);
    }

    @Override
    public float getValorTotal() {
        return passageiros.stream().map(Passageiro::getTarifa).reduce(0f, Float::sum);
    }

    @Override
    public int getLimitePassageiros() {
        return 65;
    }
}