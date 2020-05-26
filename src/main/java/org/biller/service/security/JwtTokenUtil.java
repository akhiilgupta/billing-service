package org.biller.service.security;

import java.util.Set;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.biller.service.entities.RequestEntity;
import org.biller.service.repos.RequestsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtTokenUtil {

  @Value("${security.jwt.secret.key}")
  private String secret;

  @Value("${security.jwt.valid.aud}")
  private Set<String> schemeIds;

  @Autowired
  private RequestsRepo requestsRepo;

  public String getSchemeId(String token) {
    return getClaimFromToken(token, Claims::getAudience);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  // for retrieveing any information from token we will need the secret key
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  // validate token
  public void validateTokenAndSaveRequest(String token) {
    final String audience = getSchemeId(token);
    final String uniqueId = getUniqueId(token);

    if (!schemeIds.contains(audience)) {
      throw new SignatureException("aud not authorised to access the resource");
    }
    if (StringUtils.isBlank(uniqueId)) {
      throw new MalformedJwtException("jti must be present to access the resource");
    }
    // using database to limit the request to 1 per unique id instead of using jwt expiration
    if (requestsRepo.existsByUniqueId(uniqueId)) {
      throw new SignatureException("token previously used");
    }
    requestsRepo.save(RequestEntity.builder().uniqueId(uniqueId).schemeId(audience).build());

  }

  private String getUniqueId(String token) {
    return getClaimFromToken(token, Claims::getId);
  }
}
