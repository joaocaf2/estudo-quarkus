package br.com.alura.service.http;

import br.com.alura.domain.Agencia;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Optional;

@Path("/situacao-cadastral")
@RegisterRestClient(configKey = "situacao-cadastral-api")
interface SituacaoCadastralHttpService {

    @GET
    @Path("{cnpj}")
    Optional<Agencia> buscarPorCnpj(String cnpj);
}
