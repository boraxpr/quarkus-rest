package dev.boraxpr.quarkus.todo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/todo")
public class TodoResource {

  @Inject TodoRepository todoRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Todo> getTodos() {
    return todoRepository.findAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Todo getTodo(@PathParam("id") Integer id) {
    return todoRepository.findById(id);
  }
}
