package com.ego.item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-28 11:38
 **/
@WebServlet("/demo2")
public class DemoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String funname = req.getParameter("callback");
        resp.setContentType("application/x-javascript");
        PrintWriter out = resp.getWriter();
        out.print(funname+"('data123');");
        out.flush();
        out.close();
    }
}