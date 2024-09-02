package br.lpm.main;

import br.lpm.business.Dataset;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Main {

  public static Dataset dataset = new Dataset();

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    ShowMenu();
  }

  public static void ShowMenu() {
    String[] options = {
      "Cadastrar Pessoa",
      "Remover Pessoa",
      "Pesquisar Pessoa",
      "Exibir Histograma de Formação Acadêmica",
      "Exibir Grafico de torta de distribuição Academica",
      "Sair"
    };

    int escolha;

    do {
      escolha =
          JOptionPane.showOptionDialog(
              null,
              "Escolha uma opção:",
              "Menu Principal",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.INFORMATION_MESSAGE,
              null,
              options,
              options[0]);

      switch (escolha) {
        case 0:
          cadastrarPessoas();
          break;
        case 1:
          removerPessoa();
          break;
        case 2:
          pesquisarPessoa();
          break;
        case 3:
          histogramaFormacaoAcadêmica();
          break;
        case 4:
          pieEstadoCivil();
          break;
        case 5:
          JOptionPane.showMessageDialog(null, "Saindo do programa.");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida!");
          break;
      }
    } while (escolha != 5);
  }

  public static void cadastrarPessoas() {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    String nome = JOptionPane.showInputDialog("Digite seu nome: ");

    String dataDeAniversarioAux =
        JOptionPane.showInputDialog("Digite a data de aniversário (dd/MM/yyyy): ");
    LocalDate dataDeAniversario = LocalDate.parse(dataDeAniversarioAux, fmt);

    Genero genero =
        Genero.valueOf(
            JOptionPane.showInputDialog(
                "Digite o gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): "));

    Float altura = Float.parseFloat(JOptionPane.showInputDialog("Digite a altura (em metros): "));

    int peso = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));

    float renda = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));

    String naturalidade = JOptionPane.showInputDialog("Digite a naturalidade: ");

    Hobby hobby =
        Hobby.valueOf(
            JOptionPane.showInputDialog(
                "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME, NENHUM]:"
                    + " "));

    EstadoCivil estadoCivil =
        EstadoCivil.valueOf(
            JOptionPane.showInputDialog(
                "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): "));

    Escolaridade escolaridade =
        Escolaridade.valueOf(
            JOptionPane.showInputDialog(
                    "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                        + " ")
                .toUpperCase());

    boolean feliz =
        Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));

    Moradia moradia =
        Moradia.valueOf(
            JOptionPane.showInputDialog(
                "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: "));

    Pessoa pessoa =
        new Pessoa(
            nome,
            dataDeAniversario,
            genero,
            altura,
            peso,
            renda,
            naturalidade,
            hobby,
            estadoCivil,
            escolaridade,
            feliz,
            moradia);
    dataset.addPessoa(pessoa);
    JOptionPane.showMessageDialog(
        null, pessoa, "Pessoa Cadastrada", JOptionPane.INFORMATION_MESSAGE);
  }

  public static void removerPessoa() {
    String pessoa = JOptionPane.showInputDialog("Digite o nome da pessoa que deseja remover: ");
    dataset.removePessoaByName(pessoa);
    JOptionPane.showMessageDialog(null, null, "Pessoa removida", 0);
  }

  public static void pesquisarPessoa() {
    String pessoa = JOptionPane.showInputDialog("Digite o nome da pessoa que deseja encontrar: ");
    Pessoa pessoaEncontrada = dataset.getPessoaByName(pessoa);
    
    if (pessoaEncontrada != null) {
        JOptionPane.showMessageDialog(null, pessoaEncontrada, "Pessoa Encontrada", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Pessoa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void histogramaFormacaoAcadêmica() {

    Pessoa[] listaPessoas = dataset.getAll();
    Escolaridade[] listaEscolaridades = Escolaridade.values();
    int[] contagemEscolaridades = new int[Escolaridade.values().length];

    for (int i = 0; i < dataset.size(); i++) {
      Escolaridade escolaridade = listaPessoas[i].getEscolaridade();
      for (int j = 0; j < contagemEscolaridades.length; j++) {
        if (listaEscolaridades[j] == escolaridade) {
          contagemEscolaridades[j]++;
        }
      }
    }

    DefaultCategoryDataset datasetGrafico = new DefaultCategoryDataset();
    for (int i = 0; i < contagemEscolaridades.length; i++) {
      datasetGrafico.addValue(
          contagemEscolaridades[i], "Formação Acadêmica", listaEscolaridades[i].name());
    }

    JFreeChart grafico =
        ChartFactory.createBarChart(
            "Distribuição de Formação Acadêmica",
            "Formação Acadêmica",
            "Número de Pessoas",
            datasetGrafico,
            PlotOrientation.VERTICAL,
            true,
            true,
            false);

    ChartPanel painelGrafico = new ChartPanel(grafico);
    painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frameGrafico = new JFrame();
    frameGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameGrafico.add(painelGrafico);
    frameGrafico.pack();
    frameGrafico.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frameGrafico.getContentPane(), "Histograma", JOptionPane.PLAIN_MESSAGE);
  }

  public static void pieEstadoCivil() {

    DefaultPieDataset<String> datasetGrafico = new DefaultPieDataset<>();
    datasetGrafico.setValue("Solteiro", dataset.percentEstadoCivil(EstadoCivil.SOLTEIRO));
    datasetGrafico.setValue("Casado", dataset.percentEstadoCivil(EstadoCivil.CASADO));
    datasetGrafico.setValue("Viúvo", dataset.percentEstadoCivil(EstadoCivil.VIUVO));
    datasetGrafico.setValue("Separado", dataset.percentEstadoCivil(EstadoCivil.SEPARADO));
    datasetGrafico.setValue("Divorciado", dataset.percentEstadoCivil(EstadoCivil.DIVORCIADO));

    JFreeChart grafico =
        ChartFactory.createPieChart(
            "Distribuição dos Estados Civis", datasetGrafico, true, true, false);

    ChartPanel painelGrafico = new ChartPanel(grafico);
    painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frameGrafico = new JFrame();
    frameGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameGrafico.add(painelGrafico);
    frameGrafico.pack();
    frameGrafico.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frameGrafico.getContentPane(), "Pie Chart", JOptionPane.PLAIN_MESSAGE);
  }
}
