# Tema 1 - Arquitectura Java EE

## Tipos de librerías de Java

+ JSE: Escritorio, cadenas de caracteres, acceso a datos, lo básico
+ JEE: Web, JPA
+ JME: Móvil, Raspberry
+ Android
+ Spring (muy relacionado con JEE)
+ Hibernate (persistencia JPA)

## Requerimientos para usar un programa Java

+ Sistema Operativo
+ Java Virtual Machine (JVM)
+ Servidor web (Apache, Lotus Domino, IIS)
+ Servidor de aplicaciones (Tomcat, GlassFish, WildFly, WebLogic, WebSphere)
+ Aplicación Java EE

# Contenido de Java EE

+ Componentes: lo que forma un programa Java
  + Servlets
  + Java Server Pages (JSP)
  + Enterprise JavaBeans (EJB)
+ Servicios
  + JSF
  + JPA
  + JDBC
  + JNDI
  + JAX-WS
  + JAX-RS
  + JMS (Muy relacionado con EJB)

## Contenido de aplicación Java

+ Estructura Raiz > web-inf > classes | lib
  + Raíz: JSP y HTML
  + web-inf: web.xml (para el servidor de aplicaciones). También se usan "anotaciones" directamente en el código, ambas se pueden usar a la vez.

  ```Java
  // Ejemplo de anotación
  @WebServlet("/MiServlet")
  ```

  + classes: .class
  + lib: .jar
  + Todo se puede comprimir en un archivo .war (Tomcat lo soporta)
  + Se pueden juntar varios .war o .jar en un .ear (Tomcat no lo soporta)
+ Capa/Contenedor  Web
  + Servlets + JSP
  + Genera páginas web
  + Recogida de parámetros
  + Transferencia de peticiones
  + Mantenimiento de datos
+ Capa/Contenedor  Negocio
  + EJB o clases estándares Java
  + Tomcat no lo lleva, solo tiene contenedor web

## Configurar entorno de desarrollo

+ jdk.1.8.0_161 debe estar en C:\Program Files\Java si no lo tienes ...
+ Instalar Java SE Development Kit 8
+ Instalar/Extraer Eclipse (for Java EE) con eclipse-jee-oxygen-3a-win32-x86_64.zip
+ Instalar/Extraer Tomcat con apache-tomcat-9.0.6-windows-x64.zip
+ Arrancar Eclipse, cambiar perspectiva a Web
+ En la pestaña Servers > Add Server > Apache > Tomcat 9 > Carpeta de Tomcat
+ Si en vez de **Tomcat**, instalamos **Glassfish**, tenemos que añadir un repositorio [repositorio](http://download.oracle.com/otn_software/oepe/12.2.1.8/oxygen/repository/) primero
  + Pestaña Help > Eclipse Marketplace > GlassFish Tools (ya no funciona)
  + Pestaña Help > Install new Software > Pegar url y intro > Tools
    + Oracle Jave EE Tools > Aceptar
  + Servers > Add Server > Glassfish
    + \software\payara41\glassfish
    + C:\Program Files\Java\jdk1.8.0_161
  + Click derecho en el proyecto > Targeted Runtimes > Glassfish
+ Debe estar también en Windows > Preferences > Server > Runtime Environment
+ Tomcat le da a Eclipse las librerías Java Enterprise
+ File > New > Dynamic Web Project > 01_ejemplo_app > next > next ...
+ Content root: es parte de la url y no tiene porque coincidir con el proyecto
+ Web.xml de momento no lo necesitamos
+ No cambiar la perspectiva
+ Click derecho en 01_ejemplo_app > new > Servlet
+ Java package: servlets, Class name: MiServlet > next
+ URL mapping es la url del servlet, tampoco tiene que coincidir con el nombre > next
+ Desmarcamos todo y marcamos service > Finish
+ <kbd>Control + May + o</kbd>: importa las clases necesarias automáticamente
+ <kbd>Alt + May + X</kbd>, R: arranca en el servidor el servlet
+ Generar constructores: click derecho > Source > Generate constructor using fields
+ Generar getters/setters: click derecho > Source > Generate Getters and Setters

## Recogida de parámetros

+ Se usan las funciones service (GET y POST), doGet (GET), doPost (POST)
+ Si se envían parámetros por GET y se usa el doPost se obtiene un error 405
+ Si se produce una excepción en el código se produce un error 500
+ Cuando comparamos textos es mejor usar .equals que ==

## Transferencia de peticiones

+ Un servlet le puede pasar el control a otro utilizando
  + RequestDispatcher rd = request.getRequestDispatcher("Entrada")
  + rd.forward(ServletRequest request, ServletResponse response)
  + rd.include(ServletRequest request, ServletResponse response)
  + response.sendRedirect("Entrada")
  + El primero, no hace un 301, la url se mantiene
  + El segundo, no hace 301 y devuelve el control al primer servlet
  + El tercero, hace un 301 y mantiene el control en el último servlet

## Mantenimiento de datos

+ Parámetros de petición
  + Van en la url (GET) o en la petición (POST)
  + request.getParamenter("key");
+ Atributos de petición
  + Duran lo que dura la petición
  + request.setAttribute("key", value);
+ Atributos de sesión
  + Duran lo que dura la sesión del navegador (unos 30 min sin hacer request)
  + request.getSession().setAttribute("key", value);
  + En los JSP también se puede (String)session.getAttribute("usuario");
  + All llamar al método session.invalidate() se cierra la sesión
  + Para borrar un atributo en concreto se hace un session.removeAttribute("k")
+ Atributos de aplicación
  + Cuando se quieren compartir datos entre usuarios (número de visitantes)
  + request.getServletContext().setAttribute("key", value) es una opción
  + Pero es mejor this.getServletContext().setAttribute() (this es el servlet)
+ Cookies
  + Datos de tipo texto que se almacenan en el cliente y se envía en la cabecera del response
  + Se guarda el identificador de gmail para no loguearse de nuevo
  + Preferencias de usuario
  + jsessionid está implícita e identifica la sesión del usuario

## Documentación

[https://docs.oracle.com/javaee/7/api/](https://docs.oracle.com/javaee/7/api/)

## Loop

Enhanced foreach for(String h:horas) { out.println(h); }

## Renombrar archivo Java

Click derecho > refactor > rename

## Duplicar proyecto

+ Click derecho > copiar y luego pegar
+ Cambiar antigua referencia de web.xml
+ Click derecho > properties >  Web Project Settings

## Web.xml

Si no se ha incorporado al crear el proyecto, se puede hacer con

+ Click derecho > Java EE tools > generate deployment script stub
+ <welcome-file>login.html</welcome-file>
+ La página de bienvenida se indica aquí
+ El timeout de la session

## Tomcat lento

+ Cuanto más proyectos tenemos en Eclipse asociados a Tomcat, más despacio irá
+ En la pestaña Servers hacemos click derecho en los proyectos que no usemos y seleccionamos "remove"
+ Actualizar .settings\org.eclipse.wst.common.component

## Java Server Pages (JSP)

+ HTML + Scriptlets
+ New dynamic web project (como los servlets)
+ Click derecho > New JSP file
+ Para poner sentencias java se usa <% for(int i=1;i<=6;i++) { %> (puede acabar en ;)
+ Para hacer un echo se usa <%= i %> (**nunca** puede acabar en ;)
+ Los objetos Request, Response, Session y Application están embebidos y no hace falta importarlos
+ Para importar una clase en un JSP, se hace dentro de la directiva:

```Java
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.ArrayList, java.time.*" %>
```

+ Para poner comentarios se usa <%-- --%>
+ Hay unas etiquetas implícitas de JSP como "JSP:Forward" y "JSP:Usebean", pero no son de JSTL y no hay que confundirlas.
+ Si se declara una variable con exclamación, se crea como atributo y no como variable local, y esta se inicializará a 0 <%! int a; %>

## Listeners

+ Eventos como:
  + Inicia aplicación: request.getSession().setAttribute("key", value);
  + Finaliza sesión
  + Inicia aplicación: request.getServletContext().setAttribute("key", value)
  + Finaliza aplicación
+ Se crea una acción cuando se detecta un evento
+ Se utiliza para inicializar atributos, por ejemplo una sesión
+ HttpSessionListener
+ Click derecho > New > Listener > Next > Lifecycle
+ Usa la anotación @WebListener
+ En un servlet no se inicia los listener, a no ser que se haga por código, pero en JSP como por defecto inicializa: request, response, session y application, se ejecutaría al inicio de cargar la página. A no ser que que se use el atributo session="false" en la directiva superior

## Bases de datos

+ Pertenecen a la capa de negocio, nos conectamos a través de clases
+ Abrimos MySQLAdministrador
  + Stored connection (nada)
  + Server Host: localhosts
  + Username: root
  + Password: root
+ En "Catalogs" tenemos las bases de datos que vamos  a usar
+ Para acceder desde Java se utiliza la clase JDBC que con el uso de un "driver", abstrae al código de cómo conectarse a la base de datos.
+ Con SQL realizaremos 4 operaciones: Select, Insert, Update, Delete
+ Necesitaremos una libreria externa mysql-connector-java-5.1.46.jar
+ Dentro de Java Resources > Libraries
+ Toda aplicación Java web necesita dos librerias
  + Java Standar: JRE System (usamos la v8 pero vamos por la v11)
  + Java Enterprise: Apache tomcat v9.0
+ Click derecho > Properties > Java Build Path > Libraries > Add External JARs
+ Click derecho > Properties > Deployment Assembly > Add > Java Build Pack Entries
+ La primera hace que el jar aparezca debajo de Referenced Libraries
+ La segunda para que cuando se exporte el proyecto, se incluya el jar también.
+ **DAO**: Data Access Object
+ ResultSet: es un puntero hacia las filas de la base de datos, no contiene datos, solo es un enlace.
+ Siempre que creamos una clase (Java Bean) representando una tabla, hay que incluir todos los campos, incluso los autonuméricos, porque no solo la vamos a utilizar para escribir en la base de datos, si no también, para leer de ésta.

## Datasource

Es un objeto que se crea dentro del servidor de aplicaciones y contiene un "pool" con un número finito de conexiones contra una base de datos. Cuando la aplicación abre una conexión, no la abre directamente, se la pide al pool. Cuando ésta la cierra, no la cierra realmente, se la devuelve al pool. Se necesita un pool por cada base de datos.

Para configurar el Datasourse, lo hacemos desde la consola administrativa de Glassfish:

+ Pero primero hay que copiar el driver (mysql-connector-java-5.1.46.jar) de MySql a la carpeta: software\payara41\glassfish\domains\domain1\lib\ext
+ Arrancamos el servidor de Glassfish
+ Accedemos a [http://localhost:4848/](http://localhost:4848/)
+ Resources > JDBC > JDBC Connection Pools > New JDBC Connection Pool
+ Pool Name: poollibros, Resource Type: java.sql.DataSource Database, Driver Vendor: MySql
+ Next > Scroll y bajo Additional Properties (231) > Ordenar por nombre
+ DatabaseName: libros, User: root, Password: root, ServerName: localhost,
+ Url|URL: jdbc:mysql://localhost:3306/libros > Finish
+ Click poollibros > Click ping > Si nos hemos equivocado vamos a Additional Properties
+ Resources > JDBC > JDBC Resources > New JDBC Resource
+ JNDI Name: jdbc/librosds, Pool Name: poollibros > OK
+ En el código debo usar las librerías (JNDI):
  + javax.sql.datasource
  + javax.naming.Context
  + javax.naming.InitialContext
  + javax.naming.NamingException;

## Referencias (alias)

Las referencias o alias se anotan en el web.xml y los valores asociados a esas referencias se incluyen en otro .xml, que dependiendo del servidor tendrá un nombre diferente:

+ Glashfish: glassfish-web.xml

Para crear una referencia, creamos el web.xml

+ Click derecho en proyecto > Java EE tools > Generate Deployment Descriptor Stub

```xml
  <resource-ref>
    <res-ref-name>aliaslibros</res-ref-name>
    <res-type>javax.sql.DataSource</res-type>
    <res-auth>Container</res-auth>
  </resource-ref>
```

+ Click derecho en proyecto > New > Other > Glassfish web (no funciona)
+ Crear un fichero glassfish-web.xml en el mismo directorio que el web.xml
+ Actualizar  el context-root para que coincida con el proyecto
+ Posteriormente incluir:

```xml
  <resource-ref>
    <res-ref-name>aliaslibros</res-ref-name>
    <jndi-name>jdbc/librosds</jndi-name>
  </resource-ref>
```

+ En el código reemplazar jdbc/librosds con java:comp/env/aliaslibros

## CDI (Common Dependency Injection)

También llamado inyección de dependencias, Introducido por Spring, se utiliza cuando un componente necesita usar un objeto de otro componente, en lugar de llamarlo directamente, es el entorno Java el que coge ese objeto y lo mete en una variable.

Se implementa con anotaciones:

+ @Named (javax.inject.Named) con el nombre de la clase en minúsculas:
  + @Named("daoClientes")
+ @RequestScoped (javax.enterprise.context.RequestScoped) Scope de petición
  + @RequestScoped
+ @Inject (javax.inject.Inject) En el componente que necesite usar el objeto de otro
  + @Inject
  + DaoTemas dTemas;
+ @Named("daoLibros") En el caso de que haya dos clases que implementen la misma interfaz, se utilizar esto bajo el @Inject

Las variables deben ser atributos, no pueden ser locales. En general, cuando una capa necesita un objeto de otra, este se tiene que inyectar, nunca crear.

# Tema 2 - Modelo Vista Controlador

## Introducción

El patrón MVC define como hay que estructurar una aplicación:
+ **Modelo**: (.class) tiene los objetos con sus métodos
+ **Vista**: (.jsp) genera respuestas, cogen datos y los muestran, no toman decisiones
+ **Controlador**: (.java) todas las peticiones como formularios, enlaces, pasan por aquí, además, interactúa con el modelo para ejecutar sus métodos y manda los resultados a la vista. Los enlaces no pueden ir a un html, deben ir a un servlet.

La mayor ventaja que tiene, a parte de organizar el código, es la escalabilidad.

## Controlador

Es el componente más importante y puede ser de dos tipos:

+ **Front controller**: Servlet que realizara un “forward” (mas bien un “include” porque más adelante le vuelve el control) en función de la naturaleza de la aplicación
+ **Action**: Servlets encargados de realizar una acción, procesa la petición y llamará al método correspondiente del modelo.

Como las peticiones deberán estar centralizadas en un solo front controller, para diferenciar acciones, se pueden utilizar parámetros en la url del servlet:

+ Peticiones a páginas estáticas: ?op=ToRegistro
+ Peticiones que requieran acciones: ?op=DoGuardar

## Vistas

Serían las páginas HTML y JSP. Se intentará reducir al máximo el código Java utilizado en estas últimas (scriptlets).

+ **JSTL**: La “Java Standard Tag Library” es una alternativa al código Java son las etiquetas JSTL. La librería JSTL forma parte del Java EE.
+ **EL**: Las “Expression Language” se usan en conjunción con las etiquetas JSTL. Se diferencian de las expresiones de JSP (<% %>) por estar delimitadas con ${}. Tiene sus propios objetos y operadores.

## EL (Expression Language)

En cuando a objetos, no tienen métodos, solo propiedades, por ejemplo:

+ Para replicar <%= request.getParameter(“usuario”) %> en EL haríamos lo siguiente ${param.usuario}.

+ Para clases propias como <% Persona pr = (Persona)request.getAttribute(“per”); %> utilizaremos ${requestScope.per.nombre}
+ Para cookies, usamos ${cookie.fecha.value}

No se puede compartir código entre el del JSP y el de EL, pero si entre JSTL y EL

Hay varios tipos de objetos en  EL:

**Objetos implícitos**:

+ Param
+ RequestScope
+ SessionScope
+ ApplicationScope
+ Cookie

Hay varios tipos de operadores en EL:

+ Aritméticos: +, -, *, /, %
+ Condicionales: >, <, >=, <=, !=
+ Lógicos: &&, ||, !
+ Otros: Empty (equivalente al null, vacio, 0), ?: (ternario)

## JSTL (Java Standard Tag Library)

Hay varias librerías JST:
+ Core (la que vamos a usar)
+ FMT
+ XML
+ SQL

No vienen con el Tomcat (aunque sí en Glassfish), al igual que el driver de MySQL. Para obtener y organizar dichas librerías empezaremos a utilizar Maven:
+ Click derecho > Configure > Convert to Maven Project > Finish
+ Se creará un pom.xml en el que aparecerán las librerías que se necesitan.
+ Vamos a https://mvnrepository.com/ > Buscamos JSTL
+ Seleccionamos https://mvnrepository.com/artifact/javax.servlet/jstl (la mas usada)
+ Seleccionamos https://mvnrepository.com/artifact/javax.servlet/jstl/1.2 (última)
+ Copiamos el XML y lo pegamos dentro de una nueva etiqueta <dependencies>
+ Al guardar se se suele guardar solo, si no lo hace damos click derecho al proyecto:
  + Maven > Update Project
+ La librería debería aparecer en Java Resources > Libraries > Maven Dependencies
+ Tambien debería estar en Properties > Deployment Assembly

Si queremos deshabilitar Maven porque utilizamos un datasource (que no necesita driver MySQL) o estamos en Glassfish (que tiene las librerías JSTL), hacemos click derecho en el proyecto > Maven > Disable Maven Nature
En el JSP, para indicarle que vamos a usar JSTL, se usa la directiva:

```Java
// prefix suele usar “c”
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

Ya no voy a necesitar importar librerías en <%@ page import="beans.Contacto">

Existen varias etiquetas en JSTL:

+ SET: Para crear una variable con JSTL se establece el valor con una expresión EL

```JSP
<c:set var="contactos" value="${requestScope.contactos}" />
```

+ IF: Para crear un “if” con JST se establece la condición con una expresión EL

```JSP
<c:if test="${!empty contactos}">...</c:if>
```

+ CHOOSE: Como IF no tiene ELSE, cuando lo necesitemos usaremos CHOOSE

```JSP
<c:choose>
<c:when test=”${condicion}”>...</c:when>
<c:when test=”${condicion}”>...</c:when>
<c:otherwise>...</c:otherwise>
</c:choose>
```

+ FOREACH: Para crear un bucle también se usa una expresión EL

```JSP
<c:forEach var="con" items="${contactos}">...</c:forEach>
// después ${con.nombre} se podrá imprimir la variable
// si necesito un índice, se declara varStatus="vs" en el forEach y luego ${vs.index}
// Para un bucle sin items <c:forEach var=”i” begin=”1” end=”10”>
```