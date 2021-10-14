package co.escuelaing.edu.arep;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

import org.json.JSONObject;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        port(getPort());
        options("/*",
        (request, response) -> {

            String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        get("sin","application/json",(req,res)->sin(req,res));
        get("tan","application/json",(req,res)->tan(req,res));
    }

    static JSONObject sin(Request req,Response res){
        JSONObject obj = new JSONObject();
        obj.put("operation", "sin");
        obj.put("input",req.queryParams("value"));
        obj.put("output", Math.sin(Double.parseDouble(req.queryParams("value"))));
        return obj;
    }

    static JSONObject tan(Request req,Response res){
        JSONObject obj = new JSONObject();
        obj.put("operation", "tan");
        obj.put("input",req.queryParams("value"));
        obj.put("output", Math.tan(Double.parseDouble(req.queryParams("value"))));
        return obj;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
