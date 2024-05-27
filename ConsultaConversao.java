import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConsultaConversao {

   public static class Conversao{
      public String result;
      public String base_code;
      public Map<String, Double> conversion_rates;

      public Conversao(){
         this.conversion_rates = new HashMap<>();
      }
   }

         public Conversao buscaConversao(String apiKey) throws IOException {
            String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

// fazendo a requisição
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            ApiResponse response = gson.fromJson(reader, ApiResponse.class);

            //Filtrando as moedas que vou usar
            String[] moedasDeInteresse = {"ARS", "BOB","BRL", "CLP", "COP", "USD"};
            Conversao conversao = new Conversao();
            conversao.result = response.result;
            conversao.base_code = response.base_code;

            for (String moeda : moedasDeInteresse){
               if(response.conversion_rates.containsKey(moeda)){
                  conversao.conversion_rates.put(moeda, response.conversion_rates.get(moeda));
               }

            }


            reader.close();
            connection.disconnect();

            if (!"success".equals(conversao.result)) {
               throw new IOException("API request failed with result: " + conversao.result);
            }

            return conversao;




        }


    }














