ejecutar servidor wildfly: 
1- acceder a la carpeta bin dentro de wildfly 
2- ejecutar el standalone.bat, en el cmd de windows el comando es standalone.bat
ejecutar la aplicacion mientras el servidor wildfly se esta ejecutando
1- mvn clean package
2- mvn wildfly:deploy
3- ejemplo de url: http://localhost:8080/ecommerce-backend/api/test-db
