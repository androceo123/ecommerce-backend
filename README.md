📦 Proyecto: Sistema de Gestión de Taller Mecánico
Este proyecto contiene el backend desarrollado con Java EE (JAX-RS, EJB, JPA) y el frontend simple en HTML + JavaScript, dentro de la carpeta taller-frontend.

📁 Estructura del repositorio
1F-backend/
├── taller-backend/     # Backend (Java, WAR para WildFly)
└── taller-frontend/    # Frontend estático (HTML + JS)
🚀 Levantar el Frontend
Podés levantar el frontend desde la carpeta taller-frontend usando Python 3 de forma rápida:

🔧 Requisitos
Tener Python 3 instalado.

▶️ Comando para levantar el servidor
cd taller-frontend
python3 -m http.server 8080
Esto levanta el sitio en:

http://localhost:8080
Asegurate de que el backend también esté corriendo (por ejemplo en http://localhost:8081 o http://localhost:8080/backend, según tu configuración).

🛠️ Levantar el Backend
El backend debe estar desplegado en un servidor Java EE como WildFly, usando el archivo WAR generado con Maven:

cd taller-backend
mvn clean package
El WAR generado estará en taller-backend/target/taller-backend-1.0-SNAPSHOT.war

Subilo a WildFly para que los endpoints REST estén disponibles.

