import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class App {

    public static String recuperaImagemMaior(String url){
        String[] partesEndereco = url.split("._");

        return partesEndereco[0] + ".jpg";
    } 

    public static void main(String[] args) throws Exception {

        Properties prop = ArquivoProperties.getProp();

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://mocki.io/v1/" + prop.getProperty("key");
        
        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);
            
        //Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for(var i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            try{
                geradora.cria(inputStream, nomeArquivo);
            } catch  (Exception e){
                System.out.println("Não foi possível usar essa imagem!");
            }

            System.out.println("\u001b[1m Nome:\u001b[m " + conteudo.getTitulo());
            System.out.println("\u001b[1m Poster:\u001b[m " + conteudo.getUrlImagem());
            // System.out.print("\u001b[105;1m Classificação:\u001b[m ");

            //Classificação em formato de estrelas (Desafio)
            // var nota = Double.parseDouble(conteudo.get("imDbRating"));
            // for(int j = 0; j < Math.floor(nota/2); j++) {
            //     System.out.print("\u2B50");
            // }

            // System.out.println();
            System.out.println();
        }       
    }
}
