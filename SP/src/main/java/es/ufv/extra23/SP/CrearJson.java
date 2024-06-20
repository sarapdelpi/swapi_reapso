package es.ufv.extra23.SP;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CrearJson {

    public static void GuardarDatos(ArrayList<Starship> spaceship) throws IOException {
        // Crear una instancia de la clase Gson para convertir objetos a JSON
        Gson gson = new Gson();

        // Convertir el objeto a JSON
        String json = gson.toJson(spaceship);

        //no creo aqui el id porque como ya existen id's en el json,solo se añadirán id's nuevos
        //cuando se añadan nuevos tweets

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("starship.json"))) {
            bw.write(json);
            System.out.println("Fichero creado");
        }
    }
}
