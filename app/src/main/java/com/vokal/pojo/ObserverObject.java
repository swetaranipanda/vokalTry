package com.vokal.pojo;

import java.util.Observable;
import java.util.Observer;

public class ObserverObject extends Observable {
    String valueCon;

    public ObserverObject(String value) {
        valueCon = value;
    }

    public void setValue(String value) {

        if (!valueCon.equals(value)) {
            valueCon = value;
            setChanged();
        }
    }

}
