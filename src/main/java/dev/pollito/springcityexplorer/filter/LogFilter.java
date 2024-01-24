package dev.pollito.springcityexplorer.filter;

import static dev.pollito.springcityexplorer.util.Constants.MDC_SESSION_ID_KEY;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;

public class LogFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    MDC.put(MDC_SESSION_ID_KEY, UUID.randomUUID().toString());
    try {
      filterChain.doFilter(servletRequest, servletResponse);
    } finally {
      MDC.clear();
    }
  }
}
