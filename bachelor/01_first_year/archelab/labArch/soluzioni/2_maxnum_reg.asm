# return the max of three numbers present in registers
.globl _start

# max of three numbers present in registers
.text
_start:
    addi  t0, zero, 41   # load the numbers in the registers
    addi  t1, zero, 47
    addi  t2, zero, 45

    add   t3, zero, t0   # this register will hold the max among the three
    bge   t3, t1, end1
    add   t3, zero, t1   # if t1 > t0, hold t1
end1:
    bge   t3, t2, end2
    add   t3, zero, t2   # if t2 > max(t1, t0), hold t2
end2:
    # t3 has the max

print:
    addi  a0, t3, 0    	 # print the result
    li    a7, 1
    ecall
exit:
    li    a7, 10         # call number 10 = exit
    ecall
