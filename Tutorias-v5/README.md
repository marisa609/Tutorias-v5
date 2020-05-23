## Profesor: Andrés Rubio del Río
## Alumno: María Isabel Fuentes Martínez 

El cliente nos acaba de dar unos nuevos requisitos a aplicar sobre la �ltima versi�n que le mostramos y que le gust� bastante. Lo que nos pide el cliente es lo siguiente:

    Que la aplicaci�n no almacene los datos en ficheros y que lo haga en una base de datos creada para tal efecto.

Los datos de la BD, que es una BD MongoDB, son los siguientes:

    Servidor: IP de vuestro servidor en Google Cloud Platfom.
    Puerto: 27017
    BD: tutorias
    Usuario: tutorias
    Contrase�a: tutorias-2020

Tu tarea consiste en dotar a la aplicaci�n de la tarea anterior de un nuevo modelo de datos que en vez de utilizar ficheros para almacenar los datos lo haga haciendo uso de una Base de Datos NoSQL. Se pide al menos:

    Acomodar el proyecto para que gradle gestione la dependencia con el driver para java de MongoDB en su �ltima vesi�n. Adem�s deber�s modificar el proyecto para que se puedan ejecutar todas las versiones: ficheros con IU textual, ficheros con IU gr�fica, BD con IU textual y BD con IU gr�fica, haciendo uso de los par�metros pasados a la aplicaci�n.
    Gestionar los profesores para que su persistencia se lleve a cabo por medio de dicha BD.
    Gestionar las tutor�as para que su persistencia se lleve a cabo por medio de dicha BD.
    Gestionar las sesiones para que su persistencia se lleve a cabo por medio de dicha BD.
    Gestionar los alumnos para que su persistencia se lleve a cabo por medio de dicha BD.
    Gestionar las citas para que su persistencia se lleve a cabo por medio de dicha BD.

Para ello debes realizar las siguientes acciones:

    Lo primero que debes hacer es crear un repositorio  en GitHub a partir de tu repositorio de la tarea anterior.
    Clona dicho repositorio localmente para empezar a modicfiarlo. Modifica el fichero README.md para que indique tus datos y los de esta tarea. Realiza tu primer commit.
    Realiza los cambios necesarios para que el proyecto pueda lanzar la aplicaci�n eligiendo tanto la vista como el modelo por par�metros. Realiza el commit correspondiente.
    Haz que la gesti�n de profesores utilice la persistencia en la BD. Realiza el commit correspondiente.
    Haz que la gesti�n de tutor�as utilice la persistencia en la BD. Realiza el commit correspondiente.
    Haz que la gesti�n de sesiones utilice la persistencia en la BD. Realiza el commit correspondiente.
    Haz que la gesti�n de alumnos utilice la persistencia en la BD. Realiza el commit correspondiente.
    Haz que la gesti�n de citas utilice la persistencia en la BD. Realiza el commit correspondiente.

Se valorar�:

    La nomenclatura del repositorio de GitHub y del archivo entregado sigue las indicaciones de entrega.
    La indentaci�n debe ser correcta en cada uno de los apartados.
    El nombre de las variables debe ser adecuado.
    El proyecto debe pasar todas las pruebas que van en el esqueleto del mismo y toda entrada del programa ser� validada para evitar que el programa termine abruptamente debido a una excepci�n.
    Se deben utilizar los comentarios adecuados.
    Se valorar� la correcci�n ortogr�fica tanto en los comentarios como en los mensajes que se muestren al usuario.

