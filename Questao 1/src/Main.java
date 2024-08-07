// DIEGO DANIEL BORBA

import model.DateUtils;
import model.Empresa;
import model.passageiro.Estudante;
import model.passageiro.Idoso;
import model.passageiro.Passageiro;
import model.viagem.Intermunicipal;
import model.viagem.Municipal;
import model.viagem.Viagem;
import model.viagem.ViagemFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Main extends JFrame {
    private final DefaultListModel<Viagem> viagemListModel;
    private final JList<Viagem> viagemList;

    private final Empresa empresa = new Empresa();

    public Main() {
        setTitle("Empresa de Ônibus");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        viagemListModel = new DefaultListModel<>();
        viagemList = new JList<>(viagemListModel);
        viagemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(viagemList);

        JButton addMunicipalButton = new JButton("Adicionar Viagem Municipal");
        JButton addIntermunicipalButton = new JButton("Adicionar Viagem Intermunicipal");
        JButton getPassageirosMaisIdososButton = new JButton("Ver Passageiros Mais Idosos");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addMunicipalButton);
        buttonPanel.add(addIntermunicipalButton);
        buttonPanel.add(getPassageirosMaisIdososButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addMunicipalButton.addActionListener(_ -> showAddViagemDialog("Municipal", Municipal::new));
        addIntermunicipalButton.addActionListener(_ -> showAddViagemDialog("Intermunicipal", Intermunicipal::new));
        getPassageirosMaisIdososButton.addActionListener(_ -> getPassageirosMaisIdososButtonDialog());

        viagemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = viagemList.getSelectedIndex();
                    if (index != -1) {
                        Viagem viagemSelecionada = viagemListModel.get(index);
                        showPassengerManagementDialog(viagemSelecionada);
                    }
                }
            }
        });
    }

    private void getPassageirosMaisIdososButtonDialog() {
        final List<Passageiro> passageirosMaisIdosos = empresa.getPassageirosMaisIdosos();
        if(passageirosMaisIdosos.isEmpty()){
            JOptionPane.showMessageDialog(this, "Não há passageiros mais idosos");
        } else {
            JDialog dialog = new JDialog(this, "Passageiros Mais Idosos", true);
            dialog.setSize(400, 300);

            DefaultListModel<Passageiro> passageirosMaisIdososListModel = new DefaultListModel<>();
            passageirosMaisIdosos.forEach(passageirosMaisIdososListModel::addElement);

            JList<Passageiro> passageirosMaisIdososList = new JList<>(passageirosMaisIdososListModel);
            passageirosMaisIdososList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(passageirosMaisIdososList);
            dialog.add(scrollPane, BorderLayout.CENTER);

            dialog.setVisible(true);
        }
    }

    private void showAddViagemDialog(String tipo, ViagemFactory viagemFactory) {
        JDialog dialog = new JDialog(this, "Adicionar Viagem " + tipo, true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(4, 2));

        JTextField placaField = new JTextField();
        JTextField motoristaField = new JTextField();
        JTextField dataField = new JTextField();

        dialog.add(new JLabel("Placa do Ônibus:"));
        dialog.add(placaField);
        dialog.add(new JLabel("Nome do Motorista:"));
        dialog.add(motoristaField);
        dialog.add(new JLabel("Data da Viagem (dd/MM/yyyy):"));
        dialog.add(dataField);

        JButton addButton = new JButton("Adicionar");
        dialog.add(addButton);
        addButton.addActionListener(_ -> {
            Viagem viagem = viagemFactory.create(placaField.getText(), motoristaField.getText(), DateUtils.parseData(dataField.getText()));
            empresa.adicionarViagem(viagem);
            viagemListModel.addElement(viagem);
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    private void showPassengerManagementDialog(Viagem viagemSelecionada) {
        JDialog dialog = new JDialog(this, "Gerenciar Passageiros", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(4, 1));

        JButton cadastrarPassageiroButton = new JButton("Cadastrar Passageiro");
        JButton cadastrarIdosoButton = new JButton("Cadastrar Idoso");
        JButton cadastrarEstudanteButton = new JButton("Cadastrar Estudante");
        JButton getValorTotalButton = new JButton("Consultar Valor Total");

        dialog.add(cadastrarPassageiroButton);
        dialog.add(cadastrarIdosoButton);
        dialog.add(cadastrarEstudanteButton);
        dialog.add(getValorTotalButton);

        cadastrarPassageiroButton.addActionListener(_ -> showAddPassengerDialog("Passageiro", viagemSelecionada));

        cadastrarIdosoButton.addActionListener(_ -> showAddPassengerDialog("Idoso", viagemSelecionada));

        cadastrarEstudanteButton.addActionListener(_ -> showAddPassengerDialog("Estudante", viagemSelecionada));

        getValorTotalButton.addActionListener(_ -> JOptionPane.showMessageDialog(dialog, "Valor arrecadado: " + viagemSelecionada.getValorTotal()));

        dialog.setVisible(true);
    }

    private void showAddPassengerDialog(String tipo, Viagem viagemSelecionada) {
        JDialog dialog = new JDialog(this, "Adicionar " + tipo, true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nomeField = new JTextField();
        JTextField idadeField = new JTextField();
        JTextField rgField = new JTextField();
        JTextField escolaField = new JTextField();

        dialog.add(new JLabel("Nome:"));
        dialog.add(nomeField);
        dialog.add(new JLabel("Idade:"));
        dialog.add(idadeField);

        if (tipo.equals("Idoso")) {
            dialog.add(new JLabel("RG:"));
            dialog.add(rgField);
        }

        if (tipo.equals("Estudante")) {
            dialog.add(new JLabel("Escola:"));
            dialog.add(escolaField);
        }

        JButton addButton = new JButton("Adicionar");
        dialog.add(addButton);
        addButton.addActionListener(_ -> {
            Passageiro passageiro;
            switch (tipo) {
                case "Idoso" -> passageiro = new Idoso(nomeField.getText(), Integer.parseInt(idadeField.getText()), rgField.getText());
                case "Estudante" -> passageiro = new Estudante(nomeField.getText(), Integer.parseInt(idadeField.getText()), escolaField.getText());
                default -> passageiro = new Passageiro(nomeField.getText(), Integer.parseInt(idadeField.getText()));
            }
            viagemSelecionada.addPassageiro(passageiro);
            JOptionPane.showMessageDialog(dialog, "Passageiro Adicionado: " + passageiro);
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}