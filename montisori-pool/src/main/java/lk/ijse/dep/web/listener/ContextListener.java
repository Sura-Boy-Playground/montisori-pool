package lk.ijse.dep.web.listener;

import lk.ijse.dep.web.db.MontisoriPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MontisoriPool montisoriPool = new MontisoriPool();
        sce.getServletContext().setAttribute("cp",montisoriPool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
