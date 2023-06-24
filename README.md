# Denuncie Agora

# Sobre
O projeto é um aplicativo de denúncia anônima contra crimes de ódio. Nosso objetivo é oferecer uma plataforma segura e de fácil acesso para que pessoas que não desejam se expor possam fazer suas denúncias. Além disso, buscamos conscientizar aqueles que não vivenciaram esses crimes e fornecer apoio às vítimas, evitando que passem por situações semelhantes novamente.

Visamos ser uma referência em denúncias anônimas, proporcionando um espaço confiável e acessível para relatar incidentes de crimes de ódio. Acreditamos que ao fornecer essa plataforma, estamos contribuindo para a construção de uma sociedade mais consciente e igualitária.

# Por que?
O projeto teve início durante as aulas de projeto de vida, onde foi abordada a importância da conscientização. No primeiro passo, apresentamos slides e discutimos temas relevantes. No segundo passo, enfrentamos o desafio de criar um projeto que envolvesse a comunidade escolar. Escolhemos o tema do racismo e decidimos desenvolver um aplicativo de denúncias anônimas relacionadas a esse problema. Conforme avançamos no desenvolvimento, percebemos que poderíamos expandir a funcionalidade do aplicativo para abranger outros crimes de ódio.

Nossa motivação principal é contribuir ativamente para a conscientização e combate ao racismo e outros crimes de ódio. Acreditamos no papel fundamental da tecnologia nessa luta, oferecendo uma plataforma segura e acessível para denúncias anônimas. Queremos proporcionar um ambiente onde as vítimas desses crimes possam se expressar e buscar justiça, ao mesmo tempo em que promovemos a sensibilização da comunidade e a educação sobre essas questões.

Acreditamos firmemente que todos têm o direito de viver em um mundo livre de discriminação e violência, e estamos comprometidos em fazer nossa parte para alcançar essa realidade. Com este projeto, esperamos encorajar outras pessoas a se engajarem na luta contra o racismo e os crimes de ódio, promovendo um ambiente mais inclusivo e igualitário para todos.

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Lombok
- Flyway
- Javax Validation
- JUnit / Mockito
- Maven

## Android Nativo
- Kotlin
- Retrofit
- Dagger / Hilt
- Lottie
- Gradle

## Implantação 
- Back end: Railway
- Banco de dados: PostgreSQL

# Como executar o projeto

## Back end
Pré-requisitos: Java 17

```bash
# Clonar o repositório
git clone https://github.com/jaomanoel/Denuncie-Agora

# Entrar na pasta do projeto back end
cd ./backend

# executar o projeto
./mvnw spring-boot:run
```
## Android Nativo
Pré-requisitos: SDK do Android 33

```bash
# Clonar o repositório
git clone https://github.com/jaomanoel/Denuncie-Agora

# Entrar na pasta do projeto Android
cd ./DenuncieAgora

# Executar o projeto com o Gradle
./gradlew installDebug

# Executar o projeto com o comando adb
adb install -r app/build/outputs/apk/debug/app-debug.apk
```
# Autor

João Manoel Vieira Neto

https://www.linkedin.com/in/jaomanoel/
