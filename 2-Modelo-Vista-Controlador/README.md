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