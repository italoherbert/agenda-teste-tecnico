# Agenda - Teste Técnico

Este readme trata do projeto agenda produzido como teste técnico para uma vaga de emprego.

## Os testes 01 e 02 e o MER

Os testes 01 e 02 estão na raiz do projeto, respectivamente, nos arquivos 
TESTE1.pdf e TESTE2.pdf. O modelo de entidade e relacionamento (MER) do banco 
de dados está no arquivo MER.jpg.

## Tecnologias

A principal linguagem de programação utilizada é a linguagem java. Veja as tecnologias utilizadas abaixo:

* Java
* Spring Boot
* JUnit
* H2
* Swagger/OpenAPI
* Maven
* VS Code

## O banco de dados

O banco de dados utilizado é o H2 e não roda como servidor de banco de dados. Funciona no modo arquivo. Logo, se o banco de dados não existir, será criado um novo na pasta de seu usuário. Se já existir, será utilizado pelo sistema.

No linux o caminho do arquivo de banco de dados é o seguinte:

```
/home/$USER/agenda.mv.db
```

No arquivo application.properties está a configuração do banco de dados que é a 
seguinte:

```
driver=org.h2.Driver
url=jdbc:h2:~/agenda
username=sa
password=
```

## Como rodar o projeto

Primeiramente faça o clone deste projeto, em:

```
git clone hhttps://github.com/italoherbert/agenda-teste-tecnico.git
```

Feito isto, entre na pasta do projeto que deve ter o nome "agenda-teste-tecnico" e execute o seguinte comando MAVEN:

```
./mvnw clean package
```

Após o empacotamento da aplicação completar, o jar da aplicação deve estar na pasta target. Então, rode o projeto como a seguir:

```
java -jar target/agenda-0.0.1-SNAPSHOT.jar
```
Após subir a aplicação, pode utilizar a interface gráfica do swagger para realizar testes. Segue o link:

```
http://localhost:8080/swagger-ui/index.html
```