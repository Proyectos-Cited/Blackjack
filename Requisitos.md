Realiza un programa en Java para jugar al Blackjack entre varios jugadores y el crupier, utilizando Programación Orientada a Objetos.
El programa deberá implementar, como mínimo, las siguientes clases:

- Carta
- Mazo
- Mano
- Jugador
- Crupier
- JuegoBlackjack

Cada clase debe tener sus atributos privados, constructores, getters/setters y los métodos necesarios para su funcionamiento conforme a los principios de POO. Además, el programa deberá contar, como mínimo, con los siguientes métodos clave dentro del flujo del juego:

- ganador(); // Determina quién gana comparando las puntuaciones del jugador y del crupier (21 exacto, blackjack, pasarse, o valor más alto válido).
- es_Válido(); // Comprueba si el movimiento del jugador es válido (por ejemplo, pedir carta solo si no se ha pasado y no tiene blackjack).
- movimiento(); // Ejecuta la acción del jugador: pedir carta (hit) o plantarse (stand). El crupier también actuará según las reglas básicas (pedir hasta 17).
- mostrar_tablero(); Muestra el estado de la partida: cartas visibles de los jugadores y del crupier, y la puntuación conocida.
- inicio_juego(); Controla todo el flujo del juego: creación del mazo, reparto inicial, turnos, decisiones de los jugadores, turno del crupier y resultado final.

El ganador o los ganadores de cada ronda sumarán un punto. El primero en conseguir 5 gana el juego. Si el crupier llega antes a los 5 puntos, gana la banca. Los puntos de los jugadores son independientes entre sí. El jugador debe poder decidir cada turno si quiere:

- Pedir carta (Hit)
- Plantarse (Stand) El crupier debe jugar siguiendo la norma oficial:
- Pedir cartas hasta llegar al menos a 17. Deben contemplarse las siguientes situaciones:
- Blackjack natural (21 con dos cartas)
- Jugador o croupier se pasan (más de 21), si el crupier se pasa, ganan los jugadores automáticamente la ronda.
- Empate (push) - Ganador por puntuación

Reglas y mecánicas del juego

Vamos a ir planteando el proyecto, implementaciones que se pueden realizar, por ejemplo:

- Chupito: Si el jugador bebe 3 chupitos su fortuna aumenta, es decir que tiene más probabilidades de ganar, estos chupitos valen dinero, es decir, estos se pagan con el dinero apostado. Su tiempo de uso es de una partida a partir de esa partida pierde sus efectos, si durante dos partidas bebe chupitos en las dos este se desmayara y perderá parte de su dinero como si le hubieran robado
- Interfaz gráfica en dos dimensiones del tablero del juego y sus cartas
- Seguro ludópata a todo riesgo: Al ganar dos manos seguidas, recibe este objeto el cual le sirve como seguro por si en la siguiente ronda o en la que el decida usar, se pasa de 21, pues puede retroceder y pedir de nuevo.

Sistema de penalización por inacción:

División del Proyecto Blackjack POO (Java) entre 5 personas
El programa debe implementar como mínimo las clases: Carta, Mazo, Mano, Jugador, Crupier y JuegoBlackjack, además de los métodos principales del flujo del juego: ganador(), es_Válido(), movimiento(), mostrar tablero() e inicio_juego().
El objetivo es que el juego se desarrolle por rondas, otorgándo un punto al ganador de cada una. El primer jugador o el crupier que alcance 5 puntos será el vencedor final.

Mario Alvarez: Desarrollo de Carta y Mazo
Responsable de implementar la base del sistema de cartas y el funcionamiento del mazo.
Tareas asignadas:
Implementar la clase Carta:

Atributos privados: palo, valor, nombre o representación.

Constructor completo.

Métodos getters y setters.

Método toString() para mostrar la carta en pantalla.

Implementar la clase Mazo:

Estructura interna para almacenar cartas (por ejemplo ArrayList).

Generación automática de las 52 cartas.

Método barajar().

Método robarCarta().

Reinicio del mazo si se agotan las cartas.

Archivos principales:
Carta.java

Mazo.java

Fran: Implementación de Mano y cálculo de puntuaciones
Encargado de gestionar la mano de cada jugador y calcular correctamente el valor en Blackjack.
Tareas asignadas:
Implementar la clase Mano:

Lista de cartas en mano.

Método agregarCarta(Carta c).

Método calcularPuntos(), contemplando:

As con valor 1 u 11 según convenga.

Figuras con valor 10.

Método tieneBlackjack().

Método estaPasado() (más de 21).

Método para mostrar cartas, con opción de ocultar la primera carta del crupier.

Archivo principal:
Mano.java

Alejandro: Desarrollo de Jugador y Crupier
Responsable del comportamiento de los jugadores y de la lógica automática del crupier.
Tareas asignadas:
Implementar la clase Jugador:

Atributos: nombre, mano, puntos acumulados, estado de plantado.

Método movimiento(): permite decidir entre pedir carta o plantarse.

Método es_Válido(): verifica si el jugador puede seguir jugando.

Reinicio de la mano al iniciar cada ronda.

Implementar la clase Crupier (heredando de Jugador):

Movimiento automático siguiendo las reglas oficiales:

Pedir cartas hasta llegar al menos a 17 puntos.

Mostrar solo una carta al inicio de la ronda.

Archivos principales:
Jugador.java

Crupier.java

Mario Rodriguez: Motor principal del juego (JuegoBlackjack)
Encargado de controlar el flujo completo de la partida, las rondas y el sistema de puntuación hasta llegar a 5 puntos.
Tareas asignadas:
Implementar la clase JuegoBlackjack:

Lista de jugadores.

Instancia del crupier.

Instancia del mazo.

Implementar los métodos clave del programa:

inicio_juego():

Crear el mazo.

Repartir cartas iniciales.

Controlar turnos de jugadores.

Ejecutar turno del crupier.

Calcular ganador.

Sumar puntos.

Repetir rondas hasta que alguien llegue a 5 puntos.

mostrar_tablero():

Mostrar cartas visibles de cada jugador.

Mostrar una carta del crupier al inicio.

Mostrar puntuaciones conocidas.

ganador():

Evaluar resultados según:

Blackjack natural.

Jugador o crupier se pasan.

Empate (push).

Mayor puntuación válida.

Archivo principal:
JuegoBlackjack.java

Luis : Mecánicas adicionales y ampliaciones del proyecto
Responsable de implementar mejoras opcionales propuestas para enriquecer el juego.
Tareas asignadas:
Implementación del sistema “Chupito”:

Si un jugador bebe 3 chupitos, aumenta su fortuna temporalmente.

El efecto dura una sola partida.

Si bebe en dos partidas consecutivas, se desmaya y pierde parte del dinero.

Implementación del “Seguro ludópata a todo riesgo”:

Se obtiene al ganar dos rondas seguidas.

Permite evitar la pérdida si el jugador se pasa de 21 en una ronda futura.

Desarrollo opcional de interfaz gráfica 2D:

Representación visual del tablero y cartas con Swing o JavaFX.

Archivos posibles:
Chupito.java

Seguro.java

InterfazGrafica.java (opcional)

todo en eclipse
