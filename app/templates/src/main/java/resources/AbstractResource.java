package <%= packageName %>.resources;

import spark.Spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractResource implements Controller {
    protected static final String APPLICATION_JSON_UTF_8 = "application/json;charset=utf-8";
    private final Logger LOG = LoggerFactory.getLogger(AbstractResource.class);

    /**
     * The base URL for the resource
     */
    private String resourceRoot;
    private final static String FORWARD_SLASH = "/";

    public AbstractResource() {
        this("/api");
    }
    
    public AbstractResource(final String root) {
        if (root.startsWith(FORWARD_SLASH)) {
            this.resourceRoot = root.replaceFirst(FORWARD_SLASH, "");
        } else {
            this.resourceRoot = root;
        }
    }
    
    public String getRootPath() {
        return FORWARD_SLASH.concat(this.resourceRoot);
    }
    
    protected String withContext(String apiPath) {
        return getRootPath().concat(
                apiPath.startsWith(FORWARD_SLASH) ? apiPath : FORWARD_SLASH.concat(apiPath));
    }
}
