# ScheduleGuide 

<h4 align="center">  
:construction:  Projeto em construção  :construction: 
</h4>

## Componentes
- [Fabiola Meireles Vilaça](https://github.com/fabiolameireles)
- [Igor Wandekochen Bittencourt](https://github.com/IWBittencourt)
- [Rafael Vieira de Almeida](https://github.com/rvieira1001)

## Conteúdo
- [Visão geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Ferramentas utilizadas](#ferramentas-escolhidas-git-build-testes-issue-tracking-cicd-container)
- [Frameworks reutilizados](#frameworks-reutilizados)
- [Documentação](#como-gerar-a-documentação-do-código-ex-javadoc)
- [Execução do sistema](#execução-do-sistema)


## Visão geral
O ScheduleGuide é um sistema pensado para ajudar estudantes no planejamento e execução de suas sessões de estudo, por meio da visualização dos objetivos e sugestão de cronogramas.

O projeto é desenvolvido em Java 25 e JavaScript. Uma vez em execução, a máquina se torna o servidor de acesso via Web. Para manter os dados em memória secundária, ele utiliza um banco de dados SQL.
<img width="1185" height="801" alt="DiagramaClasses-ScheduleGuide-ExportPNG" src="https://github.com/user-attachments/assets/2a07ddca-bf89-48d6-8d07-0abd65e51a06" />

## Funcionalidades
- **Registrar conteúdo:** O sistema permite que o usuário registre os conteúdos que deseja estudar com os recursos de apoio cabíveis e preencher um calendário com suas janelas de disponibilidade.
- **Registrar disponibilidade:** O sistema permite que o usuário registre em um calendário suas janelas de disponibilidade.
- **Criação de um cronograma:** A partir dos dados registrados, o sistema sugere um cronograma.
- **Ambiente de estudos:** O sistema apresenta um ambiente de estudo que minimiza distrações, exibindo apenas o tempo restante para cada fase (entre foco e pausa) e uma referência para o conteúdo atual

## Ferramentas escolhidas
- **Maven:** gerenciamento das dependências escolhidas, bem como padronização da sua implantação.
- **Spring Boot:** veiculação do sistema como uma plataforma Web, almejando primeiramente o acesso em desktop.
- **GitHub:** versionamento dos artefatos produzidos.
- **JUnit & Mockito:** realização de testes de unidade.


## Frameworks reutilizados
- **Spring Web:**
- **Spring Data JPA & H2 Database:** criação e manutenção de um banco de dados.
- **Bootstrap:** design das interfaces.
- **Thymeleaf:** reutilização e padronização dos elementos das páginas.
- **Axios:** tratamento as requisições.

## Como gerar a documentação do código (ex.: JavaDoc)

## Execução do sistema
### Maven Commands
```bash
./mvnw spring-boot:run`
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
