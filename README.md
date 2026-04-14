# 🦸 MarvelDle — Adivina el Personaje de Marvel

<div align="center">

**Un juego de adivinanzas por consola inspirado en [Wordle](https://www.nytimes.com/games/wordle), pero con personajes del Universo Cinematográfico de Marvel (MCU).**

![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven)
![Gson](https://img.shields.io/badge/Gson-2.10.1-green?logo=google)
![License](https://img.shields.io/badge/Licencia-Educativo-lightgrey)

</div>

---

## 📖 ¿De qué va el proyecto?

**MarvelDle** es un minijuego de consola desarrollado en Java como proyecto de 1º DAW. El programa selecciona aleatoriamente un personaje secreto de una base de datos de **20 personajes del MCU** y el jugador debe adivinarlo introduciendo nombres.

Tras cada intento, el juego muestra **pistas visuales** para cada atributo del personaje:

| Pista | Significado |
|:-----:|-------------|
| ✅ | El atributo coincide con el personaje secreto |
| ❌ | El atributo **no** coincide |
| ⬆️ | El año de aparición del personaje secreto es **más reciente** |
| ⬇️ | El año de aparición del personaje secreto es **más antiguo** |

### 🎮 Atributos comparados

Cada intento compara **5 atributos** entre el personaje introducido y el secreto:

- **Género** — Hombre / Mujer
- **Tipo** — Héroe / Villano
- **Especie** — Humano, Dios, Titán, Androide, etc.
- **Origen** — Tierra, Asgard, Titán, etc.
- **Año de aparición** — Año de debut en el MCU (con flechas direccionales)

### 🕹️ Ejemplo de partida

```
=====================================
   🦸‍♂️ BIENVENIDO A MARVEL-DLE 🦸‍♀️
=====================================
Se ha elegido un personaje secreto de la base de datos.
Base de datos cargada con: 20 personajes.
Pistas: ✅ (Correcto) | ❌ (Incorrecto) | ⬆️ ⬇️ (Aparicion mayor/menor)

Introduce un nombre de personaje:
> Iron Man

Analizando a Iron Man...
Género: ✅ | Tipo (Héroe/Villano): ✅ | Especie: ✅ | Origen: ✅ | Año: ⬆️ (El personaje es más reciente)

Introduce un nombre de personaje:
> Spider-Man

Analizando a Spider-Man...
Género: ✅ | Tipo (Héroe/Villano): ✅ | Especie: ✅ | Origen: ✅ | Año: ✅

🎉 ¡BRUTAL! Has acertado. El personaje era Spider-Man
🎉 Has hecho 2 intentos.
```

### 🗂️ Base de datos de personajes

El juego incluye **20 personajes** del MCU organizados en un archivo JSON:

| Personaje | Tipo | Especie | Afiliación |
|-----------|------|---------|------------|
| Iron Man | Héroe | Humano | Vengadores |
| Thor | Héroe | Dios | Vengadores |
| Thanos | Villano | Titán | Ninguna |
| Spider-Man | Héroe | Humano | Vengadores |
| Loki | Villano | Gigante de Hielo | Ninguna |
| Doctor Strange | Héroe | Humano | Maestros de las Artes Místicas |
| Groot | Héroe | Flora colossus | Guardianes de la Galaxia |
| Ultron | Villano | Inteligencia Artificial | Ninguna |
| ... | ... | ... | ... |

---

## 🔧 Sección Técnica

### Requisitos previos

- **Java JDK 25** o superior
- **Apache Maven** instalado y configurado en el PATH

### Instalación y ejecución

```bash
# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/Proyecto-Java-1-DAW.git
cd Proyecto-Java-1-DAW

# 2. Compilar el proyecto
mvn compile

# 3. Ejecutar el juego
mvn exec:java -Dexec.mainClass="org.example.MarvelDle.Main"
```

---

### 📁 Estructura del proyecto

```
Proyecto-Java-1-DAW/
├── pom.xml                         # Configuración Maven + dependencias
├── personajes.json                 # Base de datos de personajes (JSON)
├── guia.md                         # Guía interna de arquitectura
├── README.md                       # Este archivo
└── src/
    └── main/
        └── java/
            └── org/example/MarvelDle/
                ├── Main.java           # Punto de entrada
                ├── JuegoMarvel.java    # Lógica principal del juego
                └── Personaje.java      # Modelo de datos del personaje
```

```mermaid
graph TD
    A["📁 Proyecto-Java-1-DAW"] --> B["📄 pom.xml"]
    A --> C["📄 personajes.json"]
    A --> D["📁 src/main/java/org/example/MarvelDle"]
    D --> E["📄 Main.java"]
    D --> F["📄 JuegoMarvel.java"]
    D --> G["📄 Personaje.java"]

    style A fill:#1a1a2e,stroke:#e94560,color:#fff
    style D fill:#16213e,stroke:#0f3460,color:#fff
    style E fill:#0f3460,stroke:#53354a,color:#fff
    style F fill:#0f3460,stroke:#53354a,color:#fff
    style G fill:#0f3460,stroke:#53354a,color:#fff
    style B fill:#533483,stroke:#e94560,color:#fff
    style C fill:#533483,stroke:#e94560,color:#fff
```

---

### 🏗️ Diagrama de clases

```mermaid
classDiagram
    class Main {
        +main(String[] args)$ void
    }

    class JuegoMarvel {
        -ArrayList~Personaje~ baseDeDatos
        -Personaje personajeSecreto
        -Scanner scanner
        -int intentos
        +JuegoMarvel()
        +iniciarJuego() void
        -cargarPersonajes() void
        -elegirPersonajeSecreto() void
        -compararAtributos(Personaje intento) void
    }

    class Personaje {
        -String nombre
        -String genero
        -String tipo
        -String especie
        -String origen
        -String afiliacion
        -int aparicion
        +Personaje(String, String, String, String, String, String, int)
        +getNombre() String
        +getGenero() String
        +getTipo() String
        +getEspecie() String
        +getOrigen() String
        +getAfiliacion() String
        +getAparicion() int
    }

    Main --> JuegoMarvel : crea e inicia
    JuegoMarvel "1" *-- "0..*" Personaje : baseDeDatos
    JuegoMarvel "1" o-- "1" Personaje : personajeSecreto
```

---

### 🔄 Flujo de ejecución

El siguiente diagrama muestra el flujo completo de una partida desde que el usuario ejecuta la aplicación:

```mermaid
flowchart TD
    A(["▶️ Inicio"]) --> B["Main.main()"]
    B --> C["Crear instancia de JuegoMarvel"]
    C --> D["cargarPersonajes() — Leer personajes.json con Gson"]
    D --> E["elegirPersonajeSecreto() — Selección aleatoria"]
    E --> F["iniciarJuego() — Mostrar bienvenida"]
    F --> G["Pedir nombre de personaje al usuario"]
    G --> H{"¿Existe en la base de datos?"}
    H -- "No" --> I["⚠️ Mostrar mensaje de error"]
    I --> G
    H -- "Sí" --> J["compararAtributos() — Evaluar pistas"]
    J --> K{"¿Es el personaje secreto?"}
    K -- "No" --> G
    K -- "Sí" --> L["🎉 Mostrar victoria + nº de intentos"]
    L --> M(["🏁 Fin"])

    style A fill:#00b894,stroke:#00b894,color:#fff
    style M fill:#e17055,stroke:#e17055,color:#fff
    style H fill:#fdcb6e,stroke:#f39c12,color:#2d3436
    style K fill:#fdcb6e,stroke:#f39c12,color:#2d3436
    style L fill:#00cec9,stroke:#00b894,color:#2d3436
```

---

### 🔗 Diagrama de dependencias

```mermaid
flowchart LR
    subgraph Proyecto["📦 MarvelDle"]
        Main["Main.java"]
        JuegoMarvel["JuegoMarvel.java"]
        Personaje["Personaje.java"]
    end

    subgraph Externas["📚 Dependencias externas"]
        Gson["com.google.gson\nGson 2.10.1"]
        JavaIO["java.io\nFileReader / Reader"]
        JavaUtil["java.util\nArrayList / Random / Scanner"]
    end

    subgraph Datos["💾 Datos"]
        JSON["personajes.json"]
    end

    Main --> JuegoMarvel
    JuegoMarvel --> Personaje
    JuegoMarvel --> Gson
    JuegoMarvel --> JavaIO
    JuegoMarvel --> JavaUtil
    JuegoMarvel -.->|"Lee al iniciar"| JSON
    Gson -.->|"Deserializa"| JSON

    style Proyecto fill:#2d3436,stroke:#636e72,color:#dfe6e9
    style Externas fill:#2d3436,stroke:#636e72,color:#dfe6e9
    style Datos fill:#2d3436,stroke:#636e72,color:#dfe6e9
    style Gson fill:#4285f4,stroke:#1a73e8,color:#fff
    style JSON fill:#f39c12,stroke:#e67e22,color:#fff
```

---


### 📊 Modelo de datos — Personaje

Cada personaje se almacena como un objeto JSON con la siguiente estructura:

```mermaid
erDiagram
    PERSONAJE {
        String nombre PK "Ej: Iron Man"
        String genero "Hombre / Mujer"
        String tipo "Héroe / Villano"
        String especie "Humano, Dios, Titán..."
        String origen "Tierra, Asgard..."
        String afiliacion "Vengadores, Guardianes..."
        int aparicion "Año de debut en el MCU"
    }
```

```json
{
  "nombre": "Iron Man",
  "genero": "Hombre",
  "tipo": "Héroe",
  "especie": "Humano",
  "origen": "Tierra",
  "afiliacion": "Vengadores",
  "aparicion": 2008
}
```

---

### 🧩 Extensibilidad

El proyecto está diseñado para ser **fácilmente extensible**. Se puede añadir nuevos minijuegos implementando la interfaz `Juego`:

```mermaid
classDiagram
    class Juego {
        <<interface>>
        +iniciarJuego() void
    }

    class JuegoMarvel {
        +iniciarJuego() void
    }

    class NuevoMinijuego {
        +iniciarJuego() void
    }

    class Main {
        +main(String[] args)$
        +mostrarMenu()
        +seleccionarJuego(int opcion)
    }

    Juego <|.. JuegoMarvel : implementa
    Juego <|.. NuevoMinijuego : implementa
    Main --> Juego : selecciona y ejecuta
```
.
Para añadir un nuevo juego:

1. Crear una clase que implemente `iniciarJuego()`
2. Registrarla en el menú principal de `Main`
3. Implementar la lógica del juego internamente

---

### 🛠️ Stack tecnológico

| Tecnología | Versión | Uso |
|------------|---------|-----|
| **Java** | JDK 25 | Lenguaje principal |
| **Maven** | — | Gestión del proyecto y dependencias |
| **Gson** | 2.10.1 | Deserialización del archivo JSON a objetos Java |

---

<div align="center">

*Proyecto desarrollado como parte de 1º DAW — Desarrollo de Aplicaciones Web*

</div>
