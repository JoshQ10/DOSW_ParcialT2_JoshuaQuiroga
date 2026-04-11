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

Explique la diferencia entre Validaciones de input y Validaciones de
negocio


## Análisis
Son formas de verificacion divididas en FrontEnd (UI) y Backend, siendo estos implementaciones a partir de la inserccion o solicitudes tomandas desde el controlador y convertidas por los mappers, luego en el ambito de la logica de negocio comprobar que la solicitud es valida o exista, por ejemplo:

cuando el usuario quiere registrarse con un correo con un correo institucional, se envia la solicitud (inserccion de datos) a partir de los DTOS, para que luego la logica del negocio del servicio verifique que tipo de solicitud es, y si es valida cumplienod con los caracteres especiales de la contraseña que use( verificacion en UI/input/DTO) al agregar los datos se verifica el correo que tenga "@", que la contraseña cumpla con ciertos caracteres tec.., luego de ello se verifica que los datos se cumplan segun su funcionalidad y proposito de la aplicacion atravez de los servicios (que sea un correo educativo) 
---



# Punto 4

## Diagrama de componentes especifico
<img width="1595" height="566" alt="image" src="https://github.com/user-attachments/assets/3b1788d4-9ed1-4980-be1e-3a901988125f" />

---

# Punto 5

## Descripción

¿Qué problemas pueden surgir si no se separan correctamente las capas dentro de un proyecto de software?

## Análisis

Pueden llegar a surgir multiples errores, desde codigo repetido (metodos, funcionalidad, responsabilidades), mal flujo de solicitudes, conversion de datos, problemas con los modelos, el flujo de os test tambien se podria ver afectado ya que al implementar un test digamos para un DTO, se debe haber un flujo con las clases de los mappers y al no tener una buena organizacion, el test se veria afectado

---

# Punto 6

[Diagrama de Componentes Especifico](https://lucid.app/lucidchart/80129cc3-e2c9-4f90-9a09-084a448140d9/edit?viewport_loc=-2438%2C-3000%2C6282%2C2470%2C0_0&invitationId=inv_b3e60ec2-05de-4bac-8a0d-b244518e43c3)

# Punto 7 

## Descripción

¿Cuáles son las diferencias entre un validador, una utilidad y un servicio?

## Análisis

### Validador

Es una clase que verifica el proposito de la aplicacion (funcionalidad) y esos se ven representados a partir de (Request DTOs, entidades, etc.). En donde se implementan las anotaciones (@NotNull, @Email, @Size, etc.), se realiza a partir de la inserccion de datos, en donde se cumpla los inputs, con ciertos requerimientos com el correo, o la contraseña con ciertos caracteres

### Utilidad 

Son clases, adicionales que complementan las clases de modelo, siendo estas genericas pero utiles como una interfaz, No manejan persistencia, ni lógica de negocio compleja. implementadfas como delegacion de responsabilidad Son estáticas, no necesitan inyección de dependencias

### Servicio

Son clases que contiene reglas complementando la logica de negocio, representado con @service, ,maneja acceso a la base de datos

---

# Punto 9

---

# Punto 10

## Descripcion

Proponga 2 índices que mejoren el rendimiento de las consultas de ECIXPRESS y establezca con un criterio técnico el porque dan valor a la solución.

## Analisis

Índice sobre email en la tabla users "CREATE INDEX idx_users_email ON users(email);"

Este índice tiene valor técnico directo en dos funcionalidades críticas del sistema. En F-01 (registro), el sistema debe verificar que el correo institucional no exista antes de crear el usuario, lo que implica un SELECT por email. En F-02 (login), Spring Security carga el usuario por su email para autenticarlo. Sin el índice, ambas operaciones hacen un full table scan, es decir, recorren toda la tabla fila por fila.

Índice compuesto sobre user_id y status en la tabla orders "CREATE INDEX idx_orders_user_status ON orders(user_id, status);"

Este índice responde directamente a una regla de negocio definida en el caso de estudio: un usuario solo puede tener un pedido activo a la vez. Para validar esa regla, el OrderRepository ejecuta un findByUserAndStatus cada vez que un cliente intenta crear un nuevo pedido, buscando si ya existe una orden en estado CREADO o EN_PREPARACION para ese usuario. 

---
# Punto 11

## Descripcion

Como parte de la solución, es fundamental definir un conjunto robusto de
pruebas que garantice la calidad y correcto funcionamiento de las
funcionalidades expuestas en el sistema. Dado el enfoque de
transparencia con el cliente, se requiere evidenciar cómo se desarrollaría
la funcionalidad de “Solicitar pedido” siguiendo el enfoque de TDD (Test
Driven Development). En este contexto, se espera que usted:

● Describa cómo se aplican las fases de TDD (Red, Green,
Refactor) en la implementación de esta funcionalidad.

● Defina los casos de prueba iniciales antes de la implementación,
contemplando tanto escenarios exitosos como de error.

● Identifique las validaciones clave que deben ser cubiertas por las
pruebas.

## Analisis

**Fase Green — escribir el mínimo código para que la prueba pase**

Las fases de TDD se aplican de manera en la que primeramente se aplica una prueba Red la cual es una prueba que esta destinada a fallar, luego tenemos la prueba Green la cual es una prueba minima del codigo funcional. Por ultimo se implementa el Refactor de ser
necesario en caso de que se necesite añadir codigo sin que la prueba falle por las modificaciones.


Esta prueba falla porque orderService.crearPedido() no existe todavía. Ese error en rojo es la señal de que se puede empezar a implementar.

Se implementa crearPedido() con el código más simple posible que haga pasar la prueba, sin preocuparse aún por elegancia ni casos borde. El objetivo es únicamente ver la prueba en verde.

```java
public OrderResponse crearPedido(CreateOrderRequest request) {
    Order order = new Order();
    order.setUser(request.getUser());
    order.setItems(request.getItems());
    order.setStatus(OrderStatus.CREADO);
    order.setTotal(calcularTotal(request.getItems()));
    order.setCreatedAt(LocalDateTime.now());
    orderRepository.save(order);
    return orderDtoMapper.toResponse(order);
}
```
**Fase Refactor — mejorar el código sin romper las pruebas**

Con la prueba en verde, se mejora la implementación: se extraen métodos, se aplican validaciones, se mejora la legibilidad. Después de cada cambio se corre la prueba para confirmar que sigue en verde. Aquí es donde entran los validators, el manejo de stock y la verificación de pedido activo.

*Validaciones de negocio* — estas las cubre `OrderService` y son las más críticas para el dominio:

- El usuario no debe tener ningún pedido en estado `CREADO` o `EN_PREPARACION` antes de crear uno nuevo
- Cada producto de la lista debe tener `status = DISPONIBLE`
- El stock de cada producto debe ser mayor o igual a la cantidad solicitada
- El total del pedido debe calcularse como la sumatoria de `precio × cantidad` para cada item
- El stock de cada producto debe reducirse correctamente al momento de persistir el pedido
- El pedido debe iniciarse siempre en estado `CREADO`, nunca en otro estado

---
# Punto 12

**Fase Green — escribir el mínimo código para que la prueba pase**


```java
@Test
void deberiaCrearPedidoExitosamente() {
    // Arrange
    CreateOrderRequest request = new CreateOrderRequest(usuario, List.of(item));

    // Act
    OrderResponse response = orderService.crearPedido(request);

    // Assert
    assertNotNull(response);
    assertEquals(OrderStatus.CREADO, response.getStatus());
}
```
Esta prueba falla porque orderService.crearPedido() no existe todavía. Ese error en rojo es la señal de que se puede empezar a implementar.

Se implementa crearPedido() con el código más simple posible que haga pasar la prueba, sin preocuparse aún por elegancia ni casos borde. El objetivo es únicamente ver la prueba en verde.

**Fase Refactor — mejorar el código sin romper las pruebas**

```java
public OrderResponse crearPedido(CreateOrderRequest request) {
    Order order = new Order();
    order.setUser(request.getUser());
    order.setItems(request.getItems());
    order.setStatus(OrderStatus.CREADO);
    order.setTotal(calcularTotal(request.getItems()));
    order.setCreatedAt(LocalDateTime.now());
    orderRepository.save(order);
    return orderDtoMapper.toResponse(order);
}
```
**Casos de prueba iniciales**

```java
// 
@Test
void deberiaCrearPedidoExitosamente() {
    // usuario sin pedido activo, productos con stock disponible
    // esperado: OrderResponse con status CREADO y total calculado
}

// MAL: usuario ya tiene un pedido activo
@Test
void deberiaLanzarExcepcionSiUsuarioTienePedidoActivo() {
    // usuario con pedido en estado CREADO
    // esperado: BusinessException "El usuario ya tiene un pedido activo"
}

// MAL: producto sin stock
@Test
void deberiaLanzarExcepcionSiProductoSinStock() {
    // producto con stock = 0
    // esperado: BusinessException "Producto sin stock disponible"
}

// MAL: producto no disponible
@Test
void deberiaLanzarExcepcionSiProductoNoDisponible() {
    // producto con status NO_DISPONIBLE
    // esperado: BusinessException "Producto no disponible"
}

// MAL: lista de productos vacía
@Test
void deberiaLanzarExcepcionSiListaProductosEsVacia() {
    // request con lista de items vacía
    // esperado: BusinessException "El pedido debe tener al menos un producto"
}

// MAL: cantidad de producto es cero o negativa
@Test
void deberiaLanzarExcepcionSiCantidadEsInvalida() {
    // item con quantity = 0 o quantity = -1
    // esperado: BusinessException "La cantidad debe ser mayor a cero"
}

// MAL: stock insuficiente para la cantidad solicitada
@Test
void deberiaLanzarExcepcionSiStockInsuficiente() {
    // producto con stock = 2, cantidad solicitada = 5
    // esperado: BusinessException "Stock insuficiente para el producto"
}

//verificar que el total se calcula correctamente
@Test
void deberiaCalcularTotalCorrectamente() {
    // producto A precio 5000 cantidad 2, producto B precio 3000 cantidad 1
    // esperado: total = 13000
}

//verificar que el stock se descuenta al confirmar
@Test
void deberiaDescontarStockAlCrearPedido() {
    // producto con stock = 10, cantidad solicitada = 3
    // esperado: stock del producto = 7 después de crear el pedido
}
```
## Punto 13

Un pipeline es un proceso automatizado que toma tu código desde que lo subes al repositorio hasta que queda desplegado y funcionando en producción, sin que tengas que hacer nada manual.
Las etapas principales son:

1. Source (Fuente)
Es el punto de partida. El pipeline se activa automáticamente cuando alguien sube código o hace una mezcla de ramas, por ejemplo cuando se mezcla una rama feature sobre develop. Desde ese momento el pipeline toma el control.

2. Build (Construcción)
El pipeline toma el código fuente y lo compila. En el caso de ECIXPRESS con Spring Boot, esto significa que Maven descarga las dependencias del pom.xml y genera el archivo .jar ejecutable. Si el código tiene errores de compilación, el pipeline se detiene aquí y notifica al equipo.

3. Test (Pruebas)
Se ejecutan todas las pruebas unitarias y de integración que el equipo escribió. En ECIXPRESS esto incluiría los tests de OrderServiceTest, UserControllerTest, entre otros. Si alguna prueba falla, el pipeline se detiene y no permite continuar. Esta etapa es la que garantiza que el código que va a producción funciona correctamente.

4. Code Analysis (Análisis estático)
Herramientas como SonarQube o Jacoco revisan el código en busca de problemas de calidad: cobertura de pruebas insuficiente, código duplicado, malas prácticas o vulnerabilidades de seguridad. Es como una revisión automática de código.

5. Package (Empaquetado)
Una vez que el código pasó las pruebas y el análisis, se empaqueta la aplicación en un artefacto listo para desplegar, en el caso de Spring Boot ese artefacto es el .jar, o también puede construirse una imagen de Docker.

6. Deploy (Despliegue)
El artefacto empaquetado se sube al servidor o plataforma cloud donde va a correr la aplicación. En el caso del bono de ECIXPRESS esto sería Azure. Esta etapa puede tener varios ambientes en orden: primero se despliega en un ambiente de desarrollo, luego en uno de pruebas y finalmente en producción.

---

# Punto 15

Explique el concepto de logging en el manejo de errores:
a. ¿Qué información debería registrarse?
La informacion que deberia registrarse son datos de movimientos monetarios, cantidad de los productos, manejo de errores, inventarios
b. ¿Qué NO debería registrarse (por seguridad)?
Lo que no deberia ir son las credenciales e informacion especifica de clientes y de la señora del cafe
---


# Punto 16

## Diagramas de pantalla

1) Login
<img width="1066" height="597" alt="image" src="https://github.com/user-attachments/assets/9af16b6c-a14d-4ab7-9682-6ef3ce652aaf" />

2) Registro
<img width="919" height="516" alt="image" src="https://github.com/user-attachments/assets/5b9a0f70-961a-413f-97d5-74e96bef0aa4" />

3) Menu Home
<img width="1062" height="596" alt="image" src="https://github.com/user-attachments/assets/4fa9c45e-948e-4fcb-b280-daf4e8113b8a" />

4) Carrito de compra (Sin terminar)
<img width="1064" height="590" alt="image" src="https://github.com/user-attachments/assets/b5cab38f-2230-46b3-be12-d93b57862d24" />



Juancarlos Bohorquez Monroy, tuve puntos extra en los laboratorio, de la semana 10 
