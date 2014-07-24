jboss6eap-rabbitmq
==================

Simple integration of Jboss EAP 6 and RabbitMq 3.2 integration using QPid Jca 0.28

```xml
        <subsystem xmlns="urn:jboss:domain:resource-adapters:1.1">
            <resource-adapters>
                <resource-adapter id="qpid-ra-0.28.rar">
                    <archive>
                        qpid-ra-0.28.rar
                    </archive>
                    <transaction-support>LocalTransaction</transaction-support>
                    <config-property name="TransactionManagerLocatorClass">
                        org.apache.qpid.ra.tm.JBoss7TransactionManagerLocator
                    </config-property>
                    <config-property name="connectionURL">
                        amqp://guest:guest@/?brokerlist='localhost:5672'
                    </config-property>
                    <config-property name="TransactionManagerLocatorMethod">
                        getTm
                    </config-property>
                    <connection-definitions>
                        <connection-definition class-name="org.apache.qpid.ra.QpidRAManagedConnectionFactory" jndi-name="QpidJMSXA" pool-name="QpidJMSXA">
                            <config-property name="connectionURL">
                                amqp://guest:guest@/?brokerlist='localhost:5672'
                            </config-property>
                            <config-property name="SessionDefaultType">
                                javax.jms.Queue
                            </config-property>
                        </connection-definition>
                    </connection-definitions>
                    <admin-objects>
                        <admin-object class-name="org.apache.qpid.ra.admin.QpidQueueImpl" jndi-name="java:/amqp/queue/ldav" use-java-context="false" pool-name="LdavAmqp">
                            <config-property name="DestinationAddress">
                                BURL:direct://javamagazine//jmagQueue1?routingkey='info1'
                            </config-property>
                        </admin-object>
                    </admin-objects>
                </resource-adapter>
            </resource-adapters>
        </subsystem>
```
