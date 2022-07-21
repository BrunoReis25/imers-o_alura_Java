import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
      

      public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

            //leitura da imagem
            BufferedImage imagemOriginal = ImageIO.read(inputStream);

            //cria nova imagem em memória com transparência e com tamanho novo
            int largura = imagemOriginal.getWidth();
            int altura = imagemOriginal.getHeight();
            int novaAltura = altura + 200;

            BufferedImage novaImage = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

            //copiar a imagem original para nova imagem (em memória)
            Graphics2D graphics = (Graphics2D) novaImage.getGraphics();

            graphics.drawImage(imagemOriginal, 0, 0, null);

            //escrever uma frase (centralizada) na nova imagem

            Font fonte = new Font("Comic Sans MS", Font.BOLD, 64);
            graphics.setFont(fonte);

            String frase = "TOPZERA";
            FontMetrics tamanhoFonte = graphics.getFontMetrics();
            int posicaox = largura/2 - tamanhoFonte.stringWidth(frase)/2;
            int posicaoy = novaAltura - (100 - tamanhoFonte.getHeight());

            FontRenderContext fontRenderContext = graphics.getFontRenderContext();
            var textLayout = new TextLayout(frase, fonte, fontRenderContext);
            
            Shape outline = textLayout.getOutline(null);
            AffineTransform transform = graphics.getTransform();
            transform.translate(posicaox, posicaoy);
            graphics.setTransform(transform);

            var outlineStroke = new BasicStroke(largura * 0.004166f);
            graphics.setStroke(outlineStroke);
            
            graphics.fill(outline);
            graphics.setColor(Color.BLACK);
            graphics.draw(outline);
            graphics.setClip(outline);

            //escrever a nova imagem em um arquivo
            String saida = "saida/" + nomeArquivo;
            ImageIO.write(novaImage, "png", new File(saida));

      }
}
