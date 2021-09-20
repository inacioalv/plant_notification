<h1 align="center">
  Api Plant notification
</h1>


<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#Config">Config</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>


<br>


## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- [Spring](https://spring.io/)
- [Jpa](https://spring.io/projects/spring-data-redis)
- [Spring Boot Starter Mail](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail)
- [RabbitMQ](https://www.rabbitmq.com/)
- [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth)
- [zipkin](https://zipkin.io/)
- [gateway](https://spring.io/projects/spring-cloud-gateway)
- [netflix-eureka](https://spring.io/projects/spring-cloud-sleuth)
- [Postgresql](https://www.postgresql.org/)
- [lombok](https://projectlombok.org/)
- [swagger](https://swagger.io/)


## 💻 Projeto

Desenvolvimento serviços web RESTful . A combinação de Spring Boot, Spring Web MVC, Spring Web Services,
JPA e Microsserviços. Serviços web RESTful desenvolvendo tratamento de exceção, 
documentação (Swagger) e implementando microsserviços usando o Spring Cloud. 
Estabelecendo comunicação entre microsserviços, possibilitar o balanceamento de carga, 
dimensionando para cima e para baixo de microsserviços, 
implementando o Eureka Naming Server e o Rastreamento Distribuído com Spring Cloud Sleuth e Zipkin. 
Esse projeto tem como objetivo desmotar a cuminicação entre microsserviço e a utilizanção servidor de mensageria RabbitMQ.


## Config
<h3>smtp</h3>
Guia para gerar código de 16 dígitos para 
configurar o smtp do gmail:https://support.google.com/accounts/answer/185833
<h3>RabbitMQ</h3>
Através da criação da conta no site RabbitMQ séra necessario gerar endreço AMQP URL para configuração do application.properties

## :hammer: Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

## Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8000
http://localhost:8100
```



## 📝 Licença

Este projeto esta sobe a licença MIT. Veja a [LICENÇA](https://opensource.org/licenses/MIT) para saber mais.


