/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package nl.zrb.hu.backend;

import java.io.IOException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

    int count = 0;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        count++;
        resp.setContentType("text/plain");
        resp.getWriter().println("U bent nummer " + count);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        count++;
        resp.setContentType("text/plain");
        resp.getWriter().println("U bent nummer " + count);
    }
}
