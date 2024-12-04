.globl _start

.text

#algoritmo di Euclide per MCD
#int MCD(int a, int b) {
#  while (a != b)
#    if (a > b)
#	a = a - b; 
#    else
#      b = b - a;
#  return a;
#}
#void main() {
#    int a = 24;
#    int b = 30;
#    int result;
#    result = MCD(a,b);
#    printf("%d\n", result);
#}

_start:
	li a0, 24 # a 
	li a1, 30 # b
	jal ra, MCD
	mv t0, a0
print:
	addi a0, t0, 0
	li a7, 10
	ecall
MCD:
	beq a0, a1, mcd_end
	bgt a1, a0, mcd_else
	sub a0, a0, a1
	j MCD
	
	mcd_end:
		jr ra	
		ret
	mcd_else:
		sub a1, a1, a0
		j MCD

	
	
	
