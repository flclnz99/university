# Convert the following C function into RISC-V
# It calculates the Nth Fibonacci number
#
#   int N = 8;
#   int R = 1;
#   int A = 0; int B = 1;
#   while (N > 0) {
#       R = A + B;
#       A = B;
#       B = R;
#       N = N - 1;
#   }
#

.globl _start

.text
_start:
        li t0, 8
        li t1, 1
        li t2, 0
        li t3, 1
loop1:
        beq t0, zero, end1

        add  t1, t2, t3
        add  t2, t3, zero
        add  t3, t1, zero
        addi t0, t0, -1
        j loop1
end1:


print:
        addi  a0, t1, 0    	 # print the result
        li    a7, 1
        ecall
exit:
        li    a7, 10         # call number 10 = exit
        ecall
