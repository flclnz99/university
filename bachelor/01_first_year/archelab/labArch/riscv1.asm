.globl	_start
.data
	src: .string "first."
	dst: .string "first"
	char: .string "f"
	
.text
_start:
	la a0, src
	la a1, char
	jal ra, strchr
	j print_exit
	
strchr:
	addi sp, sp, -24
	sd ra, 0(sp)
	sd a0, 8(sp)
	sd s1, 16(sp)
	
	jal ra, strlen
	addi s1, a0, 0
	
	ld a0, 8(sp)
	lb t0, 0(a0)
	lb t1, 0(a1)
	strchrloop:
		beqz s1, exitstrchr
		bne t0, t1, endstrchr
		addi a0, a0, 1
		addi s1, s1, -1
		j strchrloop
	endstrchr:
		li a0, 0
	exitstrchr:
		ld ra, 0(sp)
		ld s1, 16(sp)
		addi sp, sp, 24
		ret
strcmp:
	addi sp, sp, -32
	sd ra, 0(sp)
	sd s1, 8(sp)
	sd a0, 16(sp)
	sd a1, 24(sp)
	
	jal ra, strlen
	addi s1, a0, 0	#s1=26 source length
	
	addi a0, a1, 0
	jal ra, strlen
	addi t2, a0, 0	#t2=13 destination length
	
	bne t2, s1, scend
	ld a0, 16(sp)
	ld a1, 24(sp)
	scloop:
		lbu t3, 0(a0)
		lbu t4, 0(a1) 
		beqz t1, endloop
		bne t3, t4, scend
		addi a0, a0, 1
		addi a1, a1, 1
		addi t1, t1, -1
	endloop:
		li a0, 0
		j fine
	scend:
		li a0, 1
		j fine
	fine:
		ld ra, 0(sp)
		ld s1, 8(sp)
		addi sp, sp, 32
		ret
	
strcpy:
	addi sp, sp, -32
	sd ra, 0(sp)
	sd s1, 8(sp)
	sd a0, 16(sp)
	sd a1, 24(sp)
	
	jal ra, strlen
	addi s1, a0, 0	#s1=26 source length
	
	addi a0, a1, 0
	jal ra, strlen
	addi t2, a0, 0	#t2=13 destination length
	
	ld a0, 16(sp)	#restore a0 with source address
	ld a1, 24(sp) 	#restore a1 with destination address
	li t3, 0	#t3=i
	
	loop0:
		beq t3, s1, loop1
		lb t4, 0(a0)
		sb t4, 0(a1)
		addi t3, t3, 1
		addi a0, a0, 1
		addi a1, a1, 1
	loop1:
		
	
	ld ra, 0(sp)
	ld s1, 8(sp)
	addi sp, sp, 32
	
	ret
		

strlen:
	li t0, 0
	loop:
		lb t1, 0(a0)
		beqz t1, end
		addi t0, t0, 1
		addi a0, a0, 1
		j loop
	end:
		addi a0, t0, 0
		ret

print_exit:
	li a7, 1
	ecall
	li a7, 10
	ecall