// DIEGO DANIEL BORBA

package model.viagem;

import java.util.Date;

@FunctionalInterface
public interface ViagemFactory {
    Viagem create(String origem, String destino, Date data);
}