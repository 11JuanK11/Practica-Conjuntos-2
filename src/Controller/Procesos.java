package Controller;

import Model.Profesor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.*;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Procesos {
    //archivo donde estan los registros de los profesores
    private File F = new File("./src/Data/Information.txt");
    //conjuntos donde se almacenaran los profesores
    public SortedSet<Profesor> profes_tiempo_completo = new TreeSet();
    public SortedSet<Profesor> profes_ocasional = new TreeSet();
    public SortedSet<Profesor> profes_catedra = new TreeSet();
    //metodo constructor
    public Procesos(){
        SortedSet<Profesor> general = recuperar_informacion();
        Pattern patron1 = Pattern.compile("Ocasional"), patron2 = Pattern.compile("Catedra"), patron3 = Pattern.compile("Completo");
        Matcher emparejador;
        for (Profesor profesor : general) {
            //comparacion para agregar a conjunto de profesores con tipo contrato ocasional
            emparejador = patron1.matcher(profesor.getTipo_contratos());
            if (emparejador.find()) {
                profes_ocasional.add(profesor);
            }
            //comparacion para agregar a conjunto de profesores con tipo contrato catedra
            emparejador = patron2.matcher(profesor.getTipo_contratos());
            if (emparejador.find()) {
                profes_catedra.add(profesor);
            }
            //comparacion para agregar a conjunto de profesores con tipo contrato completo
            emparejador = patron3.matcher(profesor.getTipo_contratos());
            if (emparejador.find()) {
                profes_tiempo_completo.add(profesor);
            }
        }      
    }
     
    //recupera la informacion del archivo plano
    private SortedSet<Profesor> recuperar_informacion(){
        SortedSet<Profesor> general = new TreeSet<>();
        try {
            String iteracion = "";
            String[] vector;
            FileReader archivo = new FileReader(F);
            BufferedReader lector = new BufferedReader(archivo);
            while (iteracion != null) {  
                iteracion = lector.readLine();
                if (iteracion != null) {
                    Profesor auxiliar = new Profesor();
                    vector = iteracion.split(",");
                    auxiliar.setCc(Integer.parseInt(vector[0]));
                    auxiliar.setNombre(vector[1]);
                    auxiliar.setSexo(vector[2]);
                    auxiliar.setFacultad(vector[3]);
                    auxiliar.setTitulo(vector[4]);
                    auxiliar.setTipo_contratos(vector[5]);
                    auxiliar.setCant_asignaturas(Integer.parseInt(vector[6]));
                    LocalTime hora = LocalTime.of( Integer.parseInt(vector[7]), 0);
                    auxiliar.setHoras(hora);
                    LocalDate fecha = LocalDate.parse(vector[8]);
                    auxiliar.setFecha_nacimiento(fecha);
                    general.add(auxiliar);
                }      
            }
            lector.close();    
        } catch (Exception e) {
            System.out.println("pum");
        }
        return general;   
    }
    
    public boolean Cedula(String Cedula){
        boolean Cumple = false;
        Pattern Validar = Pattern.compile("[0-9]+");

        Matcher ComparacionCedula = Validar.matcher(Cedula);
        if (ComparacionCedula.matches()){
            Cumple = true;
        }
        return Cumple;
    }
    
    public boolean NombreCompleto(String Nombre){
        boolean Cumple = false;
        Pattern Validar = Pattern.compile("([a-zA-Z]+\\s?[a-zA-Z]*)+");
        
        Matcher ComparacionNombre = Validar.matcher(Nombre);
        if (ComparacionNombre.matches()){
            Cumple = true;
        }  
        return Cumple;
    }
    
    public boolean CantidadAsignaturas(String Asignatura){
        boolean Cumple = false;
        Pattern Validar = Pattern.compile("[1-9]{1}|10");
        
        Matcher ComparacionAsignatura = Validar.matcher(Asignatura);
        if (ComparacionAsignatura.matches()){
            Cumple = true;
        }
        return Cumple;
    }
    
    public boolean CantidadHoras(String Horas){
        boolean Cumple = false;
        Pattern Validar = Pattern.compile("0?[1-9]{1}|10|(1[1-9]){1}|20");
        
        Matcher ComparacionHoras = Validar.matcher(Horas);
        if (ComparacionHoras.matches()){
            Cumple = true;
        }
        return Cumple;
    }
    
    public boolean FechaNacimiento(String Fecha){
        boolean Cumple = false;
        Pattern Validar = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        
        Matcher ComparacionFecha = Validar.matcher(Fecha);
        if (ComparacionFecha.matches()){
            Cumple = true;
        }
        return Cumple;
    }
    
    public void Agregar(Profesor profesor){
        Pattern patron1 = Pattern.compile("Ocasional"), patron2 = Pattern.compile("Catedra"), patron3 = Pattern.compile("Completo");
        Matcher emparejador;
        
            //comparacion para agregar a conjunto de profesores con tipo contrato ocasional
            emparejador = patron1.matcher(profesor.getTipo_contratos());
            if (emparejador.find()) {
                profes_ocasional.add(profesor);
            }
            
            //comparacion para agregar a conjunto de profesores con tipo contrato catedra
            emparejador = patron2.matcher(profesor.getTipo_contratos());
            if (emparejador.find()) {
                profes_catedra.add(profesor);
            }
            
            //comparacion para agregar a conjunto de profesores con tipo contrato completo
            emparejador = patron3.matcher(profesor.getTipo_contratos());
            if (emparejador.find()) {
                profes_tiempo_completo.add(profesor);
            }
    }
    
    public String Listar_tipo_contrato_unico(String contrato){
        Pattern patron = Pattern.compile(contrato);
        Matcher emparejador;
        int contador = 0;
        String cadena = "";
        switch (contrato) {
            case "Catedra" -> {
                for (Profesor profesor : profes_catedra) {
                    emparejador = patron.matcher(profesor.getTipo_contratos());
                    if (emparejador.matches()) {
                        contador++;
                        cadena += profesor.toString() + "\n";
                    }
                }
                cadena+= "Numero de profesores con contrato de catedra: " + contador;
            }
            case "Completo" -> {
                for (Profesor profesor : profes_tiempo_completo) {
                    emparejador = patron.matcher(profesor.getTipo_contratos());
                    if (emparejador.matches()) {
                        contador++;
                        cadena += profesor.toString() + "\n";
                    }
                }   
                cadena+= "Numero de profesores con contrato de tiempo completo: " + contador; 
            }
            case "Ocasional" -> {
                for (Profesor profesor : profes_ocasional) {
                    emparejador = patron.matcher(profesor.getTipo_contratos());
                    if (emparejador.matches()) {
                        contador++;
                        cadena += profesor.toString() + "\n";
                    }
                }
                cadena+= "Numero de profesores con contrato ocasional: " + contador;
            }
            default -> cadena = "";
        }
        if (!cadena.equals("")) {
            return cadena;
        }else{
            return "No se han encontrado algun profesor con esa condicion";
        }
    }
    
    public String Listar_Profesores(){
        SortedSet<Profesor> general = Crear_General();
        String cadena = "";
        
        for (Profesor profesor : general) {
            cadena += profesor.toString()+ "\n";
        }
        cadena += "Numero total de profesores: " + general.size();
        return cadena;
    }
    
    public String Listar_tipo_contrato_combinado(int opcion){
        String cadena = "";
        int contador = 0;
        switch (opcion) {
            case 1 -> {
                //Catedra y ocasional
                for (Profesor profesor : profes_ocasional) {
                    if (profes_catedra.contains(profesor) && !profes_tiempo_completo.contains(profesor)) {
                        cadena += profesor.toString() + "\n";
                        contador++;
                    }   
                }
                cadena += "Cantidad de profesores con contrato de catedra y ocasional: " + contador;
            }
            case 2 -> {
                //catedra y tiempo completo
                for (Profesor profesor : profes_tiempo_completo) {
                    if (profes_catedra.contains(profesor) && !profes_ocasional.contains(profesor)) {
                        cadena += profesor.toString() + "\n";
                        contador++;
                    }   
                }
                cadena += "Cantidad de profesores con contrato de catedra y tiempo completo: " + contador;
            }
            case 3 -> {
                //tiempo completo y ocasional
                for (Profesor profesor : profes_ocasional) {
                    if (profes_tiempo_completo.contains(profesor) && !profes_catedra.contains(profesor)) {
                        cadena += profesor.toString() + "\n";
                        contador++;
                    }   
                }
                cadena += "Cantidad de profesores con contrato de tiempo completo y ocasional: " + contador;
            }
            case 4 -> {
                //todos los tipos de contrato
                for (Profesor profesor : profes_ocasional) {
                    if (profes_tiempo_completo.contains(profesor) && profes_catedra.contains(profesor)) {
                        cadena += profesor.toString() + "\n";
                        contador++;
                    }   
                }
                cadena += "Cantidad de profesores con contrato de tiempo completo, ocasional y de catedra: " + contador;
            }
            default -> cadena = "Ha ocurrido un error";     
        }
        
        return cadena;
    }
    
    public String cantidad_sexos_contrato(){
        String cadena = "";
        int contador_hombres = 0, contador_mujeres = 0;
        for (Profesor profesor : profes_tiempo_completo) {
            if(profesor.getSexo().equalsIgnoreCase("Masculino")){
                contador_hombres++;
            }else{
                contador_mujeres++;
            }
        }
        cadena += "Contrato de tiempo completo.\n";
        cadena += "Cantidad de hombres: " + contador_hombres + "\nCantidad de mujeres: "+ contador_mujeres + "\n\n"; 
        contador_hombres = 0;
        contador_mujeres = 0;
        for (Profesor profesor : profes_ocasional) {
            if(profesor.getSexo().equalsIgnoreCase("Masculino")){
                contador_hombres++;
            }else{
                contador_mujeres++;
            }
        }
        cadena += "Contrato ocasional.\n";
        cadena += "Cantidad de hombres: " + contador_hombres + "\nCantidad de mujeres: "+ contador_mujeres + "\n\n"; 
        contador_hombres = 0;
        contador_mujeres = 0;
        for (Profesor profesor : profes_catedra) {
            if(profesor.getSexo().equalsIgnoreCase("Masculino")){
                contador_hombres++;
            }else{
                contador_mujeres++;
            }
        }
        cadena += "Contrato de catedra.\n";
        cadena += "Cantidad de hombres: " + contador_hombres + "\nCantidad de mujeres: "+ contador_mujeres;  
        return cadena;
    }
    
    private SortedSet<Profesor> Crear_General(){
        SortedSet<Profesor> general = new TreeSet<>(profes_catedra);
        general.addAll(profes_ocasional);
        general.addAll(profes_tiempo_completo);
        return general;
    }
    
    public void cantidad_facultad(){
        SortedSet<Profesor> general = Crear_General();
        String cadena_ingenieria = "Facultad Ingenieria.\n", cadena_deportes = "Facultad Deportes.\n", cadena_comunicacion = "Facultad Comunicacion.\n", cadena_administracion = "Facultad Administracion.\n", cadena_idiomas = "Facultad Idiomas.\n", cadena_ciencias_basicas = "Facultad Ciencias basicas.\n";
        int cantidad_ingenieria = 0, cantidad_deportes = 0, cantidad_comunicacion = 0, cantidad_administracion = 0, cantidad_idiomas = 0, cantidad_ciencias_basicas = 0;
        for (Profesor profesor : general) {
            switch (profesor.getFacultad()) {
                case "Ingenieria" -> {
                    cadena_ingenieria += profesor.toString() + "\n";
                    cantidad_ingenieria++;
                }
                case "Deportes" -> {
                    cadena_deportes += profesor.toString() + "\n";
                    cantidad_deportes++;
                }
                case "Comunicación" -> {
                    cadena_comunicacion += profesor.toString() + "\n";
                    cantidad_comunicacion++;
                }
                case "Administracion" -> {
                    cadena_administracion += profesor.toString() + "\n";
                    cantidad_administracion++;
                }
                case "Idiomas" -> {
                    cadena_idiomas += profesor.toString() + "\n";
                    cantidad_idiomas++;
                }
                case "Ciencias Basicas" -> {
                    cadena_ciencias_basicas += profesor.toString() + "\n";
                    cantidad_ciencias_basicas++;
                }
            }
        }
        cadena_ingenieria += "Cantidad de profesores: " + cantidad_ingenieria + "\n\n";
        cadena_administracion += "Cantidad de profesores: " + cantidad_administracion + "\n\n";
        cadena_deportes += "Cantidad de profesores: " + cantidad_deportes + "\n\n";
        cadena_ciencias_basicas += "Cantidad de profesores: " + cantidad_ciencias_basicas + "\n\n";
        cadena_comunicacion += "Cantidad de profesores: " + cantidad_comunicacion + "\n\n";
        cadena_idiomas += "Cantidad de profesores: " + cantidad_idiomas + "\n\n";
        
        JOptionPane.showMessageDialog(null, cadena_ingenieria);
        JOptionPane.showMessageDialog(null, cadena_administracion);
        JOptionPane.showMessageDialog(null, cadena_deportes);
        JOptionPane.showMessageDialog(null, cadena_ciencias_basicas);
        JOptionPane.showMessageDialog(null, cadena_comunicacion);
        JOptionPane.showMessageDialog(null, cadena_idiomas);
    }
    
    public String CantidadPregados(){
        SortedSet<Profesor> general = Crear_General();
        String S = "";
        int Cant = 0;
        
        for (Profesor profesor : general) {
            if (profesor.getTitulo().equalsIgnoreCase("Pregrado")){
                Cant++;
                S += profesor.toString() + "\n";
            }
        }
        S += "\nCantidad total: " + Cant;   
        return S;
    } 
}
