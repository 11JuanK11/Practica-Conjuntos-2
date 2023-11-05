package Controller;

import Model.Profesor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.*;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Procesos {
    //archivo donde estan los registros de los profesores
    File F = new File("./src/Data/Information.txt");
    //conjuntos donde se almacenaran los profesores
    public SortedSet<Profesor> profes_tiempo_completo = new TreeSet();
    public SortedSet<Profesor> profes_ocasional = new TreeSet();
    public SortedSet<Profesor> profes_catedra = new TreeSet();
    
    //metodo constructor
    public Procesos(){
        SortedSet<Profesor> general = recuperar_informacion();
        Pattern patron1 = Pattern.compile("ocasional"), patron2 = Pattern.compile("catedra"), patron3 = Pattern.compile("completo");
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
            String[] vector, vector_fecha;
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
                    vector_fecha = vector[8].split("-");
                    LocalDate fecha = LocalDate.of(Integer.parseInt(vector_fecha[2]), Integer.parseInt(vector_fecha[1]), Integer.parseInt(vector_fecha[0]));
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
        Pattern Validar = Pattern.compile("d{4}-\\d{2}-\\d{2}");
        
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
}
