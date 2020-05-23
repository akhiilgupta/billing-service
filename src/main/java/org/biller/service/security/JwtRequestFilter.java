package org.biller.service.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.SignatureException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");
    // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      String jwtToken = requestTokenHeader.substring(7);
      try {
        jwtTokenUtil.validateTokenAndSaveRequest(jwtToken);
        filterChain.doFilter(request, response);
      } catch (SignatureException e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "token validation failed: " + e.getMessage());
      }
    } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "incorrect token format");
    }
  }

}
