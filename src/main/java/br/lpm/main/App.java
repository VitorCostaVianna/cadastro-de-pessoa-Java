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
import javax.swing.JOptionPane;

public class App {

    public static Dataset dataset = new Dataset();
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    String qtdePessoasAux =
        JOptionPane.showInputDialog("Digite a quantidade de pessoas que deseja cadastrar: ");
    int qtdePessoas = Integer.parseInt(qtdePessoasAux);

    for (int i = 0; i < qtdePessoas; i++) {
      String nome = JOptionPane.showInputDialog("Digite seu nome: ");

      String dateInput = JOptionPane.showInputDialog("Digite a data de aniversário (dd/MM/yyyy): ");
      LocalDate dataDeAniversario = LocalDate.parse(dateInput, fmt);

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
                  "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME,"
                      + " NENHUM]: "));

      EstadoCivil estadoCivil =
          EstadoCivil.valueOf(
              JOptionPane.showInputDialog(
                  "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): "));

      Escolaridade escolaridade =
          Escolaridade.valueOf(
              JOptionPane.showInputDialog(
                      "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO,"
                          + " NENHUMA): ")
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
  }
}
