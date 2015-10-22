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
void generateRotationalMatrix(Gate g, double complex *A, double complex *B, double complex *C,int nrows,int ncols, int mrows, int mcols)
{
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

		A[0] = cexp(-I*g.parameter/2);
		A[1] = 0;
		A[2] = 0;
		A[3] = cexp(I*g.parameter/2);
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
	}
void generateInteractionMatrix(Gate g, double complex *A, double complex *B, double complex *C,int nrows,int ncols, int mrows, int mcols)
{

	int i;
	for (i = 0 ; i<nrows*ncols;i++){
		*(A+i)=0;
	}
	A[0] = 1;
	A[5]  =  cexp(I *g.parameter);
	A[10] = cexp(I*g.parameter);
	A[15] = 1;
	for (i = 0; i<nrows*ncols;i++){
		A[i] = A[i]*cexp(-I*g.parameter/2);
	}

	for (i = 0 ; i<mrows*mcols;i++){
			*(B+i)=0;
		}
	for (i = 0 ; i<mrows;i++){
		B[i*mcols+i]=1;
	}

	Kronecker_CProduct(C,A,nrows,ncols,B,mrows,mcols);
	}


void getMatrix(Gate g, Qbit q){
	//same indexes mean this is an rotational gate
	if (g.index1 == g.index2){
		double complex *C;
		double complex *A;
		int    nrows = 2;
		int    ncols = 2;
		double complex *B;
		int    mrows=4; //      The number of rows of B.                             //
		int    mcols = 4; //     The number of cols of B.
		A = (double complex *) malloc(sizeof(double complex) * nrows * ncols);//Axis rotation gate
		B = (double complex *) malloc(sizeof(double complex) * mrows * mcols);//Big Identity matrix
		C = (double complex *) malloc(sizeof(double complex) * nrows * ncols * mrows * mcols); // Gate matrix
		generateRotationalMatrix(g,A,B,C,nrows,ncols,mrows,mcols);
		int i;
		for (i = 0 ; i<mrows*nrows*ncols*mcols;i++){
			if (i% (mcols*ncols) == 0)printf("\n");
			printf("%lf + I * %lf ",creal(*(C+i)),cimag(*(C+i)));
		}
		printf("\n");

	}
	//indexes differing by one mean it is interaction gate
	if (abs(g.index1-g.index2)==1){
		double complex *C; // Resulting gate will be here
		double complex *A; // Interaction between neighboring qbits
		int    nrows = 4;
		int    ncols = 4;
		double complex *B; // an Identity
		int    mrows=2; //      The number of rows of B.                             //
		int    mcols = 2; //     The number of cols of B.
		A = (double complex *) malloc(sizeof(double complex) * nrows * ncols);
		B = (double complex *) malloc(sizeof(double complex) * mrows * mcols);
		C = (double complex *) malloc(sizeof(double complex) * nrows * ncols * mrows * mcols);
		generateInteractionMatrix( g, A, B, C, nrows, ncols, mrows,mcols);
		int i;
		for (i = 0 ; i<mrows*nrows*ncols*mcols;i++){
			if (i% (mcols*ncols) == 0)printf("\n");
			printf("%lf + I * %lf ",creal(*(C+i)),cimag(*(C+i)));
		}
		printf("\n");

	}
	//indexes differing more than by one mean there should be swap gates
	if (abs(g.index1-g.index2)>1){
//TODO implement swap and
	}

	}
}

int main(void) {
	Gate g;
	g.index1 = 1;
	g.index2 = 2;
	g.parameter = 3;
	g.direction = X;
	Qbit q;
	q.i[0]=3+2*I;
	q.i[1]=3+2*I;
	getMatrix(g,q);
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	return EXIT_SUCCESS;
}
