// DIEGO DANIEL BORBA

package model.viagem;

import model.DateUtils;
import model.passageiro.Passageiro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Viagem {

    private final String placaOnibus;
    private final String nomeMotorista;
    private final Date dataViagem;

    protected List<Passageiro> passageiros = new ArrayList<>();

    public Viagem(String placaOnibus, String nomeMotorista, Date dataViagem) {
        this.placaOnibus = placaOnibus;
        this.nomeMotorista = nomeMotorista;
        this.dataViagem = dataViagem;
    }

    public final void addPassageiro(Passageiro passageiro) {
        if (passageiros.size() == getLimitePassageiros()) throw new IllegalStateException("Limite de passageiros atingido");
        passageiros.add(passageiro);
    }

    public final List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public abstract float getValorTotal();

    public abstract int getLimitePassageiros();

    @Override
    public String toString() {
        return "Placa do Onibus: " + placaOnibus + " - " +
                "Nome do Motorista:" + nomeMotorista + " - " +
                "Data: " + DateUtils.formatarData(dataViagem);
    }
}
