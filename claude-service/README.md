# claude-service

Microservicio Spring Boot (Java 17) que expone Claude a tu front Angular.

## Importar en Eclipse
1. File > Import > Maven > Existing Maven Projects
2. Selecciona la carpeta `claude-service` > Finish
3. Espera a que descargue las dependencias (clic derecho > Maven > Update Project si hace falta)

## Configurar la API key
Run > Run Configurations > Java Application (ClaudeServiceApplication) > pestana Environment:
anade `ANTHROPIC_API_KEY` = tu clave.

## Ejecutar
Clic derecho en `ClaudeServiceApplication` > Run As > Java Application. Arranca en http://localhost:8080

## Endpoints
- GET  /api/claude/health
- POST /api/claude/preguntar   {"prompt":"Hola"}
- POST /api/claude/chat        {"messages":[{"role":"user","content":"Hola"}],"system":"Responde en espanol"}

Respuesta: {"respuesta":"..."}

## Prueba rapida
curl -X POST http://localhost:8080/api/claude/preguntar -H "Content-Type: application/json" -d '{"prompt":"Hola Claude"}'
