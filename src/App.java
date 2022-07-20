import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {

        Properties prop = ArquivoProperties.getProp();

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://mocki.io/v1/" + prop.getProperty("key");
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //Pegar só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());

        //Exibir e manipular os dados
        for(Map<String, String> filme : listaDeFilmes) {
            System.out.println("\u001b[1m Nome:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1m Poster:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[105;1m Classificação:\u001b[m " + filme.get("imDbRating"));
            
            //Classificação em formato de estrelas
            var nota = Double.parseDouble(filme.get("imDbRating"));
            for(int i = 0; i < Math.floor(nota); i++) {
                System.out.print("\uD83D\uDC99");
            }
            System.out.println("");
        }
            
    }
}
