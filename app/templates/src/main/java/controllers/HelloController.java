package <%= packageName %>.controllers;

import spark.Spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import <%= packageName %>.resources.Controller;

public class HelloController implements Controller {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
    
    @Override
    public void registerRoutes() {  
    
        /**
         * URL /hello/:name
         * @author <authorName>
         */
        Spark.get("/hello/:name", (request, response) -> {
            String name = request.params("name");
            
            if (name.equals("")) {
                name = "Stranger";
            }
            
            StringBuilder sb = new StringBuilder();
            
            sb.append("<!DOCTYPE HTML>")
              .append("<html lang=\"en-US\">")
              .append("<head>")
              .append("<meta charset=\"UTF-8\">")
              .append("<title>Hello</title>")
              .append("</head>")
              .append("<body><h1>").append(name).append("</h1>")
              .append("<form method=\"get\">")
              .append("<input type=\"text\" name=\"name\" placeholder=\"Type your name here\"/>")
              .append("<input type=\"submit\" value=\"Say Hello\"/>")
              .append("</form>")
              .append("</body>")
              .append("</html>");
              
            return sb.toString();
        });
        LOG.info("Registered /hello/:name");
    }
}
