package br.ufpr.qrcdoor.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

public class BasicAuthenticationEntryPoint extends org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint {

    public static String REALM = "REALM";

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
        // Authentication failed, send error response.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 : " + authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName(REALM);
        super.afterPropertiesSet();
    }

}
