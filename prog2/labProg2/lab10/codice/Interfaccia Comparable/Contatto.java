public class Contatto implements Comparable<Contatto>{
    private String nome;
    private String email;

    public Contatto(String nome, String email) {
        this.nome = nome; 
        this.email = email;
    }

    public  String getNome() {
        return nome;
    }
    public  String getEmail() {
        return email;
    }

    public void setNome(String n){
        nome = n;
    }
    public void setEmail(String e){
        email = e;
    }

    public void scriviOutput() {
        System.out.println(" - " + nome + " : " + email);
    }

    @Override
    public int compareTo(Contatto c){
        return this.nome.compareTo(c.nome);    
    }
}
