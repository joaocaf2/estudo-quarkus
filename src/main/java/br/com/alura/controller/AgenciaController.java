package br.com.alura.controller;

import br.com.alura.domain.Agencia;
import br.com.alura.service.http.AgenciaService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/agencias")
public class AgenciaController {

    private final AgenciaService agenciaService;

    public AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @POST
    @Transactional
    public RestResponse<Void> cadastar(Agencia agencia, @Context UriInfo uriInfo) {
        this.agenciaService.cadastrar(agencia);

        return RestResponse.created(uriInfo.getAbsolutePathBuilder().build());
    }

    @GET
    @Path("{id}")
    public RestResponse<Agencia> buscarPorId(Long id) {
        var agencia = this.agenciaService.buscarPorId(id);

        return RestResponse.ok(agencia);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public RestResponse<Agencia> deletar(Long id) {
        this.agenciaService.deletar(id);

        return RestResponse.ok();
    }

    @PUT
    @Transactional
    public RestResponse<Agencia> alterar(Agencia agencia) {
        this.agenciaService.alterar(agencia);

        return RestResponse.ok();
    }

}
