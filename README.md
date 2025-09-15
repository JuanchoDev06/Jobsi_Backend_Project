# Jobsy — Clean Architecture (Spring Boot)

![Java](https://img.shields.io/badge/Java-17+-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Build-Maven-yellow)
![MySQL](https://img.shields.io/badge/DB-MySQL-informational)
![Status](https://img.shields.io/badge/estado-en%20desarrollo-orange)

Proyecto académico para practicar y aplicar **Arquitectura Limpia / Hexagonal** con **Spring Boot**, **JPA** y **MySQL**. El objetivo es aprender a separar dominios, puertos/adaptadores y casos de uso, manteniendo el núcleo de negocio independiente de frameworks.

---

##  Objetivos de aprendizaje

- Modelar el **dominio** y aislar la lógica de negocio de los detalles de infraestructura.
- Implementar **puertos** (interfaces) y **adaptadores** (implementaciones) siguiendo el estilo hexagonal.
- Persistir con **JPA**/**Hibernate** sobre **MySQL** manteniendo el dominio desacoplado.
- Exponer **APIs REST** con controladores delgados (thin controllers) y casos de uso ricos.
- Empaquetar con **Maven** (mvnw) y preparar bases para pruebas unitarias/integración.

---

##  Arquitectura (Clean Adaptada)

En este proyecto **no se usan adapters explícitos**, los controladores llaman directamente a los **use cases**. La estructura es:

- **Domain (Core):** entidades, value objects, reglas de negocio y servicios de dominio.
- **Application:** casos de uso (con carpetas organizadas por agregado: categoría, estado, género, etc.), DTOs y servicios de aplicación.
- **Infraestructura mínima:** configuración de Spring Boot, repositorios JPA y mapeo entidad ↔ dominio.
- **Interfaces REST:** controladores que delegan en los **use cases**.

> Beneficio: el **dominio no conoce** detalles de frameworks ni de infraestructura; esto facilita pruebas, mantenibilidad y cambios tecnológicos.

---

##  Estructura del proyecto

> _Ajusta nombres/paquetes a tu código actual. Esta estructura es compatible con Clean Architecture y Spring Boot._

```
src/
 └─ main/
     ├─ java/
     │   └─ com.example.jobsy/
     │       ├─ JobsyApplication.java
     │       ├─ config/                 # Configuración Spring (Beans, mappers, etc.)
     │       ├─ domain/                 # Núcleo de negocio
     │       │   ├─ model/              # Entidades y Value Objects
     │       │   ├─ service/            # Servicios de dominio (si aplican)
     │       │   ├─ repository/         # Repositorios y reglas de negocio
     │       │   └─ exception/
     │       ├─ application/            # Casos de uso
     │       │   ├─ usecase/            # Implementaciones de casos de uso
     │       │   ├─ service/            # Servicios exclusivos de la app
     │       └─ infrastructure/         # Adaptadores
     │           ├─ persistence/        # JPA entities, Spring Data repos (adapters)
     │           │   ├─ entity/
     │           │   └─ repository/
     │           ├─ mapper/             # Mapstruct o mapeos manuales
     │           ├─ web/                # Controllers REST (adapters in)
     │           └─ config/             # Config de infra (datasource, security, etc.)
     └─ resources/
         ├─ application.properties
         ├─ application-dev.properties
         └─ db/
            └─ migration/               # Flyway
```

---

##  Stack

- **Lenguaje:** Java (17+ recomendado)
- **Framework:** Spring Boot 3.x (Web, Validation)
- **Persistencia:** Spring Data JPA, Hibernate, MySQL
- **Build:** Maven (incluye `mvnw`/`mvnw.cmd`)
- **Testing:** JUnit 5, Mockito (sugerido)

> El repositorio está en **Java** y usa **Maven Wrapper**, por lo que puedes ejecutar sin instalar Maven globalmente.

---

##  Puesta en marcha (Local)

### 1) Requisitos

- **Java 17+**
- **MySQL 8+** (o compatible)
- **Git**

### 2) Clonar

```bash
git clone https://github.com/caes2004/jobsy-clean-arch.git
cd jobsy-clean-arch
```

### 3) Configuración de base de datos

Este proyecto soporta dos perfiles:

dev → crea automáticamente una base de datos en memoria (H2) con spring.jpa.hibernate.ddl-auto=create-drop. Ideal para probar directamente sin configurar nada.

prod → conecta a MySQL (requiere crear la BD manualmente).

Ejemplo de configuración en application-dev.properties:

spring.datasource.url=jdbc:h2:mem:jobsydb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.h2.console.enabled=true


Ejemplo de configuración en application-prod.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/jobsy
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update



### 4) Ejecutar

Con Maven Wrapper (sin instalar Maven):

```bash
# compilar y correr
./mvnw spring-boot:run
# Windows
mvnw.cmd spring-boot:run


Con perfil dev (H2):

./mvnw spring-boot:run -Dspring-boot.run.profiles=dev


Con perfil prod (MySQL):

./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```
La app quedará en: `http://localhost:8080`.

---


##  Swagger (API Docs)

La documentación de la API está disponible al levantar el proyecto en:

 http://localhost:8080/swagger-ui.html

---

##  Guía de contribución (para equipo)

1. **Ramas**
    - `main`: estable.
    - `dev`: rama de integración para desarrollo activo.
    - `feature/*`: desarrollo de features.
    - `fix/*`: correcciones.
2. **Commits**
    - Sigue **Conventional Commits**: `feat: …`, `fix: …`, `refactor: …`, `test: …`, `docs: …`.
3. **PRs**
    - Incluye descripción, checklist y evidencia (tests/local).
4. **Code style**
    - Recomendado: Checkstyle/Spotless + EditorConfig.
5. **Revisiones**
    - Al menos 1 aprobación antes de merge.

---


##  Licencia

Este proyecto es académico.

---

##  Autor

- **Esteban Cano (caes2004)** — mantenedor del proyecto.

---

##  Notas

- Proyecto **Java** con **Maven Wrapper** y enfoque educativo en **Clean Architecture** sobre **Spring Boot/JPA/MySQL**.
