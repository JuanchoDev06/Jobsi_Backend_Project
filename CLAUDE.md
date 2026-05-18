# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Jobsy is a Spring Boot 3.5 / Java 21 REST backend for a job-matching platform, built as an academic exercise in Clean / Hexagonal Architecture. Code, comments, and domain names are in Spanish; keep new code consistent with that.

## Commands

The project uses the Maven Wrapper (`mvnw` / `mvnw.cmd`) — no global Maven needed. On Windows use `mvnw.cmd`.

```bash
# Run
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev    # H2 in-memory
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=prod   # PostgreSQL

# Build
mvnw.cmd package                 # build jar
mvnw.cmd package -DskipTests     # skip tests

# Tests
mvnw.cmd test                                  # all tests
mvnw.cmd test -Dtest=JobsyApplicationTests     # single test class

# Docker (builds image + Postgres, prod profile)
docker compose up --build
```

App runs on `http://localhost:8080`. Swagger UI at `/swagger-ui.html`, H2 console at `/h2-console` (dev only), Actuator under `/internal`.

## Configuration

`application.properties` references env vars (`SPRING_PROFILE`, `SERVER_PORT`, `BD_*`, `CORS_*`) supplied by the `.env` file or Docker Compose. Two profiles:
- **dev** — H2 in-memory (`ddl-auto=create-drop`), Flyway disabled.
- **prod** — PostgreSQL (`ddl-auto=create-drop`), Flyway disabled.

Flyway and the MySQL/Oracle drivers are declared but currently unused; the active DB is PostgreSQL (prod) / H2 (dev). `DataInitializer` seeds reference data only under the `prod` profile.

## Architecture

Hexagonal layering under `com.escaes.jobsy`. A request flows: **controller → use case → domain repository (port) → JPA adapter → Spring Data repository**.

- **`domain/`** — framework-agnostic core. `domain/model/` holds immutable Java `record`s (e.g. `Categoria`, accessed as `categoria.id()`/`categoria.nombre()`). `domain/repository/` holds repository **interfaces (ports)** that the domain depends on.
- **`application/`** — `usecase/` business logic organized per aggregate (categoria, estado, genero, institucion, pago, rol, solicitud, trabajo, ubicacion, usuario). Each aggregate typically has a `Gestion*UseCase` (writes/lookups) and a `Listar*UseCase` (reads). `dto/` holds `Request`/`Response` records. Validation lives in use cases, throwing `IllegalArgumentException`.
- **`infraestructure/`** (note the spelling) — outward adapters:
  - `rest/controller/` — thin `@RestController`s under base path `/v1`; delegate directly to use cases. `rest/exception/` has global handlers.
  - `adapter/` — `Jpa*RepositoryAdapter` classes implement the domain repository ports.
  - `jpa/` — `SpringData*Repository` Spring Data interfaces.
  - `persistence/entity/` — JPA `@Entity` classes (separate from domain records).
  - `mapper/` — static mappers converting between domain records, JPA entities, and DTOs.
- **`config/`** — Spring config: `SecurityConfig` (Spring Security + JWT), `jwt/` (`JwtAuthFilter`, `JwtProvider`), `CorsConfig`, `SwaggerConfig`, `DataInitializer`.

Key rule: there is no separate "adapter" indirection between controllers and use cases — controllers call use cases directly. Domain code must not import Spring or JPA types; that separation is enforced by keeping ports in `domain/repository/` and implementations in `infraestructure/adapter/`.

When adding a new aggregate, follow the existing per-aggregate pattern across all layers: domain record + repository port, use cases, DTOs, JPA entity + Spring Data repo + adapter, mapper, and controller.

## Security

Stateless JWT auth via `JwtAuthFilter`. `/auth/**`, Swagger, `/h2-console/**`, `/v1/public/**`, and `/internal/**` are public; other `/v1/**` paths require roles (`USER` / `ADMIN`) as configured in `SecurityConfig`.

## Conventions

- Branches: `main` (stable), `dev` (integration), `feature/*`, `fix/*`.
- Commits follow Conventional Commits (`feat:`, `fix:`, `refactor:`, `test:`, `docs:`).
- PRs target `main` and need at least one approval.
