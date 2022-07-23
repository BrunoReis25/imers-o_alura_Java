import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static String recuperaImagemMaior(String url){
        String[] partesEndereco = url.split("._");

        return partesEndereco[0] + ".jpg";
    } 

    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        var API = "IMDB";
        String url = Extratores.valueOf(API).getUrl();
        ExtratorDeConteudo extrator = Extratores.valueOf(API).getExtrator();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);
            
        //Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for(var i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo() + ".png";

            try{
                geradora.cria(inputStream, nomeArquivo);
            } catch  (Exception e){
                System.out.println("Não foi possível usar essa imagem!");
            }

            System.out.println("\u001b[1m Nome:\u001b[m " + conteudo.titulo());
            System.out.println("\u001b[1m Poster:\u001b[m " + conteudo.urlImagem());
            System.out.print("\u001b[105;1m Classificação:\u001b[m ");

            //Classificação em formato de estrelas (Desafio)
            var nota = Double.parseDouble(conteudo.ranting());
            for(int j = 0; j < nota ; j++) {
                System.out.print("\u2B50");
            }
            System.out.println();

            System.out.println();
        }       
    }
}
