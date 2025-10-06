package dev.boraxpr.quarkus.todo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/todo")
@Tag(name = "Todo", description = "Todo management operations")
public class TodoResource {

  @Inject TodoRepository todoRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Get all todos", description = "Retrieve a list of all todo items")
  @APIResponse(responseCode = "200", description = "List of todos retrieved successfully")
  public List<Todo> getTodos() {
    return todoRepository.findAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Get todo by ID", description = "Retrieve a specific todo item by its ID")
  @APIResponse(responseCode = "200", description = "Todo retrieved successfully")
  @APIResponse(responseCode = "404", description = "Todo not found")
  public Todo getTodo(@Parameter(description = "Todo ID") @PathParam("id") Integer id) {
    return todoRepository.findById(id);
  }
}
