/************************************
* Struttura dati degli alberi k-ari
************************************/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
#define SEPARATOR "#<ab@17943918#@>#"

struct kTreeVertex {
    int                  key;
    struct kTreeVertex*  child;
    struct kTreeVertex*  sibling;
};

typedef struct kTreeVertex* kTree;

kTree consTree(int k, kTree c, kTree s) {
    kTree t = malloc(sizeof(struct kTreeVertex));
    t->key = k;
    t->child = c;
    t->sibling = s;
    return t;
}

kTree leaf(int k, kTree s) {
    return consTree(k, NULL, s);
}

kTree root(int k, kTree c) {
    return consTree(k, c, NULL);
}

int max(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}


// post: stampa indentata dell'albero t con margine
//       iniziale di n tab
void printTree(kTree t, int d) {
    if (t != NULL)
    for (int i = 0; i < d; ++i)
        printf("   ");
    printf("%d\n", t->key);
    kTree cl = t->child;
    while (cl != NULL) {
        printTree(cl, d + 1);
        cl = cl->sibling;
    }
}

/*
int fatherChildSum(kTree t){
    if(t==NULL){
        return 0;
    }
    if(t->child==NULL){
        return 1;
    }
    int sum=0;
    kTree tmp = t->child;
    int b=1;
    while(tmp!=NULL){
        sum+=tmp->key;
        b=(b && fatherChildSum(tmp));
        tmp=tmp->sibling;
    }
    return (t->key==sum && b);
}

int numNodiProfondi(kTree t, int h){
    if(t==NULL){
        return 0;
    }
    if(h==0){
        return 1;
    }
    int numNodes=1;
    kTree tmp = t->child;
    while(tmp!=NULL){
        numNodes+=numNodiProfondi(tmp,h-1);
        tmp=tmp->sibling;
    }
    return numNodes;
}
*/

//pre:  t non è vuoto
//post: ritorna l'altezza di t
int height(kTree t){
    if(t==NULL){
        return 0;
    }
    if(t->child==NULL){
        return 0;
    }
    int ht=0;
    kTree current = t->child;
    while(current){
        ht=max(ht,height(current));
        current=current->sibling;
    }
    return ht+1;
}

int degree(kTree t) {
    if(t->child==NULL){
        return 1;
    }else{
        int dg=0;
        kTree c=t->child;
        while(c){
            dg=max(dg,degree(c));
            c=c->sibling;
        }
        return dg+1;
    }
}

// post: ritorna la somma delle etichette
//       delle foglie di t
int sumLeaf (kTree t) {
    if(t==NULL){
        return 0;
    }
    if(t->child==NULL){
        return t->key;
    }
    int sum=0;
    kTree current = t->child;
    while(current){
        sum+=sumLeaf(current);
        current=current->sibling;
    }
    return sum;
}

// post: ritorna la più grande somma delle etichette tra i rami
int maxSumBranch(kTree t){
	if(t==NULL){
        return 0;
    }else{
        if(t->child==NULL){
            return t->key;
        }else{
            int sum=0;
            kTree c=t->child;
            while(c){
                sum=max(sum,maxSumBranch(c));
                c=c->sibling;
            }
            return t->key+sum;
        }
    }
}

// pre: albero etichettato con interi
//post: ritorna true se tutte le etichette sono dispari, false altrimenti
bool isOdd(kTree t){
    if(t==NULL){
        return false;
    }else{
        if(t->child==NULL){
            if(t->key%2!=0){
                return true;
            }else{
                return false;
            }
        }
        bool odd=true;
        kTree c=t->child;
        while(c){
            odd=odd&&isOdd(c);
            c=c->sibling;
        }
        return odd;
    }
}


//post: ritorna il numero di nodi non foglie dell'albero
int numNodeTree(kTree t) {
    if (t == NULL) {
        return 0;
    } else if (t->child == NULL) {
        return 0;
    } else {
        int numNode = 0;
        kTree c = t->child;
        while (c != NULL) {
            numNode += numNodeTree(c);
            c = c->sibling;
        }
        return 1 + numNode;
    }
}



int main() {

// test 1
    kTree t =
       root(1, 
            consTree(21, 
                leaf(1,NULL), 
                leaf(3, 
                    root(33, 
                        leaf(10, 
                            leaf(5, NULL)
                        )
                    )
                )
            )
       );

    

/* t in forma indentata:
12
	22
		1
	2
	32
		3
		4
*/

    printf("Albero dato:\n");
    printTree(t, 0);
    //printf("%d\n",height(t));
    //printf("%d\n",degree(t));
    printf("%d\n",maxSumBranch(t));
    //printf("%d\n",isOdd(t));
    //printSumLiv(t);
    //printf("%d\n",numNodeTree(t));
    //printf("%d\n",sumLeaf(t));
    //printf("%d\n",fatherChildSum(t));
    //printf("%d\n",numNodiProfondi(t,3));
}
