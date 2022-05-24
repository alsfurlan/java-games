package br.com.esucri.games.plataforma;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("plataformas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlataformaController {

    @Inject
    private PlataformaService plataformaService;

    @GET
    public List<Plataforma> findAll() {
        return this.plataformaService.findAll();
    }

    @GET
    @Path("{id}")
    public Plataforma findById(@PathParam("id") Long id) {
        Plataforma plataforma = this.plataformaService.findById(id);
        if (plataforma == null) {
            throw new WebApplicationException("Plataforma não encontrada", Response.Status.NOT_FOUND);
        }
        return plataforma;
    }

    @POST
    public Plataforma add(Plataforma plataforma) {
        return this.plataformaService.add(plataforma);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") long id) {
        Plataforma plataforma = this.plataformaService.findById(id);
        if (plataforma == null) {
            throw new NotFoundException("Plataforma não encontrada");
        }
        this.plataformaService.remove(plataforma);
    }

    @PUT
    @Path("{id}")
    public Plataforma update(@PathParam("id") Long id, Plataforma plataformaAtualizada) {
        Plataforma plataformaEncontrada = this.plataformaService.findById(id);
        if (plataformaEncontrada == null) {
            throw new NotFoundException("Plataforma não encontrada");
        }
        plataformaAtualizada.setId(id);
        return this.plataformaService.update(plataformaAtualizada);
    }

    @GET
    @Path("search")
    public List<Plataforma> search(@QueryParam("desc") String descricao) {
        if(descricao == null) {
            throw new BadRequestException("Paramêtro desc não informado");
        }
        return this.plataformaService.search(descricao);
    }
}
