# Casa Automática

## O que é?

 - Uma API destinada ao gerenciamento de dispositivos conectados a internet, possibilitando a atribuição de tarefas de forma remota.

## Integrantes:

 - Paulo Henrique Da Silva Maccari 
 - Matheus De Souza Constante 
 - Gabriel Dos Reis Klein
 
## Problema

- Centralizar controle. Atualmente, gerenciar dispositivos conectados à internet exige o uso do app da fabricante para ligá-los ou enviar tarefas a eles.

## Tecnologias utilizadas

 - H3 - Banco de dados em memória para persistência dos dados
 - Spring - Framework para a construção das APIs REST e gerenciar dependencias
 - Java - Linguagem principal para o desenvolvimento
 - IntelliJ - IDE principal para o desenvolvimento.
 
## Limitações



## Casos de uso:

- API para controle de dispositivos conectados a internet 
- Gerenciamento para dispositivos executar tarefas 
- Registrar novos dispositivos para execução de funcionalidades específicas.
- Gerenciar e atualizar informações de dispositivos previamente cadastrados.
- Realizar verificações de operabilidade e desempenho dos dispositivos existentes.
- Remover cadastros de dispositivos inoperantes ou obsoletos.
- Agendar e registrar novas tarefas nos dispositivos.
- Consultar e acompanhar o status de tarefas pendentes ou em andamento.
- Alterar os parâmetros ou detalhes de tarefas já cadastradas.
- Excluir definitivamente tarefas do sistema.
- Cadastrar novos usuários para permitir para poder gerenciar os aparalhos.
- Consulta de informações do usuário para mostrar aparelhos do usuário.
- Atualizar informações de usuários para manter os registros sempre precisos.
- Remover usuários do sistema que não usam mas o sistema.
- Criar novas categorias para organizar aparelhos de maneira estruturada e eficiente.
- Administrar categorias existentes, otimizando a classificação dos aparelhos.
- Atualizar dados de categorias para refletir alterações de finalidade.
- Excluir categorias obsoletas garantindo a manutenção de um ambiente limpo e funcional.

## Entidades

- `aparelhos`: A entidade que representa os próprios aparelhos.
- `tarefas`: A tarefas que os aparelhos tem (Ex: ligar aspirador de pó e limpar por 10 minutos)
- `categoria`: Destina-se a filtrar e evitar a atribuição de tarefas relativas a categorias de dispositivos distintas.
- `usuario`: Destina-se a prevenir que duas pessoas que utilizam o mesmo dispositivo não intervenham uma na tarefa da outra.

## Relacionamentos

- Um usuário pode ter vários aparelhos, mas o aparelho só pôde ter um usuário (UUID)
- Um aparelho pode ter várias tarefas que podem ocorrer ao longo do dia
- Uma tarefa pode ser feitas por vários aparelho se forem do mesmo tipo (Draw.io)
- Uma categoria pode estar em vários aparelhos, porém o aparelho só pode ter uma categoria

## Endpoints

| Descrição                              | URI                                                                         | Método HTTP | Corpo                                                                                                           | Resposta Esperada | Erros esperados                                     |
| -------------------------------------- | --------------------------------------------------------------------------- | ----------- | --------------------------------------------------------------------------------------------------------------- | ----------------- | --------------------------------------------------- |
| Retornar um usuário                    | `GET usuario/{usuario-id}`                                                  | `GET`       | Vazio                                                                                                           | `200 OK`          | `404 Not Found` - aparelho não foi encontrado.      |
| Inserir novo usuário                   | `POST usuario`                                                              | `POST`      | `{ "usuario-nome": "Gabriel", "e-mail": "*****************@gmail.com" }`                                        | `201 Created`     | `400 Bad Request` - não foi possível criar usuário  |
| Atualizar usuário                      | `PUT usuario/{usuario-id}`                                                  | `PUT`       | `{ "usuario-nome": "Gabriel Reis", "e-mail": "*****************@hotmail.com" }`                                 | `200 OK`          | `404 Not Found` - usuario não foi encontrado.       |
| Deletar usuário                        | `DELETE usuario/{usuario-id}`                                               | `DELETE`    | Vazio                                                                                                           | `200 OK`          | `404 Not Found` - usuario não foi encontrado.       |
| Retornar um aparelho                   | `GET usuario/{usuario-id}/aparelhos/{aparelhos-id}`                         | `GET`       | Vazio                                                                                                           | `200 OK`          | `404 Not Found` - aparelho não foi encontrado.      |
| Inserir novo aparelho                  | `POST usuario/{usuario-id}/aparelhos`                                       | `POST`      | `{"nome":"robô aspirador","descricao": "aspirar pó", "categoria":"limpeza"}`                                    | `201 Created`     | `400 Bad Request` - não foi possível criar aparelho |                                         
| Atualizar informações de um aparelho   | `PUT usuario/{usuario-id}/aparelhos/{aparelhos-id}`                         | `PUT`       | `{"descrição": "aspira pó e passa pano" }`                                                                      | `200 OK`          | `404 Not Found` - aparelho não foi encontrado       |
| Deletar um aparelho                    | `DELETE usuario/{usuario-id}/aparelhos/{aparelhos-id}`                      | `DELETE`    | Vazio                                                                                                           | `200 OK`          | `404 Nota Found` - aparelho não foi encontrado      |
| Retornar tarefas do aparelho           | `GET usuario/{usuario-id}/aparelhos/{aparelhos-id}/tarefas`                 | `GET`       | Vazio                                                                                                           | `200 OK`          | `404 Not Found` - aparelho não foi encontrado.      |
| Retornar uma tarefa do aparelho        | `GET usuario/{usuario-id}/aparelhos/{aparelhos-id}/tarefas/{tarefas-id}`    | `GET`       | Vazio                                                                                                           | `200 OK`          | `404 Not Found` - aparelho não foi encontrado.      |
| Inserir nova tarefa                    | `POST usuario/{usuario-id}/aparelhos/{aparelhos-id}/tarefas`                | `POST`      | `{ "tarefa": ligar aspirador de pó por 10 minutos, "hora":"16:20", "repetir": "segunda", "periodo":"dentro"}`   | `201 Created`     | `404 Not Found` - aparelho não foi encontrado.      |
| Atualizar tarefa                       | `PUT usuario/{usuario-id}/aparelhos/{aparelhos-id}/tarefas/{tarefas-id}`    | `PUT`       | `"tarefa": ligar aspirador de pó por 20 minutos }`                                                              | `200 OK`          | `404 Not Found` - tarefa não foi encontrado.        |
| Deleta uma tarefa                      | `DELETE usuario/{usuario-id}/aparelhos/{aparelhos-id}/tarefas/{tarefas-id}` | `DELETE`    | Vazio                                                                                                           | `200 OK`          | `404 Not Found` - tarefa não foi encontrado.        |
| Inserir nova categoria                 | `POST usuario/{usuario-id}/categoria`                                       | `POST`      | `{"categoria-nome":"limpeza", "descricao":"limpeza da casa"}`                                                   | `201 Created`     | `400 Bad Request` - não foi possível criar aparelho |                                         
| Atualizar informações de uma categoria | `PUT usuario/{usuario-id}/categoria/{categoria-id}`                         | `PUT`       | `{"descricao": "aparelhos que limpam a casa" }`                                                                 | `200 OK`          | `404 Not Found` - aparelho não foi encontrado       |
| Deletar uma categoria                  | `DELETE usuario/{usuario-id}/categoria/{categoria-id}`                      | `DELETE`    | Vazio                                                                                                           | `200 OK`          | `404 Nota Found` - aparelho não foi encontrado      |

## Como executar projeto localmente



## Carta Desafio

 - A implementação da carta desafio foi feita através da inutilização do aparelho fora do período no dia e hora determinado pelo o usuário e através da inutilização de o aparelho dentro de um período determinado pelo usuário
