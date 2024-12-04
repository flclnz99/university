#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

enum color {rosso, bianco, verde};
typedef enum color Color;

void swap(Color A[], int i, int j){
  Color color = A[j];
  A[i]=j;
  A[j]=color;
}


// pre:  0 <= n == dim di B[]
// post: in B[0..n-1] i valori verde precedono i bianco ed i rosso, i bianco precedono i rosso
//{rosso,  verde,  rosso, bianco, verde, bianco}
//{bianco, bianco, rosso, rosso,  verde, verde}
void Bandiera(Color B[], int n){
  int i=0;
  int j=0;
  for(int k=0;k<n;k++){
    if(B[k]==bianco){
      swap(B,j,k);
      j++;
    }
    if(B[k]==verde){
      swap(B,j,k);
      swap(B,i,j);
      i++;
      j++;
    }
  }
}

void printBandiera(Color A[]){
  for(int i=0; A[i]!=NULL;i++){
    printf("%s\n",A[i]);
  }
}

int main(){
  Color B1[] = {rosso, verde, rosso, bianco, verde, bianco};
  int dimB1 = sizeof(B1)/sizeof(int);

  //Bandiera(B1, dimB1);
  //printBandiera(B1, dimB1);
}