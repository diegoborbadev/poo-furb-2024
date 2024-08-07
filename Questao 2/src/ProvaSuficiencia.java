// DIEGO DANIEL BORBA

import exception.EArquivoOrigemIncorreto;
import model.Atleta;
import model.Endereco;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class ProvaSuficiencia {
    public static void serializar(String arqOrigem, String arqDestino) throws EArquivoOrigemIncorreto {
        Set<Atleta> atletas = new TreeSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arqOrigem))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Atleta atleta = getAtleta(dados, linha);
                atletas.add(atleta);
            }
        } catch (IOException e) {
            throw new EArquivoOrigemIncorreto("Erro ao ler o arquivo de origem: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arqDestino))) {
            oos.writeObject(atletas);
        } catch (IOException e) {
            throw new EArquivoOrigemIncorreto("Erro ao serializar o arquivo destino: " + e.getMessage());
        }
    }

    private static Atleta getAtleta(String[] dados, String linha) throws EArquivoOrigemIncorreto {
        if (dados.length < 9) {
            throw new EArquivoOrigemIncorreto("Formato de dados incorreto na linha: " + linha);
        }

        String nome = dados[0].trim();
        String cpf = dados[1].trim();
        float altura;
        float peso;

        try {
            altura = Float.parseFloat(dados[2].trim());
            peso = Float.parseFloat(dados[3].trim());
        } catch (NumberFormatException e) {
            throw new EArquivoOrigemIncorreto("Altura ou peso inválidos na linha: " + linha);
        }

        String rua = dados[4].trim();
        String numero = dados[5].trim();
        String complemento = dados[6].trim();
        String cep = dados[7].trim();
        String cidade = dados[8].trim();
        String estado = dados[9].trim();

        Endereco endereco = new Endereco(rua, numero, complemento, cep, cidade, estado);
        return new Atleta(nome, cpf, altura, peso, endereco);
    }
}
