# AssessmentPlanApp
DESCRIZIONE
L'applicazione fornisce un modo  intuitivo per creare un assessment plan inserendo il framework utilizzato ed i vari controlli implementati o pianificati.


#TECNOLOGIE USATE
##SERVER-SIDE
* Java
* Spring
* Hibernate
* lombok

DATABASE
*MySql

##CLIENT SIDE
*BOOTSTRAP
*CSS
*HTML
*THYMELEAF


#ISTRUZIONI

#Creare il db su mysql workbench eseguendo i file nella cartella dumps (leggere il readme)



#Importare il progetto come existing maeven project su eclipse


#CREARE RUN CONFIGURATIONS SOTTO SPRINGBOOT APP CON LE SEGUENTI VARIABILI D'AMBIENTE
DATA_BASE_DRIVER=com.mysql.jdbc.Driver
DATA_BASE_URL=jdbc:mysql://localhost:3306/db_tesi //url database
DATA_BASE_USER_NAME= //nome database
DATA_BASE_USER_PASSWORD= //password database 
SERVER_PORT=8084

#Dopo aver startato il progetto andare su localhost:8084 su internet