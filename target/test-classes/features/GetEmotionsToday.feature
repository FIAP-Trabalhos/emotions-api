#language: pt

@regressivo
Funcionalidade: Consulta das emoções de hoje
  Como usuário da API
  Quero consultar as emoções que foram cadastradas hoje
  Para que possa exibir os dados corretamente

  Cenário: Atualização de emoção bem-sucedido
    Dado que eu tenha os seguintes dados:
      | deviceId | Teste |
    Quando eu enviar a requisição para o endpoint "/emotions/Teste/today" com o método GET
    Entao o status code da resposta deve ser 200
