/***********************************
*  Struttura dati per alberi binari
*  con puntatore al padre
***********************************/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
#define SEPARATOR "#<ab@17943918#@>#"

struct BtreeNd {
    int             key;
    struct BtreeNd* parent;
    struct BtreeNd* left;
    struct BtreeNd* right;
};

typedef struct BtreeNd* btree;

// pre: k etichetta, p punta al padre (NULL se si alloca la radice)
//      l punta al figlio sinistro, r al figlio destro (NULL se assenti)
// post: alloca un nuovo nodo con etichetta k, padre p, 
//       figlio sin. l e figlio des. r
btree ConsTree(int k, btree p, btree l, btree r) {
    btree rootnode = malloc(sizeof(struct BtreeNd));
    rootnode->key = k;
    rootnode->parent = p;
    rootnode->left = l;
    rootnode->right = r;
    return rootnode;
}

// post: stampa indentata dell'albero bt con margine
//       iniziale di n tab
void printtree(btree bt, int n) {
    if (bt != NULL) {
        for (int i = 0; i < n; i++) 
            printf("   ");
        printf("%d\n", bt->key);
        printtree(bt->left, n + 1);
        printtree(bt->right, n + 1);
    }
}

int successor(btree tree){
    if(tree->right!=NULL){
        btree tmp = tree->right;
        while(tmp->left!=NULL){
            tmp=tmp->left;
        }
        return tmp->key;
    }
    return 0;
}

btree findAncestor(btree tree, int n){
    if(tree==NULL||tree->key==n){
        return NULL;
    }
    if(tree->left!=NULL&&tree->left->key==n){
        return tree;
    }
    if(tree->right!=NULL&&tree->right->key==n){
        return tree;
    }
    btree leftAncestor = findAncestor(tree->left, n);
    if (leftAncestor != NULL) {
        return leftAncestor;  // Antenato trovato nel sottoalbero sinistro
    }

    btree rightAncestor = findAncestor(tree->right, n);
    if (rightAncestor != NULL) {
        return rightAncestor;  // Antenato trovato nel sottoalbero destro
    }

    return NULL;
}

int main (){
    btree root = ConsTree(4, NULL, NULL, NULL);

    btree left = ConsTree(2, root, NULL, NULL);
    root->left = left;

    btree right = ConsTree(6, root, NULL, NULL);
    root->right = right;

    btree left_left = ConsTree(1, left, NULL, NULL);
    left->left = left_left;

    btree left_right = ConsTree(3, left, NULL, NULL);
    left->right = left_right;

    btree right_left = ConsTree(5, right, NULL, NULL);
    right->left = right_left;

    btree right_right = ConsTree(7, right, NULL, NULL);
    right->right = right_right;

    //printtree(root,0);
    btree ancestor=findAncestor(root,3);
    printtree(ancestor,0);
    
    return 0;
}