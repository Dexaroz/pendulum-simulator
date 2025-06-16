# Simulador de Péndulo

Este proyecto es una simulación del movimiento de un péndulo basada en un enfoque modular y patrones arquitectónicos. A continuación, se explica la organización del código y los conceptos clave detrás de su arquitectura.

## Ejemplo de Vista:  

![image](https://github.com/user-attachments/assets/c6746a87-947c-428c-8b10-2b7e1824cc1a)  

## Estructura del Directorio
La organización del proyecto se basa en una separación de responsabilidades, agrupando componentes relacionados:  

![image](https://github.com/user-attachments/assets/36cc46ab-4308-4b3c-89b7-ac9dac30c0d4)  

## Patrones Arquitectónicos

### Modelo-Vista-Presentador (MVP)
La arquitectura general del proyecto sigue este enfoque, separando las responsabilidades en tres capas:

#### Modelo:
- Define los datos y la lógica de la simulación.
- Contiene clases para representar el péndulo y su comportamiento físico.
- **Ejemplo**: `Pendulum`, `PendulumSimulator`.

#### Vista:
- Gestiona la presentación visual.
- Proporciona componentes para mostrar el péndulo y su movimiento.
- **Ejemplo**: `SwingPendulumDisplay`.

#### Presentador:
- Media entre el modelo y la vista, actuando como coordinador.
- Gestiona eventos de usuario, actualiza el modelo y notifica a la vista.
- **Ejemplo**: `PendulumPresenter`.

## Servicios Auxiliares
Encapsulan funcionalidades específicas para facilitar el mantenimiento y la reutilización del código:

### Simulación Física:
- Gestiona el cálculo del movimiento del péndulo.
- **Implementado en**: `PendulumSimulator`.

### Presentación de Datos:
- Gestiona la visualización del péndulo en la interfaz gráfica.
- **Implementado en**: `SwingPendulumDisplay`.

## Modularidad
Cada componente está diseñado con una única responsabilidad. Esto mejora la mantenibilidad y escalabilidad del proyecto. Por ejemplo:

- **Módulo del modelo**: Representación de entidades centrales como `Pendulum` y `PendulumSimulator`.
- **Módulo de la vista**: Interfaz visual como `SwingPendulumDisplay`.
- **Módulo del presentador**: Coordinación entre el modelo y la vista mediante `PendulumPresenter`.

## Ejemplo de Flujo
1. El usuario ejecuta la aplicación.
2. El presentador (`PendulumPresenter`) inicializa el péndulo y lo añade al simulador.
3. La vista (`SwingPendulumDisplay`) muestra el péndulo en movimiento.
4. Durante la simulación:
   - El modelo (`PendulumSimulator`) actualiza el estado del péndulo.
   - La vista se actualiza en tiempo real para reflejar los cambios.

## Tecnologías Utilizadas
- **Java AWT/Swing**: Para la interfaz gráfica.
- **Java 17**: Versión requerida para ejecutar el proyecto.

## Configuración y Ejecución
1. Clonar el repositorio.
2. Compilar y ejecutar el proyecto:
   ```bash
   javac -d bin src/software/ulpgc/simulator/app/Main.java
   java -cp bin software.ulpgc.simulator.app.Main
