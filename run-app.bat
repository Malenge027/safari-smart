@echo off
set JAVA_HOME=D:\jdk-21.0.11+10\jdk-21.0.11+10
set PATH=%JAVA_HOME%\bin;%PATH%
cd /d D:\jdk-21.0.11+10\Safari-smart
call .\mvnw spring-boot:run > app.log 2>&1
