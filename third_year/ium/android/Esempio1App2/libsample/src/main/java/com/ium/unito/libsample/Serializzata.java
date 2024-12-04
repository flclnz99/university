package com.ium.unito.libsample;

import java.io.Serializable;

public class Serializzata implements Serializable {
        private String id;

        public Serializzata(){}

        public void setId(String s){
            this.id = s;
        }
        public String getId(){
            return id;
        }


}
