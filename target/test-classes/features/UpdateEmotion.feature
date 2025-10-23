#language: pt

@regressivo
Funcionalidade: Atualização de emoções
  Como usuário da API
  Quero atualizar uma emoção existente
  Para que o registro seja atualizado corretamente

  Cenário: Atualização de emoção bem-sucedido
    Dado que eu tenha os seguintes dados:
      | campo    | valor                    |
      | id       | 68f2b9bab8a290d95de287a8 |
      | deviceId | Teste                    |
      | date     | 2025-10-17               |
      | emotion  | CANSADO                  |
    Quando eu enviar a requisição para o endpoint "/emotions/68f2b9bab8a290d95de287a8" com o método "PUT"
    Entao o status code da resposta deve ser 200

  Cenário: Atualização de emoção bem-sucedido
    Dado que eu tenha os seguintes dados:
      | campo    | valor                    |
      | id       | 68f2b9bab8a290d95de287a7 |
      | deviceId | Teste                    |
      | date     | 2025-10-17               |
      | emotion  | CANSADO                  |
    Quando eu enviar a requisição para o endpoint "/emotions/68f2b9bab8a290d95de287a7" com o método "PUT"
    Entao o status code da resposta deve ser 404
