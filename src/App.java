import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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

        //Pegar só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for(Map<String, String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1m Nome:\u001b[m " + titulo);
            System.out.println("\u001b[1m Poster:\u001b[m " + urlImage);
            System.out.print("\u001b[105;1m Classificação:\u001b[m ");

            //Classificação em formato de estrelas (Desafio)
            var nota = Double.parseDouble(filme.get("imDbRating"));
            for(int i = 0; i < Math.floor(nota/2); i++) {
                System.out.print("\u2B50");
            }

            System.out.println();
        }
            
    }
}
