.globl _start

.text
_start:
	li x5, 0x8000000000000000
	li x6, 0xD000000000000000
	
	#add x30, x5, x6
	
	sub x30, x5, x6
exit:
	addi x17, x0, 10
	ecall 
	