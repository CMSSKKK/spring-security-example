package kr.ron2.security.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ron2.security.auth.JwtProvider;
import kr.ron2.security.auth.LoginMember;
import kr.ron2.security.auth.LoginResponse;
import kr.ron2.security.auth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        LoginMember principal = (LoginMember) authentication.getPrincipal();

        String accessToken = jwtProvider.generateAccessToken(principal.getId());
        String refreshToken = jwtProvider.generateRefreshToken(principal.getId());

        objectMapper.writeValue(response.getWriter(), new LoginResponse(principal, accessToken, refreshToken));
    }
}
