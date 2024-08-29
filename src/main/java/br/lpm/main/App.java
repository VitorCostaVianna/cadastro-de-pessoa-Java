package br.lpm.main;

import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class App {
  public static void main(String[] args) throws Exception {
    Locale.setDefault(Locale.US);
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner sc = new Scanner(System.in);

    Pessoa pessoa[] = new Pessoa[500];

    System.out.print("Digite a quantidade de pessoas que deseja cadastrar: ");
    int qtdePessoas = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < qtdePessoas; i++) {
      System.out.print("Digite seu nome: ");
      String nome = sc.nextLine();

      System.out.print("Digite a data de aniversário (dd/MM/yyyy): ");
      String dateInput = sc.next();
      LocalDate dataDeAniversario = LocalDate.parse(dateInput, fmt);
      sc.nextLine();

      System.out.print("Digite o gênero (MASCULINO, FEMININO, OUTRO): ");
      Genero genero = Genero.valueOf(sc.nextLine());

      System.out.print("Digite a altura (em metros, por exemplo 1.75): ");
      Float altura = sc.nextFloat();

      System.out.print("Digite o peso (em kg): ");
      int peso = sc.nextInt();

      System.out.print("Digite a renda: ");
      float renda = sc.nextFloat();
      sc.nextLine();

      System.out.print("Digite a naturalidade: ");
      String naturalidade = sc.nextLine();

      System.out.print(
          "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME, NENHUM]: ");
      Hobby hobby = Hobby.valueOf(sc.nextLine());

      System.out.print("Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO): ");
      EstadoCivil estadoCivil = EstadoCivil.valueOf(sc.nextLine().toUpperCase());

      System.out.print(
          "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, MESTRADO,"
              + " DOUTORADO): ");
      Escolaridade escolaridade = Escolaridade.valueOf(sc.nextLine());

      System.out.println("Você está feliz? (true/false): ");
      boolean feliz = sc.nextBoolean();
      sc.nextLine();

      System.out.print("Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ");
      Moradia moradia = Moradia.valueOf(sc.nextLine());

      pessoa[i] =
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

      System.out.println(pessoa[i]);
    }
    sc.close();
  }
}
