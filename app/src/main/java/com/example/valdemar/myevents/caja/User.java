package com.example.valdemar.myevents.caja;

public class User {
    public String Nomb;
    public String Apel;
    public String Prof;
    public String Inst;
    public String Carg;
    public String Cedu;

    public User(String nomb, String apel, String prof, String inst, String carg, String cedu) {
        Nomb = nomb;
        Apel = apel;
        Prof = prof;
        Inst = inst;
        Carg = carg;
        Cedu = cedu;
    }

    public String getNomb() {
        return Nomb;
    }

    public void setNomb(String nomb) {
        Nomb = nomb;
    }

    public String getApel() {
        return Apel;
    }

    public void setApel(String apel) {
        Apel = apel;
    }

    public String getProf() {
        return Prof;
    }

    public void setProf(String prof) {
        Prof = prof;
    }

    public String getInst() {
        return Inst;
    }

    public void setInst(String inst) {
        Inst = inst;
    }

    public String getCarg() {
        return Carg;
    }

    public void setCarg(String carg) {
        Carg = carg;
    }

    public String getCedu() {
        return Cedu;
    }

    public void setCedu(String cedu) {
        Cedu = cedu;
    }
}
