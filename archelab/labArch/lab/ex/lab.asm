.text
_start:

#esercizio 1
#x=x-y
# if !(x < 0) jump
#	x=0
# else
#	y=y-1

#	li t1, 0 #x
#	li t2, 5 #y
#	sub t1, t1, t2
#	blt t1, x0, else
#	li t1, 0
#else:
#	addi t2, t2, -1


#esercizio 2
#x = (x - 2) + y
#if (x < y)
#	x=x+1 
#else
#	y=y+1

#	addi t1, t1, -2
#	add t1, t1, t2
	
#	bge t1, t2, else
#	addi t1, t1, 1
#	j end
#else:
#	addi t2, t2, 1
#end:

#esercizio 3 (max tra 3 numeri)
#	li t0, 0
#	li t1, 0
#	li t2, 0	
#	bgt t0, t1, _t0		#t0>t1
#	bgt t1, t0, _t1 	#t1>t0
#	bgt t2, t0, bt2	
#_t0:
#	bgt t0, t2, bt0		#t0>t1 && t0>t2
#	j bt2			#t0>t1 && t0<t2
#_t1:
#	bgt t1, t2, bt1
#	j bt2
#bt0:
#	add t3, t0, x0
#	j end
#bt1:
#	add t3, t1, x0
#	j end
#bt2:
#	add t3, t2, x0	
#	j end
#end:








