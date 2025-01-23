# Tenpo Challenge

## Descripción del Proyecto

Este repositorio ha sido creado para resolver el desafío de Tenpo. El proyecto está dividido en dos partes principales:

### Backend - `backend-tenpo`
Esta carpeta contiene el código del backend para el desafío de Tenpo. Está construido utilizando:
- **Java** con **Spring Boot** para crear servicios web RESTful.
- **Maven** para la gestión de dependencias y la automatización de la construcción.

#### Características Clave:
- Endpoints de API REST para varias funcionalidades.
- Integración con base de datos utilizando Spring Data JPA.
- Configuraciones de seguridad y autenticación de usuarios.

### Frontend - `tenpo-frontend`
Esta carpeta contiene el código del frontend para el desafío de Tenpo. Está construido utilizando:
- **TypeScript** y **JavaScript** para la programación.
- **React** para construir la interfaz de usuario.
- **npm** para la gestión de paquetes.

#### Características Clave:
- Componentes de UI responsivos.
- Gestión de estado utilizando hooks de React.
- Integración con las APIs del backend para la obtención de datos.

## Instrucciones para Ejecutar los Servicios y la Base de Datos Localmente


### Pasos para Ejecutar

1. Clonar el repositorio:
   ```sh
   https://github.com/dmunoza/tenpo-challenger.git
   cd tenpo-challenge

   docker-compose up --build
   ```

2. Acceda a las aplicaciones desde los siguientes enlaces:
    - **Frontend**: [http://localhost:80](http://localhost:80)
    - **Backend**: [http://localhost:8080](http://localhost:8080)

---

## Detalles sobre Cómo Interactuar con la API

### Endpoints Disponibles

#### Obtener Transacciones
- **URL**: `/api/transactions`
- **Método**: `GET`
- **Descripción**: Obtiene todas las transacciones.

##### Ejemplo de Respuesta:
```json
[
  {
    "id": "e174c2d0-c358-4630-8f9a-b72ca6f6e4b2",
    "amount": 100,
    "commerce": "Amazon",
    "user_tenpo": "tenpo",
    "date_transaction": "2021-01-01T00:00:00"
  }
]
```

---

#### Crear una Nueva Transacción
- **URL**: `/api/transactions`
- **Método**: `POST`
- **Descripción**: Crea una nueva transacción.

##### Cuerpo de la Solicitud:
```json
{
  "amount": 150,
  "commerce": "eBay",
  "user_tenpo": "tenpo_user",
  "date_transaction": "2021-02-01T00:00:00"
}
```

##### Ejemplo de Respuesta:
```json
{
  "id": "uuid",
  "amount": 150,
  "commerce": "eBay",
  "user_tenpo": "tenpo_user",
  "date_transaction": "2021-02-01T00:00:00"
}
```

---

## Documentación Adicional
Para más detalles sobre los endpoints disponibles y cómo interactuar con la API, consulte la api:
- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

