package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.*;
import io.restassured.http.Method;
import model.ErrorMessageModel;
import org.json.JSONException;
import org.junit.Assert;
import services.EmotionService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmotionSteps {
    EmotionService emotionService = new EmotionService();

    @Dado("que eu tenha os seguintes dados:")
    public void queEuTenhaOsSeguintesDados(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            emotionService.setFieldsDelivery(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} com o método {string}")
    public void euEnviarARequisicaoParaOEndpointComOMetodoPost(String endpoint, String method) {
        Method httpMethod = Method.valueOf(method);
        emotionService.createDelievery(endpoint, httpMethod);
    }

    @Entao("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, emotionService.response.statusCode());
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoEO(String contract) throws JSONException, IOException {
        emotionService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeveEstarEmConformidadeComOContratoSelecionado() throws JSONException, IOException {
        Set<ValidationMessage> validateResponse = emotionService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessage = emotionService.gson.fromJson(
                emotionService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessage.getError().getFirst());
    }
}