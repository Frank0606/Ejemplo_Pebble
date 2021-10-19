package mx.uv;

import spark.template.pebble.*;

import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import static spark.Spark.*;

public class App 
{
    public static void main( String[] args )
    {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
    
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
    
            return "OK";
        });

        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        get("/prueba", (req, res) -> {

            Map<String, Object> model = new HashMap<>();

            model.put("nombre", "Frank");

            return new ModelAndView(model, "secundaria.pebble");
        }, new PebbleTemplateEngine());


    /*PebbleEngine engine = new PebbleEngine.Builder().build();
    PebbleTemplate compiledTemplate = engine.getTemplate("principal.html");

    Map<String, Object> context = new HashMap<>();
    context.put("name", "Mitchell");

    Writer writer = new StringWriter();
    compiledTemplate.evaluate(writer, context);

    String output = writer.toString();*/

    }
}
