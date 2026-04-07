package org.example.MarvelDle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;

public class JuegoMarvel {
    private ArrayList<Personaje> baseDeDatos;
    private Personaje personajeSecreto;
    private Scanner scanner;
    private int intentos;

    public JuegoMarvel() {
        baseDeDatos = new ArrayList<>();
        scanner = new Scanner(System.in);
        cargarPersonajes();
        elegirPersonajeSecreto();
    }

    private void cargarPersonajes() {
        try (java.io.Reader reader = new java.io.FileReader("personajes.json")) {
            com.google.gson.Gson gson = new com.google.gson.Gson();

            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Personaje>>(){}.getType();

            this.baseDeDatos = gson.fromJson(reader, listType);

            System.out.println("✅ Base de datos cargada desde JSON con " + baseDeDatos.size() + " personajes.");
        } catch (Exception e) {
            System.out.println("❌ Error al cargar el JSON: " + e.getMessage());
            this.baseDeDatos = new ArrayList<>();
        }
    }

    private void elegirPersonajeSecreto() {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(baseDeDatos.size());
        personajeSecreto = baseDeDatos.get(indiceAleatorio);
    }

    public void iniciarJuego() {
        System.out.println("=====================================");
        System.out.println("   🦸‍♂️ BIENVENIDO A MARVEL-DLE 🦸‍♀️   ");
        System.out.println("=====================================");
        System.out.println("He elegido un personaje secreto de la base de datos.");
        System.out.println("Base de datos cargada con: " + baseDeDatos.size() + " personajes.");
        System.out.println("Pistas: ✅ (Correcto) | ❌ (Incorrecto) | ⬆️ ⬇️ (Año mayor/menor)\n");

        boolean ganado = false;

        while (!ganado) {
            System.out.println("\nIntroduce un nombre de personaje:");
            String intentoNombre = scanner.nextLine();
            this.intentos++;

            Personaje intentoUsario = null;
            for (Personaje p : baseDeDatos) {
                if (p.getNombre().equalsIgnoreCase(intentoNombre)) {
                    intentoUsario = p;
                    break;
                }
            }

            if (intentoUsario == null) {
                System.out.println("⚠️ Ese personaje no está en la base de datos. ¡Prueba otro!");
                continue;
            }

            System.out.println("\nAnalizando a " + intentoUsario.getNombre() + "...");
            compararAtributos(intentoUsario);

            if (intentoUsario.getNombre().equalsIgnoreCase(personajeSecreto.getNombre())) {
                System.out.println("\n🎉 ¡BRUTAL! Has acertado. El personaje era " + personajeSecreto.getNombre());
                System.out.println("\n🎉 Has echo " + intentos + " intentos.");
                ganado = true;
            }
        }
    }

    private void compararAtributos(Personaje intento) {
        System.out.print("Género: " + (intento.getGenero().equals(personajeSecreto.getGenero()) ? "✅ " : "❌ "));
        System.out.print("| Tipo: " + (intento.getTipo().equals(personajeSecreto.getTipo()) ? "✅ " : "❌ "));
        System.out.print("| Especie: " + (intento.getEspecie().equals(personajeSecreto.getEspecie()) ? "✅ " : "❌ "));
        System.out.print("| Origen: " + (intento.getOrigen().equals(personajeSecreto.getOrigen()) ? "✅ " : "❌ "));

        if (intento.getAparicion() == personajeSecreto.getAparicion()) {
            System.out.println("| Año: ✅");
        } else if (intento.getAparicion() < personajeSecreto.getAparicion()) {
            System.out.println("| Año: ⬆️ (El secreto es más reciente)");
        } else {
            System.out.println("| Año: ⬇️ (El secreto es más antiguo)");
        }
    }
}
