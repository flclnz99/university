#include <stdio.h>
#include <stdbool.h>

//enum color={bianco,rosso,verde};

/*
  void bandiera(Color B[], int n){
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
*/

void printArray(int array[], int n){
  int i=0;
  printf("{");
  while(i<n){
    printf("%d ",array[i]);
    i++;
  }
  printf("}\n");
}

void swap(int array[], int i, int j){
  int tmp=array[i];
  array[i]=array[j];
  array[j]=tmp;
}

int max(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}

int bubbleSortCount(int array[], int n){
  int count=0;
  bool sw=true;
  for(int i=n-1;i>0&&sw;i--){
    sw=false;
    for(int j=0;j<i;j++){
      count++;
      if(array[j]>array[j+1]){
        swap(array,j,j+1);
        sw=true;
      } 
    }
  }
  return count;
}

/*
int bubbleSort(int array[], int n){
  int count=0;
  int swapped=1;
  for(int i=0;i<n-1;i++){
    swapped=0;
    for(int j=0;j<n-1-i;j++){
      count++;
      if(array[j]>array[j+1]){
        swap(array,j,j+1);
        swapped=1;
      }
    }
    if(!swapped){
      break;
    }
  }
  return count;
}

//ESERCIZIO ESAME
//int A[] = {4,3,2,1,0,1,6,7,9,3,2,8,10,6};
void oddEven(int array[], int n){
  int i=0;
  for(int k=0;k<n;k++){
    if((array[k]%2)!=0){
      swap(array,k,i);
      i++;
    }
  }
}

//ESERCIZIO ESAME
//massimo tra le lunghezze decresenti in A[0..n-1]
int maxMinimum(int array[], int n){
  int curr_len=1;
  int max_len=1;
  for(int i=1;i<n;i++){
    if(array[i]<array[i-1]){
      curr_len++;
    } else {
      if(curr_len>max_len){
        max_len=curr_len;
      }
      curr_len=1;
    }
  }
  if(curr_len>max_len){
    max_len=curr_len;
  }
  return max_len;
}
*/

//2.CountBubbleSort
// pre:  0 <= n <= dimensione di A[]
// post: A[0..n-1] è ordinato in senso non decrescente;
//       ritorna il numero di confronti
int countBubbleSort(int A[], int n) {
  int count=0;
  bool sw=true;
  for(int i=n-1;i>0&&sw;i--){
    sw=false;
    for(int j=0;j<i;j++){
      count++;
      if(A[j]>A[j+1]){
        swap(A,j,j+1);
        sw=true;
      }
    }
  }
  return count;
}

int maxSubSequence(int arr[], int n){
  if(n==0){
    return 0;
  }
  int currLen=1;
  int maxLen=1;
  for(int i=1;i<n;i++){
    if(arr[i]<arr[i-1]){
      currLen++;
    }else{
      if(currLen>maxLen){
        maxLen=currLen;
      }
      currLen=1; 
    }
  }
  if(currLen>maxLen){
    maxLen=currLen;
  }
  return maxLen;
}


int main() {
  int A[] = {10, 9, 8, 10, 6, 5, 4, 3, 2, 1};
  int n = sizeof(A)/sizeof(int);
  printf("Array dato: \n");
  printArray(A, n);
  printf("%d\n",maxSubSequence(A,n));
  //int ntest = countBubbleSort(A, n);
  //printf("Array ordinato: \n");
  //printArray(A, n);
  //printf("Numero dei test = %d\n", ntest);
  //printf("max minimum: %d\n",maxMinimum(A,n));
  //printf("Array ordinato: \n");
  //oddEven(A,n);
  //printArray(A, n);
  //printf("num confronti: %d\n", countBubbleSort(A,n));

  return 0;
}