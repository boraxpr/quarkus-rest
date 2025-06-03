package dev.boraxpr.quarkus.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@ApplicationScoped
public class SqlHelper {

  @Inject AgroalDataSource dataSource;
  @Inject ObjectMapper objectMapper;

  public <T> List<T> runTypedJsonQuery(String sql, Class<T> type, Object... params) {
    List<T> results = new ArrayList<>();
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      for (int i = 0; i < params.length; i++) {
        stmt.setObject(i + 1, params[i]);
      }

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          results.add(objectMapper.readValue(rs.getString(1), type));
        }
      }

    } catch (Exception e) {
      throw new RuntimeException("Query failed: " + sql, e);
    }
    return results;
  }

  public List<JsonNode> runJsonQuery(String sql, Object... params) {
    return runTypedJsonQuery(sql, JsonNode.class, params);
  }

  public <T> List<T> runRowMapperQuery(
      String sql, Function<ResultSet, T> rowMapper, Object... params) {
    List<T> results = new ArrayList<>();
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      for (int i = 0; i < params.length; i++) {
        stmt.setObject(i + 1, params[i]);
      }

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          results.add(rowMapper.apply(rs));
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Query failed: " + sql, e);
    }
    return results;
  }
}
