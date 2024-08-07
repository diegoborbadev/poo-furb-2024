// DIEGO DANIEL BORBA

package model;

import model.passageiro.Idoso;
import model.passageiro.Passageiro;
import model.viagem.Viagem;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private final List<Viagem> viagens = new ArrayList<>();

    public void adicionarViagem(Viagem viagem) {
        viagens.add(viagem);
    }

    public List<Passageiro> getPassageirosMaisIdosos() {
        return viagens.stream()
                .flatMap(viagem -> viagem.getPassageiros().stream())
                .filter(p -> p.getClass().equals(Idoso.class))
                .toList();
    }
}