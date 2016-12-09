package tech.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class ServerMain extends AbstractHandler{
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        //server.setHandler(new ServerMain());

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ctx.setContextPath("/");
        ctx.setResourceBase(".");
        ctx.addServlet(DefaultServlet.class, "/");
        ctx.addServlet(MainServlet.class, "/main");

        server.setHandler(ctx);

        server.start();
        server.join();
    }

    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        httpServletResponse.getWriter().write("HANDLER: Hello, World!");
    }
}
