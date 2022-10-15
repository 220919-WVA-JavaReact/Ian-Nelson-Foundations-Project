package com.revature.strings.foundationsProject.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomFilter extends HttpFilter {

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - Custom Filter initialized at " + LocalDateTime.now());
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("[LOG] - Custom Filter intercepts web request at " + LocalDateTime.now());
    }
}
