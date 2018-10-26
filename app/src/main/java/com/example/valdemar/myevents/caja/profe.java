package com.example.valdemar.myevents.caja;

public class profe {
    private String profesion;
    private String precio;

    public profe(){};
    public profe (String profesion,String precio){
        setProfesion(profesion);
        setPrecio(precio);
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /*@Override
    public String toString() {
        return profesion;
    }*/

    @Override
    public String toString() {
        return profesion +" costo: "+ precio;
    }
}
