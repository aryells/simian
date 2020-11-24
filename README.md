[![Build Status](https://travis-ci.com/aryells/simian.svg?token=JagPGDWpyfSPinW9okvF&branch=master)](https://travis-ci.com/aryells/simian)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.mercadolivre%3Asimian&metric=alert_status&token=1871ea73bdfcc9d0b1aa239b2fe8f81f0876f099)](https://sonarcloud.io/dashboard?id=com.mercadolivre%3Asimian)
# Simian
## Documentação

### Solução dos desafios
### Nível 1:
O método isSimian(String[] dna) está implementado na classe HumanService.
Este médoto lança uma exceção quando o DNA não corresponde a característica humana (bad request) e lança outra exceção quando o DNA não é Símio (Forbidden).
### Nível 2 / Nível 3:
Para a solução deste desafio utilizei as seguintes estruturas:
* Testes:
 * Conceitos de BDD (Behavior Driven Development) com Cucumber.
 * jacoco para realizar a covertura de código.
 * [SonarCloud](https://sonarcloud.io/) para avaliar a qualidade do código
* Documentação dos serviços para o nível 2 e 3 do teste disponível em:
 * [swagger-local](http://localhost:8080/swagger-ui.html)
 * [swagger-PRD](https://simian-274117.rj.r.appspot.com/swagger-ui.html)
* Para garantir que não haverá duplicidade de registros implementei um md5 com base na sequência de DNA.
* Para garantir a performance, deixei a gravação do novo registro de forma assincrona.
* Para estrutura de cumputação em nuvem: Google Cloud Platform
 * AppEngine
 * Datastore
 * CD - deploy automatizado para AppEngine passando ${git.commit.id.abbrev} como número de versão do deploy

### Execução do projeto
* Para rodar na IDE: classe principal - com.mercadolivre.simian.SimianApplication

### TODO
Para melhorar a performance da gravação de novos registros, poderá ser criado uma estrutura de filas.
Com essa abordagem ao invés de executar o método salvar, seria publicado em uma fila a requisição.
E um consumidor ficaria encarregado de validar se é um novo registro e persistir no Datastore.
