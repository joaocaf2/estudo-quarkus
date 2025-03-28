package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.Endereco;
import br.com.alura.domain.http.AgenciaHttp;
import br.com.alura.exception.AgenciaNaoAtivaException;
import br.com.alura.exception.AgenciaNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@QuarkusTest
public class AgenciaServiceTest {

    @InjectMock
    private AgenciaRepository agenciaRepository;

    @InjectMock
    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    @Inject
    private AgenciaService agenciaService;

    @DisplayName("Deve não cadastrar quando cliente retornar null")
    @Test
    public void deveNaoCadastrarQuandoClienteRetornarNull() {
        var agencia = criarAgencia();

        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj(any())).thenReturn(null);

        Assertions.assertThrows(AgenciaNaoEncontradaException.class, () -> agenciaService.cadastrar(agencia));

        Mockito.verify(agenciaRepository, Mockito.never()).persist(agencia);
    }

    @Test
    public void deveCadastrarQuandoClientRetornarSituacaoCadastralAtiva() {
        var agencia = criarAgencia();

        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj(anyString())).thenReturn(criarAgenciaHttp("ATIVO"));

        agenciaService.cadastrar(agencia);

        Mockito.verify(agenciaRepository).persist(agencia);
    }

    @DisplayName("Não deve cadastrar agência inativa")
    @Test
    public void naoDeveCadastrarAgenciaInativa() {
        var agencia = criarAgencia();

        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj(anyString())).thenReturn(criarAgenciaHttp("INATIVO"));

        Assertions.assertThrows(AgenciaNaoAtivaException.class, () -> agenciaService.cadastrar(agencia));

        Mockito.verify(agenciaRepository, Mockito.never()).persist(agencia);
    }

    private Agencia criarAgencia() {
        Endereco endereco = new Endereco(1, "", "", "", 1);

        return new Agencia(1, "", "", "", endereco);
    }

    private AgenciaHttp criarAgenciaHttp(String situacaoCadastral) {
        return new AgenciaHttp("Agencia Teste",
                "Razao Agencia Teste",
                "123", situacaoCadastral);
    }

}


