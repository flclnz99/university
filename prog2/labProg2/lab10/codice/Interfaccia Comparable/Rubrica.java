public  class Rubrica {
    private int numContatti;
    private Contatto[] contatti;
    
    public Rubrica(int maxContatti) {
        numContatti = 0;
        contatti =  new Contatto[maxContatti];
    }

    public  int getNumContatti() { 
        return numContatti; 
    }
    
    public void scriviOutput() {
        int i=0;
        System.out.println("Num. contatti = " + numContatti);
        while(i < numContatti) {
            contatti[i].scriviOutput();
            ++i;
        }
    }

    private int cercaIndice(String n) {
        int i=0;
        while(i < numContatti) {
            if (contatti[i].getNome().equals(n)) 
                return i; 
            ++i;
        }   
        return numContatti;
    }

    public boolean presente(String n) {
        return (cercaIndice(n) < numContatti);
    }

    public boolean piena() {
        return (numContatti  == contatti.length);
    }

    public boolean aggiungi(String n, String e) {  
        if (presente(n)) 
            return false;
        if (piena()) 
            return false;
        contatti[numContatti] = new Contatto(n,e); 
        ++numContatti;
        return true; 
    }

    public boolean rimuovi(String n) {
        int i = cercaIndice(n);
        if (i == numContatti) 
            return false;
        --numContatti;
        while (i < numContatti) {
            contatti[i] = contatti[i+1]; 
            ++i;
        }
        return true;
    }

    public boolean cambiaNome(String n, String n2) {
        int i = cercaIndice(n);
        if (i == numContatti) 
            return false;
        int i2 = cercaIndice(n2);
        if (i2 != numContatti) 
            return false;
        contatti[i].setNome(n2);
        return true;
    }
    
    public boolean cambiaEmail(String n, String e2) {  
        int i = cercaIndice(n);
        if (i == numContatti) 
            return false;
        contatti[i].setEmail(e2);
        return true;
    }

    public void sort(){
        int i=0;
        int j=1;
        Contatto tmp;
        while(i < numContatti-1){
            while(j<0){
                if(contatti[i].compareTo(contatti[i+1])>0){
                    tmp=contatti[i];
                    contatti[i]=contatti[i+1];
                    contatti[i+1]=tmp;
                }
            }
            i++;
        }
    }
}

