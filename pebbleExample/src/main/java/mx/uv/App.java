package mx.uv;

import spark.template.pebble.*;

import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.*;

import static spark.Spark.*;

public class App 
{

    private static Gson gson = new Gson();
    private static Map<String, Usuario> usuarios = new HashMap<>();

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

        get("/pagina", (req, res) -> {
            res.redirect("estatica.html");
            return null;
        });

        post("/usuario", (req, res) -> {

            String payload = req.body();

            String id = UUID.randomUUID().toString();

            Usuario u = gson.fromJson(payload, Usuario.class);

            u.setId(id);

            usuarios.put(id, u);

            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", "ok");
            objetoJson.addProperty("id", id);

            return objetoJson;
        });

        get("/estatica", (req, res) -> {

            Map<String, Object> model = new HashMap<>();

            return new PebbleTemplateEngine().render(new ModelAndView(model, "estatica.html"));
        });

        get("/usuarios", (req, res) -> {

            Map<String, Object> model = new HashMap<>();

            model.put("id", usuarios.get("id"));

            return new ModelAndView(model, "principal.pebble");
		}, new PebbleTemplateEngine());

        get("/prueba", (req, res) -> {

            Map<String, Object> model = new HashMap<>();

            model.put("nombre", "Frank");
            
            return new PebbleTemplateEngine().render(new ModelAndView(model, "estatica.html"));
        });

    }
}
