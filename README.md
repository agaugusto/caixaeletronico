Essa API foi desenvolvida para atender o desafio do dojopuzzles.com sobre um caixa eletrônico.
Seguem abaixo as regras contempladas para o dojo.

Desenvolva um programa que simule a entrega de notas quando um cliente efetuar um saque em um caixa eletrônico. Os requisitos 
básicos são os seguintes:

Entregar o menor número de notas;
É possível sacar o valor solicitado com as notas disponíveis;
Saldo do cliente infinito;
Quantidade de notas infinito (pode-se colocar um valor finito de cédulas para aumentar a dificuldade do problema);
Notas disponíveis de R$ 100,00; R$ 50,00; R$ 20,00 e R$ 10,00

Instruções de uso.

Executar o projeto caixa eletrônico na sua IDE.
Configurar Postman ou qualquer outra ferramenta de RESTFul para fazer o put no endereço 
localhost:8080/saque/:valor
Configurar o valor a ser sacado, só é permitido valores inteiros.
Ao executar, se estiver notas disponível será retornado um json informando a nota e quantidade.
Caso não tenha mais nota, será retornado uma exception.

Quantidade inicial de notas no dispenser.
R$ 100,00 - 5
R$  50,00 - 7
R$  20,00 - 2
R$  10,00 - 5