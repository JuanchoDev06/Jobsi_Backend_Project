#  Docker Compose — Jobsy Backend

Guía para entender y ejecutar el entorno de contenedores del proyecto.

---

## ¿Qué es Docker Compose?

Docker Compose es una herramienta que permite definir y ejecutar múltiples contenedores como un solo servicio. En lugar de levantar cada contenedor manualmente, se describe todo en un archivo `docker-compose.yml` y se arranca con un solo comando.

---

## Arquitectura del proyecto

El `docker-compose.yml` de este proyecto define **dos servicios**:

```
┌─────────────────────────────────────────┐
│              Docker Network             │
│                                         │
│  ┌──────────────┐   ┌────────────────┐  │
│  │   jobsy-app  │──▶│   jobsy-db     │  │
│  │ Spring Boot  │   │  PostgreSQL 16 │  │
│  │  Port 8080   │   │   Port 5432    │  │
│  └──────────────┘   └────────────────┘  │
└─────────────────────────────────────────┘
```

### 🗄️ Servicio: `postgres` (jobsy-db)

| Propiedad        | Valor                        |
|------------------|------------------------------|
| Imagen           | `postgres:16`                |
| Puerto           | `5432:5432`                  |
| Base de datos    | `jobsy`                      |
| Usuario          | `jobsy_user`                 |
| Contraseña       | `jobsy_pass`                 |
| Persistencia     | Volumen `postgres_data`      |

Los datos de la base de datos se guardan en un **volumen Docker** llamado `postgres_data`, por lo que persisten aunque el contenedor se detenga o reinicie.

###  Servicio: `app` (jobsy-app)

| Propiedad         | Valor                                      |
|-------------------|--------------------------------------------|
| Build             | Desde el `Dockerfile` en la raíz del proyecto |
| Puerto            | `8080:8080`                                |
| Perfil de Spring  | `prod`                                     |
| URL de la DB      | `jdbc:postgresql://postgres:5432/jobsy`    |
| Depende de        | `postgres` (espera que la DB esté lista)   |

>  La app usa el nombre del servicio `postgres` como host, no `localhost`. Esto funciona porque ambos contenedores están en la misma red de Docker.

---

##  Variables de entorno

### jobsy-db
```env
POSTGRES_DB=jobsy
POSTGRES_USER=jobsy_user
POSTGRES_PASSWORD=jobsy_pass
```

### jobsy-app
```env
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
BD_URL=jdbc:postgresql://postgres:5432/jobsy
BD_USERNAME=jobsy_user
BD_PASSWORD=jobsy_pass
CORS_ALLOWED_ORIGINS=*
```

---

##  Cómo ejecutar el proyecto

### 1. Requisitos previos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y corriendo
- Estar en la raíz del proyecto (`Jobsi_Backend_Project`)

### 2. Primera vez (build completo)

```bash
docker-compose up --build
```

Esto compilará el proyecto con Maven, generará el JAR y levantará ambos contenedores.

### 3. Iniciar sin recompilar

```bash
docker-compose up
```

Usa las imágenes ya construidas anteriormente.

### 4. Iniciar en segundo plano

```bash
docker-compose up -d
```

Los contenedores corren en background y liberan la terminal.

### 5. Forzar rebuild sin caché

Útil cuando hay cambios en el código o en el Dockerfile:

```bash
docker-compose down
docker-compose build --no-cache
docker-compose up
```

---

##  Cómo detener el proyecto

```bash
# Detener los contenedores (sin borrar datos)
docker-compose down

# Detener y eliminar volúmenes (borra la base de datos)
docker-compose down -v
```

---

##  Comandos útiles para depuración

```bash
# Ver logs de la app en tiempo real
docker logs jobsy-app --follow

# Ver logs de la base de datos
docker logs jobsy-db --tail 50

# Entrar al contenedor de la app
docker exec -it jobsy-app sh

# Conectarse a la base de datos
docker exec -it jobsy-db psql -U jobsy_user -d jobsy

# Ver estado de los contenedores
docker ps
```

---

##  Verificar que todo funciona

Una vez levantados los contenedores, verifica que la API responde:

```bash
# Desde navegador o Postman
GET http://localhost:8080/

# Desde terminal (PowerShell)
Invoke-WebRequest -Uri http://localhost:8080
```

Si ves una respuesta del servidor, el backend está corriendo correctamente. 🎉

---

##  Estructura de archivos relacionados

```
Jobsi_Backend_Project/
├── docker-compose.yml     # Define los servicios (app + db)
├── Dockerfile             # Instrucciones para construir la imagen de la app
├── pom.xml                # Dependencias Maven
└── src/                   # Código fuente de Spring Boot
```