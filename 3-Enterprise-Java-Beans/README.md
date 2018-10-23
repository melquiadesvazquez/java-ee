# Tema 3 - Enterprise Java Beans (EJBs)

## Introducción

Para crear un modelo, en lugar de crear una clase estándar, se utiliza un contenedor EJB y dentro de éste se crean las clases EJB. Con Java EE7 se utiliza la versión EJBs 3.x, hay un salto de calidad remarcable a partir de ésta versión (salió con Java EE5)

## Ventajas

Las ventajas que tiene son:

+ Inyección de dependencias: el contenedor se lo proporciona al EJB, es mucho más potente que la que utilizamos en CDI
+ Pool de instancias: similar al pool de conexiones pero con instancias, para que no se desborde la creación de objetos y siempre esté limitada
+ Varios tipos de clases EJB, hay 4
+ Gestión automática de transacciones, esto está relacionado con las bases de datos
+ Seguridad declarativa, políticas de seguridad en la lógica de negocio
+ Acceso remoto, no tiene por qué estar en local, lo que ayuda a reutilizar el código
+ Ejecución asíncrona, no hace falta esperar a que acaben su ejecución

## Tipos

Hay varios tipos de clases EJB, hay 4:

+ EJB de sesión sin estado (Stateless)
  + No necesitan mantener información para la siguiente llamada
  + Ejemplo, autenticar un usuario
+ EJB de sesión con estado (Stateful)
  + Mantienen el estado y lo guardan en atributos
  + Ejemplo, añadir al carrito
  + Con servlets solo funcionan con JNDI

+ Singleton
  + Una única instancia por aplicación compartida por todos
  + Habría que utilizar bloques sincronized al ser la misma instancia para todos los usuarios, pero es suficiente con la anotación @Lock encima del método
+ MDB (Message Driven Bean)
  + El más complicado, se basa en el concepto de mensajería
  + Un aplicación manda un mensaje a una cola de mensajes y la otra aplicación lo va leyendo cuando puede.
  + El MDB implementa al destinatario y JMS a la cola
+ Entity (que ya no existe)

En conclusión, si no tienes que guardar datos, Stateless, si tienes que guardar datos no compartidos entre usuarios, Stateful. Por último, si tienes que guardar datos y compartirlos entre todos los usuarios, Singleton.

## Estructura

El patrón de factoría está implícitamente presente:

+ Interfaz de negocio
+ Clase implementación

El contenedor crea un objeto negocio en el JNDI (donde se guardan los Datasources), que hace de intermediario entre el cliente (servlet o EJB), y el contenedor EJB, ya que el cliente no interacciona directamente con el contenedor.

## Creación

+ File > New > Dynamic Web Project > Next …
+ Click derecho en el proyecto > New > Other > Session Bean (EJB 3.x)
+ Package: ejbs, ClassName: SaludoEjb, StateType: Stateless, Local
+ **No se utiliza el constructor, eso ya lo gestiona el contenedor. En lugar de constructor se usa la anotación @PostConstruct en un método cualquiera**
+ Tras generar el método en la clase, para llevarlos a la interfaz, hacemos click derecho en el código > Refactor > Pull up
+ Si hemos renombrado un archivo sin interfaz usamos Refactor > Extract Interface
+ Ejemplo:

```Java
  @Stateless
  @LocalBean
  public class SaludoEjb implements SaludoEjbLocal {
    @Override
    public String getSaludo(String nombre) {
      return "Bienvenido a los EJBs " + nombre;
    }
  }
```

+ Podemos meter el Datasource en una variable con:

```Java
  @Resource(mappedName = "java:comp/env/aliaslibros",
    type = javax.sql.DataSource.class)
  DataSource ds;
```

+ Para destruir el objeto, se crea un método y se le añade la anotación @Remove. A esta función la llama el programador
+ Para que algo se ejecute automáticamente antes de destruirse la sesión se utiliza @PreDestroy

## Acceso desde app cliente

+ Se utiliza inyección de dependencias y hay 2 formas:

```Java
  // desde Java EE 5
  @EJB
  SaludoEjbLocal miejb;

  // desde Java EE 7
  @Inject (desde Java EE 7)
  SaludoEjbLocal miejb;
```

+ Se puede utilizar JNDI (Datasource)
  + Context ct = new InitialContext();
  + SaludoEjbLocal miejb = (SaludoEjgLocal)ct.lookup("java:global/<proyecto>/<.java>!ejbs.<clase>");

Hay casos, por ejemplo cuando se utilizan EJBs de tipo Stateful, donde no se deben aplicar inyección de dependencias y es aquí donde utilizaremos el JNDI.

## Pasivación / Activación

Se libera la memoria de las instancias y se descargan a disco, dependiendo del servidor, los algoritmos son diferentes. Cuando un componente llama al objeto, el servidor lo vuelve a traer del disco a memoria. Si creamos un método y le añadimos la anotación @PrePassivate lo ejecutaremos cuando pase a pasivación y para detectar la activación se utiliza @PostActivate

## Referencias a un EJB

En lugar de utilizar:

```Java
ct.lookup("java:global/04_contador_stateful/ContadorEjb!ejbs.ContadorEjbLocal");
```

se utiliza

```Java
@EJB(name="aliasejb", beanInterface=ejbs.ContadorEjbLocal.class)
```

Encima de @WebServlet

Y posteriormente

```Java
ct.lookup("java:comp/env/carritoRef");
```

## Message Driven Bean (MDB)

Se utiliza el servicio de mensajería de Java JMS para aquellos casos en que dos aplicaciones se deben comunicar entre sí de manera asíncrona. El consumidor de mensajes (la aplicación que los recibe) se implementa con un MDB. El generador de mensajes (la aplicación que envía), utiliza el Connection Factory, que tiene que configurarse junto con la cola en el servidor.

+ Entramos en la consola administrativa [http://localhost:4848/](http://localhost:4848/)
+ Resources > JMS Resources > JMS Destination Resources > New
+ JNDI Name: jms/ejemplocola, Physical Destination Name: ejemplocola
+ Resource Type: javax.jms.Queue > OK
+ Resources > JMS Resources > JMS Connection Factories > New
+ JNDI Name: jms/ejemplofactory, Resource Type: javax.jms.QueueconnectionFactory
+ En Eclipse > New > Other > Message Driver Bean > ejbs, SaludoMdb, Queue

```Java
@MessageDriven(
  activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/ejemplocola")})
```

+ En Eclipse > New > Servlet

```Java
  @Resource(mappedName="jms/ejemplofactory", type=ConnectionFactory.class)
  ConnectionFactory cf;

  @Resource(mappedName="jms/ejemplofactory", type=Queue.class)
  Queue cola;
```

## EJBs y Servlets Asíncronos

Cuando no se desea utilizar una cola y el tiempo de espera puede ser grande, se pueden utilizar los EJBs asíncronos, utilizando la notación @Asynchronous.

El tipo que se devolverá

```Java
// Siendo Integer una clase cualquiera
Future<Integer>
```

y al final de la función se devolverá

```Java
return new AsyncResult<Integer>(result)
```

Posteriormente, cuando necesitemos obtener el valor se utilizará

```Java
Future<Integer> ft = calculo.sumaNumeros(50).get();
```

Si necesitamos hacer un bucle antes de que se ejecute, lo metemos en un bucle

```Java
while(!ft.isDone()) {System.out.println("esperando!!!");}.
```

Tambien existen los servlets asyncronos, se crean con la notación:

```Java
@WebServlet(asyncSupported = true, urlPatterns = { "/Asincrono" })
```

Posteriormente añadimos:

```Java
AsyncContext contexto = request.startAsync();
contexto.setTimeout(1000);
contexto.start(new Runnable() {
  public void run() {
    <FUNCION>
    contexto.complete();
  };
});

contexto.addListener(new AsyncListener() {
  public void onComplete(AsyncEvent arg0) throws IOException {
    <FUNCION>
  }
});
```

## Transacciones

Dentro de un método EJB, si se ejecuta un SQL, se generará una transacción automáticamente, si se produce una "Runtime Excepcion", se anula la transacción, pero no cuando se produce un "SQLException". Por lo tanto, lo que se puede hacer, es en el catch del "SQLException", lanzar una "Runtime Exception", o una "Excepcion" personalizada que herede de "Runtime Exception".

Si queremos configurar la transaccionalidad del EJB, se usa:

+ Si está dentro de una transacción abierta (es llamado por otro método EJB, devuelve el control y no realiza el commit (lo hace el padre por defecto)

```Java
@TransactionAttribute(TransactionAttributeType.REQUIRED)
```

+ Crea su propia transacción y la confirma al acabar, si falla el método, no afecta al método padre (transacción abierta)

```Java
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
```

+ Al método hay que llamarle dentro de una transacción ya creada

```Java
@TransactionAttribute(TransactionAttributeType.MANDATORY)
```

+ Si hay una transacción abierta participa y si no, no participa

```Java
@TransactionAttribute(TransactionAttributeType.SUPPORT)
```

+ No se realizan transacciones

```Java
@TransactionAttribute(TransactionAttributeType.NEVER)
```

Cuando se llama a un método asíncrono dentro de una transacción, este crea su propia transacción (REQUIRES_NEW) y si falla, no afecta al método padre.

Para desactivar la gestión de transacciones desde el EJB, se utiliza la anotación:

@TransactionManagement(TransactionMangementType.BEAN)

Si además estamos usando un JPA, debemos seleccionar en el persistance.xml

+ Connection > Transaction type > Resource Local

## Temporizadores

Se utilizan cuando necesitamos la ejecución de una tarea en segundo plano periódicamente (cronjob). Es gestionado por el contenedor EJB y se aplica sobre EJBs de tipo Stateless (aunque se puede utilizar en Stateful y Singleton). El método que se vaya a ejecutar periódicamente, debe tener la anotación @Timeout y pueden ser varios, no sólo uno.

Junto al método que se ejecuta periódicamente, hay que crear uno que inicia el temporizador y otro que lo para.

Se llama al recurso SessionContext y se crea la variable global Timer de EJB:

```Java
  @Resource
  SessionContext sc;
  // Esto no pertenece a @Resource
  Timer tm;
```

Se crea el método periódico:

```Java
  @Timeout
  void imprimirMensaje(Timer t) {<FUNCION> }
```

Se crean los métodos para crear y cancelar el timer:

```Java
  public void iniciarTemporizador(long periodo) {
    tm = sc.getTimerService().createTimer(retardo, periodo, adicional);
  }

  public void detenerTemporizador() {
    tm.cancel();
  }
```
