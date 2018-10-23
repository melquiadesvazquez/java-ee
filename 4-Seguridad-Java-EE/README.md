# Tema 4 - Seguridad Java EE

## Introducción

Se define como un proceso de Autenticación y Autorización de usuarios, en el cual se crean unas políticas de seguridad y las aplicamos. Estas se hacen de manera "declarativa" (a nivel de contenedor, nunca a nivel de código). Para tal efecto, contamos con dos servicios, uno a nivel de contenedor web (servlets y jsp) y otro a nivel de contenedor EJB.

## Autenticación

Debe haber una lista de usuarios, que en este caso guardaremos en el servidor de aplicaciones. En Glassfish:

+ Vamos a [http://localhost:4848/](http://localhost:4848/)
+ Configurations > default-config > security > Realms > file
+ Bajo Edit Realm > Hacer click en Manage Users

También debe de haber una lista de roles.

Por otro lado, hay que configurar el web.xml en el que hay 4 tipos de autenticaciones:

+ Basic: la típica autenticación nativa del navegador
+ Digest: Igual que la anterior, pero con la contraseña encriptada
+ Form: Un formulario de login personalizado
+ Cert: con certificado digital, parecido al DNIe

Para configurar web.xml se utiliza:

```xml
  <login-config>
    <auth-method>BASIC</auth-method>
  </login-config>
```

```xml
  <login-config>
    <auth-method>FORM</auth-method>
    <form-login-config>
      <form-login-page>/miformulario.html</form-login-page>
      <form-error-page>/error_autorizacion.html</form-error-page>
    </form-login-config>
  </login-config>
```

## Autorización a nivel de servidor

Una vez que el usuario se ha autenticado, se verifica que puede acceder a la página que quiere acceder. Se basa en las políticas de seguridad o roles que hayamos definido anteriormente en el web.xml. Estas políticas incluyen roles y restricciones:

```xml
  <security-role>
    <role-name>admin</role-name>
  </security-role>
```

```xml
  <security-constraint>
    <web-resource-collection>
      <web-resource-name>lista1</web-resource-name>
      <url-pattern>/menu.html</url-pattern>
    </web-resource-collection>
    <auth-constraint>
      <role-name>admin</role-name>
    </auth-constraint>
  </security-constraint>
```

Además debe de haber un mapeo de usuarios a roles, la aplicación solo entiende de roles y el servidor, sólo de usuarios, este mapeo se hace en el glassfish-web.xml.

```xml
  <security-role-mapping>
    <role-name>admin</role-name>
    <principal-name>user3</principal-name>
  </security-role-mapping>
```

Si se accede a una página sin usuario y contraseña correctos, se devolverá un 403

## Autorización a nivel de código

En los EJBs, se determinan los métodos que pueden ejecutar determinados roles con la anotación @RolesAllowed("{usuarios}").

Para JSPs y servlets, se utiliza

```jsp
  <% if (request.isUserInRole("admin")) { %>
```

## Filtros

Para refinar la autenticación comprobando otros atributos como sesión, cookies, etc, utilizaremos filtros. Estos se pueden encadenar unos con otros y también aplicar sólo a determinados recursos y  funciona tanto cuando recibe la petición como cuando la devuelve.

Haciendo click derecho en el proyecto > New > Filter > … > Finish. Posteriormente, añadimos la anotación @WebFilter(urlPatterns = {"/Controller"}) El problema es que al crear varios filtros, no se decide el orden en que se ejecutan. Sin embargo, si en lugar de anotaciones, se declaran los filtros en el web.xml, aquí sí se podrá.

```xml
<filter>
  <filter-name>mifiltro</filter-name>
  <filter-class>filtros.FiltroLibros</filter-class>
</filter>
<filter-mapping>
  <filter-name>mifiltro</filter-name>
  <url-pattern>/Controller</url-pattern>
</filter-mapping>
```
