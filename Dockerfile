FROM tomcat:8-jre8-slim
COPY /services/target/newsSearchService.war /usr/local/tomcat/webapps
#COPY /app/app.war /usr/local/tomcat/webapps
COPY /angular/dist/newssearchPrac/. /usr/local/tomcat/webapps/newssearchPrac
