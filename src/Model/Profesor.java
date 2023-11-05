/*
Cedula (validar con expresión regular de solo números)
- Nombre completo ( Validar con expresión regular solo letras)
- Sexo (Validar lista desplegable Masculino o Femenino)
- Facultad (Ingenieria, Deportes, Comunicación, Administracion, Idiomas, Ciencias
Basicas, con una lista desplegable)
- Titulo (Pregrado, Especialista, maestria, Doctorado, con lista desplegable)
- Cantidad de asignaturas que dicta- Validar con expresion regular-rango de 1-10
- Cantidad de horas dictadas por semana- Validar con expresion regular-rango de 1-20
- Fecha nacimiento (Validar con expresión regular fecha valida)

*/
package Model;

import java.time.*;
import java.util.Objects;

public class Profesor implements Comparable<Profesor> {
    private int cc;
    private String nombre;
    private String sexo;
    private String facultad;
    private String titulo;
    private String tipo_contratos;
    private int cant_asignaturas;
    private LocalTime horas;
    private LocalDate fecha_nacimiento;

    public Profesor(int cc, String nombre,String sexo, String facultad, String titulo, String tipo_contratos, int cant_asignaturas, LocalTime horas, LocalDate fecha_nacimiento) {
        this.cc = cc;
        this.nombre = nombre;
        this.sexo = sexo;
        this.facultad = facultad;
        this.titulo = titulo;
        this.tipo_contratos = tipo_contratos;
        this.cant_asignaturas = cant_asignaturas;
        this.horas = horas;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Profesor() {
    }
    
    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo_contratos() {
        return tipo_contratos;
    }

    public void setTipo_contratos(String tipo_contratos) {
        this.tipo_contratos = tipo_contratos;
    }

    public int getCant_asignaturas() {
        return cant_asignaturas;
    }

    public void setCant_asignaturas(int cant_asignaturas) {
        this.cant_asignaturas = cant_asignaturas;
    }

    public LocalTime getHoras() {
        return horas;
    }

    public void setHoras(LocalTime horas) {
        this.horas = horas;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.cc;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.facultad);
        hash = 67 * hash + Objects.hashCode(this.titulo);
        hash = 67 * hash + Objects.hashCode(this.tipo_contratos);
        hash = 67 * hash + this.cant_asignaturas;
        hash = 67 * hash + Objects.hashCode(this.horas);
        hash = 67 * hash + Objects.hashCode(this.fecha_nacimiento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (this.cc != other.cc) {
            return false;
        }
        if (this.cant_asignaturas != other.cant_asignaturas) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.facultad, other.facultad)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.tipo_contratos, other.tipo_contratos)) {
            return false;
        }
        if (!Objects.equals(this.horas, other.horas)) {
            return false;
        }
        return Objects.equals(this.fecha_nacimiento, other.fecha_nacimiento);
    }

    @Override
    public String toString() {
        return "CC: " + cc + ", Nombre: " + nombre + ", Sexo: " + sexo + ", Facultad: " + facultad + ", Titulo: " + titulo + ", Tipo de contrato: " + tipo_contratos + ", Cantidad de asignaturas: " + cant_asignaturas + ", Horas: " + horas + ", Fecha de nacimiento: " + fecha_nacimiento;
    }
    

    @Override
    public int compareTo(Profesor o) {
        return this.cc - o.cc;
    }
    
    
    

   
}
