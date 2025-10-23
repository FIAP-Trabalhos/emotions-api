#language: pt

@regressivo
Funcionalidade: Cadastro de emoções
  Como usuário da API
  Quero cadastrar uma nova emoção
  Para que o registro seja salvo corretamente

  Cenário: Cadastro de emoção bem-sucedido
    Dado que eu tenha os seguintes dados:
      | campo    | valor      |
      | deviceId | Teste      |
      | date     | 2025-10-22 |
      | emotion  | ANSIOSO    |
    Quando eu enviar a requisição para o endpoint "/emotions" com o método "POST"
    Entao o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Create emotion bem-sucedido"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado

  Cenário: Cadastro de emoção sem sucesso
    Dado que eu tenha os seguintes dados:
      | campo    | valor      |
      | deviceId | Teste      |
      | date     | 2025-11-01 |
      | emotion  | ANSIOSO    |
    Quando eu enviar a requisição para o endpoint "/emotions" com o método "POST"
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "A data não pode ser no futuro!"
