import com.google.gson.JsonObject;

public class Conversao {
    public String result;
    public String base_Code;
    public JsonObject conversion_rates;

    public String toString() {
        return "Conversao{" +
                "result='" + result + '\'' +
                ", base_Code='" + base_Code + '\'' +
                ", conversion_rates=" + conversion_rates +
                '}';


    }



}
