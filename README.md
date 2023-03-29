# Sistema de Votação

### Tópicos

* [**Ambiente**](#ambiente)
* [**Sobre**](#sobre)
  * [Objetivo principal](#objetivo-principal)
  * [Outras implementações](#outras-implementacoes)
* [**Instruções do sistema**](#instrucoes-sistema)
  * [O que preciso fazer antes de subir o sistema](#antes-subir-aplicacao)
  * [Portas](#portas)
  * [Como subir o sistema](#como-subir-sistema)
* [**Material de consumo da API**](#material-consumo-api)

### Ambiente <a id="ambiente"></a>

* Java 11
* Maven 3.8.1
* Docker

### Sobre <a id="sobre"></a>

Com o cenário de que, no cooperativismo, cada associado possui um voto e as decisões são tomadas em assembléias por votação, esse sistema tem por objetivo o gerenciamento de sessões das votações.

#### Objetivo principal <a id="objetivo-principal"></a>

Nesse sistema deve ser possível:

* Cadastrar uma nova Pauta;
* Abrir uma nova sessão de votação em uma pauta
* Receber votos dos associados em pautas
* Contabilizar os votos e dar o resultado da votação na pauta

#### Outras implementações <a id="outras-implementacoes"></a>

O sistema conta ainda com os seguintes itens:

* Mensageria e filas
  * Utilizando um sistema de comunicação AMQP usando o RabbitMQ
* Performance
  * Fazendo registro em um serviço GATEWAY que faz o _load balance_ das aplicações
* Versionamento
  * Versionamento da aplicação por meio do GIT
  * Versionamento do banco por meio de Flyway

### Instruções do sistema <a id="instrucoes-sistema"></a>

#### O que preciso fazer antes de subir o sistema <a id="antes-subir-aplicacao"></a>

Para que se tenha sucesso ao rodar a aplicação, deve-se primeiramente estar com o Docker instalado e rodando na máquina.
Em seguida, navegar pelo _terminal_ até a pasta **docker** localizada na raiz desse sistema e executar o comando abaixo:

```
docker-compose up
```

#### Portas <a id="portas"></a>

Esse ponto é importante, pois o serviço _mscloudgateway_ sobe na porta 8080; certifique-se que essa porta não esteja
sendo usada por outra aplicação.

Nos demais serviços:

* **eurekaserver**: por padrão, o serviço discovery (_eurekaserver_) sobe na porta **8761**;
* **mscooperativa**: porta aleatória;
* **msvotacao**: porta aleatória;
* **mscontabilizador**: porta aleatória

#### Como subir o sistema <a id="como-subir-sistema"></a>

Para subir o sistema deve-se seguir uma sequência, pois alguns serviços precisam se registrar em outros.

Segue sequência de subida dos serviços:

1. eurekaserver
1. mscooperativa
1. msvotacao
1. mscontabilizador
1. mscloudgateway

### Material de consumo da API <a id="material-consumo-api"></a>

Segue anexo a nesse projeto uma _JSON Collection_ para ser importada no Postman para facilitar as requisições.

Pode ser baixada na pasta _collections_ clicando no link abaixo:

* **[Postman Collection](https://github.com/claytoncastro/voting-system/blob/master/collections/voting-system.postman_collection.json "Postman Collection")**
