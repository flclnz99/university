# procedure to return the MCD
.globl _start

.text
_start:
        li    a0, 24
        li    a1, 30
        jal	  ra, mcd
        mv    t0, a0
print:
        addi  a0, t0, 0    	 # print the result
        li    a7, 1
        ecall
exit:
        li    a7, 10         # call number 10 = exit
        ecall

###################################
# Procedure MCD(a,b)
# a0 -> a
# a1 -> b
# return MCD su a0
###################################
mcd:
                                  # as we do not touch SP e FP we skip this
          # addi    sp, sp, -8    # open space for a double word 
          # sd      fp, 0(sp)     # save the previous frame pointer          
mcd_while:
          beq     a0, a1, mcd_end
          bge     a1, a0, mcd_else
          sub     a0, a0, a1
          j       mcd_while
mcd_else:
          sub     a1, a1, a0
          j       mcd_while
mcd_end:
          # ld      fp, 0(sp)     # restore the frame pointer
          # addi    sp, sp, 8     # restore the stack pointer
          ret
###################################
