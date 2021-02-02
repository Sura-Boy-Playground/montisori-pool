package lk.ijse.dep.web.api;

import lk.ijse.dep.web.db.MontisoriPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ConnectionServlet", value = "/release")
public class ConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MontisoriPool pool = (MontisoriPool) getServletContext().getAttribute("cp");
        pool.releaseAllConnections();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
