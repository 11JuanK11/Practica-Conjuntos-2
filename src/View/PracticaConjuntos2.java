package View;

import Controller.Procesos;
import javax.swing.JOptionPane;

public class PracticaConjuntos2 {

    public static void main(String[] args) {
        Procesos a = new Procesos();
        int Opc = 0;
        System.out.println(a.Listar_tipo_contrato_unico("Ocasional"));
        do{
            Opc = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Ingresar nuevo profesor.\n"
                                                                                    + "2. Menú listar.\n"
                                                                                    + "0. Salir.", "MENU PRINCIPAL", 3));
            
            switch (Opc) {
                case 1:
                    a = Ingresar.IngresarNuevo(a);
                    break;
                
                case 2:
                    String Vector[] = {"Todos y el total.","Solo un tipo y la cantidad.","Contratos combinados y el total.","Cantidad de hombres y mujeres por contrato.","Profesores por cada facultad y su total."};
                    Object S1 = JOptionPane.showInputDialog(null, "Escoja lo que desea listar:", "ELEGIR", JOptionPane.QUESTION_MESSAGE,null,Vector, Vector[0]);
                    String Listar = S1.toString();
                    
                    switch (Listar){
                        case "Todos y el total.": 
                            //Llamar metodo                            
                            break;
                            
                        case "Solo un tipo y la cantidad.":
                            String VectorSolo[] = {"Completo.","Catedra.","Ocasional"};
                            Object S2 = JOptionPane.showInputDialog(null, "Seleccione la opción a listar:", "ELEGIR", JOptionPane.QUESTION_MESSAGE,null,VectorSolo, VectorSolo[0]);;
                            Listar = S2.toString();
                            
                            //LLamar metodo
                            
                            break;
                            
                        case "Contratos combinados y el total.":
                            String VectorCombinados[] = {"Completo y Catedra","Catedra y Ocasional","Ocasional y Completo","Completo, Catedra y Ocasional"};
                            Object S3 = JOptionPane.showInputDialog(null, "Seleccione la opción a listar:", "ELEGIR", JOptionPane.QUESTION_MESSAGE,null,VectorCombinados, VectorCombinados[0]);;
                            Listar = S3.toString();
                            
                            switch (Listar){
                                case "Catedra y Ocasional":
                                    //Llamar metodo con 1
                                    break;
                                    
                                case "Completo y Catedra":
                                    //Llamar metodo con 2
                                    break;
                                    
                                case "Ocasional y Completo":
                                    //Llamar metodo con 3
                                    break;
                                    
                                case "Completo, Catedra y Ocasional":
                                    //Llamar metodo con 4
                                    break;
                            }
                            break;
                            
                        case "Cantidad de hombres y mujeres por contrato.": 
                            //Llamar metodo
                            break;
                            
                        case "Profesores por cada facultad y su total.":
                            S = a.cantidad_facultad();
                            break;
                    }
                    
                case 0:
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta.");
            }        
        }while(Opc != 0);
    }
}
