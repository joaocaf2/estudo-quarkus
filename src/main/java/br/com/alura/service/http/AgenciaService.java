package br.com.alura.service.http;

import br.com.alura.domain.Agencia;
import br.com.alura.exception.AgenciaNaoAtivaException;
import br.com.alura.exception.AgenciaNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public void cadastrar(Agencia agencia) {
        var agenciaHttpBuscada = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (!agenciaHttpBuscada.isPresent()) {
            throw new AgenciaNaoEncontradaException("Agência não encontrada");
        }

        if (!agenciaHttpBuscada.get().getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            throw new AgenciaNaoAtivaException("Agência não ativa no momento");
        }

        agenciaRepository.persist(agencia);
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
