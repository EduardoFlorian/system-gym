# System Gym 🏋️

**Sistema de Gestión de Gimnasio** - Una aplicación backend desarrollada con Spring Boot para administrar actividades, entrenamientos, suscripciones e inscripciones en un centro de fitness.

## 📋 Tabla de Contenidos

### Información General
- [Descripción](#descripción)
- [Características Principales](#características-principales)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Estructura del Proyecto](#estructura-del-proyecto)

### Guía de Uso
- [Uso](#uso)

### Endpoints API
- [Endpoints Disponibles](#endpoints-disponibles)

#### Partners (Socios)
- [`POST /partners/save`](#post-partnerssave-crear-nuevo-socio)
- [`GET /partners/list`](#get-partnerslist-obtener-todos-los-socios)
- [`GET /partners/find/{id}`](#get-partnersfindid-obtener-socio-por-id)
- [`PUT /partners/update/{id}`](#put-partnersupdateid-actualizar-información-del-socio)

#### Actividades
- [`POST /activities/save`](#post-activitiessave-crear-nueva-actividad-con-horarios)

#### Entrenamientos (Trainers)
- [`POST /trainers/save`](#post-trainerssave-crear-nuevo-entrenador)
- [`GET /trainers/list`](#get-trainerslist-obtener-todos-los-entrenadores)
- [`GET /trainers/find/{id}`](#get-trainersfindid-obtener-entrenador-por-id)
- [`PUT /trainers/update/{id}`](#put-trainersupdateid-actualizar-información-del-entrenador)

#### Planes de Suscripción
- [`POST /plans/save`](#post-planssave-crear-nuevo-plan-de-suscripción)
- [`GET /plans/list`](#get-planslist-obtener-todos-los-planes)
- [`GET /plans/find/{id}`](#get-plansfindid-obtener-plan-por-id)
- [`PUT /plans/update/{id}`](#put-plansupdateid-actualizar-plan-de-suscripción)

#### Suscripciones
- [`POST /subscriptions/save`](#post-subscriptionssave-crear-nueva-suscripción-para-socio)
- [`GET /subscriptions/list`](#get-subscriptionslist-obtener-todas-las-suscripciones)
- [`GET /subscriptions/find/{id}`](#get-subscriptionsfindid-obtener-suscripción-por-id)
- [`POST /subscriptions/cancel`](#post-subscriptionscancel-cancelar-suscripción)

#### Inscripciones en Actividades
- [`POST /inscriptions/save`](#post-inscriptionssave-inscribir-socio-en-una-o-más-actividades)

#### Códigos HTTP
- [Códigos de Respuesta HTTP](#códigos-de-respuesta-http)

### Documentación Técnica
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Dependencias Principales](#dependencias-principales)

### Desarrollo y Soporte
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Recursos Útiles](#recursos-útiles)
- [Contribuir](#contribuir)

### Información Adicional
- [Licencia](#licencia)
- [Contacto](#contacto)

---

## 📝 Descripción

System Gym es una aplicación backend REST API que permite gestionar:

- **Miembros (Partners)**: Registro y administración de socios del gimnasio
- **Actividades**: Creación de clases y entrenamientos con horarios
- **Entrenadores**: Gestión de instructores asignados a actividades
- **Suscripciones**: Diferentes planes de membresía con duraciones variables
- **Inscripciones**: Control de inscritos en actividades con validación de capacidad
- **Horarios**: Gestión de fechas y horas de las actividades

---

## ✨ Características Principales

✅ **CRUD Completo** - Operaciones Create, Read, Update, Delete para todas las entidades

✅ **Validación de Datos** - Validaciones a nivel de campos con mensajes personalizados

✅ **Gestión de Horarios** - Detección de conflictos de horarios en actividades

✅ **Control de Capacidad** - Validación automática de cupos disponibles en inscripciones

✅ **Tareas Programadas** - Procesamiento automático de suscripciones vencidas (Scheduled Tasks)

✅ **Manejo Global de Excepciones** - Sistema centralizado de tratamiento de errores

✅ **DTO Pattern** - Separación de capas con Data Transfer Objects

✅ **Mapeo de Entidades** - Uso de ModelMapper para conversión automática

---

## 🔧 Requisitos Previos

- **Java**: 21 o superior
- **Maven**: 3.8.0 o superior
- **PostgreSQL**: 12 o superior
- **Git**: Para clonar el repositorio

### Verificar instalación

```bash
java -version
mvn -version
psql --version
```

---

## 💾 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/EduardoFlorian/system-gym.git
cd system-gym
```

### 2. Crear la base de datos PostgreSQL

```bash
psql -U postgres

CREATE DATABASE system_gym;
```

### 3. Instalar dependencias

```bash
mvn clean install
```

### 4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

---

## ⚙️ Configuración

### Archivo: `application.properties`

Las siguientes propiedades deben configurarse antes de ejecutar:

```properties
# Nombre de la aplicación
spring.application.name=system-gym

# Configuración PostgreSQL
spring.jpa.database=postgresql
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Conexión a Base de Datos
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/system_gym
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
```

**⚠️ Importante**: No incluyas credenciales en repositorios. Usa variables de entorno:

```bash
export DB_USERNAME=postgres
export DB_PASSWORD=tu_contraseña
```

---

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/systemgym/systemgym/
│   │   ├── controller/          # Endpoints REST
│   │   ├── service/             # Lógica de negocio
│   │   │   └── implement/       # Implementaciones de servicios
│   │   ├── model/               # Entidades JPA
│   │   ├── repository/          # Interfaces de acceso a datos
│   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── request/         # DTOs para entrada
│   │   │   └── response/        # DTOs para salida
│   │   ├── mapper/              # Convertidores de entidades
│   │   ├── exception/           # Excepciones personalizadas
│   │   ├── configuration/       # Configuraciones (ej: ModelMapper)
│   │   ├── task/                # Tareas programadas
│   │   └── SystemGymApplication.java  # Clase principal
│   └── resources/
│       └── application.properties     # Configuración
└── test/
    └── java/com/systemgym/systemgym/ # Tests unitarios
```

---

## 🚀 Uso

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar tests

```bash
mvn test
```

### Generar JAR ejecutable

```bash
mvn clean package
```

El JAR se generará en `target/system-gym-0.0.1-SNAPSHOT.jar`

### Ejecutar desde JAR

```bash
java -jar target/system-gym-0.0.1-SNAPSHOT.jar
```

---

## 🔌 Endpoints Disponibles

### Partners (Socios)

#### `POST /partners/save`
Crear nuevo socio

**Request:**
```json
{
  "firstName": "Juan",
  "lastName": "Pérez García",
  "email": "juan.perez@example.com",
  "phoneNumber": "987654321"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez García",
  "email": "juan.perez@example.com",
  "phoneNumber": "987654321",
  "registrationDate": "2026-03-02T10:30:00",
  "active": true
}
```

#### `GET /partners/list`
Obtener todos los socios

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez García",
    "email": "juan.perez@example.com",
    "phoneNumber": "987654321",
    "registrationDate": "2026-03-02T10:30:00",
    "active": true
  }
]
```

#### `GET /partners/find/{id}`
Obtener socio por ID

**Response (200 OK):**
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez García",
  "email": "juan.perez@example.com",
  "phoneNumber": "987654321",
  "registrationDate": "2026-03-02T10:30:00",
  "active": true
}
```

#### `PUT /partners/update/{id}`
Actualizar información del socio

**Request:**
```json
{
  "firstName": "Juan Carlos",
  "lastName": "Pérez García",
  "email": "juan.carlos@example.com",
  "phoneNumber": "912345678"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "firstName": "Juan Carlos",
  "lastName": "Pérez García",
  "email": "juan.carlos@example.com",
  "phoneNumber": "912345678",
  "registrationDate": "2026-03-02T10:30:00",
  "active": true
}
```

---

### Actividades

#### `POST /activities/save`
Crear nueva actividad con horarios

**Request:**
```json
{
  "capacity": 20,
  "description": "Yoga Avanzado",
  "startDate": "2026-03-05",
  "endDate": "2026-06-30",
  "idTrainer": 1,
  "schedules": [
    {
      "dayOfWeekSchedule": "MONDAY",
      "startTime": "09:00:00",
      "endTime": "10:00:00"
    },
    {
      "dayOfWeekSchedule": "WEDNESDAY",
      "startTime": "09:00:00",
      "endTime": "10:00:00"
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "capacity": 20,
  "description": "Yoga Avanzado",
  "startDate": "2026-03-05",
  "endDate": "2026-06-30",
  "trainer": {
    "id": 1,
    "firstName": "Carlos",
    "lastName": "Rodríguez",
    "specialty": "Yoga"
  },
  "schedules": [
    {
      "dayOfWeekSchedule": "MONDAY",
      "startTime": "09:00:00",
      "endTime": "10:00:00"
    },
    {
      "dayOfWeekSchedule": "WEDNESDAY",
      "startTime": "09:00:00",
      "endTime": "10:00:00"
    }
  ]
}
```

---

### Entrenamientos (Trainers)

#### `POST /trainers/save`
Crear nuevo entrenador

**Request:**
```json
{
  "firstName": "Carlos",
  "lastName": "Rodríguez",
  "specialty": "Yoga"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "firstName": "Carlos",
  "lastName": "Rodríguez",
  "specialty": "Yoga"
}
```

#### `GET /trainers/list`
Obtener todos los entrenadores

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "firstName": "Carlos",
    "lastName": "Rodríguez",
    "specialty": "Yoga"
  }
]
```

#### `GET /trainers/find/{id}`
Obtener entrenador por ID

**Response (200 OK):**
```json
{
  "id": 1,
  "firstName": "Carlos",
  "lastName": "Rodríguez",
  "specialty": "Yoga"
}
```

#### `PUT /trainers/update/{id}`
Actualizar información del entrenador

**Request:**
```json
{
  "firstName": "Carlos",
  "lastName": "Rodríguez García"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "firstName": "Carlos",
  "lastName": "Rodríguez García",
  "specialty": "Yoga"
}
```

---

### Planes de Suscripción

#### `POST /plans/save`
Crear nuevo plan de suscripción

**Request:**
```json
{
  "name": "Plan Mensual",
  "description": "Acceso ilimitado por 1 mes",
  "price": 99.99,
  "idDuration": 1
}
```

**Response (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Plan Mensual",
  "description": "Acceso ilimitado por 1 mes",
  "price": 99.99,
  "duration": {
    "id": 1,
    "name": "1 Mes",
    "durationDays": 30
  }
}
```

#### `GET /plans/list`
Obtener todos los planes

**Response (200 OK):**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Plan Mensual",
    "description": "Acceso ilimitado por 1 mes",
    "price": 99.99,
    "duration": {
      "id": 1,
      "name": "1 Mes",
      "durationDays": 30
    }
  }
]
```

#### `GET /plans/find/{id}`
Obtener plan por ID

**Response (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Plan Mensual",
  "description": "Acceso ilimitado por 1 mes",
  "price": 99.99,
  "duration": {
    "id": 1,
    "name": "1 Mes",
    "durationDays": 30
  }
}
```

#### `PUT /plans/update/{id}`
Actualizar plan de suscripción

**Request:**
```json
{
  "price": 109.99
}
```

**Response (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Plan Mensual",
  "description": "Acceso ilimitado por 1 mes",
  "price": 109.99,
  "duration": {
    "id": 1,
    "name": "1 Mes",
    "durationDays": 30
  }
}
```

---

### Suscripciones

#### `POST /subscriptions/save`
Crear nueva suscripción para socio

**Request:**
```json
{
  "idPartner": 1,
  "idPlan": "550e8400-e29b-41d4-a716-446655440000"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "startDate": "2026-03-02T10:30:00",
  "endDate": "2026-04-01T10:30:00",
  "statusSubscription": "ACTIVE",
  "partner": {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez García",
    "email": "juan.perez@example.com",
    "phoneNumber": "987654321",
    "registrationDate": "2026-03-02T10:30:00",
    "active": true
  },
  "plan": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Plan Mensual",
    "description": "Acceso ilimitado por 1 mes",
    "price": 99.99,
    "duration": {
      "id": 1,
      "name": "1 Mes",
      "durationDays": 30
    }
  }
}
```

#### `GET /subscriptions/list`
Obtener todas las suscripciones

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "startDate": "2026-03-02T10:30:00",
    "endDate": "2026-04-01T10:30:00",
    "statusSubscription": "ACTIVE",
    "partner": {
      "id": 1,
      "firstName": "Juan",
      "lastName": "Pérez García",
      "email": "juan.perez@example.com",
      "phoneNumber": "987654321",
      "registrationDate": "2026-03-02T10:30:00",
      "active": true
    },
    "plan": {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "name": "Plan Mensual",
      "description": "Acceso ilimitado por 1 mes",
      "price": 99.99,
      "duration": {
        "id": 1,
        "name": "1 Mes",
        "durationDays": 30
      }
    }
  }
]
```

#### `GET /subscriptions/find/{id}`
Obtener suscripción por ID

**Response (200 OK):**
```json
{
  "id": 1,
  "startDate": "2026-03-02T10:30:00",
  "endDate": "2026-04-01T10:30:00",
  "statusSubscription": "ACTIVE",
  "partner": {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez García",
    "email": "juan.perez@example.com",
    "phoneNumber": "987654321",
    "registrationDate": "2026-03-02T10:30:00",
    "active": true
  },
  "plan": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Plan Mensual",
    "description": "Acceso ilimitado por 1 mes",
    "price": 99.99,
    "duration": {
      "id": 1,
      "name": "1 Mes",
      "durationDays": 30
    }
  }
}
```

#### `POST /subscriptions/cancel`
Cancelar suscripción

**Query Parameters:**
- `idPartner` (Integer) - ID del socio
- `idSubscription` (Integer) - ID de la suscripción

**Example:**
```
POST /subscriptions/cancel?idPartner=1&idSubscription=1
```

**Response (200 OK)**

---

### Inscripciones en Actividades

#### `POST /inscriptions/save`
Inscribir socio en una o más actividades

**Request:**
```json
{
  "idPartner": 1,
  "details": [
    {
      "idActivity": 1
    },
    {
      "idActivity": 2
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "dateRegistration": "2026-03-02T10:30:00",
  "partner": {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez García",
    "email": "juan.perez@example.com",
    "phoneNumber": "987654321",
    "registrationDate": "2026-03-02T10:30:00",
    "active": true
  },
  "status": "ACTIVE",
  "inscriptionDetails": [
    {
      "idActivity": 1,
      "descriptionActivity": "Yoga Avanzado",
      "schedules": [
        {
          "dayOfWeekSchedule": "MONDAY",
          "startTime": "09:00:00",
          "endTime": "10:00:00"
        },
        {
          "dayOfWeekSchedule": "WEDNESDAY",
          "startTime": "09:00:00",
          "endTime": "10:00:00"
        }
      ]
    }
  ]
}
```

---

### Códigos de Respuesta HTTP

| Código | Significado |
|--------|------------|
| **200** | OK - Solicitud exitosa |
| **201** | Created - Recurso creado exitosamente |
| **204** | No Content - Eliminación exitosa |
| **400** | Bad Request - Validación fallida (datos inválidos) |
| **404** | Not Found - Recurso no encontrado |
| **409** | Conflict - Conflicto (ej: capacidad llena, horarios superpuestos) |
| **500** | Internal Server Error - Error del servidor |

---

## 🏗️ Arquitectura

### Patrón de Diseño: Layered Architecture (N-Capas)

```
┌─────────────────────────────┐
│      Controllers            │  ← REST Endpoints
├─────────────────────────────┤
│      Services               │  ← Lógica de Negocio
├─────────────────────────────┤
│      Repositories           │  ← Acceso a Datos (JPA)
├─────────────────────────────┤
│      Database (PostgreSQL)  │  ← Persistencia
└─────────────────────────────┘
```

### Componentes Clave

| Componente | Responsabilidad |
|-----------|-----------------|
| **Controller** | Recibe solicitudes HTTP y delega a servicios |
| **Service** | Contiene la lógica de negocio y validaciones |
| **Repository** | Interfaz para acceso a base de datos (JPA) |
| **Model/Entity** | Representación de datos en la BD |
| **DTO** | Objetos para transferencia de datos entre capas |
| **Mapper** | Conversión entre Entities y DTOs |
| **Exception Handler** | Gestión centralizada de errores |

---

## 🛠️ Tecnologías

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| **Spring Boot** | 3.5.10 | Framework principal |
| **Spring Data JPA** | - | ORM y acceso a datos |
| **PostgreSQL** | 12+ | Base de datos |
| **Lombok** | - | Generación automática de código |
| **ModelMapper** | 3.2.5 | Mapeo de objetos |
| **Jakarta Validation** | - | Validación de datos |
| **Java** | 21 | Lenguaje de programación |
| **Maven** | 3.8.0+ | Gestor de dependencias |

---

## 📦 Dependencias Principales

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- PostgreSQL Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- ModelMapper -->
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.2.5</version>
</dependency>
```

---

## 🧪 Testing

Test en desarrollo...

```bash
mvn test
```

Para ejecutar con cobertura:

```bash
mvn test jacoco:report
```

---

## 🐛 Troubleshooting

### Error de conexión a PostgreSQL

**Problema**: `Connection refused`

**Solución**:
1. Verificar que PostgreSQL está corriendo: `systemctl status postgresql`
2. Revisar configuración en `application.properties`
3. Verificar credenciales de base de datos

### Puerto 8080 en uso

**Problema**: `Port 8080 already in use`

**Solución**:
```bash
# Cambiar puerto en application.properties
server.port=8081
```

### Errores de validación JPA

**Problema**: `No entity found`

**Solución**:
1. Asegurar que `spring.jpa.hibernate.ddl-auto=update` está configurado
2. Reiniciar la aplicación
3. Verificar que las entidades están siendo escaneadas correctamente

---

## 📚 Recursos Útiles

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [ModelMapper Documentation](http://modelmapper.org/)
- [Lombok Features](https://projectlombok.org/features/all)

---

## 🤝 Contribuir

Las contribuciones son bienvenidas. Por favor:

1. Crear una rama nueva: `git checkout -b feature/AmazingFeature`
2. Commit los cambios: `git commit -m 'Add some AmazingFeature'`
3. Push a la rama: `git push origin feature/AmazingFeature`
4. Abrir un Pull Request

### Estándares de Código

- Seguir convenciones de nomenclatura de Java
- Documentar métodos públicos
- Escribir tests unitarios
- Usar nombres descriptivos para variables

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

## 📧 Contacto

Para preguntas o sugerencias sobre el proyecto, contáctate conmigo.
LinkedIn: linkedin.com/in/e-florian/
GitHub: https://github.com/EduardoFlorian/system-gym

---

**Última actualización**: Marzo 2026