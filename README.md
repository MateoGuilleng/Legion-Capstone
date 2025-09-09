# Legion - Sistema de Ordenamiento de Tropas

## Descripción
Sistema de ordenamiento de tropas en un campo de batalla que implementa múltiples algoritmos de ordenamiento y permite configurar la orientación y distribución de las unidades.

## Parámetros de Entrada

### a - Algoritmo de Ordenamiento
- `"S"` o `"s"` → Selection sort (selección)
- `"B"` o `"b"` → Bubble sort (burbuja)
- `"I"` o `"i"` → Insertion sort (inserción)
- `"M"` o `"m"` → Merge sort (combinación)

### t - Tipo de Lista
- `"N"` o `"n"` → Lista de números
  - n>0 y n<11 → Comandante
  - n>10 y n<21 → Médico
  - n>20 y n<31 → Tanque
  - n>30 y n<41 → Sniper
  - n>40 y n<51 → Infantería
- `"C"` o `"c"` → Lista de caracteres
  - a-j → Comandante
  - k-t → Médico
  - u-z → Tanque
  - K-N → Sniper
  - O-X → Infantería

### o - Orientación
- `"N"` o `"n"` → Ordenar de sur a norte
- `"S"` o `"s"` → Ordenar de norte a sur
- `"E"` o `"e"` → Ordenar de oeste a este
- `"W"` o `"w"` → Ordenar de este a oeste

### u - Distribución de Tropas
Arreglo de enteros que representa la cantidad de tropas por tipo, organizadas jerárquicamente:
- `u[0]` → Cantidad de Comandantes
- `u[1]` → Cantidad de Médicos
- `u[2]` → Cantidad de Tanques
- `u[3]` → Cantidad de Snipers
- `u[4]` → Cantidad de Infantería

### f - Tamaño del Campo de Batalla
- Número entero positivo mayor o igual a 5 y menor o igual a 1000
- Por defecto: 10 (si no se especifica)

## Ejemplos de Uso

### Ejemplo 1: Ordenamiento de Caracteres con Insertion Sort
```bash
java Troops a=i t=c o=s u=1,2,5,5,10 f=10
```

**Salida esperada:**
```
Algorithm: [Insertion sort]
Type: [Character]
Orientation: [Sud]
Troops: [23]
Battlefield: [10 x 10]

Initial Position:
* * S * * T * * * I
I * * * * * * S * *
* * * I * * * * M *
* T * * * * I I * *
* * * * c * * * * I
I * S * * * S * T *
* * S * * T * * * *
* * * I * * * * I *
* M * * * * I * * *
S * * * * * * * T *

Final Position:
c * * * * * * * * *
M M * * * * * * * *
T T T T T * * * * *
S S S S S * * * * *
I I I I I I I I I I
* * * * * * * * * *
* * * * * * * * * *
* * * * * * * * * *
* * * * * * * * * *
* * * * * * * * * *
```

### Ejemplo 2: Ordenamiento de Números con Insertion Sort
```bash
java Troops a=i t=n o=w u=1,1,2,3,5 f=6
```

## Compilación y Ejecución

### Compilar el proyecto:
```bash
javac -cp src src/*.java
```

### Ejecutar el programa:
```bash
java -cp src Troops [parámetros]
```

### Ejecutar con el script de prueba:
```bash
test_example.bat
```

## Estructura del Proyecto

- `Main.java` - Punto de entrada principal
- `Troops.java` - Clase principal que coordina la ejecución
- `ValidadorParametros.java` - Valida y procesa los parámetros de entrada
- `AlgoritmoOrdenamiento.java` - Implementa todos los algoritmos de ordenamiento
- `CampoBatalla.java` - Maneja la representación y colocación de tropas en el campo
- `GestorTropas.java` - Gestiona los tipos de tropas y su mapeo
- `ValidadorParametros.java` - Valida los parámetros de entrada

## Características Implementadas

**4 algoritmos de ordenamiento** completos para números y caracteres

**Sistema de orientación** (Norte, Sur, Este, Oeste)

**Tamaño de campo configurable** (5x5 a 1000x1000)

**Sistema jerárquico de tropas** (Comandante → Médico → Tanque → Sper → Infantería)

**Mapeo automático** de números y caracteres a tipos de tropa

**Validación completa** de parámetros de entrada

**Código limpio** con comentarios y buenas prácticas


## Notas de Implementación

- Los algoritmos de ordenamiento están optimizados para manejar tanto listas numéricas como de caracteres
- El sistema de orientación respeta la dirección especificada al colocar las tropas ordenadas
- La validación de parámetros asegura que solo se procesen entradas válidas
- El código sigue las convenciones de Java y principios de clean code 

## Diagrama de clases

![Diiagrama-de-clases](https://res.cloudinary.com/dudftt5ha/image/upload/v1757262906/shiehiff7qy93esv7tjz.png)