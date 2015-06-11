package <%= packageName %>;

import <%= packageName %>.resources.HelloResource;
import <%= packageName %>.util.CorsFilter;

public class Application extends AbstractApplication {
    
    public static void main(String[] args) {
        try {
            new Application().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    @Override
    public void bootstrap() {
        // TODO: custom pre-run configuration stuff here
        CorsFilter.apply();
    }
    
    public void configureControllers() {
        // TODO: add your application's resources here
        // e.g. this.addResource(new PostsResource())
        // e.g. this.addController(new ArticlesController())
        this.addResource(new HelloResource());
    }
}
