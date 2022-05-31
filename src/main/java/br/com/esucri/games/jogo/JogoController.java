package br.com.esucri.games.jogo;

import br.com.esucri.games.regrasnegocio.RegraNegocioMensagem;
import br.com.esucri.games.regrasnegocio.RegraNegocioException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("jogos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JogoController {

    @Inject
    private JogoService jogoService;

    @GET
    public List<Jogo> findAll() {
        return this.jogoService.findAll();
    }

    @GET
    @Path("{id}")
    public Jogo findById(@PathParam("id") Long id) {
        return this.jogoService.findById(id);
    }

    @POST
    public Response add(Jogo jogo) {
        try {
            Jogo jogoAdicionado = this.jogoService.add(jogo);
            return Response.status(Response.Status.CREATED).entity(jogoAdicionado).build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new RegraNegocioMensagem(e.getMessage())
            ).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Jogo jogo) {
        jogo.setId(id);
        try {
            return Response.ok(this.jogoService.update(jogo)).build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new RegraNegocioMensagem(e.getMessage())
            ).build();
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.jogoService.remove(id);
    }
}
