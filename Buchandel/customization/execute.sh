#!/bin/sh
 
# Usage: execute.sh [WildFly mode] [configuration file]
#
# The default mode is 'standalone' and default configuration is based on the
# mode. It can be 'standalone.xml' or 'domain.xml'.
 
echo "=> Executing Customization script"
 
JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}
 
echo "Connection URL:" $CONNECTION_URL
 
echo "=> Hosts"
cat /etc/hosts
echo
echo "=> Umgebungsvariablen (env)"
env
 
function wait_for_server() {
  until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
    sleep 1
  done
}
 
echo "=> Starting WildFly server"
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG > /dev/null &
# $JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG &
 
echo "=> Waiting for the server to boot"
wait_for_server
 
 
$JBOSS_CLI -c << EOF
batch
 
module add \
 --name=org.apache.derby \
 --resources=/opt/jboss/wildfly/customization/derbyclient.jar \
 --resource-delimiter=, \
 --dependencies=javax.api,javax.transaction.api
 
/subsystem=datasources/jdbc-driver=derby:add(driver-name=derby, \
 driver-module-name=org.apache.derby, \
  driver-class-name=org.apache.derby.jdbc.ClientDriver, \
 driver-datasource-class-name=org.apache.derby.jdbc.ClientDataSource, \
 driver-xa-datasource-class-name=org.apache.derby.jdbc.ClientXADataSource)
 
data-source add \
 --name=dbDS \
 --driver-name=derby \
 --connection-url=jdbc:derby:${jboss.server.base.dir}/db;create=true \
 --jndi-name=java:jboss/datasources/BookDb \
 --user-name=app \
 --password=app
 
 
# Execute the batch
run-batch
EOF
 
# Deploy the WAR
echo "=> Deploy the WAR"
cp /opt/jboss/wildfly/customization/Buchandel.war $JBOSS_HOME/$JBOSS_MODE/deployments/Buchandel.war
 
echo "=> Shutting down WildFly"
if [ "$JBOSS_MODE" = "standalone" ]; then
  $JBOSS_CLI -c ":shutdown"
else
  $JBOSS_CLI -c "/host=*:shutdown"
fi
 
echo "=> Restarting WildFly"
# $JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c $JBOSS_CONFIG > /dev/null
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c $JBOSS_CONFIG