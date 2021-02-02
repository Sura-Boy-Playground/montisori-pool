package lk.ijse.dep.web.api;

import lk.ijse.dep.web.db.MontisoriPool;
import lk.ijse.dep.web.dto.Customer;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/api/v1/customers")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            MontisoriPool pool = (MontisoriPool) getServletContext().getAttribute("cp");
            Connection connection = pool.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM customer");
            List<Customer> customerList = new ArrayList<>();
            while (rst.next()){
                customerList.add(new Customer(rst.getString("id"),
                        rst.getString("name"),
                        rst.getString("address")));
            }
            response.setContentType("application/json");
            response.getWriter().println(jsonb.toJson(customerList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
