package linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.ImageMessage;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;

public class MorningCallApplication {

    //final static Logger logger = LoggerFactory.getLogger(MorningCallApplication.class);

    public static void main(String[] args) throws Exception {

        InputStream inputStream = MorningCallApplication.class
                .getClassLoader()
                .getResourceAsStream("application.yml");

        if(args.length == 0) {
            System.out.println("Start application with default setting.");
        } else {
            Arrays.asList(args).forEach(s -> {System.out.println(s);});
            //args[0] : yaml file path
            File yamlFile = new File(args[0]);
            if( !yamlFile.exists() || !yamlFile.isFile()) {
                System.err.println("input is not a file or file not found!");
                return;
            }
            inputStream = new FileInputStream(yamlFile);
        }

        Yaml yaml = new Yaml();
        Map<String, Object> settings = (Map<String, Object>) yaml.load(inputStream);

        settings.forEach( (K,V) ->{
            System.out.println( String.format("K : {%s}, V : {%s}", K, V));
        });
        String accessToken = ((Map<String, String>) settings.get("line.bot")).get("channel-token");
        final LineMessagingClient client = LineMessagingClient.builder(accessToken).build();

        URI imgURI = URI.create( (String) settings.get("image"));
        URI preURI = URI.create( (String) settings.get("pre"));

        System.out.println("imgURI : {" + imgURI.toString() +"}");
        System.out.println("preURI : {" + preURI.toString() +"}");

        ImageMessage imageMessage = new ImageMessage(imgURI, preURI);
        PushMessage pushMessage = new PushMessage(
                (String) settings.get("sendto"),
                imageMessage);
        client.pushMessage(pushMessage).whenComplete((a,b)-> {
            System.exit(0);
        });
    }
}
