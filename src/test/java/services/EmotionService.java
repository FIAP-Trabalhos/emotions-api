package services;

import br.com.fiap.emotions.model.EmotionEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import model.EmotionModel;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;

public class EmotionService {
    EmotionModel emotionModel = new EmotionModel();
    public Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonSerializer<LocalDate>)
                    (src, typeOfSrc, context) -> new com.google.gson.JsonPrimitive(src.toString()))
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080/api";
    String apiKey = "981518ff-ee69-4444-a8de-9f1fc60bfe39";
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "id" -> emotionModel.setId(value);
            case "deviceId" -> emotionModel.setDeviceId(value);
            case "date" -> emotionModel.setDate(LocalDate.parse(value));
            case "emotion" -> emotionModel.setEmotion(EmotionEnum.valueOf(value));
            default -> throw new IllegalStateException("Unexpected field" + field);
        }
    }

    public void createDelievery(String endpoint, Method method) {
        String url = baseUrl + endpoint;
        String body = gson.toJson(emotionModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .header("X-API-KEY", apiKey)
                .when()
                .request(method, url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException, JSONException {
        String jsonText = Files.readString(Paths.get(filePath));
        return new JSONObject(jsonText);
    }

    public void setContract(String contract) throws IOException, JSONException {
        switch (contract) {
            case "Create emotion bem-sucedido" -> jsonSchema = loadJsonFromFile(schemasPath + "create-emotion-bem-sucedido.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException, JSONException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        return schema.validate(jsonResponseNode);
    }
}
