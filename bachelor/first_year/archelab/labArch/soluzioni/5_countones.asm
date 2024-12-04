# Convert the following C function into RISC-V
# Counts the number of 1s in the binary present in t0
#
# N  = 37 # number in t0
# M  = 1  # mask
# R  = 0  # result
# i  = 64 # counter
# do {
#    R = R + N&M  # bitwise and and sum
#    N = N >> 1   # move the number right
#    i = i - 1
# } while (i > 0)  # stop when all bits are checked
#

.globl _start

.text
_start:

      li    t0, 37            # N
      li    t1, 1             # M
      li    t2, 0             # R
      li    t3, 64            # i
loop1:
      and   t5, t0, t1        # N & M
      add   t2, t2, t5        # R = R + N & M
      srli  t0, t0, 1         # N = N >> 1
      addi  t3, t3, -1        # i = i - 1

      beq   t3, zero, end1    # if (i==0) end
      j     loop1	            # else continue
end1:

print:
        addi  a0, t2, 0    	 # print the result
        li    a7, 1
        ecall
exit:
        li    a7, 10         # call number 10 = exit
        ecall
