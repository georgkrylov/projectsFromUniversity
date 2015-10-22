/*
 ============================================================================
 Name        : Senior.c
 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "kron.h"
#include <complex.h>    /* Standard Library of Complex Numbers */
#define X 0
#define Y 1
#define Z 2

typedef struct Qbits{
	double complex i[2];
}Qbit;


typedef struct Gates
{
	int index1;
	int index2;
	int direction;
	double complex parameter;
} Gate;

double complex matrix[8];

void getMatrix(Gate g, Qbit q){
	if (g.index1 == g.index2){
		double complex *C;
		double complex *A;
		int    nrows = 2;
		int    ncols = 2;
		double complex *B;
		int    mrows=4; //      The number of rows of B.                             //
		int    mcols = 4; //     The number of cols of B.
		A = (double complex *) malloc(sizeof(double complex) * nrows * ncols);
		B = (double complex *) malloc(sizeof(double complex) * mrows * mcols);
		C = (double complex *) malloc(sizeof(double complex) * nrows * ncols * mrows * mcols);


		//then it is a rotation
		switch (g.direction){
		case X:
			//Ensure whether it is /2 or not
			A[0] = cos(g.parameter/2);
			A[1] = -I*sin(g.parameter/2);
			A[2] = -I*sin(g.parameter/2);
			A[3] = cos(g.parameter/2);
			break;
		case Y:

			A[0] = cos(g.parameter/2);
			A[1] = -sin(g.parameter/2);
			A[2] = sin(g.parameter/2);
			A[3] = cos(g.parameter);
			break;

		case Z:

			A[0] = exp(-I*g.parameter/2);
			A[1] = 0;
			A[2] = 0;
			A[3] = exp(I*g.parameter/2);
			break;
		}
		int i;
		for (i = 0 ; i<mrows*mcols;i++){
			*(B+i)=0;
		}
		for (i = 0 ; i<mrows;i++){
			B[i*mcols+i]=1;
		}

		Kronecker_CProduct(C,A,nrows,ncols,B,mrows,mcols);
		for (i = 0 ; i<mrows*nrows*ncols*mcols;i++){
			if (i% (mcols*ncols) == 0)printf("\n");
			printf("%lf + I * %lf ",creal(*(C+i)),cimag(*(C+i)));
		}
		printf("\n");

	}
}

int main(void) {
	Gate g;
	g.index1 = 1;
	g.index2 = 1;
	g.parameter = 0.3;
	g.direction = X;
	Qbit q;
	q.i[0]=3+2*I;
	q.i[1]=3+2*I;
	getMatrix(g,q);
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	return EXIT_SUCCESS;
}
