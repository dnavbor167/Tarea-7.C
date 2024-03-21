/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author daniel
 */
public class MetodosTarea7c {

    //método para leer el fichero de la raíz
    public static List<String> leerFichero() {
        String fichero = "personas.csv";
        List<String> lineas = new ArrayList<>();

        try {
            lineas = Files.readAllLines(Paths.get(fichero),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Error leyendo el fichero " + fichero);
        }
        //eliminamos la posición 0 
        lineas.remove(0);

        return lineas;

    }

    //método para pasar la lista de String a lista de personas
    public static List<Persona> listaObjetoPersona() {
        List<String> lineas = leerFichero();
        //creo la lista que voy a devolver
        List<Persona> persona = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i++) {
            //separamos cada línea por , y lo guardamos en un array
            String[] array = lineas.get(i).split(",");

            //le damos un formato a la fecha de (día-mes-año)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            //creamos la fecha, le pasamos el string de la fecha y el formatter
            //creado anterior
            LocalDate fecha = LocalDate.parse(array[5], formatter);

            //creamos una persona y cada vez que itere este for cambiarán
            //los datos
            Persona per = new Persona(Integer.parseInt(array[0]),
                    array[1], array[2], array[3], array[4], fecha,
                    Boolean.parseBoolean(array[6]), array[7]);
            //añadimos a la lista la persona creada anteriormente
            //(esta irá modificandose en cada iteración)
            persona.add(per);
        }
        return persona;
    }

    //método que devuelve en un conjunto todos los tipos de datos de género
    public static void conjuntoGenero(List<Persona> lista) {
        //creamos el map para ir añadiendo el id y el género
        Map<Integer,String> generoLinea = new HashMap<>();
        
        //recorremos la lista de personas
        for (int i = 0; i < lista.size(); i++) {
            //cada iteración se añade al Map el id y el género (aunque se repita
            //el género el id nunca se repetirá)
            generoLinea.put(lista.get(i).id(), lista.get(i).genero());
        }
        
    }
}
