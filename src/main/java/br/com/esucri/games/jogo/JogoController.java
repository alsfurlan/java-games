package br.com.esucri.games.jogo;

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
    public Jogo add(Jogo jogo) {
        return this.jogoService.add(jogo);
    }

    @PUT
    @Path("{id}")
    public Jogo update(@PathParam("id") Long id, Jogo jogo) {
        jogo.setId(id);
        return this.jogoService.update(jogo);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.jogoService.remove(id);
    }
}
