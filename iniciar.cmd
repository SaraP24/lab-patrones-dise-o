@echo off
cd /d "%~dp0"
where java >nul 2>nul || (echo No se encuentra Java. Instala un JDK 17 o superior. & pause & exit /b 1)
start "" http://localhost:8765
java Servidor.java
echo.
echo El servidor se ha detenido.
echo Si arriba pone "Address already in use", el laboratorio ya estaba en marcha:
echo abre http://localhost:8765 en tu navegador.
pause
