
# Pruebas

## Vocabulario que usamos en el mundo del testing

- Error     Los humanos cometemos errores (por estar cansados, faltos de conocimiento, de concentración...)
- Defecto   Al cometer un error un humano, puede introducir un defecto en un producto.
- Fallo     Ese defecto, en un momento dado puede manifestarse en forma de FALLO.

## Para qué sirven las pruebas?

- Para asegurar que mi software cumple con unos requisitos
- Para ayudarme con el desarrollo del software: Test-first, TDD (Test-first + refactorización en cada iteración), BDD, ATDD
- Para intentar identificar la mayor cantidad posible de fallos antes del paso a producción de mi sistema.
  Una vez identificado un fallo, lo que queremos es arreglar el defecto que lo ocasiona...
  Para ello lo primero es identificar ese defecto: Depuración o Debugging.
- Aportar la mayor cantidad posible de información que permita una rápida identificación de el defecto que provoca un fallo.
- Para intentar identificar la mayor cantidad posible de defectos antes del paso a producción de mi sistema.
- Para saber qué tal va mi proyecto. --> INDICADOR DE PROGRESO
- Haciendo un análisis de causas raíz, para intentar identificar las causas que provocan los defectos (ERRORES), 
  tomar acciones preventivas para evitar que se vuelvan a producir esos errores... con sus correspondientes defectos y fallos.

    PRODUCTO MESA DE IKEA
    
    Me despisto (yo humano) y al medir he medido una de las patas mas corta -> DEFECTO (tengo una mesa-producto con una pata mas corta que las otras 3)
    Me hace falta llevar la mesa a producción... o tan siquiera ejecutar la mesa (usarla) para identificar el defecto que tiene la mesa?

## Tipos de pruebas

Cualquier prueba se debe centrar en una ÚNICA característica del sistema/componente del sistema que se está probando.

### En base al conocimiento que tengo del sistema a probar

- Pruebas de caja negra      (no conozco el interior del sistema)
- Pruebas de caja blanca     (conozco el interior del sistema)

### En base al objeto de prueba

- Pruebas funcionales
- Pruebas no funcionales
  - Pruebas de rendimiento
  - Pruebas de estrés
  - Pruebas de carga
  - Pruebas de seguridad
  - Pruebas de usabilidad
  - Pruebas de HA

### En base al procedimiento de ejecución

- Pruebas estáticas   No requieren ejecutar el sistema  --> Identifican DEFECTOS (Sonarqube)
- Pruebas dinámicas   Requieren ejecutar el sistema     --> Identifican FALLOS

### En base al nivel de la prueba (scope)

- Pruebas unitarias         La prueba se centra en una característica de un componente AISLADO del sistema

        TREN: 
                Motor       Sistema de frenos       Ruedas      Asientos                 

        Sistema de frenos... le meto corriente y veo que las pinzas cierran

- Pruebas de integración    Se centran en la COMUNICACIÓN entre 2 COMPONENTES

        Sistema de frenos -> Rueda ... le meto corriente y la rueda debe frenarse

- Pruebas de sistema        Se centran en el COMPORTAMIENTO del sistema en su conjunto

        TREN: Le aprieto al botón de "Vamonos" ... y el tren sale andando hacia atrás.

- Pruebas de aceptación     Son un subconjunto de las anteriores

REQUISITO 1: La app debe tener unos tiempos de respuesta aceptables         RUINA
REQUISITO 2: La app debe tener uno tiempos de respuesta inferior a 200ms    RUINA
REQUISITO 3: Tal operativa en la app, estando instalada en un entorno con tales características, y estando sometida a tal carga de trabajo,
             debe tener un percentil 95% de tiempos de respuesta inferior a 200ms, cuando hago la prueba tantas veces.

Miro la latencia de red de mi servidor a la BBDD: 95% inferior a 60ms

Página web que captura los datos de un pedido y al final se guardan en la BBDD

FRONTAL                                                     BACKEND

--------------------------------------------------------    -----------------------------------------------------------------------------
  Captura de datos       Comunicación con un Backend        Exponer el servicio   Lógica de negocio     Lógica de persistencia
   v                              v                             v                    v                  v
WebComponent: Formulario  > Servicio en Frontal          >   Controlador    >     Servicio*        >    Repositorio         > BBDD
   ^                                                                                altaDePedido
REACT, Angular, Vue, Polymer                                                            persistir el pedido >
                                                                                        mandar un email     > Servicio de Emails

Puedo hacer una prueba unitaria al servicio* , para la función altaDePedido?
- Para que sea unitaria, tengo que cortarle lazos con otros componentes (aislarlo): Test-Doubles
- Tendría que hacerle cuantas pruebas de integración? 2, una con el repositorio real y otra con el servicio de emails real.
- Pruebas de sistema: Repo real, servicio de emails real, BBDD real

Test-Doubles: Stub, Fake, Spy, Mocks, Dummies
