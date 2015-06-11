package <%= packageName %>.framework;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractApplication {
    private final Logger LOG = LoggerFactory.getLogger(AbstractApplication.class);

    protected List<AbstractResource> controllers;

    private String staticAssetsDir;
    
    protected AbstractApplication() {
        this.controllers = new ArrayList<AbstractResource>();
    }
    
    public abstract void bootstrap() throws Exception;
    
    protected void run(String... args) throws Exception {
        this.configureStaticAssets();
        this.bootstrap();
        this.configureControllers();
        this.execControllers();
        LOG.info("All Controllers registered... Running Application");
    }
    
    public void setStaticAssetsDir(String staticAssetsDir) {
        this.staticAssetsDir = staticAssetsDir;
    }
    
    public abstract void configureControllers();
    
    public void addResource(AbstractResource controller) {
        this.addController(controller);
    }
    
    public void addController(AbstractResource controller) {
        this.controllers.add(controller);
    }
    
    protected void execControllers() {
        this.controllers.forEach((controller) -> controller.registerRoutes());
    }
    
    private void configureStaticAssets() {
        Spark.staticFileLocation(this.staticAssetsDir);
    }
}
