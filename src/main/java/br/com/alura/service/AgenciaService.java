package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.exception.AgenciaNaoAtivaException;
import br.com.alura.exception.AgenciaNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.SituacaoCadastral;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;
    private final MeterRegistry meterRegistry;

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    AgenciaService(AgenciaRepository agenciaRepository, MeterRegistry meterRegistry) {
        this.agenciaRepository = agenciaRepository;
        this.meterRegistry = meterRegistry;
    }

    public void cadastrar(Agencia agencia) {
        var agenciaHttpBuscada = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (agenciaHttpBuscada == null) {
            meterRegistry.counter("agencia_nao_adicionada_counter").increment();
            throw new AgenciaNaoEncontradaException("Agência não encontrada");
        }

        if (!agenciaHttpBuscada.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            meterRegistry.counter("agencia_nao_adicionada_counter").increment();
            throw new AgenciaNaoAtivaException("Agência não ativa no momento");
        }

        agenciaRepository.persist(agencia);

        meterRegistry.counter("agencia_adicionada_counter").increment();
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    public void deletar(Long id) {
        agenciaRepository.deleteById(id);
    }

    public void alterar(Agencia agencia) {
        agenciaRepository.update("nome = ?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", agencia.getNome(), agencia.getRazaoSocial(), agencia.getCnpj(), agencia.getId());
    }

}
