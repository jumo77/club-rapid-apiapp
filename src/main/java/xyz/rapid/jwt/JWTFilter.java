package xyz.rapid.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.titles.Role;
import xyz.rapid.service.dto.MemberDetail;
import xyz.rapid.store.jpa.jpo.MemberJpo;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization==null||!authorization.startsWith("Bearer")){
            System.out.println("Incorrect token");
            filterChain.doFilter(request, response);

            return;
        }

        System.out.println("Auth");
        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)){
            System.out.println("Token expired");
            filterChain.doFilter(request,response);

            return;
        }

        MemberJpo member = new MemberJpo();
        member.setStdNum(jwtUtil.getUsername(token));
        member.setPassword("tempt");
        member.setRole(Role.valueOf(jwtUtil.getRole(token)));

        MemberDetail memberDetail = new MemberDetail(member);


        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                memberDetail, null, memberDetail.getAuthorities()));

        filterChain.doFilter(request,response);
    }
}
