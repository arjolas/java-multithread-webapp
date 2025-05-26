# Java Multithread WebApp

 **Descrizione:**  
Questa è una web application Java basata su **Spring Boot** che sfrutta la programmazione **multithread** per elaborare dati in parallelo. Attualmente elabora blocchi di dati (simulati o reali da un database) e genera report aggregati.

---

##  **Funzionalità principali**
-  API REST con **Spring Boot**.
-  Elaborazione parallela dei dati tramite **ExecutorService**.
-  Integrazione con **PostgreSQL** (o altro database relazionale).
-  Simulazione di parsing e calcoli statistici sui dati.
-  Chiusura sicura delle risorse (`@PreDestroy`).

---

## 🚀 **Come avviare l'applicazione**

Clona il repository:
```bash
git clone https://github.com/arjolas/java-multithread-webapp.git
cd java-multithread-webapp

Costruisci il progetto:

bash
Copia
Modifica
mvn clean install

Avvia l'applicazione:

bash
Copia
Modifica
mvn spring-boot:run

Endpoint API
Metodo	URL	Descrizione
GET	/api/report	Genera un report con elaborazioni parallele.

Configurazione database
Modifica src/main/resources/application.properties:

properties
Copia
Modifica
spring.datasource.url=jdbc:postgresql://localhost:5432/tuo_db
spring.datasource.username=tuo_username
spring.datasource.password=tuo_password
