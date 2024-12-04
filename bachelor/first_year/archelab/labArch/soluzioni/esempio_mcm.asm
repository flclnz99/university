.globl _start

.data

.text

_start:  
  li a0, 15    # a0 contiene a e poi risultato dopo mcm
  li a1, 12    # a1 contiene b
  
  jal mcm

print_exit_0:
  li a7, 1
  ecall
  li a7, 10
  ecall
  

# mcm(a,b) = (a*b) / MCD(a,b)
mcm:
  addi sp, sp, -4         # estendo stack
  sw ra, 0(sp)            # salvo sp
  
  mul t1, a0, a1
  
  jal mcd
  # a0 contiene mcd(a,b)
  div a0, t1, a0
  
  lw ra, 0(sp) # restore ra
  addi sp, sp, 8 # riposiziono sp

  jr ra


# int MCD(int a, int b) {
#   while (a != b)
#     if (a > b)
#       a = a - b;
#     else
#       b = b - a;
#   return a;
# }
  
# a0 -> a
# a1 -> b
# return MCD su a0
mcd:
  mcd_while:
    beq a0, a1, mcd_end
    
    blt a1, a0, else
    sub a1, a1, a0
    j mcd_while
    else: 
      sub a0, a0, a1
      j mcd_while

  mcd_end:
    jr ra











