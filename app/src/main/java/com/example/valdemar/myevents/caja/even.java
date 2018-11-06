package com.example.valdemar.myevents.caja;

public class even {
    public String Nomb;
    public String FechI;
    public String HorI;
    public String FechF;
    public String HorF;
    public String Descri;
    public String Cost;

    public even (String Nomb,String FechI,String HorI,String FechF,String HorF,String Descri,String Cost){
        this.Nomb = Nomb;
        this.FechI = FechI;
        this.HorI = HorI;
        this.FechF = FechF;
        this.HorF = HorF;
        this.Descri = Descri;
        this.Cost = Cost;
        //setNomb(Nomb);setFechI(FechI);setHorI(HorI);setFechF(FechF);setHorF(HorF);setDescri(Descri);setCost(Cost);
    }

    public String getNomb() {
        return this.Nomb;
    }

    /*public void setNomb(String nomb) {
        Nomb = nomb;
    }*/

    public String getFechI() {
        return this.FechI;
    }

    /*public void setFechI(String fechI) {
        FechI = fechI;
    }*/

    public String getHorI() {
        return this.HorI;
    }

    /*public void setHorI(String horI) {
        HorI = horI;
    }*/

    public String getFechF() {
        return this.FechF;
    }

    /*public void setFechF(String fechF) {
        FechF = fechF;
    }*/

    public String getHorF() {
        return this.HorF;
    }

    /*public void setHorF(String horF) {
        HorF = horF;
    }*/

    public String getDescri() {
        return this.Descri;
    }

    /*public void setDescri(String descri) {
        Descri = descri;
    }*/

    public String getCost() {
        return this.Cost;
    }

    /*public void setCost(String cost) {
        Cost = cost;
    }*/
}
