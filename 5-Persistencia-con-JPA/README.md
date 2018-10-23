# Tema 5 - Persistencia con JPA

## Introducción

La transformación entre un objeto JDBC (SQL) y Java (y viceversa) es muy costoso en todos los sentidos, si entre medias ponemos una capa de persistencia, nos ahorraremos traducir los objetos de un tipo a otro. Esto simplifica la lógica de negocio notablemente.

En la capa de persistencia, el motor de persistencia se encarga de transformar los objetos Java en objetos que entienda la base de datos. Anteriormente se utilizada los EJB de tipo "Entity" (que ya no existen) y éstos representaban filas de la base de datos. Actualmente, se utilizan otros frameworks de persistencia como Hibernate, Ibatis y JPA.

El framework de persistencia ofrece tres elementos:

+ Motor de persistencia
+ Sistema de configuración
+ API

## JPA (Java Persistence API)

No es simplemente un framework, es un estándar para crear una capa de persistencia utilizando cualquier motor de persistencia. Si por ejemplo utilizamos Hibernate, en lugar de usar el API de Hibernate, utilizamos JPA, de tal manera que si en el futuro, cambiamos de motor, podemos seguir usando el mismo código.

Glassfish tiene su propio motor de persistencia llamado Toplink, pero es posible cambiarlo por Hibernate.

## Componentes JPA

Tiene tres elementos, un archivo xml, las anotaciones y el API:

+ persistence.xml en el cual incluiremos las unidades de persistencia que indicarán entre otras cosas, la base de datos, el motor, etc.
+ anotaciones con las cuales indicamos a nivel mas bajo, lo que necesitamos obtener de la base de datos (@Entity, @Table, @ld,...)
+ API JPA

Para activar JPA en Eclipse hacemos

+ Click derecho en proyecto > Properties > Project Facets > JPA > Apply and close
+ Se nos creará un JPA Content y dentro un persitence.xml
+ Aunque no aparezca ningún motor, significa que utilizará el de Glassfish
+ Para generar los modelos, en vez de hacerlo manualmente, los creamos con Eclipse
  + Click derecho en proyecto > JPA > Create Entities from table
  + Hay que añadir el driver de MySql y el jar correspondiente
+ Window > Show view > Other > Data Source Explorer nos mostrará las conexiones
+ En javax.persistance tenemos la documentación de JPA
+ Necesitamos un EntityManagerFactory para crear un EntityManager
+ Las instrucciones que hacen acción en la base de datos, deben incluirse dentro de una transacción, a no ser que los creemos como EJBs
+ Sólo se pueden hacer operaciones CRUD basadas en la primary key

## Consultas JPA

Cuando necesitamos ejecutar operaciones CRUD basadas en otros campos distintos a la primary key, necesitamos realizar consultas JPA con el lenguaje JPQL. Éstas son gestionadas a través de objetos Query y TypeQuery.

Hay 3 tipos de consultas:

+ Select: "select c from contacto c where e.dni='1234'"
+ Update
+ Delete
+ Insert: no existe ya que lo gestionamos con el persist

## Named Query

Se utiliza en las entidades JPA con la anotación

```Java
  @NamedQuery(name="<alias>", query="<SQL>")
```

Su principal ventaja es la reutilización y la abstracción de SQL, ya que la instrucción ahora estaría en la entidad en lugar del EJB. Utiliza la misma sintaxis que JPQL

## Procedimientos almacenado

Se utilizan para realizar labores periódicas y se agrupan una serie de acciones dentro de un procedimiento. Se utiliza createStoredProcedureQuery para llamar a dicho procedimiento.

## Relaciones entre entidades

Cada entidad puede tener campos relacionados con otras entidades. Los tipos son:

+ 1 - Muchos: Tema - Libros (Es la más común y no genera entidad)
+ Muchos - Muchos: Alumnos - Cursos - Matrículas (genera entidad)

Se deben replicar las relaciones en Eclipse que generará las anotaciones pertinentes. Sin embargo, dichas anotaciones no serán perfectas y habrá que cambiar

```Java
  @JoinColumn(name="<primary key>")
```

con

```Java
  @JoinColumn(name="<primary key>", referencedColumnName="<foreign key>")
```

Hay dos tipos de relaciones:

+ @OneToMany: El objeto "One" traerá todos los objetos "Many" y cada uno de los objetos "Many" traerá un objeto "One".

  + Desde el objeto "One" se hace un mapeo
    ```Java
      @OneToMany(mappedBy="<primary>")
    ```
  + Desde el objeto "Many tambien"
    ```Java
      @ManyToOne
      @JoinColumn(name="<primary>", referencedColumnName="<foreign>")
    ```
  + Si no queremos que los objetos traigan todos los objetos con los que están relacionados, se hace una "Lazy load"
    ```Java
      @OneToMany(mappedBy="<object>", fetch=FetchType.LAZY)
      @ManyToOne(fetch=FetchType.LAZY)
    ```
+ @ManyToMany: En la base de datos habrá una tabla JOIN que representa la relación entre las dos entidades. Sin embargo en JPA no se creará una entidad para dicha tabla JOIN, a no ser que ésta, tenga otros campos que necesitemos, a parte de los foreign keys de las respectivas tablas. En éste caso en lugar de una relación "ManyToMany" se crearán dos relaciones "OneToMany" con la tabla JOIN.

  + En el caso de no necesitar la tabla JOIN, se elige una de las dos tablas implicadas para declarar la información de la relación
    ```Java
      @ManyToMany
      @JoinTable(
        name="titulares",
        joinColumns=
        @JoinColumn(name="<JoinID>",referencedColumnName="<TableID>"),
        inverseJoinColumns=
        @JoinColumn(name="JoinID", referencedColumnName="TableID")
      )
    ```

  + En la otra tabla se indicará lo siguiente:
    ```Java
      @ManyToMany
      @JoinColumn(name="<TableID>")
    ```

## Operaciones en cascada

Se utilizan en aquellos caso donde hay una relación "OneToMany" y se va a borrar un objeto "One" que conlleva la eliminación de todos los objetos relacionados "Many". Usaremos

```Java
@OneToMany(mappedBy="<object>", cascade= {CascadeType.REMOVE})
```

La propiedad cascade nos ofrece la propagación de operaciones sobre el árbol de objetos, la más típica es la operación REMOVE, pero también está DETACH entre otras.

## Detach

Cuando necesitamos desvincular la instancia de la capa de persistencia, utilizamos

```Java
  em.detach(t);
```

Esto significa que cuando alguien llama al método y obtiene el objeto "t", los métodos de esta instancia no podrán devolver más objetos relacionados, ya que se ha desconectado de la base datos. Se evita con esto, la manipulación de entidades fuera del modelo o capa de persistencia.

## Join

Existen dos tipos:

+ Implícito: Se utiliza en la relación 1 a muchos y la condición afecta al lado 1.
  + Obtener todos los libros (muchos) del tema (1)
  + Select l From Libro l Where l.tema.tema=?1
  + No utiliza la sentencia JOIN
+ Explícito: Se utiliza en la relación muchos a 1 y la condición afecta al lado muchos.
+ Obtener todos los clientes (1) de las ventas (muchos) a partir de una fecha
+ Select c From Cliente c JOIN c.ventas v Where v.fecha > ?1
+ En JPQL para poner la hora se usa

```Java
tq.setParameter(1, fecha, TemporalType.TIMESTAMP);
```

+ En SQL para poner la hora se usa

```Java
  tq.setParameter(1, new java.sql.Timestamp(fecha).getTime());
```

## Clase Primary Key

Cuando se da el caso de una tabla con dos claves primarias, por ejemplo, de una relación M-N. En JPA se deben declarar dos clases, una para la clave primaria combinada y otra para la propia clase que incluya los demás atributos. Esto sucede cuando hay además otros atributos en la tabla, cuando solo hay las claves y representa una relación M-N, no hace falta declarar dicha tabla.

La notación utilizada para la clave primaria es @Embeddable y ésta se utilizará en como atributo en la clase principal con la notación @EmbeddedId.

Cuando hay una tabla MN, Eclipse dará un error y no arrancará, para arreglarlo se utilizará dentro del @JoinColumn "insertable=false, updatable=false".