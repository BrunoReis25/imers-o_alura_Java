import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ArquivoProperties {

    public static Properties getProp() throws IOException
    {
        Properties properties = new Properties();
        FileInputStream arquivo = new FileInputStream("./properties/key.properties");
        properties.load(arquivo);
        return properties;
    }
}