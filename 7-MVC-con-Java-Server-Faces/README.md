# Tema 7 - MVC con Java Server Faces

# Introducción

El primer framework fue Struts, la primera y segunda versión son totalmente distintas, posteriormente surgió JSF, que tiene cierta similitud con Struts 2. Por último nació Spring MVC.

# JSF (Java Server Faces)

Las capas son:

+ Modelo: no le afecta, sigue como siempre
+ Controlador:
  + Front controller: ya viene con uno (Faces serlvet)
  + Actions: ya viene con uno (Managed bean)
+ Vista:
  + desde la versión 2.x se utilizan XHTML en lugar de JSPs
  + todos los elementos para recoger or mostrar información se generan mediante componentes JSF

## Configuración

Click derecho proyecto > Project Facets > Java Server Faces

Si lo hacemos en Tomcat, dara un error y tendremos que seleccionar Disable Library Configuration dentro de JSF Capabilities. Luego usaremos Maven para importar la librería

```xml
  <dependencies>
    <!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-api -->
    <dependency>
      <groupId>com.sun.faces</groupId>
      <artifactId>jsf-api</artifactId>
      <version>2.2.13</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-impl -->
    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-impl</artifactId>
        <version>2.2.13</version>
    </dependency>
  </dependencies>
```

Para crear un managed bean

```java
  @ManagedBean
  public class LibrosBean
```

Para utilizar un managed bean dentro de otro

```java
  @ManagedProperty("#{temasBean}")
  private TemasBean tbean;
```

## XHTML

En JSF no existen los archivos HTML nii JSP con plantillas XHTML a base de componentes:

```html
  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:h="http://java.sun.com/jsf/html">

  <h:head></h:head>
  <h:body>
    <h:form>
      Usuario:<h:inputText value="#{loginBean.usuario}"/><br/><br/>
      Contraseña:<h:inputSecret value="#{loginBean.password}"/><br/><br/>
      <h:commandButton action="#{loginBean.login()}" value="Entrar"/>
    </h:form>
  </h:body>
  </html>
```

## Componentes gráficos JSF

Textos:

+ inputText
+ inputSecret
+ outputText

Enlaces:

+ link

Comandos:

+ commandButton
+ commandLink (Igual que el button pero con aspecto de link)

Datatable

Listas:

+ Selección simple:
  + select: selectonemenu
  + li: selectonelistbox
  + radio button: selectoneradio
+ Selección múltiple:
  + select combo: selectmanymenu
  + ol: selectmanylistbox
  + checkbox:selectmanycheckbox

En un managed bean, el que genera el contenido y el que lo recoge, no tiene por que ser el mismo.

## Acceso al contexto de aplicación

```java
  public static void guardarAtributoSesion(String nombre, Object valor) {
    ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
    HttpSession s = (HttpSession)ex.getSession(true);
    s.setAttribute(nombre, valor);
  }
```

## Ciclo de vida petición JSF

+ RestoreView:
  + petición inicial: al entrar a la página
  + se construye el árbol de componentes
  + se crea una instancia del objeto managed bean (antes comprueba si existe)
  + se obtiene los valores con los métodos get
+ Apply Request Values:
  + convertimos los valores al formato de los atributos del managed bean
+ Process Validation:
  + comprobamos que los valores siguen las reglas de validación
+ Update Model Values:
  + se llaman a los métodos set del managed bean
+ Invoke Application:
  + se ejecuta el método controlador de acción del managed bean
  + si la función devuelve un string(url), devuelve el control a esa página
+ RenderResponse
  + postback o llamada a sí mismos
  + transformación de etiquetas jsf en html
  + si el bean tiene ámbito de request (por defecto) se destruye, si tiene ámbito de sesión (sessionScope) se mantiene.

## Ajax con JSF

Para utilizar ajax en JSF se añade la siguiente etiquete al componente "trigger" de la peticion:
```html
<f:ajax event="change" render="tabla tema" execute="@form"/>
```

Dentro del atributo render, se pone los componentes que necesitan ser actualizados tras la petición y en el atributo execute se ponen los parámetros que queremos mandar con la petición, si se quieren mandar todos los campos del formulario, se utiliza @form.

## Validación con JSF

Se realiza en el lado del servidor y hay varios tipos:

+ Campo requerido:

```html
  <h:inputText id="usuario" required="true" requiredMessage="falta usuario" />
  <h:message for="usuario" />
```

+ Rango numérico:

```html
  <h:inputSecret id="password">
    <f:validateLength minimum="4"></f:validateLength>
  </h:inputSecret>
```

+ Rango de caracteres
+ Máscara

Los mensajes de validación se guardan en:

+ javax.faces.jar > javax.faces > Messages_en.properties
+ Para personalizarlos creamos src/mensajesPersonalizados_es.properties

```html
   Reeemplazamos en faces-config.xml -->
  sage-bundle>resources.application</message-bundle>
   con -->
  sage-bundle>mensajesPersonalizados</message-bundle>
```

+ No se pone el sufijo _es porque usa el archivo dependiendo del locale
+ Quedaría asi:

```html
  <application>
    <message-bundle>mensajesPersonalizados</message-bundle>
    <locale-config>
      <default-locale>en</default-locale>
      <supported-locale>es</supported-locale>
    </locale-config>
  </application>
```

También podemos crear un validador personalizado en lugar de utilizar uno predefinido, para ello creamos una clase en implemente la interfaz javax.faces.validator.Validator.

+ Utilizamos la notación @FacesValidator(value="vEmail")
+ Implementamos el método validate
+ lanzamos una excepción en caso de no validar "throw new ValidatorException"
+ Dentro del faces-config creamos:

```html
  <resource-bundle>
    <base-name>mensajesPersonalizados</base-name>
    <var>m</var>
  </resource-bundle>
```

+ En la clase que valida los datos ponemos:

```java
  ResourceBundle rb = arg0.getApplication().getResourceBundle(arg0, "m");
  throw new ValidatorException(new FacesMessage(rb.getString("email.incorrecto")));
```

## Primefaces

Enriquece JSF con más funcionalidades, para configurar un proyecto:

+ Añadimos JSF al proyect Faces
+ Convertimos el proyecto a Maven
+ Añadimos al Pom.xml

```xml
    <dependency>
      <groupId>org.primefaces</groupId>
      <artifactId>primefaces</artifactId>
      <version>6.2</version>
    </dependency>
```