# election-rmi-carlavieira
election-rmi-carlavieira created by GitHub Classroom

## Instruções

Este trabalho é baseado numa proposta do livro do Coulouris, Dollimore, Kinberg, Blair (2013), pag. 227.

Considere uma interface Election que fornece dois métodos remotos:

* vote: este método possui dois parâmetros por meio dos quais o cliente fornece o nome de um candidato (um string) e o “identificador de eleitor” (um hash MD5 usado para garantir que cada usuário vote apenas uma vez).
* Os identificadores de eleitor devem ser gerados a partir de uma função MD5 do nome completo do eleitor.
* result: este método possui dois parâmetros com os quais o servidor fornece para o cliente o nome de um candidato e o número de votos desse candidato.

Desenvolva um sistema para o serviço Election utilizando o Java RMI, que garanta que seus registros permaneçam consistentes quando ele é acessado simultaneamente por vários clientes. O serviço Election deve garantir que todos os votos sejam armazenados com segurança, mesmo quando o processo servidor falha. Considerando que o Java RMI possui semântica at-most-once, implemente um mecanismo de recuperação de falha no cliente que consiga obter uma semântica exactly-once para o caso do serviço ser interrompido por um tempo inferior a 30 segundos.

## Comandos

Para rodar a aplicação, você deverá abrir três janelas do terminal direcionadas para a raiz do repositório e rodxar os seguintes comandos:

Janela 1:

```shell
rmiregistry
```
Janela 2:

```shell
java -Djava.security.policy=rmi.policy ElectionServer
```
Janela 3:

* Para votar:
```shell
java ElectionClient vote "Nome do Votante" "Nome do Candidato"
```
* Para obter resultado:
```shell
java ElectionClient result
```
