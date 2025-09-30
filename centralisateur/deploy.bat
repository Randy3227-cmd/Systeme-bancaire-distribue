```bat
@echo off
REM === Variables ===
set WAR_NAME=centralisateur-ejb-1.0-SNAPSHOT.war
set SOURCE_DIR=C:\Users\Randy\Documents\S5\Archi Log\Systeme-bancaire-distribue\centralisateur\target
set TARGET_DIR=D:\wildfly-37.0.1.Final\standalone\deployments
set WILDFLY_HOME=D:\wildfly-37.0.1.Final
set APP_URL=http://localhost:8090/centralisateur-ejb-1.0-SNAPSHOT/index.html

echo =========================================
echo  Déploiement de %WAR_NAME% sur WildFly
echo =========================================

REM === Suppression anciens fichiers ===
if exist "%TARGET_DIR%\%WAR_NAME%" del /f /q "%TARGET_DIR%\%WAR_NAME%"
if exist "%TARGET_DIR%\%WAR_NAME%.deployed" del /f /q "%TARGET_DIR%\%WAR_NAME%.deployed"

REM === Copie du nouveau WAR ===
copy "%SOURCE_DIR%\%WAR_NAME%" "%TARGET_DIR%"

REM === Lancement de WildFly ===
echo.
echo === Démarrage de WildFly ===
cd /d "%WILDFLY_HOME%\bin"
start standalone.bat

echo.
echo Attente 10 secondes le temps que WildFly démarre...
timeout /t 10 /nobreak >nul

echo.
echo === Ouverture de l'application dans le navigateur ===
start "" "%APP_URL%"

echo.
echo Déploiement terminé.
pause
```
