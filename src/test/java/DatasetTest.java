import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.Dataset;
import br.lpm.business.Pessoa;

public class DatasetTest {
    public static Pessoa pessoa;
    public static Pessoa pessoa2;
    public static Dataset dataset;

  @BeforeEach
  public void setUp() throws Exception {
    pessoa = new Pessoa();
    pessoa.setNome("Beatriz");
    pessoa2 = new Pessoa();
    pessoa2.setNome("Vitor");
    dataset = new Dataset();
    dataset.addPessoa(pessoa);
    dataset.addPessoa(pessoa2);
  }
    
    
    @Test
    void testAddPessoa() {
        dataset.addPessoa(new Pessoa());
        assertEquals(3, Dataset.size(),"Adicionando dentro do limite de MAX_PESSOA = 3 ");
        
        dataset.addPessoa(new Pessoa());
        assertEquals(3, Dataset.size(), "Adicionando fora do limite de MAX_PESSOA");
        
    }

    
    @Test
    void testRemovePessoa() {
        dataset.removePessoa(pessoa2);
        assertEquals(1, Dataset.size(), "Testando o tamanho do vetor apos a remoção por parametro");
        assertEquals(null, dataset.getPessoaByName(pessoa2.getNome()),"Testando se a pessoa passada por parametro foi removida" );
    }

    @Test
    void testRemovePessoaByName() {
        dataset.removePessoaByName(pessoa2.getNome());
        assertEquals(1, Dataset.size(), "Testando o tamanho do vetor apos a remoção por nome");
        assertEquals(null, dataset.getPessoaByName(pessoa2.getNome()), "Testando se a pessoa passada por nome foi removida");
    }

    @Test
    void testReplacePessoa() {
        Pessoa pessoaNova = new Pessoa();
        pessoaNova.setNome("Joaquim");

        dataset.replacePessoa(pessoa, pessoaNova);

        assertEquals(2 , Dataset.size(), "Teste se o tamanho permaneceu o mesmo");
        assertEquals(pessoaNova.getNome(), dataset.getPessoaByName(pessoaNova.getNome()).getNome(), "Testando se a pessoa nova esta no vetor ");
        assertEquals(null, dataset.getPessoaByName(pessoa.getNome()), "Testando se a pessoa original foi substituida");
    }

    @Test
    void testRemoveAll(){
        dataset.removeAll();
        assertEquals(0, Dataset.size(), "Verificando se o tamanho foi zerado");
        assertEquals(null, dataset.getPessoaByName(pessoa.getNome()), "Verificando se a pessoa foi removida");
    }
    @Test
    void testAvgAltura() {

    }

    @Test
    void testAvgPeso() {

    }

    @Test
    void testGetPessoaByName() {

    }

    @Test
    void testMaxAltura() {

    }

    @Test
    void testMaxPeso() {

    }

    @Test
    void testMinAltura() {

    }

    @Test
    void testMinPeso() {

    }

    @Test
    void testModeEscolaridade() {

    }

    @Test
    void testModeEstadoCivil() {

    }

    @Test
    void testModeMoradia() {

    }

    @Test
    void testPercentAdult() {

    }

    @Test
    void testPercentEscolaridade() {

    }

    @Test
    void testPercentEstadoCivil() {

    }

    @Test
    void testPercentFeliz() {

    }

    @Test
    void testPercentHobby() {

    }

    @Test
    void testPercentMoradia() {

    }

    @Test
    void testSize() {

    }
}
