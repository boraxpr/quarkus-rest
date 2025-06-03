package dev.boraxpr.quarkus.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.boraxpr.quarkus.common.SqlHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class TodoRepository {

  @Inject SqlHelper sqlHelper;
  @Inject ObjectMapper objectMapper;

  public List<Todo> findAll() {
    String sql =
        """
          SELECT *
          FROM todo
        """;

    return sqlHelper.runRowMapperQuery(
        sql,
        rs -> {
          try {
            return new Todo(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getBoolean("done"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime());
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });
  }

  public Todo findById(Integer id) {
    String sql =
        // JSON Query
        """
        SELECT row_to_json(t) AS json
        FROM (
          SELECT *
          FROM todo
          WHERE id = ?
        ) t\
        """;
    return sqlHelper.runTypedJsonQuery(sql, Todo.class, id).get(0);
  }
}
