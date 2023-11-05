package View;

import Controller.Procesos;
import Model.Profesor;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class Ingresar {
    
    public static Procesos IngresarNuevo(Procesos A){
        Profesor Profesor = new Profesor();
        String S = "";
        boolean Cumple = false;
        
        //Pide el tipo de contrato con lista desplegable
        String VectorTipoContrato[] = {"Completo","Catedra","Ocasional","Completo;Catedra","Completo;Ocasional","Catedra;Ocasional","Completo;Catedra;Ocasional"};
        Object St = JOptionPane.showInputDialog(null, "Seleccione el tipo de contrato:", "ELEGIR", JOptionPane.QUESTION_MESSAGE,null,VectorTipoContrato, VectorTipoContrato[0]);
        S = St.toString();
        Profesor.setTipo_contratos(S);
        
        //Pide la cedula y valida con expresion regular
        while (!Cumple){
            S = JOptionPane.showInputDialog(null, "Ingrese la cédula:");
            Cumple = A.Cedula(S);
            if(!Cumple){
              JOptionPane.showMessageDialog(null, "La cédula no cumple con la condición.\nSolo se permiten números.");
            }
        }
        Profesor.setCc(Integer.parseInt(S));
        Cumple = false;
        
        //Pide el nombre y valida con expresion regular
        while (!Cumple){
            S = JOptionPane.showInputDialog(null, "Ingrese el nombre completo:");
            Cumple = A.NombreCompleto(S);
            if(!Cumple){
              JOptionPane.showMessageDialog(null, "El nombre no cumple con la condición.\nSolo se permiten letras.");
            }
        }
        Profesor.setNombre(S);
        Cumple = false;
        
        //Lista desplegable de genero
        String VectorGenero[] = {"Femenino","Masculino"};
        St = JOptionPane.showInputDialog(null, "Seleccione el género:", "ELEGIR", JOptionPane.QUESTION_MESSAGE,null,VectorGenero, VectorGenero[0]);
        S = St.toString();
        Profesor.setSexo(S);
        
        //Lista desplegable de facultad
        String VectorFacultad[] = {"Ingenieria","Deportes","Comunicación","Administracion","Idiomas","Ciencias Basicas"};
        St = JOptionPane.showInputDialog(null,"Seleccione la facultad:", "ELEGIR", JOptionPane.QUESTION_MESSAGE,null,VectorFacultad, VectorFacultad[0]);
        S = St.toString();
        Profesor.setFacultad(S);
        
        //Lista desplegable de titulo
        String VectorTitulo[] = {"Pregrado","Especialista","Maestria","Doctorado"};
        St = JOptionPane.showInputDialog(null,"Seleccione el titulo de estudio:", "ELEGIR", JOptionPane.QUESTION_MESSAGE,null,VectorTitulo, VectorTitulo[0]);
        S = St.toString();
        Profesor.setTitulo(S);
        
        //Pide la cantidad de asignaturas que da y valida con expresion regular 
        while (!Cumple){
            S = JOptionPane.showInputDialog(null, "Ingrese la cantidad de asignaturas que dicta:");
            Cumple = A.CantidadAsignaturas(S);
            if(!Cumple){
              JOptionPane.showMessageDialog(null, "Solo se permiten números y debe estar en un rango de 1-10.");
            }
        }
        Profesor.setCant_asignaturas(Integer.parseInt(S));
        Cumple = false;
        
        //Pide la cantidad de horas que da y valida con expresion regular 
        while (!Cumple){
            S = JOptionPane.showInputDialog(null, "Ingrese la cantidad de horas que dicta:");
            Cumple = A.CantidadHoras(S);
            if(!Cumple){
              JOptionPane.showMessageDialog(null, "Solo se permiten números y debe estar en un rango de 1-20.");
            }
        }
        Profesor.setHoras(LocalTime.of(Integer.parseInt(S), 0));
        Cumple = false;
        
        //Pide la fecha de nacimiento y valida con expresion regular 
        while (!Cumple){
            S = JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento(AAAA-MM-DD):");
            Cumple = A.FechaNacimiento(S);
            if(!Cumple){
              JOptionPane.showMessageDialog(null, "No cumple con el formato pedido.");
            }
        }
        Profesor.setFecha_nacimiento(LocalDate.parse(S));
        
        A.Agregar(Profesor);
        JOptionPane.showMessageDialog(null, "Registro exitoso.");
        
        return A;
    }
}
