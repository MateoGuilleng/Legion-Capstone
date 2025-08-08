# Marcha de la Legión

Proyecto Java que simula el posicionamiento y ordenamiento de tropas en un campo de batalla de 6x6.

## Estructura del Proyecto

### Archivos Principales
- `src/Troops.java` - Clase principal que coordina todas las funcionalidades
- `src/Main.java` - Punto de entrada que llama a la clase Troops
- `pruebas.txt` - Archivo con ejemplos de pruebas válidas e inválidas

### Clases Componentizadas
- `src/ValidadorParametros.java` - Maneja la validación y parsing de argumentos de línea de comandos
- `src/AlgoritmoOrdenamiento.java` - Contiene los algoritmos de ordenamiento (actualmente insertion sort)
- `src/CampoBatalla.java` - Gestiona el campo de batalla 6x6 y su visualización

## Arquitectura del Proyecto

El proyecto sigue un diseño modular y componentizado:

```
Troops (Clase Principal)
├── ValidadorParametros (Validación de entrada)
├── AlgoritmoOrdenamiento (Lógica de ordenamiento)
└── CampoBatalla (Gestión del tablero)
```

### Responsabilidades de cada clase:

1. **`Troops`**: Coordinador principal que orquesta el flujo del programa
2. **`ValidadorParametros`**: Valida y parsea los argumentos de línea de comandos
3. **`AlgoritmoOrdenamiento`**: Implementa los algoritmos de ordenamiento
4. **`CampoBatalla`**: Maneja la creación, modificación y visualización del campo de batalla

## Uso

El programa acepta argumentos de línea de comandos en el siguiente formato:

```
java Troops a=<algoritmo> t=<tipo> u=<unidades>
```

### Parámetros

- `a=<algoritmo>` - Algoritmo de ordenamiento a usar:
  - `i` - Ordenamiento por inserción

- `t=<tipo>` - Tipo de datos para ordenar:
  - `c` - Caracteres
  - `n` - Números

- `u=<unidades>` o `r=<unidades>` - Unidades a ordenar (separadas por comas)

### Ejemplos

1. **Caracteres con Ordenamiento por Inserción:**
   ```
   java Troops a=i t=c u=1,1,2,3,5
   ```

2. **Números con Ordenamiento por Inserción:**
   ```
   java Troops a=i t=n r=1,1,2,3,5
   ```

3. **Múltiples unidades del mismo tipo (6 unidades C):**
   ```
   java Troops a=i t=c u=C,C,C,C,C,C
   ```

4. **Múltiples columnas para un tipo (8 unidades C):**
   ```
   java Troops a=i t=c u=C,C,C,C,C,C,C,C
   ```

5. **Parámetros Inválidos:**
   ```
   java Troops a=1 t= r=12345
   ```


## Algoritmo de Ordenamiento: Insertion Sort

### ¿Qué es Insertion Sort?

El **Ordenamiento por Inserción** es un algoritmo de ordenamiento simple que construye el array final ordenado un elemento a la vez. Es mucho menos eficiente en listas grandes que algoritmos más avanzados como quicksort, heapsort o merge sort.

### ¿Cómo funciona?

1. **Inicio**: Se considera que el primer elemento está ordenado
2. **Iteración**: Se toma el siguiente elemento y se inserta en la posición correcta dentro de la parte ya ordenada
3. **Repetición**: Se repite el proceso hasta que todos los elementos estén ordenados

### Proceso paso a paso:

**Ejemplo con números: [5, 2, 4, 6, 1, 3]**

```
Iteración 1: [5] | [2, 4, 6, 1, 3] → [2, 5] | [4, 6, 1, 3]
Iteración 2: [2, 5] | [4, 6, 1, 3] → [2, 4, 5] | [6, 1, 3]
Iteración 3: [2, 4, 5] | [6, 1, 3] → [2, 4, 5, 6] | [1, 3]
Iteración 4: [2, 4, 5, 6] | [1, 3] → [1, 2, 4, 5, 6] | [3]
Iteración 5: [1, 2, 4, 5, 6] | [3] → [1, 2, 3, 4, 5, 6]
```

### Implementación en el código:

```java
private static void ordenamientoInsercionNumeros(List<Integer> arr) {
    for (int i = 1; i < arr.size(); i++) {
        int clave = arr.get(i);  // Elemento a insertar
        int j = i - 1;           // Posición anterior
        
        // Mover elementos mayores que la clave una posición adelante
        while (j >= 0 && arr.get(j) > clave) {
            arr.set(j + 1, arr.get(j));
            j--;
        }
        arr.set(j + 1, clave);   // Insertar la clave en su posición correcta
    }
}
```

### Ventajas del Insertion Sort:

-  **Simple de implementar** y entender
-  **Eficiente para listas pequeñas** (menos de 50 elementos)
-  **Estable** (mantiene el orden relativo de elementos iguales)
-  **In-place** (no requiere espacio adicional)
-  **Adaptativo** (eficiente para datos casi ordenados)

### Desventajas:

-  **Complejidad O(n²)** en el peor caso
-  **Ineficiente para listas grandes**
-  **Muchos intercambios** comparado con otros algoritmos

### Complejidad:

- **Tiempo promedio**: O(n²)
- **Tiempo peor caso**: O(n²)
- **Tiempo mejor caso**: O(n) - cuando la lista ya está ordenada
- **Espacio**: O(1) - in-place

## Compilación

Para compilar el proyecto:
```bash
javac src/*.java
```

## Ejecución

Para ejecutar el proyecto:
```bash
java -cp src Troops <argumentos>
```

## Formato de Salida

El programa muestra:
1. Nombre del algoritmo
2. Tipo de datos
3. Número de tropas
4. Dimensiones del campo de batalla
5. Posicionamiento inicial aleatorio
6. Posicionamiento final ordenado en columnas verticales

### Posicionamiento Final

Las unidades ordenadas se colocan en forma de columnas verticales desde abajo hacia arriba:
- Cada tipo de unidad ocupa una o más columnas
- **Máximo 6 unidades por columna** del mismo tipo
- Si hay más de 6 unidades del mismo tipo, se distribuyen en múltiples columnas
- Cada columna solo puede contener un tipo de unidad
- Las columnas se llenan desde la izquierda hacia la derecha
- La cantidad de unidades determina la altura de la columna

#### Ejemplos de Posicionamiento:

**6 unidades "C":**
```
C     *     *     *     *     *
C     *     *     *     *     *
C     *     *     *     *     *
C     *     *     *     *     *
C     *     *     *     *     *
C     *     *     *     *     *
```

**8 unidades "C" (6 en primera columna, 2 en segunda):**
```
C     *     *     *     *     *
C     *     *     *     *     *
C     *     *     *     *     *
C     *     *     *     *     *
C     C     *     *     *     *
C     C     *     *     *     *
```

El campo de batalla se muestra como una cuadrícula de 6x6 con unidades posicionadas y asteriscos (*) representando espacios vacíos. 