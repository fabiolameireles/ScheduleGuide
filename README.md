# ScheduleGuide 

<h4 align="center">  
:construction:  Projeto em construção  :construction: 
</h4>

O ScheduleGuide é um sistema pensado para ajudar estudantes no planejamento e execução de suas sessões de estudo, por meio da visualização dos objetivos e sugestão de cronogramas.

Esse projeto se trata de um trabalho acadêmico da disciplina Projeto Integrado I, realizado pelos integrantes do grupo abaixo.
- [Fabiola Meireles Vilaça](https://github.com/fabiolameireles)
- [Igor Wandekochen Bittencourt](https://github.com/IWBittencourt)
- [Rafael Vieira de Almeida](https://github.com/rvieira1001)

## Conteúdo
- [Visão geral](#visão-geral)
- [Arquitetura de Projeto](#arquitetura-de-projeto)
- [Uso do sistema](#uso-do-sistema)
- [Licença](#licença)

## Visão geral
O **ScheduleGuide** oferece uma aplicação que reúne conteúdos, horários disponíveis e cronogramas em um ambiente que facilita o planejamento, execução e análise de sessões de estudo. Ela é hospedada localmente e voltada para uso individual, mas suporta o uso remoto por meio da internet.

O Diagrama de Classes do domínio do sistema está disponível em `./Diagrama-PI1.asta`, e representado abaixo:

<img width="1185" height="801" alt="Diagrama de Classes do domínio do sistema ScheduleGuide" src="https://github.com/user-attachments/assets/2a07ddca-bf89-48d6-8d07-0abd65e51a06" />

### Funcionalidades
- **Registrar conteúdo:** O sistema permite que o usuário registre os conteúdos que deseja estudar com os recursos de apoio cabíveis. Os conteúdos integram tópicos, que podem ser organizados em categorias.
- **Registrar disponibilidade:** O sistema permite que o usuário registre em um calendário suas janelas de disponibilidade.
- **Criação de um cronograma:** A partir dos dados registrados, o sistema sugere um cronograma. Detalhes adicionais auxiliam a moldar o resultado às necessidades.
- **Ambiente de estudos:** O sistema apresenta um ambiente de estudo que minimiza distrações, exibindo apenas o tempo restante para cada fase (entre foco e pausa) e uma referência para o conteúdo atual.
- **Acompanhamento do progresso:** O sistema disponibiliza ao usuário seus dados de uso, de modo que possa avaliar seu desempenho ao longo do tempo.

## Arquitetura de Projeto
O ScheduleGuide é desenvolvido em **Java** e **JavaScript**.

O desenvolvimento do projeto é apoiado pelas seguintes ferramentas:
- **GitHub:** desenvolvimento em paralelo e versionamento dos artefatos produzidos.
- **Maven:** gerenciamento das dependências, desde a padronização das instalações até sua implantação.
- **JUnit & Mockito:** realização de testes de unidade.

Além disso, são reutilizados *frameworks* que fornecem recursos úteis para concretizar a aplicação.
- **Spring Boot:** configuração e gestão da infraestrutura do projeto.
- **Spring Web:** veiculação do sistema como uma plataforma Web, almejando primeiramente o acesso via desktop.
- **Spring Data JPA & H2 Database:** criação e manutenção de um banco de dados.
- **Bootstrap:** design das interfaces.
- **Thymeleaf:** reutilização e padronização dos elementos das páginas.
- **Axios:** tratamento de promessas e simplificação da sintaxe.

## Uso do Sistema
Para utilizar o sistema, a máquina deve possuir os seguintes aplicativos:
- Java 25
- Maven

O sistema pode ser adquirido ao clonar o projeto:
```bash
git clone https://github.com/fabiolameireles/ScheduleGuide.git
```
Após baixar o repositório, é necessário trocar o diretório para sua raiz a fim de executar comandos:
```bash
cd caminho/ate/ScheduleGuide
```

### Documentação
O código fonte do domínio do sistema foi documentado conforme os padrões JavaDoc. Portanto, é possível gerar páginas em HTML que descrevem as características e comportamento das classes geradas.  Para gerar a documentação JavaDoc, deve-se executar o seguinte comando:
```bash
mvn javadoc:javadoc
```
Uma vez gerada, o acesso à documentação é feito ao abrir o arquivo em `./target/reports/apidocs/index.html`, disponível mesmo sem executar o sistema.

### Execução
O sistema entra em funcionamento ao executar o seguinte comando:
```bash
./mvnw spring-boot:run
```
Por padrão, o servidor se liga à porta 8080 da máquina e sua página inicial está disponível [localhost:8080/index.html](localhost:8080/index.html).

## Licença
Este programa é distribuído sob a licença do [MIT](https://choosealicense.com/licenses/mit/). Veja `LICENSE` para mais informações.
