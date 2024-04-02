/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import static daw.MetodosTarea7c.escribir;
import static daw.MetodosTarea7c.leerFichero;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daniel
 */
public class Tarea7cDanielNavasBorjas {

    public static void main(String[] args) {

        //mostramos los datos de la lista de persona por consola
        listaObjetoPersona().forEach(System.out::println);
        
        System.out.println("\nSiguiente método\n");
        
        //método que nos enseña por consola los géneros que hay
        Set<String> conjunto = conjuntoGenero(listaObjetoPersona());
        for (String string : conjunto) {
            System.out.println(string);
            escribir(string, "generos.txt");
        }
        
        System.out.println("\nSiguiente método\n");
        
        //método para contar géneros y guardarlo en un fichero .csv
        //lo guardamos en un map para luego recorrerlo e ir escribiendo y mostrando
        Map<String, Integer> map = contadorGeneros(listaObjetoPersona());
        
        //hacemos un for eache para ir recorriendo e ir escribiendo en el
        //fichero "contadorGeneros.csv"
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            //mostramos por consola
            System.out.println(entry.getKey() + "," + entry.getValue());
            //llamamos al método escribir para ir escribiendo como nos dice
            //el examen
            escribir(entry.getKey() + "," + entry.getValue(), "contadorGeneros.csv");
            
        }
    }

    //método para pasar la lista de String a lista de personas
    public static List<Persona> listaObjetoPersona() {
        List<String> lineas = leerFichero("personas.csv");
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

    //método que muestra los géneros que hay y escribe en un fichero cada género
    public static Set<String> conjuntoGenero(List<Persona> lista) {
        //creamos el map para ir añadiendo el id y el género
        Set<String> set = new HashSet<>();

        //recorremos la lista de personas
        for (int i = 0; i < lista.size(); i++) {
            //cada iteración se añade al set el género
            set.add(lista.get(i).genero());
        }

        //devolvemos el set
        return set;

    }

    //método para contar cuantos géneros hay
    public static Map<String, Integer> contadorGeneros(List<Persona> lista) {
        //SE PUEDE USAR TAMBIÉN EL MAP.GETORDEFAULT()
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            if (map.containsKey(lista.get(i).genero())) {
                map.put(lista.get(i).genero(), map.get(lista.get(i).genero()) + 1);
            } else {
                map.put(lista.get(i).genero(), 1);
            }
        }
        
        return map;

    }
}
