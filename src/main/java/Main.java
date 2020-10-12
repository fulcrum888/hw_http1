import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        final ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet("https://cat-fact.herokuapp.com/facts");
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            CatFacts facts = mapper.readValue(body, new TypeReference<>() {});

            Stream<CatFact> stream = facts.getAll().stream();
            stream.filter(value -> value.getUpvotes() > 0)
                    .forEach(System.out::println);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class CatResponse {
        private List<CatFact> all;

        public CatResponse(@JsonProperty("all") List<CatFact> all) {
            this.all = all;
        }

        public List<CatFact> getAll() {
            return all;
        }

        public void setAll(List<CatFact> all) {
            this.all = all;
        }
    }

}
