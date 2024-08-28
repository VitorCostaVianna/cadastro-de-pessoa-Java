package br.lpm.business;

public class Dataset {
  private Pessoa pessoas[];
  private static final int MAX_PESSOAS = 100;
  private static int i = 0;

  public Dataset() {
    pessoas = new Pessoa[MAX_PESSOAS];
  }

  public void addPessoa(Pessoa pessoa) {
    if (size() < MAX_PESSOAS) {
      pessoas[i] = pessoa;
      i++;
    }
  }

  public void removePessoa(Pessoa pessoa) {
    for (int j = 0; j < size(); j++) {
      if (pessoa.equals(pessoas[j])) {
        pessoas[j] = null;
        i--;
      }
    }
  }

  public void removePessoaByName(String name) {
    for (int j = 0; j < size(); j++) {
      if (pessoas[j].getNome().equalsIgnoreCase(name)) {
        pessoas[j] = null;
        i--;
        break;
      }
    }
  }

  public void replacePessoa(Pessoa oldPessoa, Pessoa newPessoa) {
    for (int j = 0; j < size(); j++) {
      if (oldPessoa.equals(pessoas[j])) {
        pessoas[j] = null;
        pessoas[j] = newPessoa;
        break;
      }
    }
  }

  public Pessoa getPessoaByName(String name) {
    for (int j = 0; j < size(); j++) {
      if (pessoas[j].getNome().equalsIgnoreCase(name)) {
        return pessoas[j];
      }
    }
    return null;
  }

  public Pessoa getAll() {
    return null;
  }

  public void removeAll() {
    for (int j = 0; j < size(); j++) {
      pessoas[j] = null;
    }
    i = 0;
  }

  public static int size() {
    return i;
  }

  public float maxAltura() {
    if (size() > 0) {
      float maxAltura;
      maxAltura = pessoas[0].getAltura();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j].getAltura() > maxAltura) {
          maxAltura = pessoas[j].getAltura();
        }
      }
      return maxAltura;
    }
    return -1;
  }

  public float minAltura() {
    if (size() > 0) {
      float minAltura;
      minAltura = pessoas[0].getAltura();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j].getAltura() < minAltura) {
          minAltura = pessoas[j].getAltura();
        }
      }
      return minAltura;
    }
    return -1;
  }

  public float avgAltura() {
    float sum = 0;
    for (int j = 0; j < size(); j++) {
      sum += pessoas[j].getAltura();
    }
    return sum / size();
  }

  public float maxPeso() {
    if (size() > 0) {
      float maxPeso;
      maxPeso = pessoas[0].getPeso();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j].getPeso() > maxPeso) {
          maxPeso = pessoas[j].getPeso();
        }
      }
      return maxPeso;
    }
    return -1;
  }

  public float minPeso() {
    if (size() > 0) {
      float minPeso;
      minPeso = pessoas[0].getPeso();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j].getPeso() < minPeso) {
          minPeso = pessoas[j].getPeso();
        }
      }
      return minPeso;
    }
    return -1;
  }

  public float avgPeso() {
    float sum = 0;
    for (int j = 0; j < size(); j++) {
      sum += pessoas[j].getPeso();
    }
    return sum / size();
  }

  public float percentAdult() {
    return 0;
  }

  public float percentEstadoCivil(EstadoCivil estadoCivil) {
    int sum = 0;
    int n = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j].getEstadoCivil() == null) {
        n++;
      }
      if (pessoas[j].getEstadoCivil().equals(estadoCivil)) {
        sum++;
      }
    }
    return (sum / (size() - n)) * 100;
  }

  public EstadoCivil modeEstadoCivil() {
    float qtdSolteiro = percentEstadoCivil(EstadoCivil.SOLTEIRO);
    float qtdCasado = percentEstadoCivil(EstadoCivil.CASADO);
    float qtdViuvo = percentEstadoCivil(EstadoCivil.VIUVO);
    float qtdSeparado = percentEstadoCivil(EstadoCivil.SEPARADO);
    float qtdDivorciado = percentEstadoCivil(EstadoCivil.DIVORCIADO);

    float max = qtdSolteiro;
    EstadoCivil moda = EstadoCivil.SOLTEIRO;
    
    if (qtdCasado > max) {
      max = qtdCasado;
      moda = EstadoCivil.CASADO;
    }
    if (qtdViuvo > max) {
      max = qtdViuvo;
      moda = EstadoCivil.VIUVO;
    }
    if (qtdSeparado > max) {
      max = qtdSolteiro;
      moda = EstadoCivil.SOLTEIRO;
    }
    if (qtdDivorciado > max) {
      max = qtdDivorciado;
      moda = EstadoCivil.DIVORCIADO;
    }
    
    return moda;
  }

  public float percentEscolaridade(Escolaridade escolaridade) {
    int sum = 0;
    int n = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j].getEscolaridade() == null) {
        n++;
      }
      if (pessoas[j].getEscolaridade().equals(escolaridade)) {
        sum++;
      }
    }
    return (sum / (size() - n)) * 100;
  }

  public Escolaridade modeEscolaridade() {
    float qtdFundamental = percentEscolaridade(Escolaridade.FUNDAMENTAL);
    float qtdMedio = percentEscolaridade(Escolaridade.MEDIO);
    float qtdPosGraduação = percentEscolaridade(Escolaridade.POS_GRADUACAO);
    float qtdSuperior = percentEscolaridade(Escolaridade.SUPERIOR);
    float qtdNenhuma = percentEscolaridade(Escolaridade.NENHUMA);

    float max = qtdFundamental;
    Escolaridade moda = Escolaridade.FUNDAMENTAL;
    
    if (qtdMedio > max) {
      max = qtdMedio;
      moda = Escolaridade.MEDIO;
    }
    if (qtdPosGraduação > max) {
      max = qtdPosGraduação;
      moda = Escolaridade.POS_GRADUACAO;
    }
    if (qtdSuperior > max) {
      max = qtdSuperior;
      moda = Escolaridade.SUPERIOR;
    }
    if (qtdNenhuma > max) {
      max = qtdNenhuma;
      moda = Escolaridade.NENHUMA;
    }
    
    return moda;
  }
}
