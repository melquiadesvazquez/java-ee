# Tema 6 - Hibernate

## Introducción

Por defecto Glassfish utiliza el motor de persistencia de Toplink, pero hay otras opciones como Hibernate. La desventaja de usarlo frente a Toplink, es que no reconoce los EJBs ni su gestión de las transacciones, por lo tanto, en Hibernate, tendremos que gestionar las transacciones nosotros mismos.Tampoco dispondremos de inyección de dependencias.

## Configuración

Para configurar un proyecto con hibernate, haremos lo siguiente:

+ Click derecho en proyecto > Configure > Convert Maven Project
+ Vamos a [https://mvnrepository.com/artifact/org.hibernate/hibernate-core](https://mvnrepository.com/artifact/org.hibernate/hibernate-core)
+ Aquí obtendremos el motor y la implementación para JPA
+ Copiamos el siguiente código en el POM.xml

```xml
  <dependencies>
      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.2.12.Final</version>
    </dependency>
  </dependencies>
```

+ En persistence.xml > General > Persistence provider
+ Ponemos org.hibernate.jpa.HibernatePersistenceProvider
+ En el source dentro del persistance-unit ponemos:

```xml
  <properties>
    <property name="hibernate.transaction.jta.platform"
    value="org.hibernate.service.jta.platform.internal.SunOneJtaPlatform"/>
    <property name="hibernate.enable_lazy_load_no_trans" value="true"/>
  </properties>
```

+ Cuando se cambia el motor de persistencia por defecto a Hibernate, si Glassfish no está configurado adecuadamente, Eclipse dará un error al arrancar relacionado con la librería, para arreglarlo:
+ Copiamos el archivo C:\Users\<user>\.m2\repository\org\jboss\logging\jboss-logging\3.3.0.Final\jboss-logging-3.3.0.Final.jar
+ Lo pegamos en C:\Users\<user>\...\payara41\glassfish\modules
+ En esa misma carpeta renombramos jboss-logging.jar a _jboss-logging.jar
+ Instalamos un plugin Help > Eclipse Marketplace > JBoss Tools 4.5.3.Final
+ Instalamos sólo Hibernate Tools y JBoss Maven Hibernate Configurator

## Framework Hibernate

Antes de empezar a usarlo, hay que configurarlo y hay tres partes:

+ Hibernate config: equivalente al persistance.xml
  + Session factory name: similar al persistence-unit name
+ Entidades: equivalente a los java beans con las anotaciones
+ Archivos orm(object relational mapping): Similar a las anotaciones @joinColumn

Una vez configurado el framework, se puede empezar a usar el API:

+ Click derecho en proyecto > New > Other > Hibernate > Hibernate Configuration file
  + Session factory name: agendaFactory
  + Database: MySQL
  + Driver class: com.mysql.jdbc.Driver
  + Connection URL: jdbc:mysql://localhost:3306/agenda
  + Default catalog: agenda
  + Username / Password
  + Si no funciona 5.3, probar con 5.2
+ Click derecho en proyecto > New > Other > Hibernate > Hibernate Reverse Ing.
+ Seleccionar carpeta Proyecto > src y Next… y ponemos

```xml
  <hibernate-reverse-engineering>
    <schema-selection match-catalog="agenda"/>
    <table-filter match-name="contactos"/>
  </hibernate-reverse-engineering>
```

+ Add class > Contacto.java con sus propiedades correspondientes
+ Click derecho en proyecto > New > Other > Hibernate > Hibernate XML Mapping f.

```xml
  <hibernate-mapping>
    <class name="entidades.Contacto" table="CONTACTO">
      <id name="idContacto" type="int">
        <column name="IDCONTACTO" />
        <generator class="assigned" />
      </id>
      <property name="edad" type="int">
        <column name="EDAD" />
      </property>
      <property name="email" type="java.lang.String">
        <column name="EMAIL" />
      </property>
      <property name="nombre" type="java.lang.String">
        <column name="NOMBRE" />
      </property>
    </class>
  </hibernate-mapping>
```

Tenemos otra opción para hacer lo mismo de manera más fácil:

+ Click derecho en proyecto > Configure > Convert to Maven Project
+ Añadir al Pom.xml

```xml
  <dependencies>
      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.2.17.Final</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.38</version>
    </dependency>
  </dependencies>
```

+ Arriba a la derecha cambiar a la perspectiva Hibernate
+ A la izquierda, al lado de Hibernate Configurations, pulsar en el incono [+]
  + Hibernate Version: 5.2
  + Project: nuestro proyecto
  + Database connection: Conexion MySQL, si no existe la creamos
  + Apply and Close, si da un error de configuración, Maven > Update Project
+ Al lado del icono con el play y la maleta, hay otro icono con una flecha, dare y seleccionar Hibernate Code Generation Configurations
  + Console configuation: hibernate
  + Output directory: carpeta **src** de nuestro proyecto
  + Reverse engineer from JDBC connection
  + Package: entidades
  + En la pestaña exporters, seleccionamos Domain code y Hibernate XML Map.
+ Volver a la perspectiva JAVAEE
+ Abrimos hibernate.cfg.xml > Session Factory > Mappings
  + Añadimos primero entidades.Contacto
  + Añadimos como otro mapping entidades/Contactos.hbm.xml
+ En Contactos.hbm.xml tenemos que asegurarnos de que si la clave primaria es autonumerica tiene el valor "identity"

```xml
  <id name="idContacto" type="int">
    <column name="idContacto"/>
    <generator class="identity"/>
  </id>
```

## API Hibernate

La clave está en el Interface Session, similar al entityManager

+ Creamos un paquete modelo.utilidades y metemos el HibernateUtils.java dentro
+ Funciona para la version 4.x, no para la 5.x
+ Para la 5.x https://www.boraji.com/hibernate-5-basic-configuration-example

```java
    package modelo.utilidades;
    public class HibernateUtil {
      private static final SessionFactory sessionFactory;
      static {
        try {
          Configuration configuration = new Configuration().configure();
          ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
          sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
          System.err.println("Initial SessionFactory creation failed." + ex);
          throw new ExceptionInInitializerError(ex);
        }
      }

      public static SessionFactory getSessionFactory() {
        return sessionFactory;
      }
    }
```

+ Para crear la función crearContacto por ejemplo haríamos lo siguiente

```java
  public void guardarContacto(String nombre, String email, int edad) {
    Contactos con = new Contactos(0, nombre, email, edad);
    Transaction tx = null;
    try (Session s = HibernateUtil.getSessionFactory().openSession()) {
      tx = s.beginTransaction();
      s.save(con);
      tx.commit();
    } catch(Exception ex) {
      if (tx!=null) {
        tx.rollback();
      }
      ex.printStackTrace();
    }
  }
```