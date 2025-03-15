package br.com.alura.service.http;

import br.com.alura.domain.Agencia;
import br.com.alura.exception.AgenciaNaoAtivaOUnaoEncontradaException;
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
        var agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (agencia.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {

        } else {
            throw new AgenciaNaoAtivaOUnaoEncontradaException();
        }
    }


}
