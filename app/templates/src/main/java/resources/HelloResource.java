package <%= packageName %>.resources;

import spark.Spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import <%= packageName %>.util.JsonTransformer;

public class HelloResource extends AbstractResource {
    private static final Logger LOG = LoggerFactory.getLogger(HelloResource.class);
    
    public HelloResource(String apiContext) {
        super(apiContext);
    }
    
    @Override
    public void registerRoutes() {  
    
        /**
         * @api {get} /api/hello/:name Say hello
         * @apiParam {String} name A name that should be greeted
         * @apiGroup Default
         * 
         * @author <authorName>
         */
        Spark.get(withContext("/hello/:name"), (request, response) -> {
            String name = request.params("name");
            if (name.equals("")) {
                name = "Stranger";
            }
            return "Hello, ".concat(name);
        }, new JsonTransformer());
        LOG.info("Registered /api/hello/:name");
    }
}
