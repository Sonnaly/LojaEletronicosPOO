import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class SistemaLojaEletronicosGUI extends JFrame{

    static SistemaLojaEletronicos sistema = new SistemaLojaEletronicosList();
    JMenuBar barraMenu = new JMenuBar();
    JMenu PesquisarLista = new JMenu("Pesquisar Lista de Produtos:");
    JMenuItem Celular = new JMenuItem("Celular");
    JMenuItem Notebook = new JMenuItem("Notebook");
    JMenuItem Televisão = new JMenuItem("Televisão");

    JLabel linha1;
    ImageIcon imgLoja = new ImageIcon("imagens/BEM-VINDO.png");
    ImageIcon iconCadastar = new ImageIcon("imagens/CadastrarIcon.png");
    ImageIcon iconRemover = new ImageIcon("imagens/RemoverIcon.png");
    ImageIcon iconPesquisar = new ImageIcon("imagens/PesquisarIcon.png");
    ImageIcon iconAtualizar = new ImageIcon("imagens/AtualizarIcon.png");


    public SistemaLojaEletronicosGUI() {
        setTitle("Loja Eletronicos");
        setSize(800, 800);
        setLocation(550, 100);
        setResizable(false);

        setJMenuBar(barraMenu);
        barraMenu.add(PesquisarLista);

        PesquisarLista.add(Celular);
        PesquisarLista.add(Notebook);
        PesquisarLista.add(Televisão);

        linha1 = new JLabel(imgLoja, JLabel.CENTER);

        JButton botaoCadastrar = new JButton();
        botaoCadastrar.setIcon(iconCadastar);
        botaoCadastrar.setBackground(Color.LIGHT_GRAY);

        JButton botaoRemover = new JButton();
        botaoRemover.setIcon(iconRemover);
        botaoRemover.setBackground(Color.LIGHT_GRAY);

        JButton botaoPesquisar = new JButton();
        botaoPesquisar.setIcon(iconPesquisar);
        botaoPesquisar.setBackground(Color.LIGHT_GRAY);

        JButton botaoAtualizar = new JButton();
        botaoAtualizar.setIcon(iconAtualizar);
        botaoAtualizar.setBackground(Color.LIGHT_GRAY);

        getContentPane().setBackground(Color.LIGHT_GRAY);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        Container c2 = new JPanel();
        c2.setLayout(new GridLayout(2, 2));

        c2.add(botaoCadastrar);
        c2.add(botaoPesquisar);
        c2.add(botaoRemover);
        c2.add(botaoAtualizar);

        c.add(BorderLayout.NORTH, linha1);
        c.add(BorderLayout.CENTER, c2);

        botaoCadastrar.addActionListener(this::cadastrar);
        botaoRemover.addActionListener(this::remover);
        botaoPesquisar.addActionListener(this::pesquisar);
        botaoAtualizar.addActionListener(this::atualizar);

        Celular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "Celular";
                List<Produto> listaProdutos = sistema.pesquisarListaDeProdutos(tipo);

                if (listaProdutos.size() == 0){
                    JOptionPane.showMessageDialog(null, "Não foi cadastrado nenhum produto desse tipo");
                }
                else{
                    for (Produto p: listaProdutos){
                        JOptionPane.showMessageDialog(null, p.toString());
                    }
                }
            }
        });

        Notebook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "Notebook";
                List<Produto> listaProdutos = sistema.pesquisarListaDeProdutos(tipo);

                if (listaProdutos.size() == 0){
                    JOptionPane.showMessageDialog(null, "Não foi cadastrado nenhum produto desse tipo");
                }
                else{
                    for (Produto p: listaProdutos){
                        JOptionPane.showMessageDialog(null, p.toString());
                    }
                }
            }
        });

        Televisão.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "Televisão";
                List<Produto> listaProdutos = sistema.pesquisarListaDeProdutos(tipo);

                if (listaProdutos.size() == 0){
                    JOptionPane.showMessageDialog(null, "Não foi cadastrado nenhum produto desse tipo");
                }
                else{
                    for (Produto p: listaProdutos){
                        JOptionPane.showMessageDialog(null, p.toString());
                    }
                }
            }
        });
    }

    private void cadastrar(ActionEvent actionEvent) {
        Produto p = null;
        String tipoProduto = JOptionPane.showInputDialog("Qual produto você deseja cadastrar?\n1.Celular\n2.Notebook\n3.Televisão");

        if (tipoProduto.equals("1")) {
            p = new Celular();
        } else if (tipoProduto.equals("2")) {
            p = new Notebook();
        } else if (tipoProduto.equals("3")) {
            p = new Televisao();
        } else {
            JOptionPane.showMessageDialog(null, "Esse tipo de produto não pode ser cadastrado. Tente Novamente");
        }

        String cod = JOptionPane.showInputDialog("Digite o código do produto");
        String marc = JOptionPane.showInputDialog("Digite a marca do produto");
        String model = JOptionPane.showInputDialog("Digite o modelo do produto");
        String cr = JOptionPane.showInputDialog("Digite a cor do produto");
        Double prc = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto"));

        p.setCodigo(cod);
        p.setMarca(marc);
        p.setModelo(model);
        p.setCor(cr);
        p.setPreco(prc);

        boolean cadastrou = sistema.cadastrarProduto(p);

        if (cadastrou) {
            JOptionPane.showMessageDialog(null, "O produto foi cadastrado no sistema." + p.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar esse produto.");
        }
    }

    private void remover(ActionEvent actionEvent) {
        String codigo = JOptionPane.showInputDialog("Digite o código do produto");
        try {
            sistema.apagarProduto(codigo);
            JOptionPane.showMessageDialog(null, "O produto foi removido com sucesso!");
        } catch (ProdutoNaoExisteException e) {
            JOptionPane.showMessageDialog(null, "Erro: Produto não encontrado" + e.getMessage());
        }
    }

    private void pesquisar(ActionEvent actionEvent) {
        String codigoPesquisar = JOptionPane.showInputDialog("Qual codigo do produto?");
        String produtoEspecifico = sistema.pesquisarProduto(codigoPesquisar);

        if (produtoEspecifico == "Produto não encontrado") {
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
        } else {
            JOptionPane.showMessageDialog(null, produtoEspecifico);
        }
    }

    private void atualizar(ActionEvent actionEvent) {
        Produto prod = null;

        String codigo = JOptionPane.showInputDialog("Digite o código do produto que deseja atualizar");
        String tipoProduto = JOptionPane.showInputDialog("Qual produto você deseja cadastrar?\n1.Celular\n2.Notebook\n3.Televisao");

        if (tipoProduto.equals("1")) {
            prod = new Celular();
        } else if (tipoProduto.equals("2")) {
            prod = new Notebook();
        } else if (tipoProduto.equals("3")) {
            prod = new Televisao();
        }

        String cod = JOptionPane.showInputDialog("Digite o código do produto");
        String marc = JOptionPane.showInputDialog("Digite a marca do produto");
        String model = JOptionPane.showInputDialog("Digite o modelo do produto");
        String cr = JOptionPane.showInputDialog("Digite a cor do produto");
        Double prc = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto"));

        sistema.atualizarProduto(codigo, prod);

        prod.setCodigo(cod);
        prod.setMarca(marc);
        prod.setModelo(model);
        prod.setCor(cr);
        prod.setPreco(prc);

        JOptionPane.showMessageDialog(null, "O atualizado com sucesso!");
    }

    public static void main(String[] args) {

        JFrame janela = new SistemaLojaEletronicosGUI();
        janela.setVisible(true);

        try{
            sistema.recuperarDados();
            JOptionPane.showMessageDialog(null, "As informações dos produtos cadastrados foram recuperadas com sucesso!");
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Não foi possivel recuperar os dados dos produtos cadastrados!");
        }

        WindowListener fechadorJanelaPrincipal = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    sistema.salvarDados();
                    JOptionPane.showMessageDialog(null, "Os produtos cadastrados foram salvos com sucesso!");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Problema ao salvar dados dos produtos!");
                }
                System.exit(0);
            }
        };
        janela.addWindowListener(fechadorJanelaPrincipal);
    }
}