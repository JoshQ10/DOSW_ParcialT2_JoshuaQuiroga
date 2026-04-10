# DOSW_ParcialT2_JuanBohorquez

# Parcial Segundo Corte - DOSW

## Información del Estudiante
- **Nombre:** Joshua David Quiroga Landazabal
- **Curso:** #1  
- **Materia:** Desarrollo de Software (DOSW)  
- **Profesor:** Andres Martin Cantor  

---

# Descripción

Este documento corresponde al desarrollo del **parcial del segundo corte** de la materia DOSW.  
Aquí se presentan las soluciones a los diferentes puntos propuestos, junto con su respectivo análisis, implementación y pruebas (según aplique).

---
1, 2, 3, 4, 8, 9, listar
metamodelo conceptual
entidad -> relacion
# Punto 1

`a,b,c,d`
| # | Funcionalidad | Verbo HTTP | Idempotente? | Razon Tecnica | Roles con acceso |
|----|---|---|---|---|---|
| F-01 | Registro de usuario | Post | No | Cada llamada crea un nuevo recurso (usuario). Dos peticiones idénticas generarían duplicados o error de unicidad. POST no garantiza idempotencia por diseño REST. | Cliente/Señora de la cafeteria |
| F-02 | Autenticación (login) | Post | No |  Genera un token JWT nuevo en cada llamada. El resultado varía, no es seguro ni correcto que dos peticiones iguales produzcan el mismo token. | Cliente/Señora de la cafeteria |
| F0-3 | Consulta Productos | Get | Si | Solo lee datos. Múltiples llamadas con los mismos parámetros devuelven el mismo resultado (mientras no cambie el estado del servidor). | Cliente/Señora de la cafeteria |
| F-04 | Crear Pedidos | Post | No | Crear o actualizar un pedido modifica estado del servidor. Si el pedido ya existe podría sumar la cantidad.| Cliente/Empleado |
| F-05 | Cambiar estado del pedido | Patch | No | Al cambiar el estado se esta haciendo una modificacion al estado del pedido ya creado, y Patch por definicion no es idempotente | Señora de la cafeteria |
| F0-6 | Cancelar pedido | Delete | Si | Al un cliente eliminar su pedido, no hay ninguna modificacion de cantidad en el sistema, ademas por definicion el verbo delete es idempotente. | Cliente |
| F-07 | Listar Productos | Post | No | Cuando se lista un producto ya sea por parte del cliente o de la señora de la cafeteria, la cantidad del producto cambia, por lo que no es idempotente. | Cliente/Señora de la cafeteria |

`e`
| F-01 | 
| Campo | Tipo | Obligatorio |
|---|---|---|
| name | String | ✅ |
| email | String (email) | ✅ |
| password | String | ✅ | 
| **Salida** | | |
| id | UUID | | 
| name | String | 
| email | String | 

| F-02 |

| F-03 | 
| F-04 | 
| F-05 | 
| F-06 | 
| F-07 | 

`f`
| F-01 |
```json
// POST /api/v1/auth/register
// REQUEST
{
  "name": "Carlos Monroy",
  "email": "carlosmonroy@mail.escuelaing.edu.co",
  "password": "Segura123!"
}

// RESPONSE 201 Created
{
  "id": "a1b2c3d4-...",
  "name": "Carlos Monroy",
  "email": "carlosmonroy@mail.escuelaing.edu.co",
  "createdAt": "2025-04-09T10:00:00Z"
}
```
| F-02 |
```json
// POST /api/v1/auth/login
// REQUEST
{
  "email": "carlosmonroy@mail.escuelaing.edu.co",
  "password": "Segura123!"
}

// RESPONSE 200 OK
{
  "accessToken": "eyJhbGci...",
  "tokenType": "Bearer",
  "user": {
    "id": "a1b2c3d4-...",
    "name": "Carlos Monroy",
    "email": "carlosmonroy@mail.escuelaing.edu.co",
    "role": "CLIENTE"
  }
}
```
| F0-3 |
```json
// GET /api/v1/products?category=running&name=ECIEXPRESS=0&size=10

// RESPONSE 200 OK
{
  "content": [
    {
      "QR_id": "prod-001",
      "name": "Cafe Cargado",
      "price": 3000,
      "stock": 15,
      "status": "DISPONIBLE",
    }
  ]
}
```
| F-04 |
```json
// POST /api/v1/pedidos
// REQUEST
{
  "pedidoId: "pedido-001",
  "user": Cliente
}

// RESPONSE 200 OK
{
  "pedidoId: "pedido-001",
  "user": Cliente
    {
      "productsList": "prod-001" "prod-002" "prod-003",
      "quantity": 4,
      "status": CREADO,
    }
}
```
| F-05 |
```json
// PATCH /api/v1/pedidos
// REQUEST
{
  "pedidoId: "pedido-001",
  "user": Cliente
}

// RESPONSE 200 OK
{
  "pedidoId: "pedido-001",
  "user": Cliente
    {
      "productsList": "prod-001" "prod-002" "prod-003",
      "quantity": 4,
      "status": CREADO,
    }
}
```
| F0-6 | 
| F-07 |

`g`
| F-01 | El correo debe ser institucional. Es decir de dominio @mail.escuelaing.edu.co o @escuelaing.edu.co |
| F-02 | N/A |
| F-03 | El producto debe ser consultado mediante escaneo de codigo QR |
| F-04 | El pedido solo puede ser creado con minimo un producto escaneado |
| F-05 | Unicamente la señora del cafe puede cambiar el estado del pedido y solo a dos estados: EN_PREPARACION o ENTREGADO |
| F-06 | Unicamente el cliente puede cancelar el pedido y solo cuando el pedido este en estado CREADO |
| F-07 | El cliente solo puede listar productos si han sido añadidos (listados) por la señora del cafe previamente |

`h`
| F-01 |
| Caso | Código | Mensaje |
|------|--------|---------|
| Happy Path | 201 Created | Usuario registrado exitosamente |
| Email duplicado | 409 Conflict | El correo ya está registrado |
| Validación fallida | 400 Bad Request | Campos inválidos: {detalle} |
| Error servidor | 500 Internal Server Error | Error interno |

| F-02 |
| Caso | Código | Mensaje |
|------|--------|---------|
| Happy Path | 200 OK | Login exitoso |
| Credenciales incorrectas | 401 Unauthorized | Credenciales inválidas |
| Validación | 400 Bad Request | Campos requeridos faltantes |

| F-03 |
| Caso | Código | Mensaje |
|------|--------|---------|
| Happy Path | 200 OK | Lista de productos |
| Sin resultados | 200 OK | content: [] |

| Caso | Código | Mensaje |
|------|--------|---------|
| Happy Path | 200 OK | Producto creado |
| Sin stock suficiente | 422 Unprocessable Entity | Stock insuficiente. Disponible: {n} |
| Producto no encontrado | 404 Not Found | Producto no existe |
| No autenticado | 401 Unauthorized | Token requerido |

| F-05 |


| F-06 |
| Caso | Código | Mensaje |
|------|--------|---------|
| Eliminación exitosa | 204 No Content | Pedido Eliminado |
| Producto no ha sido creado | 404 Not Found | Pedido no encontrado |

| F-07 |
| Caso | Código | Mensaje |
|------|--------|---------|
| Happy Path | 200 OK | Prodcutos agregados |
| Sin resultados | 200 OK | content: [] |
---

# Punto 2

## Descripción
_(Describe aquí el enunciado del punto 2)_

## Análisis
_(Explica tu razonamiento)_

## Implementación
_(Incluye código o explicación)_

## Pruebas
_(Evidencia o validación)_

---

#Punto 5
<img width="1595" height="566" alt="image" src="https://github.com/user-attachments/assets/3b1788d4-9ed1-4980-be1e-3a901988125f" />


# Punto 11

`A`
Las fases de TDD se aplican de manera en la que primeramente se aplica una prueba Red la cual es una prueba que esta destinada a fallar, luego tenemos la prueba Green la cual es una prueba minima del codigo funcional. Por ultimo se implementa el Refactor de ser
necesario en caso de que se necesite añadir codigo sin que la prueba falle por las modificaciones.

`B`

# Punto 15

Explique el concepto de logging en el manejo de errores:
a. ¿Qué información debería registrarse?
La informacion que deberia registrarse son datos de movimientos monetarios, cantidad de los productos, manejo de errores, inventarios
b. ¿Qué NO debería registrarse (por seguridad)?
Lo que no deberia ir son las credenciales e informacion especifica de clientes y de la señora del cafe
---
