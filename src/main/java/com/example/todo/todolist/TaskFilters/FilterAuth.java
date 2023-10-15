package com.example.todo.todolist.TaskFilters;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.todo.todolist.users.IUsersDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterAuth extends OncePerRequestFilter {
    @Autowired
    private IUsersDTO usersDTO;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //checking the path to make the validation correct
        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {
            // filter the enconded password and username
            var authorization = request.getHeader("Authorization");
            var authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecoded);
            String[] credential = authString.split(":");
            String username = credential[0];
            String password = credential[1];
            // user validation
            var user = this.usersDTO.findByUsername(username);
            if (user == null) {
                response.sendError(401, "Usuario sem autorização");
            } else {
                // password validation
                var passwordIsOk = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordIsOk.verified) {
                    request.setAttribute("userId", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401, "Usuario sem autorização");
                }
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }
}
