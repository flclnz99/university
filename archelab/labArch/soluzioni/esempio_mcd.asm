
.globl _start

.data

.text

_start:  
  li a0, 15    # a0 contiene a e poi risultato dopo mcd
  li a1, 12    # a1 contiene b
  
  jal mcd
  mv t0, a0

print_exit_0:
  add a0, zero, t0
  li a7, 1
  ecall
  li a7, 10
  ecall
  

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











