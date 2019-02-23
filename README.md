# Star Wars Planet API
Este repositório consiste em desenvolver um CRUD com planetas, consumir uma api para verificar aparições nos filmes de starwars (swapi) e armazenar os dados necessários em um banco de dados com MongoDB.

## Tecnologias Utilizadas
* Java 8
* Spring Boot
* MongoDB

## Conexão do MongoDb

Para conectar ao mongodb são necessárias as seguintes variáveis de ambiente:

```
MONGO_HOST=127.0.0.1
MONGO_PORT=27017
MONGO_DATABASE=planetsdb
``` 

## Requests
A api suporta requests de GET, POST, DELETE.

GET: `localhost:8080/api/planet/` Busca todos os planetas cadastrados na base de dados.

    Resposta:
    [
       {
           "name": "nome do planeta",
           "terrain": "terreno do planeta",
           "climate": "clima do planeta"
        }
    ]

GET: `localhost:8080/api/planet/{ID}` Busca o planeta cadastrado com o ID indicado.
GET: `localhost:8080/api/planet/name/?name={NAME}` Busca o planeta cadastrado com o nome indicado.

    Resposta:
    
    {
        "name": "nome do planeta",
        "terrain": "terreno do planeta",
        "climate": "clima do planeta"
    }


POST: `localhost:8080/api/planet/` Insere um novo planeta na base de dados. Para isso se faz necessário enviar um json com a seguinte estrutura:

```
{
    "name": "nome do planeta",
    "terrain": "terreno do planeta",
    "climate": "clima do planeta"
}
```

DELETE: `localhost:8080/api/planet/{ID}` Deleta um planeta na base de dados com o id indicado.
    
    Sem conteúdo de resposta