# Práctica Fundamentos de Android (con Kotlin). KC Bootcamp V

## Enunciado

La aplicación:

-  Debe descargar la lista de posibles platos del restaurante de un servicio. Como no es el propósito principal de esta práctica hacer el servicio puedes valerte de Mocky: http://www.mocky.io/ o de tu propio servicio, lo que prefieras.
-  Una vez descargada la carta, desde la aplicación ha de ser posible acceder a un listado de las mesas de los clientes.
-  Cuando se accede a una mesa debe poder verse lo que han pedido hasta el momento. No es necesario guardar qué ha pedido cada cliente individual, confiaremos en que el camarero se acuerde qué plato iba para cada cliente en una mesa.
-  Desde esa vista de mesa se deben poder añadir platos. Para esto aparecerá otra pantalla donde se pueda elegir el plato de una lista. En esa lista aparece el nombre del plato, una pequeña imagen, unos iconos que indiquen los alérgenos que posee (si los tiene) y su precio.
-  Al pulsar sobre un plato aparece una pantalla con la información del plato (imagen, algún detalle más…), y una caja de texto donde poner las pequeñas variantes que pueda pedir un cliente.
-  Si guardamos dicho plato se añadirá a la lista de los platos que han pedido en una mesa.
-  Debe existir un pequeño menú para calcular la cuenta.

La aplicación no tiene por qué persistir nada, bastante tienes ya con esto. Además oficialmente no se tiene constancia de ningún móvil Android que se cuelgue y haya que reiniciarlo, por lo que puedes respirar tranquilo.

### Valoración

En la aplicación se valorará:

-  Buena aplicación de los conceptos vistos en el curso
-  Diseño material allá donde sea posible y tenga sentido
-  Organización y claridad del código
-  Facilidad de uso para el usuario
-  Distintas visualizaciones y formas de interactuar para distintos dispositivos como hemos visto en el curso (al menos distingue entre dispositivos tipo teléfono y tipo tableta)
-  Soporte de varias versiones de Android, cubriendo un mínimo del 90% de los dispositivos actuales.


#### Detalles a tener en cuenta
En el curso hemos visto que los procesos que pueden durar tiempo necesitan hacerse asíncronos, indicárselo al usuario… etc. Procura hacerlo, en esta práctica tienes algún lugar donde poder aplicarlo.

También hemos visto algunos pequeños detalles de aplicación de diseño material, intenta aplicarlos.
La aplicación es deliberadamente “abierta” para que pienses (preferiblemente antes de ponerte a codificar) cómo diseñarla, qué pantallas abren cuáles y de qué modo… etc. No hay necesariamente una única forma “correcta” de hacerla y seguro (eso espero) que cada uno la hace diferente, pero sí que hay varias formas incorrectas: procura evitarlas.

En varios puntos he hablado de una lista. No tiene por qué ser un ListView si no quieres, eres libre de implementarlo como quieras siempre y cuando tenga sentido.

Piensa bien qué debe ser fragment, qué debe ser actividad y cómo comunicar unos con otros.
No te compliques con los datos del servicio, es decir, no hace falta que crees una carta de 128 platos con imágenes Hi-res, lo importante es que vea que has aprendido los fundamentos de Android.

## Información de la solución publicada

- Se han utilizado Activities junto con Fragments, para que fuera cómoda y accesible la navegación entre pantallas.
- Se ha utilizado RecyclerView para mostrar las Cards con la información resumida de cada plato.
- Se reutiliza el Fragment que muestra el listado de platos (este se maneja desde el detalle de mesa como desde el menú)
- Se han añadido funciones auxiliares al modelo de Table, para manejar de forma fácil los platos correspondientes a cada mesa.
- Se han añadido funciones auxiliares al modelo de Dish, para manejar de forma fácil los cambios de cada plato.
- El listado de mesas es un Singleton, de esta forma no es necesario actualizar ni enviar datos entre Acitivities cuando se actualiza la información.
- Las imágenes de platos e iconos de alrgenos se manejan en una clase de Utilidad (este método podría ser ms dinámico, se anota como posible mejora)
- Se han creado visualizaciones de layut especiales para una pantalla de densidad mínima de 400dp, y con orientación landscape.

## Información sobre el proyecto

Versión de Android Studio utilizada:

```
Android Studio 3.0
Build #AI-171.4408382, built on October 20, 2017
JRE: 1.8.0_152-release-915-b08 x86_64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Mac OS X 10.13
```

Emuladores utilizados:

- Nexus 5 API 27. Resolución: 1080 x 1920: 420 dpi
- Nexus 10 API 24. Resolución: 2560 x 1600: xhdpi


## Extras

Se añade en el detalle de plato (ya añadido a la mesa) la opción de menú de Borrar plato. Esta acción es inmediata, y no pide segunda confirmación.

En este mismo menú se añade la opción de borrar todos los platos. Esta acción es inmediata, y no pide segunda confirmación.

Si no hay conexión a internet, se puede utilizar un json incluido en la aplicación, con los mismos datos que el json alojado en el Mocky (http://www.mocky.io/v2/5a2062b2310000b739c0b28b)

Iconos diseñados por [Freepik](http://www.freepik.com "Freepik") desde [www.flaticon.com](https://www.flaticon.es/ "Flaticon") con licencia [CC 3.0 BY](http://creativecommons.org/licenses/by/3.0/ "Creative Commons BY 3.0")

Logo creado con [Logojoy](https://logojoy.com).


## Posibles mejoras

- Mejorar la parte visual de los layout. Optimizar el uso del espacio en función del dispositivo.
- Modificar el layout para mostrar en caso de tener una pantalla amplia el listado de mesas y detalle de mesa a la vez.
- Establecer un menú de Settings para definir el número de mesas disponibles, así como un floating button en el listado de mesas para añadir una ms.
- Establecer en el menú de Settings el idioma a mostrar. Ampliar el fichero de strings con las traducciones correspondientes.
- Manejar dinámicamente la descarga de imágenes e iconos (en esta dase del proyecto están alojados localmente)
- Almacenar los datos en el dispositivo, ahora mismo al cerrar la aplicación se pierden los datos gestionados.
- Crear iconos para la aplicación y gestionar las imágenes en función del dispositivo.
- Cualquier idea o crítica constructiva es bienvenida :)

## Demo

![](https://media.giphy.com/media/3o6fJ5c04XuQEX2nBe/giphy.gif)
