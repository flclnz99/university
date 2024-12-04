public class lab2 {
  
  public static String longest(String[] arr){
    assert arr!=null: "array in input nullo";
    String len=null;
    for(int i=0; i<arr.length;i++){
      if(arr[i]!=null){
        if(len==null||len.length()<arr[i].length()){
          len=arr[i];
        }
      }
    }
    assert len!=null:"l'array non contiene stringhe non nulle";
    return len;
  }

  public static String concatAll(String[] arr){
    assert arr!=null: "array in input nullo";
    String concat="";
    for(int i=0; i<arr.length;i++){
      if(arr[i]!=null){
        concat=concat.concat(arr[i]);
      }
    }
    return concat;
  }

  

  public static void main(String[] args){
    String[] arr={"ciao","sonnnnnno","bellooo"};
    String str="   ciao  a   ";
    //String[] arr={};
    //System.out.println(concatAll(arr));
    //System.out.println(arr.length);
    //System.out.println(str);
    //System.out.println(trim_(str));
  }
}
