/*****************************************
*  Struttura dati per le liste semplici
*  di interi
******************************************/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
#define SEPARATOR "#<ab@17943918#@>#"

struct listEl {
    int            info;
    struct listEl* next;
};

typedef struct listEl* list;

list Cons(int x, list xs) {
    list newlist = malloc(sizeof(struct listEl));
    newlist->info = x;
    newlist->next = xs;
    return newlist;
}

void printlist (list l) {
    while (l != NULL) {
        printf("%d ", l->info);
        l = l->next;
    }
    printf("\n");
}

// post: concatena distruttivamente le liste as e bs
list concat(list as, list bs) {
    if (as == NULL)
        return bs;
    else {
        as->next = concat(as->next, bs);
        return as;
    }
}

// post: genera una nuova lista, copia di xs
list copyList(list xs) {
    if (xs == NULL)
        return NULL;
    else
        return Cons(xs->info, copyList(xs->next));
}


/*
list deleteAll(int n, list as) {
    if(as==NULL){
        return NULL;
    } else if(as->info==n){
        list tmp = as->next;
        free(as);
        return deleteAll(n,tmp);
    } else {
        as->next=deleteAll(n,as->next);
        return as;
    }
}



int members(int elem, list l){
    int count=0;
    while(l!=NULL){
        if(elem==l->info){
            count++;
        }
        l=l->next;
    }
    return count;
}

//restituisce il numero degli elementi che non hanno duplicati
int multiples(list l){
    if(l==NULL){
        return 0;
    } else {
        if(members(l->info,l->next)==1){
            //printf("%d->%d\n",l->info,members(l->info,l));
            return 1+multiples(l->next);
        }
        return multiples(l->next);
    }
}

int rankElemen(int elem,list l){
    int rank=0;
    while(l->info!=elem){
        l=l->next;
    }
    l=l->next;
    while(l!=NULL){
        rank+=l->info;
        l=l->next;
    }
    return rank;
}


list rank(list l){
    list head=l;
    while(l!=NULL){
        l->info=rankElemen(l->info,l);
        //printf("%d\n",l->info);
        l=l->next;
    }
    l=head;
    return l;
}

list reverse(list l){
    list prev=NULL;
    list current=l;
    list next=l;
    while(current){
        next=next->next;        [1,2,3]
        current->next=prev;
        prev=current;
        current=next;
    }
    return prev;
}

// pre:  xs e ys liste di interi ordinate in senso crescente
// post: restituiesce una nuova lista ordinata D(xs, ys) 
//       degli interi in una ma non nell'altra delle due liste,
//       ordinata in senso crescente
list symmDiff(list xs, list ys) {
    if(xs==NULL){
        return ys;
    }
    if(ys==NULL){
        return xs;
    }
    if(xs->info==ys->info){
        return symmDiff(xs->next,ys->next);
    } else {
        if(xs->info<ys->info){
            return Cons(xs->info,symmDiff(xs->next,ys));
        } else {
            return Cons(ys->info,symmDiff(xs,ys->next));
        }
    }
}

// post: concatena distruttivamente le liste as e bs
list concat(list as, list bs) {
    if (as == NULL)
        return bs;
    else {
        as->next = concat(as->next, bs);
        return as;
    }
}

list profSymmDiff(list xs, list ys) {
    if (xs == NULL)
        return ys;
    else if (ys == NULL)
        return xs;
    else if (xs->info == ys->info)
        return symmDiff(xs->next, ys->next);
    else if (xs->info < ys->info)
        return Cons(xs->info, symmDiff(xs->next, ys));
    else return Cons(ys->info, symmDiff(xs, ys->next));
}

list insert(list as, list bs, int n){
    if(as==NULL||bs==NULL){
        return NULL;
    }
    if(n==0){
        return concat(as,bs);
    } else {
        bs->next=insert(as,bs->next,n-1);
        return bs;
    }
}

int aux_corank(list l, int n){
    if(l==NULL){
        return 0;
    }
    int count=0;
    while(l->info!=n){
        count+=aux_corank(l->next,n);
    }
    return 0;
}

//oddEven(L) riordina distruttivamente la lista in modo che i dispari precedano i pari

//dividi la lista in due e restituisci il puntatore alla seconda parte della lista
list split(list l){
   list slow=l;
   list fast=l->next;
   while(fast){
    fast=fast->next;
    if(fast){
        fast=fast->next;
        slow=slow->next;
    }
   }
   l=slow->next;
   return l;
}

list list_merge(list l1, list l2){
    list tmp;
    while (l1 != NULL && l2 != NULL) {
        tmp->next = l1;
        l1 = l1->next;
        tmp = tmp->next;

        tmp->next = l2;
        l2 = l2->next;
        tmp = tmp->next;
    }
    printlist(tmp);
    return tmp;
}
*/


//1.deleteAll:
// post: ritorna la lista as da cui sono state rimosse 
//       tutte le occorrenze di n
list deleteAll(int n, list as) {
    if(as==NULL){
        return NULL;
    }
    if(as->info==n){
        as=deleteAll(n,as->next);
        return as;
    } else {
        as->next=deleteAll(n,as->next);
        return as;
    }
}

//2.Mergesort (merge)
// pre:  l, m ordinate in senso non decrescente
// post: fonde distruttivamente l m in una lista ordinata
list merge(list l, list m) {
    return NULL;
}

//3.symmDiff:
// pre:  xs e ys sono ordinate in senso crescente
// post: restituesce una nuova lista degli el. 
//       in una ma non nell'altra delle due liste,
//       ordinata in senso crescente
//(usare copyList())
list symmDiff(list xs, list ys){
    if(xs==NULL){
        return copyList(ys);
    }
    if(ys==NULL){
        return copyList(xs);
    }
    if(xs->info==ys->info){
        return symmDiff(xs->next,ys->next);
    }
    if(xs->info<=ys->info){
        return Cons(xs->info,symmDiff(xs->next,ys));
    } else {
        return Cons(ys->info,symmDiff(xs,ys->next));
    }
}

//4.insert:
// pre:  0 <= n < lunghezza di bs
// post: inserisce distruttivamente as in bs dopo n elementi, seguita
//       dai rimanenti el. di bs
list insert(list as, list bs, int n){
    if(n==0){
        return concat(as,bs);
    }else{
        bs->next=insert(as,bs->next,n-1);
        return bs;
    }
}

//4.pari/dispari:
list oddEven(list l){
    if(l==NULL){
        return NULL;
    }
    list oddHead=NULL;
    list oddTail=NULL;
    list evenHead=NULL;
    list evenTail=NULL;

    list current=l;
    while(current){
        list nextNode=current->next;
        if(current->info%2!=0){         //elemento pari
            if(oddHead==NULL){          //lista dei dispari vuota
                oddHead=current;
                oddTail=current;
                oddTail->next=NULL;
            } else {                    //lista dei dispari non vuota
                oddTail->next=current;
                oddTail=current;
                oddTail->next=NULL;
            }
        }else{                          //elemento dispari
            if(evenHead==NULL){          //lista dei dispari vuota
                evenHead=current;
                evenTail=current;
                evenTail->next=NULL;
            } else {                    //lista dei dispari non vuota
                evenTail->next=current;
                evenTail=current;
                evenTail->next=NULL;
            }
        }
        current=nextNode;
    }
    //printf("pari: ");
    //printlist(oddHead);
    //printf("dispari: ");
    //printlist(evenHead);
    if(oddHead==NULL){
        return evenHead;
    } else if(evenHead==NULL){
        return oddHead;
    } else{
        oddTail->next=evenHead;
        //oddTail->next=NULL;
        return oddHead;
    }
}

int corank(list l){
    return 0;
}
/*
list fast_reverse(list l) {
    list reversed=NULL;
    list current=l;
    while(current){
        reversed=Cons(current->info,reversed);
        current=current->next;
    }
    return reversed;
}
*/



list split(list l){
    if(l==NULL){
        return NULL;
    }
    list slow=l;
    list fast=l->next;
    while(fast){
        fast=fast->next;
        if(fast){
            fast=fast->next;
            slow=slow->next;
        }
    }
    l=slow->next;
    return l;
}

list merge(list l, list m) {
    if(l==NULL){
        return m;
    }
    if(m==NULL){
        return l;
    }
    if(l->info<=m->info){
        l->next=merge(l->next,m); 
        return l;
    }else{
        m->next=merge(l,m->next); 
        return m;
    }
}

list deleteAll(int n, list as) {
    if(as==NULL){
        return NULL;
    }
    if(as->info==n){
        as=deleteAll(n,as->next);
        return as;
    }else{
        as->next=deleteAll(n,as->next);
        return as;
    }
}

list symmDiff(list xs, list ys) {
    if(xs==NULL){
        return copyList(ys);
    } 
    if(ys==NULL){
        return copyList(xs);
    }
    if(xs->info==ys->info){
        return symmDiff(xs->next,ys->next);
    }
    if(xs->info<=ys->info){
        return Cons(xs->info,symmDiff(xs->next,ys));
    }else{
        return Cons(ys->info,symmDiff(xs,ys->next));
    }
}

/*
list oddEven(list l){
    if(l==NULL||l->next==NULL){
        return NULL;
    }
    list oddHead=NULL;
    list oddTail=NULL;
    list evenHead=NULL;
    list evenTail=NULL;

    list current=l;
    while(current){
        list nextNode=current->next;
        if(current->info%2!=0){         //elemento dispari, aggiungo alla lista dei dispari
            if(oddHead==NULL){          //lista dei dispari vuota
                oddHead=current;
                oddTail=current;
                oddTail->next=NULL;
            } else {                    //lista dei dispari non vuota
                oddTail->next=current;
                oddTail=current;
                oddTail->next=NULL;
            }
        } else {                        //elemento pari, aggiungo alla lista dei pari
            if(evenHead==NULL){         //lista dei pari vuota
                evenHead=current;
                evenTail=current;
                evenTail->next=NULL;
            } else {                    //lista dei pari non vuota
                evenTail->next=current;
                evenTail=current;
                evenTail->next=NULL;
            }
        }
        current=nextNode;
    }
    printf("\n");
    printf("pari: ");
    printlist(evenHead);
    printf("dispari: ");
    printlist(oddHead);
    if(oddHead==NULL){
        return evenHead;
    } else if(evenHead==NULL){
        return oddHead;
    } 
    oddTail->next=evenHead;
    return oddHead;
}
*/
list oddEven(list l){
    if(l==NULL){
        return NULL;
    }else{
        list oddHead=NULL;
        list oddTail=NULL;
        list evenHead=NULL;
        list evenTail=NULL;

        list current=l;
        while(current){
            list nextNode=current->next;
            if(current->info%2!=0){
                if(oddHead==NULL){
                    oddHead=current;
                    oddTail=current;
                    oddTail->next=NULL;
                }else{
                    oddTail->next=current;
                    oddTail=current;
                    oddTail->next=NULL;
                }
            }else{
                if(evenHead==NULL){
                    evenHead=current;
                    evenTail=current;
                    evenTail->next=NULL;
                }else{
                    evenTail->next=current;
                    evenTail=current;
                    evenTail->next=NULL;
                }
            }
            current=nextNode;
        }
        printf("pari: ");
        printlist(evenHead);
        printf("dispari: ");
        printlist(evenHead);
        if(oddHead==NULL){
            return evenHead;
        }else if(evenHead==NULL){
            return oddHead;
        }else{
            oddTail=evenHead;
            oddTail->next=NULL;
            return oddHead;
        }
    }
}


int main() {
    list bs1 = Cons(2, Cons(3, Cons(4, Cons(10, Cons(7,NULL)))));
    //list cs1 = Cons(4, Cons(5, Cons(6,NULL)));
    //list as1 = Cons(10, NULL);
    //printlist(bs1);
    //printlist(as1);
    /*
    printf("as1 = ");
    printlist(as1);
    printf("bs1 = ");
    printlist(bs1);
    list cs1 = insert(as1, bs1, 2);
    printf("Inserimento di as1 in bs1 in posizione 2: ");
    printlist(cs1);
    */
    //printf("%d\n",ausRank(bs1,1));
    //list bs2=reverse(bs1);
    //printlist(bs2);

    //list bs3=merge(bs1,cs1);
    //list bs3=insert(bs1,cs1,2);
    //list bs3=deleteAll(1,bs1);
    //list bs3=symmDiff(bs1,cs1);
    //list bs3=oddEven(bs1);
    //corank(cs1);
    list cs1 = oddEven(bs1);
    printlist(cs1);
    return 0;
}

