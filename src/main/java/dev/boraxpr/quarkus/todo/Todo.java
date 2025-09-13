package dev.boraxpr.quarkus.todo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Todo(
                @NotNull(message = "Id is required", groups = {
                                UpdateTodo.class, DeleteTodo.class }) Integer id,
                @NotBlank(message = "Title is required", groups = { CreateTodo.class }) String title,
                String description,
                boolean done,
                LocalDateTime created_at,
                LocalDateTime updated_at){

        public interface CreateTodo {
        }

        public interface UpdateTodo {
        }

        public interface DeleteTodo {
        }

        public interface ReadTodo {
        }

}
