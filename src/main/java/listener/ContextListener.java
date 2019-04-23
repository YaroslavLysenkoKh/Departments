package listener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
@WebListener
public class ContextListener implements ServletContextListener {
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
    }

    private void initDataSource() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/departments");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
