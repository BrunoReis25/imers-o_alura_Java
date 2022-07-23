import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

      public List<Conteudo> extraiConteudos(String json) {

            //Pegar só os dados que interessam (titulo, poster, classificação)
            JsonParser parser = new JsonParser();
            List<Map<String, String>> listaDeAtributos = parser.parse(json);

            List<Conteudo> conteudos = new ArrayList<>();

            //popular a lista de conteudos
            for (Map<String, String> atributos : listaDeAtributos) {
                  
                  String titulo = atributos.get("title");
                  String urlImagem = atributos.get("url");
                   
                  var conteudo = new Conteudo(titulo, urlImagem, null);

                  conteudos.add(conteudo);
            }

            return conteudos;
      }
}
