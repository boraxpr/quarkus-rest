package dev.boraxpr.quarkus.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import org.jboss.logging.Logger;

@Provider
public class RequestLoggerFilter implements ContainerRequestFilter, ContainerResponseFilter {

  private static final Logger LOG = Logger.getLogger("HTTP");

  @Override
  public void filter(ContainerRequestContext requestContext) {
    requestContext.setProperty("START_TIME", System.currentTimeMillis());
  }

  @Override
  public void filter(
      ContainerRequestContext requestContext, ContainerResponseContext responseContext)
      throws IOException {
    LOG.infof(
        "%s %s [%d] in %d ms",
        requestContext.getMethod(),
        requestContext.getUriInfo().getPath(),
        responseContext.getStatus(),
        System.currentTimeMillis() - (long) requestContext.getProperty("START_TIME"));
  }
}
