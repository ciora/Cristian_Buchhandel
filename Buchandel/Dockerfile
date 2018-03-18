FROM jboss/wildfly:latest

RUN /opt/jboss/wildfly/bin/add-user.sh admin passme --silent

ADD customization /opt/jboss/wildfly/customization/

COPY target/Buchandel.war /opt/jboss/wildfly/customization/Buchandel.war

CMD ["/opt/jboss/wildfly/customization/execute.sh"]