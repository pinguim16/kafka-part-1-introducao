# Introdução 

Atualmente o Kafka é utilizado para realizar transferência de dados entre aplicações.
Como podemos notar, o volume de dados está crescendo a cada dia e, além disso, não podemos correr o
risco de perder qualquer dado por algum bug da aplicação, deploy, entre outros motivos.
Imagine a compra de um livro na amazon: o pagamento é realizado pelo cartão de crédito e a operadora 
precisa se certificar que tenha limite no cartão, que os dados estão corretos, que não é fraude e 
mais algumas validações. Nessa caso podemos ter várias aplicações com as suas regras de negócio isoladas
recebendo estímulos para processar essas informações e não precisam se preocupar 
com entrega/captura dos dados para processamento.
E nesse ponto que começamos a adentrar no data streaming.

![Imagem8](src/main/resources/img/img8.png)
*Exemplo de comunicação de sistema*

## Data Streaming

Data streaming é um fluxo constante e sem controle de dados, normalmente não se sabe onde
começa e termina o fluxo. Os dados vão sendo processados a medida que chegam no seu consumidor
praticamente em tempo real. Mas não quer dizer que essa é apenas a sua única caraterística, 
os dados também podem ser processados em batch ou lote com hora e data pré-estabelecidas.
Os dados chegam através de mensagens que são armazenadas. Esse sistema de mensagem nos permite 
paralelizar o processamento entre aplicações ou dependendo da 
aplicação podemos deixar de forma assíncrona, uma possível forma de se trabalhar com o Kafka.
Pode-se usar esse forma de sistema em qualquer ramo para agilizar na forma de processamento dos dados:
bancário, imobiliário, industrial e até mesmo para migração de grandes bases de dados.

## Mas afinal, o que é Kafka?

![Imagem9](src/main/resources/img/img9.png) 

O Apache Kafka foi um sistema desenvolvido pelo Linkedin para streaming de dados.
Originalmente Kafka foi criado para ser um sistema baseado em logs e teve até os seguintes nomes:
write-ahead logs, commit logs ou até mesmo transaction logs. Para ajudar no entendimento dessa
prática explicitamos abaixo o funcionamento das técnicas (as 3 mencionadas possuem o mesmo propósito 
nas fontes encontradas durante a pesquisa):

* Write-ahead Logs (WAL) é uma técnica que fornece atomicidade e durabilidade (propriedades do ACID -
Atomicidade, Consistência, Isolamento e Durabilidade) num sistema de banco de dados.
A técnica consiste em gravar todas as informações, primeiramente num log e depois aplica-las no banco de 
dados. Exemplo de um cenário que essa técnica pode ser extremamente útil: 
  * Se a máquina onde a aplicação está hospedada perde energia ou é desligada no meio de um processo.
Ao religa-la, essa aplicação precisará de informação se o processo que estava realizando 
foi concluído com sucesso ou até mesmo continuar de onde parou 
    e essa informação pode ser obtida através dos logs.

*Lembrando que um log é nada menos que uma forma de armazenamento de dado, 
onde toda nova informação é adicionada no final do arquivo. Esse é o princípio do Kafka.*

## Kafka e suas funcionalidades:

* Publish/Subscribe — é um pattern que consiste em ter um ou mais publicadores que terá um ou mais
  consumidor/inscrito e as duas pontas trocam mensagens de forma indireta.
  Dentro desse pattern temos uma subdivisão de publicador/canal, evento/publicação e inscrito/assinante.


* Sistema de armazenamento, por padrão as mensagens são armazenadas por 7 dias, mas pode ser alterado
 para armazenar até indefinidamente.


* Processamento de stream: processamento imediato de um fluxo de mensagens (data streaming).

![Imagem1](src/main/resources/img/img1.png) \
*Pattern publish/subscrive* 


O Kafka é um intermediário que trabalha coletando informações e armazenando para os consumidores.
![Imagem2](src/main/resources/img/img2.png)

O kafka vem sendo adotado para processos ETL(Extract Transform and Load), de forma a copiar os dados 
de uma banco de dados tradicional (OLTP) para um analítico (OLAP).
* ETL: é um data integration em 3 etapas, que consiste em extração, transformação e carregamento de dados.
Utilizado normalmente para combinar dados de diversas fontes gerando um data warehouse. Para alguns, o futuro 
  do ETL seja utilizar kafka para diminuir a dificuldade desse processo.

# Conceitos , nomenclatura e características  


A mensagem ou evento é composto por:
- Nome do Tópico: fila ao qual mensagem será postada/gravada;
- Partição: subdivisão de um tópico, a partição ajuda no balanceamento de carga, entre outras funções.
- Timestamp: os registros são ordenados por ordem de chegada.
- Chave: utilizada para cenários mais avançados;
- Valor: informação que deseja se enviar, normalmente composta por json, xml ou até mesmo uma string.

Normalmente temos dois de sistema de mensageira publish-subscribe e point-to-point.

O modelo point-to-point é baseado em conceito de filas, onde o produtor envia a mensagem para 
uma fila especifica que a armazena para entregar ao consumidor ou expirar. Caso essa fila possua
mais de um consumidor a fila garante que apenas um receberá a mensagem.

![Imagem3](src/main/resources/img/img3.png)

O modelo do publish/subscribe a troca de mensagens acontece pelo modelo de tópicos e as mensagens
são enviadas para os consumidores que assinaram o tópico.
Ao contrário do point-to-point esse modelo permite que envie a mesma mensagem para vários
consumidores.

![Imagem4](src/main/resources/img/img4.png)


O Apache Kafka trabalha com o publish/subscribe, pois a solução tem baixa latência 
para receber e enviar as mensagens. 
Além do pattern, a arquitetura ainda possui as seguintes características:

- Escalabilidade: o cluster do Kafka permite o redimensionamento para atender a demanda de maneira simples;
- Distribuído: o cluster pode operar com vários nós (brokers) para facilitar o processamento;
- Replicado, particionado e ordenado: as mensagens podem ser replicados na ordem que chegam para 
facilitar processamento, segurança e até mesmo na velocidade de entrega.
- Alta disponibilidade: o cluster tem diversos nós (brokers) e várias cópias tornando-o
sempre disponível caso um nó caia.
  
 
# Arquitetura Apache Kafka

Arquitetura do Kafka é composta por producers, consumers e o seu cluster.
Atualmente Netflix, Spotify, Uber, Linkedin e Twitter estão utilizando nas suas plataformas. 

![Imagem5](src/main/resources/img/img5.png)

O producer é qualquer aplicação que publica uma mensagem no Kafka. O consumer é qualquer aplicação que
consume as mensagens do kafka. Já o cluster é conjunto de nós (brokers kafka) que funcionam 
como única instância de serviço de mensageria.

Um cluster Kafka possui vários brokers. Um broker ou nó é um servidor kafka que recebe a mensagem dos produtores e armazena as mensagens
em disco com uma chave exclusiva de offset. Um broker do Kafka permite que os consumidores
busquem a mensagem por tópico, consumer group, partição e offset. Brokers fazem parte
de um cluster compartilhando informações entre si direta ou indiretamente,
sendo que um dos brokers atua como controlador(controller).

Para gerenciar os brokers de Kafka temos o Zookeeper que armazena todos os metadados dos cluster,
partições, nomes tópicos e os nós disponíveis, além de manter a sincronização entre os clusters.
Em caso de queda de algum cluster o Zookeeper elege o próximo cluster que irá substituir.

![Imagem7](src/main/resources/img/img7.png)

#Acesso Sequencial ao Disco
O Kafka trabalha com gravação e leitura sequencial no disco para garantir que há perda de dados
caso acontece algum desligado acidental da máquina. O acesso sequencial permite que o Kafka saiba onde 
começa e termina cada bloco de mensagens.

 
# Ferramentas semelhantes

Existe outras ferramentas similares ao Kafka, como :

ActiveMq;
RabbitMq;

Sendo que cada uma possui as suas caracteristicas especificas.



O que aprendemos nessa aula:

O que são produtores
O que são consumidores
Criação de tópicos manualmente
Como instalar e rodar o Kafka


Como rodar diversos consumidores no mesmo grupo
Como paralelizar tarefas
A importância da chave para hash - A chave defini em qual partição o kafka irá postar a mensagem
Cuidado com poll longo


O que aprendemos nessa aula:

A importância de evitar copy e paste
Criando nossa camada de abstração
Criando nosso Dispatcher
Criando nosso Service


Como limpar os diretórios de log e dados do zookeeper e kafka
Como utilizar diretórios não temporátios para o zookeeper e kafka
Como utilizar o GSON
Criando um serializador customizado do Kafka
Verificar o conteúdo exato de uma mensagem em um programa
Deserialização customizada
Lidando com customização por serviço

O que aprendemos nessa aula:

como criar módulos
como manter tudo em um mono repo
como gerenciar dependências entre módulos
como gerar os binários de cada módulo