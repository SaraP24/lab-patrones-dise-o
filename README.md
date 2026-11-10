# Laboratorio de Patrones de Diseño

Página interactiva para estudiar patrones de diseño y estructuras de datos en Java, con un compilador real (`javac`) para practicar.

## Contenido

- **Creacionales:** Builder, Abstract Factory, Factory Method, Prototype, Singleton
- **Comportamiento:** Observer, Strategy
- **Estructurales:** Adapter, Decorator, Composite, DAO, Facade, Proxy
- **Estructuras y algoritmos:** árbol binario de búsqueda, lista/pila/cola, problema de la mochila
- **Repaso:** test teórico de opción múltiple y tablas comparativas

Cada patrón tiene teoría (problema, analogía, estructura, uso en Java) y al menos dos ejercicios:

- **Modo programa:** el ejercicio tiene su propio `main` y se compara toda la salida.
- **Modo pruebas (estilo CodeRunner):** escribís solo las clases y cada prueba se ejecuta por separado con su salida esperada.

## Requisitos

- Java 17 o superior (JDK, con `javac`)

## Cómo usarlo

En Windows, doble clic en `iniciar.cmd`. O desde una terminal en esta carpeta:

```bash
java Servidor.java
```

Luego abrí http://localhost:8765 en el navegador. El servidor solo escucha en tu equipo (127.0.0.1 y ::1).

El código y el progreso se guardan en el navegador (localStorage).

## Estructura

```
Servidor.java          servidor HTTP: sirve la página y compila/ejecuta el código
index.html             la página (teoría, editor, consola, test)
iniciar.cmd            arranque rápido en Windows
ejercicios/<id>/
  inicio.java          código inicial con TODOs
  solucion.java        solución de referencia
  esperado.txt         salida esperada (modo programa)
  pruebas.txt          pruebas y salidas esperadas (modo pruebas)
```
