# 🛡️ TPI: Laboratorio de Ciberseguridad OWASP en Spring Boot

Trabajo Práctico Integrador sobre incidencias de programación y ciberseguridad desarrollado para el **CFP N°8 - SMATA**. La aplicación demuestra de forma interactiva y visual el comportamiento de tres vulnerabilidades críticas del Top 10 de OWASP, comparando el entorno vulnerable frente a su respectiva mitigación en código seguro.

## 🏢 Estructura de Capas por Proyecto
El backend está modularizado de forma independiente para cada incidencia respetando la arquitectura limpia solicitada:
*   `proyecto_1` (Inyección SQL): Contiene las capas de `model`, `dto`, `repository`, `service` y `controller`.
*   `proyecto_2` (Control de Acceso Roto): Contiene las capas correspondientes para aislar las reglas de negocio de acceso.
*   `proyecto_3` (XSS Stored Keylogger): Módulos dedicados al tablón de comentarios interactivo.

## 🛠️ Tecnologías Utilizadas
*   **Java 17 / 21** & **Spring Boot**
*   **Spring Data JPA / Hibernate**
*   **MySQL** (Base de datos local persistente con auto-creación de esquema)
*   **Bootstrap 5** & **Vanilla JavaScript** (Frontend desacoplado en carpetas estáticas y CSS limpio)

## 🧬 Incidencias Demostradas en los Laboratorios
1.  **OWASP A05: Inyección SQL:** Manipulación de consultas nativas construidas por concatenación directa frente a consultas parametrizadas automáticas con JPA.
2.  **OWASP A01: Control de Acceso Roto:** Explotación de referencias directas inseguras a objetos (IDOR) en endpoints de facturación mitigado mediante validación de pertenencia lógica en la capa de servicios.
3.  **OWASP A03 / XSS Stored Keylogger:** Inyección persistente de scripts maliciosos capaces de registrar pulsaciones de teclas (`keyup`) mitigado mediante funciones de escape de entidades HTML.

## 🚀 Configuración y Ejecución
1. Asegúrate de tener levantado tu servidor de MySQL local.
2. Configura las credenciales correctas de base de datos en tu archivo `src/main/resources/application.properties` (el esquema `xss_demo_db` se creará automáticamente si no existe).
3. Ejecuta la aplicación de Spring Boot en tu entorno local.
4. Ingresa en el navegador a: `http://localhost:8080/` para acceder al Panel Central interactivo.
