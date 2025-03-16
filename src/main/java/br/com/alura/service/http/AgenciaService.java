package br.com.alura.service.http;

import br.com.alura.domain.Agencia;
import br.com.alura.exception.AgenciaNaoAtivaException;
import br.com.alura.exception.AgenciaNaoEncontradaException;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia) {
        var agenciaBuscada = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (agenciaBuscada.isEmpty()) {
            throw new AgenciaNaoEncontradaException();
        }

        if (!agencia.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            throw new AgenciaNaoAtivaException();
        }

        agencias.add(agenciaBuscada.get());
    }

    public Agencia buscarPorId(Integer id) {
        return agencias
                .stream()
                .filter(agencia -> agencia.getId().equals(id))
                .toList()
                .stream()
                .findFirst()
                .get();
    }

    public void deletar(Integer id) {
        agencias.removeIf(agencia -> agencia.getId().equals(id));
    }

    public void alterar(Agencia agencia) {
        deletar(agencia.getId());
        cadastrar(agencia);
    }

}
