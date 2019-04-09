	.file	"compute_root_vec_x.c"
	.text
	.globl	pipeline
	.type	pipeline, @function
pipeline:
.LFB3667:
	.cfi_startproc
	leaq	8(%rsp), %r10
	.cfi_def_cfa 10, 0
	andq	$-32, %rsp
	pushq	-8(%r10)
	pushq	%rbp
	.cfi_escape 0x10,0x6,0x2,0x76,0
	movq	%rsp, %rbp
	pushq	%r10
	.cfi_escape 0xf,0x3,0x76,0x78,0x6
	subq	$5800, %rsp
	movq	%rdi, -5768(%rbp)
	movq	%rsi, -5776(%rbp)
	movl	%edx, -5780(%rbp)
	movl	%ecx, -5784(%rbp)
	movl	-5780(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -5672(%rbp)
	movl	-5672(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -5668(%rbp)
	movl	-5668(%rbp), %eax
	imull	-5784(%rbp), %eax
	movl	%eax, -5664(%rbp)
	movl	-5664(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5660(%rbp)
	movl	-5660(%rbp), %eax
	cltq
	addq	%rax, %rax
	movq	%rax, %rdi
	call	malloc@PLT
	movq	%rax, -5408(%rbp)
	movl	-5668(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5656(%rbp)
	movl	-5656(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -5652(%rbp)
	movl	-5652(%rbp), %eax
	leal	15(%rax), %edx
	testl	%eax, %eax
	cmovs	%edx, %eax
	sarl	$4, %eax
	movl	%eax, -5648(%rbp)
	movl	-5672(%rbp), %eax
	subl	$16, %eax
	movl	%eax, -5644(%rbp)
	movl	-5644(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -5640(%rbp)
	movw	$21846, -5728(%rbp)
	movw	$21846, -5726(%rbp)
	movw	$21846, -5724(%rbp)
	movw	$21846, -5722(%rbp)
	movw	$21846, -5720(%rbp)
	movw	$21846, -5718(%rbp)
	movw	$21846, -5716(%rbp)
	movw	$21846, -5714(%rbp)
	movw	$21846, -5712(%rbp)
	movw	$21846, -5710(%rbp)
	movw	$21846, -5708(%rbp)
	movw	$21846, -5706(%rbp)
	movw	$21846, -5704(%rbp)
	movw	$21846, -5702(%rbp)
	movw	$21846, -5700(%rbp)
	movw	$21846, -5698(%rbp)
	movzwl	-5698(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5700(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm1
	movzwl	-5702(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5704(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm4
	movzwl	-5706(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5708(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm2
	movzwl	-5710(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5712(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm3
	vpunpckldq	%xmm4, %xmm1, %xmm0
	vmovdqa	%xmm0, %xmm1
	vpunpckldq	%xmm3, %xmm2, %xmm0
	vpunpcklqdq	%xmm0, %xmm1, %xmm0
	vmovdqa	%xmm0, %xmm1
	movzwl	-5714(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5716(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm2
	movzwl	-5718(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5720(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm5
	movzwl	-5722(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5724(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm3
	movzwl	-5726(%rbp), %eax
	movl	%eax, -5792(%rbp)
	vmovd	-5792(%rbp), %xmm0
	movzwl	-5728(%rbp), %eax
	vpinsrw	$1, %eax, %xmm0, %xmm0
	vmovdqa	%xmm0, %xmm4
	vpunpckldq	%xmm5, %xmm2, %xmm0
	vmovdqa	%xmm0, %xmm2
	vpunpckldq	%xmm4, %xmm3, %xmm0
	vpunpcklqdq	%xmm0, %xmm2, %xmm0
	vinserti128	$0x1, %xmm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4784(%rbp)
	movl	$0, -5696(%rbp)
	jmp	.L3
.L47:
	movl	-5668(%rbp), %eax
	imull	-5696(%rbp), %eax
	movl	%eax, -5544(%rbp)
	movl	-5780(%rbp), %eax
	imull	-5696(%rbp), %eax
	movl	%eax, -5540(%rbp)
	movl	$0, -5692(%rbp)
	jmp	.L4
.L46:
	movl	-5692(%rbp), %eax
	sall	$4, %eax
	movl	%eax, -5536(%rbp)
	movl	-5536(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -5532(%rbp)
	movl	-5532(%rbp), %eax
	cmpl	-5644(%rbp), %eax
	setg	%al
	movb	%al, -5747(%rbp)
	cmpb	$0, -5747(%rbp)
	je	.L5
	movl	-5640(%rbp), %eax
	movl	%eax, -5688(%rbp)
	jmp	.L6
.L5:
	movl	-5536(%rbp), %eax
	movl	%eax, -5688(%rbp)
.L6:
	movl	-5688(%rbp), %edx
	movl	-5544(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -5528(%rbp)
	movl	-5528(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5524(%rbp)
	movl	-5524(%rbp), %eax
	addl	$32, %eax
	movl	%eax, -5520(%rbp)
	movl	-5688(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -5516(%rbp)
	movl	-5516(%rbp), %edx
	movl	-5540(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -5512(%rbp)
	movl	-5512(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5508(%rbp)
	movl	-5508(%rbp), %eax
	addl	$32, %eax
	movl	%eax, -5504(%rbp)
	movl	-5504(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5304(%rbp)
	movq	-5304(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5120(%rbp)
	vmovdqa	-5120(%rbp), %xmm0
	vmovaps	%xmm0, -4848(%rbp)
	vmovdqa	-4848(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -3568(%rbp)
	movl	-5516(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -5500(%rbp)
	movl	-5500(%rbp), %edx
	movl	-5540(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -5496(%rbp)
	movl	-5496(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5492(%rbp)
	movl	-5492(%rbp), %eax
	addl	$32, %eax
	movl	%eax, -5488(%rbp)
	movl	-5488(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5312(%rbp)
	movq	-5312(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5104(%rbp)
	vmovdqa	-5104(%rbp), %xmm0
	vmovaps	%xmm0, -4864(%rbp)
	vmovdqa	-4864(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -3536(%rbp)
	vmovdqa	-3568(%rbp), %ymm0
	vmovdqa	%ymm0, -1424(%rbp)
	vmovdqa	-3536(%rbp), %ymm0
	vmovdqa	%ymm0, -1392(%rbp)
	vmovdqa	-1424(%rbp), %ymm1
	vmovdqa	-1392(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3504(%rbp)
	movl	-5688(%rbp), %edx
	movl	-5540(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -5484(%rbp)
	movl	-5484(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5480(%rbp)
	movl	-5480(%rbp), %eax
	addl	$32, %eax
	movl	%eax, -5476(%rbp)
	movl	-5476(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5320(%rbp)
	movq	-5320(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5088(%rbp)
	vmovdqa	-5088(%rbp), %xmm0
	vmovaps	%xmm0, -4880(%rbp)
	vmovdqa	-4880(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -3472(%rbp)
	vmovdqa	-3504(%rbp), %ymm0
	vmovdqa	%ymm0, -1488(%rbp)
	vmovdqa	-3472(%rbp), %ymm0
	vmovdqa	%ymm0, -1456(%rbp)
	vmovdqa	-1488(%rbp), %ymm1
	vmovdqa	-1456(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3440(%rbp)
	vmovdqa	-3440(%rbp), %ymm0
	vmovdqa	%ymm0, -1552(%rbp)
	vmovdqa	-4784(%rbp), %ymm0
	vmovdqa	%ymm0, -1520(%rbp)
	vmovdqa	-1520(%rbp), %ymm0
	vmovdqa	-1552(%rbp), %ymm1
	vpmulhw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3408(%rbp)
	vmovdqa	-3440(%rbp), %ymm0
	vmovdqa	%ymm0, -1616(%rbp)
	vmovdqa	-3408(%rbp), %ymm0
	vmovdqa	%ymm0, -1584(%rbp)
	vmovdqa	-1616(%rbp), %ymm0
	vmovdqa	-1584(%rbp), %ymm1
	vpsubw	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -3376(%rbp)
	vmovdqa	-3376(%rbp), %ymm0
	vmovdqa	%ymm0, -1648(%rbp)
	movl	$1, -5436(%rbp)
	vmovdqa	-1648(%rbp), %ymm0
	movl	-5436(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm6
	vpsrlw	%xmm6, %ymm0, %ymm0
	vmovdqa	%ymm0, -3344(%rbp)
	vmovdqa	-3408(%rbp), %ymm0
	vmovdqa	%ymm0, -1712(%rbp)
	vmovdqa	-3344(%rbp), %ymm0
	vmovdqa	%ymm0, -1680(%rbp)
	vmovdqa	-1712(%rbp), %ymm1
	vmovdqa	-1680(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3312(%rbp)
	vmovdqa	-3312(%rbp), %ymm0
	vmovdqa	%ymm0, -1744(%rbp)
	movl	$1, -5440(%rbp)
	vmovdqa	-1744(%rbp), %ymm0
	movl	-5440(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm7
	vpsrlw	%xmm7, %ymm0, %ymm0
	vmovdqa	%ymm0, -3280(%rbp)
	movl	-5524(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5472(%rbp)
	movl	-5508(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5468(%rbp)
	movl	-5468(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5328(%rbp)
	movq	-5328(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5072(%rbp)
	vmovdqa	-5072(%rbp), %xmm0
	vmovaps	%xmm0, -4896(%rbp)
	vmovdqa	-4896(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -3248(%rbp)
	movl	-5492(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5464(%rbp)
	movl	-5464(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5336(%rbp)
	movq	-5336(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5056(%rbp)
	vmovdqa	-5056(%rbp), %xmm0
	vmovaps	%xmm0, -4912(%rbp)
	vmovdqa	-4912(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -3216(%rbp)
	vmovdqa	-3248(%rbp), %ymm0
	vmovdqa	%ymm0, -1808(%rbp)
	vmovdqa	-3216(%rbp), %ymm0
	vmovdqa	%ymm0, -1776(%rbp)
	vmovdqa	-1808(%rbp), %ymm1
	vmovdqa	-1776(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3184(%rbp)
	movl	-5480(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5460(%rbp)
	movl	-5460(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5344(%rbp)
	movq	-5344(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5040(%rbp)
	vmovdqa	-5040(%rbp), %xmm0
	vmovaps	%xmm0, -4928(%rbp)
	vmovdqa	-4928(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -3152(%rbp)
	vmovdqa	-3184(%rbp), %ymm0
	vmovdqa	%ymm0, -1872(%rbp)
	vmovdqa	-3152(%rbp), %ymm0
	vmovdqa	%ymm0, -1840(%rbp)
	vmovdqa	-1872(%rbp), %ymm1
	vmovdqa	-1840(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3120(%rbp)
	vmovdqa	-3120(%rbp), %ymm0
	vmovdqa	%ymm0, -1936(%rbp)
	vmovdqa	-4784(%rbp), %ymm0
	vmovdqa	%ymm0, -1904(%rbp)
	vmovdqa	-1904(%rbp), %ymm0
	vmovdqa	-1936(%rbp), %ymm1
	vpmulhw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3088(%rbp)
	vmovdqa	-3120(%rbp), %ymm0
	vmovdqa	%ymm0, -2000(%rbp)
	vmovdqa	-3088(%rbp), %ymm0
	vmovdqa	%ymm0, -1968(%rbp)
	vmovdqa	-2000(%rbp), %ymm0
	vmovdqa	-1968(%rbp), %ymm1
	vpsubw	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -3056(%rbp)
	vmovdqa	-3056(%rbp), %ymm0
	vmovdqa	%ymm0, -2032(%rbp)
	movl	$1, -5444(%rbp)
	vmovdqa	-2032(%rbp), %ymm0
	movl	-5444(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm5
	vpsrlw	%xmm5, %ymm0, %ymm0
	vmovdqa	%ymm0, -3024(%rbp)
	vmovdqa	-3088(%rbp), %ymm0
	vmovdqa	%ymm0, -2096(%rbp)
	vmovdqa	-3024(%rbp), %ymm0
	vmovdqa	%ymm0, -2064(%rbp)
	vmovdqa	-2096(%rbp), %ymm1
	vmovdqa	-2064(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -2992(%rbp)
	vmovdqa	-2992(%rbp), %ymm0
	vmovdqa	%ymm0, -2128(%rbp)
	movl	$1, -5448(%rbp)
	vmovdqa	-2128(%rbp), %ymm0
	movl	-5448(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm6
	vpsrlw	%xmm6, %ymm0, %ymm0
	vmovdqa	%ymm0, -2960(%rbp)
	movl	-5508(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5352(%rbp)
	movq	-5352(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5024(%rbp)
	vmovdqa	-5024(%rbp), %xmm0
	vmovaps	%xmm0, -4944(%rbp)
	vmovdqa	-4944(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -2928(%rbp)
	movl	-5492(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5360(%rbp)
	movq	-5360(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -5008(%rbp)
	vmovdqa	-5008(%rbp), %xmm0
	vmovaps	%xmm0, -4960(%rbp)
	vmovdqa	-4960(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -2896(%rbp)
	vmovdqa	-2928(%rbp), %ymm0
	vmovdqa	%ymm0, -2192(%rbp)
	vmovdqa	-2896(%rbp), %ymm0
	vmovdqa	%ymm0, -2160(%rbp)
	vmovdqa	-2192(%rbp), %ymm1
	vmovdqa	-2160(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -2864(%rbp)
	movl	-5480(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5768(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5368(%rbp)
	movq	-5368(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vmovaps	%xmm0, -4992(%rbp)
	vmovdqa	-4992(%rbp), %xmm0
	vmovaps	%xmm0, -4976(%rbp)
	vmovdqa	-4976(%rbp), %xmm0
	vpmovzxbw	%xmm0, %ymm0
	vmovdqa	%ymm0, -2832(%rbp)
	vmovdqa	-2864(%rbp), %ymm0
	vmovdqa	%ymm0, -2256(%rbp)
	vmovdqa	-2832(%rbp), %ymm0
	vmovdqa	%ymm0, -2224(%rbp)
	vmovdqa	-2256(%rbp), %ymm1
	vmovdqa	-2224(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -2800(%rbp)
	vmovdqa	-2800(%rbp), %ymm0
	vmovdqa	%ymm0, -2320(%rbp)
	vmovdqa	-4784(%rbp), %ymm0
	vmovdqa	%ymm0, -2288(%rbp)
	vmovdqa	-2288(%rbp), %ymm0
	vmovdqa	-2320(%rbp), %ymm1
	vpmulhw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -2768(%rbp)
	vmovdqa	-2800(%rbp), %ymm0
	vmovdqa	%ymm0, -2384(%rbp)
	vmovdqa	-2768(%rbp), %ymm0
	vmovdqa	%ymm0, -2352(%rbp)
	vmovdqa	-2384(%rbp), %ymm0
	vmovdqa	-2352(%rbp), %ymm1
	vpsubw	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -2736(%rbp)
	vmovdqa	-2736(%rbp), %ymm0
	vmovdqa	%ymm0, -2416(%rbp)
	movl	$1, -5452(%rbp)
	vmovdqa	-2416(%rbp), %ymm0
	movl	-5452(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm7
	vpsrlw	%xmm7, %ymm0, %ymm0
	vmovdqa	%ymm0, -2704(%rbp)
	vmovdqa	-2768(%rbp), %ymm0
	vmovdqa	%ymm0, -2480(%rbp)
	vmovdqa	-2704(%rbp), %ymm0
	vmovdqa	%ymm0, -2448(%rbp)
	vmovdqa	-2480(%rbp), %ymm1
	vmovdqa	-2448(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -2672(%rbp)
	vmovdqa	-2672(%rbp), %ymm0
	vmovdqa	%ymm0, -2512(%rbp)
	movl	$1, -5456(%rbp)
	vmovdqa	-2512(%rbp), %ymm0
	movl	-5456(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm3
	vpsrlw	%xmm3, %ymm0, %ymm0
	vmovdqa	%ymm0, -2640(%rbp)
	movl	-5520(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5376(%rbp)
	vmovdqa	-3280(%rbp), %ymm0
	vmovdqa	%ymm0, -2544(%rbp)
	vmovdqa	-2544(%rbp), %ymm0
	movq	-5376(%rbp), %rax
	vmovups	%xmm0, (%rax)
	vextracti128	$0x1, %ymm0, 16(%rax)
	movl	-5472(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5384(%rbp)
	vmovdqa	-2960(%rbp), %ymm0
	vmovdqa	%ymm0, -2576(%rbp)
	vmovdqa	-2576(%rbp), %ymm0
	movq	-5384(%rbp), %rax
	vmovups	%xmm0, (%rax)
	vextracti128	$0x1, %ymm0, 16(%rax)
	movl	-5524(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5392(%rbp)
	vmovdqa	-2640(%rbp), %ymm0
	vmovdqa	%ymm0, -2608(%rbp)
	vmovdqa	-2608(%rbp), %ymm0
	movq	-5392(%rbp), %rax
	vmovups	%xmm0, (%rax)
	vextracti128	$0x1, %ymm0, 16(%rax)
	addl	$1, -5692(%rbp)
.L4:
	movl	-5692(%rbp), %eax
	cmpl	-5648(%rbp), %eax
	jl	.L46
	addl	$1, -5696(%rbp)
.L3:
	movl	-5696(%rbp), %eax
	cmpl	-5784(%rbp), %eax
	jl	.L47
	movl	-5784(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -5636(%rbp)
	movl	-5636(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -5632(%rbp)
	movl	-5668(%rbp), %eax
	imull	-5632(%rbp), %eax
	movl	%eax, -5628(%rbp)
	movl	-5628(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5624(%rbp)
	movl	-5624(%rbp), %eax
	cltq
	movq	%rax, %rdi
	call	malloc@PLT
	movq	%rax, -5400(%rbp)
	movq	$0, -5296(%rbp)
	movq	$0, -5288(%rbp)
	movq	$0, -5280(%rbp)
	movq	$0, -5272(%rbp)
	movq	-5296(%rbp), %rax
	vmovq	-5288(%rbp), %xmm0
	vpinsrq	$1, %rax, %xmm0, %xmm1
	movq	-5280(%rbp), %rax
	vmovq	-5272(%rbp), %xmm0
	vpinsrq	$1, %rax, %xmm0, %xmm0
	vinserti128	$0x1, %xmm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -4752(%rbp)
	movl	$1, -5684(%rbp)
	jmp	.L49
.L87:
	movl	-5668(%rbp), %eax
	imull	-5684(%rbp), %eax
	movl	%eax, -5620(%rbp)
	movl	-5684(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -5616(%rbp)
	movl	-5668(%rbp), %eax
	imull	-5616(%rbp), %eax
	movl	%eax, -5612(%rbp)
	movl	-5684(%rbp), %eax
	subl	$1, %eax
	movl	%eax, -5608(%rbp)
	movl	-5668(%rbp), %eax
	imull	-5608(%rbp), %eax
	movl	%eax, -5604(%rbp)
	movl	$0, -5680(%rbp)
	jmp	.L50
.L86:
	movl	-5680(%rbp), %eax
	sall	$4, %eax
	movl	%eax, -5600(%rbp)
	movl	-5600(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -5596(%rbp)
	movl	-5596(%rbp), %eax
	cmpl	-5644(%rbp), %eax
	setg	%al
	movb	%al, -5748(%rbp)
	cmpb	$0, -5748(%rbp)
	je	.L51
	movl	-5640(%rbp), %eax
	movl	%eax, -5676(%rbp)
	jmp	.L52
.L51:
	movl	-5600(%rbp), %eax
	movl	%eax, -5676(%rbp)
.L52:
	movl	-5676(%rbp), %edx
	movl	-5620(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -5592(%rbp)
	movl	-5592(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5588(%rbp)
	movl	-5588(%rbp), %eax
	addl	$32, %eax
	movl	%eax, -5584(%rbp)
	movl	-5588(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5580(%rbp)
	movl	-5676(%rbp), %edx
	movl	-5612(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -5576(%rbp)
	movl	-5576(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5572(%rbp)
	movl	-5572(%rbp), %eax
	addl	$32, %eax
	movl	%eax, -5568(%rbp)
	movl	-5572(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5564(%rbp)
	movl	-5676(%rbp), %edx
	movl	-5604(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -5560(%rbp)
	movl	-5560(%rbp), %edx
	movl	%edx, %eax
	addl	%eax, %eax
	addl	%edx, %eax
	movl	%eax, -5556(%rbp)
	movl	-5556(%rbp), %eax
	addl	$32, %eax
	movl	%eax, -5552(%rbp)
	movl	-5556(%rbp), %eax
	addl	$16, %eax
	movl	%eax, -5548(%rbp)
	movl	-5584(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5746(%rbp)
	movl	-5580(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5744(%rbp)
	movl	-5588(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5742(%rbp)
	movl	-5568(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5740(%rbp)
	movl	-5564(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5738(%rbp)
	movl	-5572(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5736(%rbp)
	movl	-5552(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5734(%rbp)
	movl	-5548(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5732(%rbp)
	movl	-5556(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movzwl	(%rax), %eax
	movw	%ax, -5730(%rbp)
	movl	-5584(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5176(%rbp)
	movq	-5176(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -4720(%rbp)
	movl	-5568(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5184(%rbp)
	movq	-5184(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -4688(%rbp)
	movl	-5552(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5192(%rbp)
	movq	-5192(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -4656(%rbp)
	vmovdqa	-4720(%rbp), %ymm0
	vmovdqa	%ymm0, -80(%rbp)
	vmovdqa	-4688(%rbp), %ymm0
	vmovdqa	%ymm0, -48(%rbp)
	vmovdqa	-80(%rbp), %ymm1
	vmovdqa	-48(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4624(%rbp)
	vmovdqa	-4624(%rbp), %ymm0
	vmovdqa	%ymm0, -144(%rbp)
	vmovdqa	-4656(%rbp), %ymm0
	vmovdqa	%ymm0, -112(%rbp)
	vmovdqa	-144(%rbp), %ymm1
	vmovdqa	-112(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4592(%rbp)
	vmovdqa	-4592(%rbp), %ymm0
	vmovdqa	%ymm0, -208(%rbp)
	vmovdqa	-4784(%rbp), %ymm0
	vmovdqa	%ymm0, -176(%rbp)
	vmovdqa	-176(%rbp), %ymm0
	vmovdqa	-208(%rbp), %ymm1
	vpmulhw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4560(%rbp)
	vmovdqa	-4592(%rbp), %ymm0
	vmovdqa	%ymm0, -272(%rbp)
	vmovdqa	-4560(%rbp), %ymm0
	vmovdqa	%ymm0, -240(%rbp)
	vmovdqa	-272(%rbp), %ymm0
	vmovdqa	-240(%rbp), %ymm1
	vpsubw	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -4528(%rbp)
	vmovdqa	-4528(%rbp), %ymm0
	vmovdqa	%ymm0, -304(%rbp)
	movl	$1, -5412(%rbp)
	vmovdqa	-304(%rbp), %ymm0
	movl	-5412(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm4
	vpsrlw	%xmm4, %ymm0, %ymm0
	vmovdqa	%ymm0, -4496(%rbp)
	vmovdqa	-4560(%rbp), %ymm0
	vmovdqa	%ymm0, -368(%rbp)
	vmovdqa	-4496(%rbp), %ymm0
	vmovdqa	%ymm0, -336(%rbp)
	vmovdqa	-368(%rbp), %ymm1
	vmovdqa	-336(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4464(%rbp)
	vmovdqa	-4464(%rbp), %ymm0
	vmovdqa	%ymm0, -400(%rbp)
	movl	$1, -5416(%rbp)
	vmovdqa	-400(%rbp), %ymm0
	movl	-5416(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm5
	vpsrlw	%xmm5, %ymm0, %ymm0
	vmovdqa	%ymm0, -4432(%rbp)
	vmovdqa	-4432(%rbp), %ymm0
	vmovdqa	%ymm0, -464(%rbp)
	vmovdqa	-4752(%rbp), %ymm0
	vmovdqa	%ymm0, -432(%rbp)
	vmovdqa	-432(%rbp), %ymm1
	vmovdqa	-464(%rbp), %ymm0
	vpackuswb	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -4400(%rbp)
	vpermq	$216, -4400(%rbp), %ymm0
	vmovdqa	%ymm0, -4368(%rbp)
	vmovdqa	-4368(%rbp), %ymm0
	vmovaps	%xmm0, -5168(%rbp)
	movl	-5552(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5400(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5200(%rbp)
	vmovdqa	-5168(%rbp), %xmm0
	vmovaps	%xmm0, -4800(%rbp)
	vmovdqa	-4800(%rbp), %xmm0
	movq	-5200(%rbp), %rax
	vmovups	%xmm0, (%rax)
	movl	-5580(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5208(%rbp)
	movq	-5208(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -4336(%rbp)
	movl	-5564(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5216(%rbp)
	movq	-5216(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -4304(%rbp)
	movl	-5548(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5224(%rbp)
	movq	-5224(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -4272(%rbp)
	vmovdqa	-4336(%rbp), %ymm0
	vmovdqa	%ymm0, -528(%rbp)
	vmovdqa	-4304(%rbp), %ymm0
	vmovdqa	%ymm0, -496(%rbp)
	vmovdqa	-528(%rbp), %ymm1
	vmovdqa	-496(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4240(%rbp)
	vmovdqa	-4240(%rbp), %ymm0
	vmovdqa	%ymm0, -592(%rbp)
	vmovdqa	-4272(%rbp), %ymm0
	vmovdqa	%ymm0, -560(%rbp)
	vmovdqa	-592(%rbp), %ymm1
	vmovdqa	-560(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4208(%rbp)
	vmovdqa	-4208(%rbp), %ymm0
	vmovdqa	%ymm0, -656(%rbp)
	vmovdqa	-4784(%rbp), %ymm0
	vmovdqa	%ymm0, -624(%rbp)
	vmovdqa	-624(%rbp), %ymm0
	vmovdqa	-656(%rbp), %ymm1
	vpmulhw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4176(%rbp)
	vmovdqa	-4208(%rbp), %ymm0
	vmovdqa	%ymm0, -720(%rbp)
	vmovdqa	-4176(%rbp), %ymm0
	vmovdqa	%ymm0, -688(%rbp)
	vmovdqa	-720(%rbp), %ymm0
	vmovdqa	-688(%rbp), %ymm1
	vpsubw	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -4144(%rbp)
	vmovdqa	-4144(%rbp), %ymm0
	vmovdqa	%ymm0, -752(%rbp)
	movl	$1, -5420(%rbp)
	vmovdqa	-752(%rbp), %ymm0
	movl	-5420(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm6
	vpsrlw	%xmm6, %ymm0, %ymm0
	vmovdqa	%ymm0, -4112(%rbp)
	vmovdqa	-4176(%rbp), %ymm0
	vmovdqa	%ymm0, -816(%rbp)
	vmovdqa	-4112(%rbp), %ymm0
	vmovdqa	%ymm0, -784(%rbp)
	vmovdqa	-816(%rbp), %ymm1
	vmovdqa	-784(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -4080(%rbp)
	vmovdqa	-4080(%rbp), %ymm0
	vmovdqa	%ymm0, -848(%rbp)
	movl	$1, -5424(%rbp)
	vmovdqa	-848(%rbp), %ymm0
	movl	-5424(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm7
	vpsrlw	%xmm7, %ymm0, %ymm0
	vmovdqa	%ymm0, -4048(%rbp)
	vmovdqa	-4048(%rbp), %ymm0
	vmovdqa	%ymm0, -912(%rbp)
	vmovdqa	-4752(%rbp), %ymm0
	vmovdqa	%ymm0, -880(%rbp)
	vmovdqa	-880(%rbp), %ymm1
	vmovdqa	-912(%rbp), %ymm0
	vpackuswb	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -4016(%rbp)
	vpermq	$216, -4016(%rbp), %ymm0
	vmovdqa	%ymm0, -3984(%rbp)
	vmovdqa	-3984(%rbp), %ymm0
	vmovaps	%xmm0, -5152(%rbp)
	movl	-5548(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5400(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5232(%rbp)
	vmovdqa	-5152(%rbp), %xmm0
	vmovaps	%xmm0, -4816(%rbp)
	vmovdqa	-4816(%rbp), %xmm0
	movq	-5232(%rbp), %rax
	vmovups	%xmm0, (%rax)
	movl	-5588(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5240(%rbp)
	movq	-5240(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -3952(%rbp)
	movl	-5572(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5248(%rbp)
	movq	-5248(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -3920(%rbp)
	movl	-5556(%rbp), %eax
	cltq
	leaq	(%rax,%rax), %rdx
	movq	-5408(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5256(%rbp)
	movq	-5256(%rbp), %rax
	vmovdqu	(%rax), %xmm0
	vinserti128	$0x1, 16(%rax), %ymm0, %ymm0
	vmovdqa	%ymm0, -3888(%rbp)
	vmovdqa	-3952(%rbp), %ymm0
	vmovdqa	%ymm0, -976(%rbp)
	vmovdqa	-3920(%rbp), %ymm0
	vmovdqa	%ymm0, -944(%rbp)
	vmovdqa	-976(%rbp), %ymm1
	vmovdqa	-944(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3856(%rbp)
	vmovdqa	-3856(%rbp), %ymm0
	vmovdqa	%ymm0, -1040(%rbp)
	vmovdqa	-3888(%rbp), %ymm0
	vmovdqa	%ymm0, -1008(%rbp)
	vmovdqa	-1040(%rbp), %ymm1
	vmovdqa	-1008(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3824(%rbp)
	vmovdqa	-3824(%rbp), %ymm0
	vmovdqa	%ymm0, -1104(%rbp)
	vmovdqa	-4784(%rbp), %ymm0
	vmovdqa	%ymm0, -1072(%rbp)
	vmovdqa	-1072(%rbp), %ymm0
	vmovdqa	-1104(%rbp), %ymm1
	vpmulhw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3792(%rbp)
	vmovdqa	-3824(%rbp), %ymm0
	vmovdqa	%ymm0, -1168(%rbp)
	vmovdqa	-3792(%rbp), %ymm0
	vmovdqa	%ymm0, -1136(%rbp)
	vmovdqa	-1168(%rbp), %ymm0
	vmovdqa	-1136(%rbp), %ymm1
	vpsubw	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -3760(%rbp)
	vmovdqa	-3760(%rbp), %ymm0
	vmovdqa	%ymm0, -1200(%rbp)
	movl	$1, -5428(%rbp)
	vmovdqa	-1200(%rbp), %ymm0
	movl	-5428(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm2
	vpsrlw	%xmm2, %ymm0, %ymm0
	vmovdqa	%ymm0, -3728(%rbp)
	vmovdqa	-3792(%rbp), %ymm0
	vmovdqa	%ymm0, -1264(%rbp)
	vmovdqa	-3728(%rbp), %ymm0
	vmovdqa	%ymm0, -1232(%rbp)
	vmovdqa	-1264(%rbp), %ymm1
	vmovdqa	-1232(%rbp), %ymm0
	vpaddw	%ymm0, %ymm1, %ymm0
	vmovdqa	%ymm0, -3696(%rbp)
	vmovdqa	-3696(%rbp), %ymm0
	vmovdqa	%ymm0, -1296(%rbp)
	movl	$1, -5432(%rbp)
	vmovdqa	-1296(%rbp), %ymm0
	movl	-5432(%rbp), %eax
	movq	%rax, -5792(%rbp)
	vmovq	-5792(%rbp), %xmm3
	vpsrlw	%xmm3, %ymm0, %ymm0
	vmovdqa	%ymm0, -3664(%rbp)
	vmovdqa	-3664(%rbp), %ymm0
	vmovdqa	%ymm0, -1360(%rbp)
	vmovdqa	-4752(%rbp), %ymm0
	vmovdqa	%ymm0, -1328(%rbp)
	vmovdqa	-1328(%rbp), %ymm1
	vmovdqa	-1360(%rbp), %ymm0
	vpackuswb	%ymm1, %ymm0, %ymm0
	vmovdqa	%ymm0, -3632(%rbp)
	vpermq	$216, -3632(%rbp), %ymm0
	vmovdqa	%ymm0, -3600(%rbp)
	vmovdqa	-3600(%rbp), %ymm0
	vmovaps	%xmm0, -5136(%rbp)
	movl	-5556(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5400(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -5264(%rbp)
	vmovdqa	-5136(%rbp), %xmm0
	vmovaps	%xmm0, -4832(%rbp)
	vmovdqa	-4832(%rbp), %xmm0
	movq	-5264(%rbp), %rax
	vmovups	%xmm0, (%rax)
	addl	$1, -5680(%rbp)
.L50:
	movl	-5680(%rbp), %eax
	cmpl	-5648(%rbp), %eax
	jl	.L86
	addl	$1, -5684(%rbp)
.L49:
	movl	-5684(%rbp), %eax
	cmpl	-5636(%rbp), %eax
	jl	.L87
	movq	-5408(%rbp), %rax
	movq	%rax, %rdi
	call	free@PLT
	movl	-5624(%rbp), %eax
	movslq	%eax, %rdx
	movq	-5400(%rbp), %rcx
	movq	-5776(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	memcpy@PLT
	movq	-5400(%rbp), %rax
	movq	%rax, %rdi
	call	free@PLT
	nop
	addq	$5800, %rsp
	popq	%r10
	.cfi_def_cfa 10, 0
	popq	%rbp
	leaq	-8(%r10), %rsp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE3667:
	.size	pipeline, .-pipeline
	.globl	WIDTH_OUT_DIFF
	.data
	.align 4
	.type	WIDTH_OUT_DIFF, @object
	.size	WIDTH_OUT_DIFF, 4
WIDTH_OUT_DIFF:
	.long	2
	.globl	HEIGHT_OUT_DIFF
	.align 4
	.type	HEIGHT_OUT_DIFF, @object
	.size	HEIGHT_OUT_DIFF, 4
HEIGHT_OUT_DIFF:
	.long	2
	.ident	"GCC: (Ubuntu 7.3.0-27ubuntu1~18.04) 7.3.0"
	.section	.note.GNU-stack,"",@progbits
