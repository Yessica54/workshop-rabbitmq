# Optimiza tus colas de mensajes

Workshop donde entenderemos el uso de los diferentes estándares y protocolos para el manejo de colas. Construyendo un servicio de mensajes con Spring Boot y RabbitMq utilizando un ejemplo real.Este proyecto es una simplificación del problema planteado sobre facturación electronica.

## Instalación

Para la ejecución de estos proyectos es necesario importarlos en eclipse como un proyecto Gradle existente. Adicionalmente es necesario implementar servidor de RabbitMQ.

### Servidor RabbiMQ

Para poner en funcionamiento un servidor Rabbit en local se utilizara Docker, para descargar la imagen ejecutar:

```bash
docker pull rabbitmq:3-management
```
para ejecutar la imagen
```bash
docker run -d --hostname localhost --name rabbitmq -p 5672:5672 -p 5673:5673 -p 15672:15672 rabbitmq:3-management
```
Los datos por defecto de acceso al servidor son los siguientes:

- **URL:** [localhost:15672](http://localhost:15672/)
- **User:** guest
- **Password:** guest
 
### Servidor PostgreSQL

Es necesario tener implementado un servidor de base de datos de PosgrestSQL para ello véase la [Documentación de PostgreSQL](https://www.postgresql.org/docs/)

## Configuración

Para la configuración de las colas, exchanges, routes Key y Base de datos es necesaria la edición de los archivos application.properties sean sea el caso

### propiedades para el productor de mensajes (messaging-rabbitmq)
```properties
## Connect Rabbit
spring.rabbitmq.host=192.168.0.12
spring.rabbitmq.port=15672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.main.allow-bean-definition-overriding=true
spring.rabbitmq.virtual-host: /

##	Name for queue
rabbitmq.exchange=exchange-invoice
rabbitmq.queue=queue-invoice
rabbitmq.routingkey=routingkey-invoice
```

### propiedades para el consumidor de mensajes (messaging-rabbitmq-consumer)
```properties

## config
server.port=8081
spring.main.allow-bean-definition-overriding=true

## config server rabbitmq
spring.rabbitmq.host=192.168.0.12
spring.rabbitmq.port=15672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.virtual-host: /

## Config queue
rabbitmq.queue=queue-invoice

## datasource
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/rabbitmq
spring.datasource.username=postgres
spring.datasource.password=root

```

## Usage

Una vez importados los proyecto e implementado el servidor de RabbitMQ podemos podemos consumir el enpoint expuesto para encolar los mensajes

**Enpoint:** [http://localhost:8080/invoice/create](http://localhost:8080/invoice/create)

**Metodo:** POST

**Request**
```json
{
    "biller": {
        "name": "Facturador Ukuff",
        "document": "103157778",
        "typeDocument": "CC",
        "address": "KR Judsada",
        "phone": "3059791775"
    },
    "acquirer": {
        "name": "Aquiriente 2",
        "document": "103157712",
        "typeDocument": "CC",
        "address": "KR Judsada",
        "phone": "3059791775",
        "email": "dfasas@dsad.com"
    },
    "invoiceNumber": "FSE1218",
    "expeditionDate": "2020-10-10",
    "payDate": "2020-10-10",
    "methodPayment": "Efectivo",
    "taxTotal": 1200.55,
    "amountTotal": 12000.55,
    "invoiceLine": [
        {
            "description": "prueba",
            "quantity": 1,
            "amount": 12000.55,
            "totalAmount": 12000.55,
            "taxAmount": 1200.55
        }
    ]
}
```

## Referencias

- [Conectar microservicios con colas de mensajes usando Spring y RabbitMQ](https://www.sdos.es/blog/microservicios-mensajes-spring-rabbitmq)

- [Integración de RabbitMQ con Spring](https://www.adictosaltrabajo.com/2015/10/15/integracion-de-rabbitmq-con-spring/)

- [Haciendo BDD en microservicios hexagonales Spring Boot](https://www.adictosaltrabajo.com/2019/01/08/haciendo-bdd-en-microservicios-hexagonales-spring-boot/)

- [Spring Boot + RabbitMQ Consume Message Simple Example](https://www.javainuse.com/spring/spring-boot-rabbitmq-consume)

- [Spring Boot + RabbitMQ Hello World Example](https://www.javainuse.com/spring/spring-boot-rabbitmq-hello-world)

## License
[MIT](https://choosealicense.com/licenses/mit/)
