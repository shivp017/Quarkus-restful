package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieResource {

    public static List<Movie> movies = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        return Response.ok(movies).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    public Integer countMovies() {
        return movies.size();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie newMovie) {
        movies.add(newMovie);
        return Response.ok(movies).build();
    }

    @PUT
    @Path("{id}/{tittle}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(
            @PathParam("id") Long id,
            @PathParam("tittle") String tittle) {
        //System.out.println("Inside put " + movies);
        movies = movies.stream().map(movie -> {
                if (movie.getId() == id) {
                movie.setTittle(tittle);
            }
            return movie;
        }).collect(Collectors.toList());
        return Response.ok(movies).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") Long id) {

        Optional<Movie> movieTodelete=movies.stream().filter(movie -> movie.getId()==id).findFirst();
        boolean removed = false;
        if (movieTodelete.isPresent()){
            removed=movies.remove(movieTodelete.get());
        }
        if (removed) {
            return Response.noContent().build();
        }
               return Response.status(Response.Status.BAD_REQUEST).build();
    }
}