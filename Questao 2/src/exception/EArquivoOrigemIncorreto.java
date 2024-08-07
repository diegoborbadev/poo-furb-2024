// DIEGO DANIEL BORBA

package exception;

import java.io.IOException;

public class EArquivoOrigemIncorreto extends IOException {
    public EArquivoOrigemIncorreto(String message) {
        super(message);
    }
}