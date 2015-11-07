/*
============================================================================
Name        : Senior.c
Author      :
Version     :
Copyright   : Your copyright notice
Description : Hello World in C, Ansi-style
============================================================================
*/
#include <time.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include "Las.h"
#include <iostream>
#include <fstream>
#include <cuComplex.h>
#include <complex>

#define X 0
#define Y 1
#define Z 2
using namespace std;
typedef struct Qbits{
	cuDoubleComplex  i[2];
}Qbit;
cuDoubleComplex cuCsin(cuDoubleComplex x){
	double real = cuCreal(x);
	double imag = cuCimag(x);

	return make_cuDoubleComplex(sin(real)*cosh(imag), cos(real)*sinh(imag));

}
__host__ __device__ static __inline__ cuDoubleComplex cuCexp(cuDoubleComplex x)
{
	double factor = exp(x.x);
	return make_cuDoubleComplex(factor * cos(x.y), factor * sin(x.y));
}
cuDoubleComplex cuCcos(cuDoubleComplex x){
	double real = cuCreal(x);
	double imag = cuCimag(x);

	return make_cuDoubleComplex(cos(real)*cosh(imag), -sin(real)*sinh(imag));

}
void printm(cuDoubleComplex *C, int nrows, int ncols){
	int i;
	FILE * pFile;
	pFile = fopen("myfiles.txt", "w");
	for (i = 0; i<ncols*nrows; i++){
		if (i % (ncols) == 0)fprintf(pFile,"\n");
		fprintf(pFile, "%2lf + I * %2lf ", cuCreal(*(C + i)), cuCimag(*(C + i)));
	}
	fprintf(pFile, "\n");
	fclose(pFile);
}
typedef struct Gates
{
	int index1;
	int index2;
	int direction;
	cuDoubleComplex parameter;
} Gate;
void generateRotationalMatrix(Gate g, cuDoubleComplex *A, cuDoubleComplex *B, cuDoubleComplex *C, int index, int numberOfWires )
{
	switch (g.direction){
	case X:
		;
		//Ensure whether it is /2 or not
		A[0] = cuCcos(cuCdiv(g.parameter, make_cuDoubleComplex(2, 0)));
		A[1] = cuCmul(make_cuDoubleComplex(0, -1), cuCsin(cuCdiv(g.parameter, make_cuDoubleComplex(2, 0))));
		A[2] = cuCmul(make_cuDoubleComplex(0, -1), cuCdiv(g.parameter, make_cuDoubleComplex(2, 0)));
		A[3] = cuCcos(cuCdiv(g.parameter, make_cuDoubleComplex(2, 0)));
		break;
	case Y:

		A[0] = cuCcos(cuCdiv(g.parameter, make_cuDoubleComplex(2, 0)));
		A[1] = cuCsin(cuCdiv(g.parameter, make_cuDoubleComplex(-2, 0)));
		A[2] = cuCsin(cuCdiv(g.parameter, make_cuDoubleComplex(-2, 0)));
		A[3] = cuCcos(g.parameter);
		break;

	case Z:

		A[0] = cuCexp(cuCmul(make_cuDoubleComplex(0,-1), cuCdiv(g.parameter, make_cuDoubleComplex(2, 0))));
		A[1] = { 0, 0 };
		A[2] = { 0, 0 };
		A[3] = cuCexp(cuCmul(make_cuDoubleComplex(0, -1), cuCdiv(g.parameter, make_cuDoubleComplex(2, 0))));
		break;
	}
	int i, j;
	int size1 = pow(2, index-1);
	for (i = 0; i <size1*size1; i++){
		*(B + i) = { 0, 0 };
	}
	for (i = 0; i <size1; i++){
		B[i*(size1) + i] = { 1, 0 };
	}

	Kronecker_CProduct(C, B, size1, size1, A, 2, 2);
	
	for (i = 0; i < 4 * size1 * size1; i++){
		A[i] = C[i];
	}
	int size2 = pow(2, (numberOfWires - index));
	for (i = 0; i <size2*size2; i++){
		*(B + i) = { 0, 0 };
	}

	for (i = 0; i <size2; i++){
		B[i*(size2 ) + i] = { 1, 0 };
	}
	Kronecker_CProduct(C, A, size1,size1, B, size2,size2);
	printm(C, size1*2*size2,size1*2*size2);

	
}
void generateInteractionMatrix(Gate g, cuDoubleComplex *A, cuDoubleComplex *B, cuDoubleComplex *C, int nrows, int ncols, int mrows, int mcols)
{

	int i;
	for (i = 0; i<nrows*ncols; i++){
		*(A + i) = {0,0};
	}
	A[0] = { 1, 0 };
	A[5] = cuCexp(cuCmul(g.parameter, {0,1}));
	A[10] = cuCexp(cuCmul(g.parameter, { 0, 1 }));
	A[15] = { 1, 0 };
	for (i = 0; i<nrows*ncols; i++){
		A[i] = cuCmul(A[i], cuCexp(cuCmul({ 0, -1 },cuCdiv(g.parameter, make_cuDoubleComplex(2, 0)))));
	}

	for (i = 0; i<mrows*mcols; i++){
		*(B + i) = { 0, 0 };
	}
	for (i = 0; i<mrows; i++){
		B[i*mcols + i] = { 1, 0 };
	}

	Kronecker_CProduct(C, A, nrows, ncols, B, mrows, mcols);
}

 
cuDoubleComplex* getGateMatrix(Gate g, int numberOfWires){
	int size = pow(2, numberOfWires);
	if (g.index1 == 0 && g.index2 == 0){
		
		cuDoubleComplex *C; // Resulting gate will be here
		C = new cuDoubleComplex[size*size];
		int i = 0;
		for (i = 0; i < size*size; i++){
			C[i] = { 0, 0 };
		}
		for (i = 0; i < size; i++){
			C[i * size + i] = { 1, 0 };
		}
		return C;
	}
	//same indexes mean this is an rotational gate
	if (g.index1 == g.index2){
		cuDoubleComplex *A = new cuDoubleComplex[size*size];
		cuDoubleComplex *B = new cuDoubleComplex[size*size];
		cuDoubleComplex *C = new cuDoubleComplex[size*size];
		int i = 0;
		for (i = 0; i < size*size; i++){
			C[i] = { 0, 0 };
		}
		for (i = 0; i < size; i++){
			C[i * size + i] = { 1, 0 };
		}
		// TODO optimize, g.index passed twice
		generateRotationalMatrix(g, A, B, C,g.index1,numberOfWires);

		return C;
	}
	//indexes differing by one mean it is interaction gate
	if (abs(g.index1 - g.index2) == 1){
		cuDoubleComplex *C; // Resulting gate will be here
		cuDoubleComplex *A; // Interaction between neighboring qbits
		int    nrows = 4;
		int    ncols = 4;
		cuDoubleComplex *B; // an Identity
		int    mrows = pow(2,size-2); //      The number of rows of B.                             //
		int    mcols = pow(2, size - 2); //     The number of cols of B.
		A = (cuDoubleComplex *)malloc(sizeof(cuDoubleComplex) * nrows * ncols);
		B = (cuDoubleComplex *)malloc(sizeof(cuDoubleComplex) * mrows * mcols);
		C = (cuDoubleComplex *)malloc(sizeof(cuDoubleComplex) * nrows * ncols * mrows * mcols);
		generateInteractionMatrix(g, A, B, C, nrows, ncols, mrows, mcols);
	/*	free(A);
		free(B);
		A = (cuDoubleComplex *)malloc(sizeof(cuDoubleComplex) * nrows * ncols * mrows * mcols);
		B = (cuDoubleComplex *)malloc(sizeof(cuDoubleComplex) * nrows * ncols * mrows * mcols);
		memcpy(A, C, sizeof(cuDoubleComplex) * nrows * ncols * mrows * mcols);
		memcpy(B, C, sizeof(cuDoubleComplex) * nrows * ncols * mrows * mcols);
		complexConj(B, nrows*mrows, ncols*mcols);
		transpose(B, nrows*mrows, ncols*mcols);
		multiplyC(C, A, nrows*mrows, ncols*mcols, B, nrows*mrows, ncols*mcols);
		printm(C, ncols*mcols, nrows*mrows);*/
		return C;
	}
	//indexes differing more than by one mean there should be swap gates
	if (abs(g.index1 - g.index2) > 1){
		//TODO implement swap and
	}

	return new cuDoubleComplex{ 0, 0 };
}
//TODO Consider Swap
Gate* generateChromosome(int sizeOfChromosome,int numberOfWires){
	int i = 0;
	int r;
	Gate *g = new	 Gate[sizeOfChromosome];
	
	srand(time(NULL));
	for (i = 0; i < sizeOfChromosome; i++){
		//r = 1+rand()%3; TODO uncomment
		r = 1;
		if (r == 1) {
			g[i].index1 = 1 + rand() % numberOfWires;
			g[i].index1 = 3;
			g[i].index2 = g[i].index1;
			g[i].direction = rand() % 3;
		}
		if (r == 2) {
			// TODO rewrite to universal
			g[i].index1 = 1;
			g[i].index2 = 2;
		}
		if (r == 3){
			g[i].index1 = 0;
			g[i].index2 = 0;
		}
		cout << g[i].index1;
		double parameter = 1.0;
			parameter *= rand() % 628 / 100;
			g[i].parameter = { parameter, 0 };
	}
	
	return g;
}
cuDoubleComplex* generateCircuitMatrix(Gate *g,int numberOfGates, int numberOfWires){
	int i;
	cuDoubleComplex *A, *B, *C;
	int size = pow(2, numberOfWires);
	A = new cuDoubleComplex[size*size];
	B = new cuDoubleComplex[size*size];
	C = new cuDoubleComplex[size*size];
	for (i = 0; i < size*size; i++){
		B[i] = { 0, 0 };
	}
	for (i = 0; i <size; i++){
		B[i * size + i] = { 1, 0 };
	}
	for (i = 0; i < numberOfGates; i++){
		C = getGateMatrix(g[i], numberOfWires);
		multiplyC(A, B, size, size, C, size, size);

		B = A;
	}
	return B;
}

Gate* mutation(Gate *g,int sizeOfChromosome,double probabilityOfMutation){
	int i = 0;
	int r;

	srand(time(NULL));
	for (i = 0; i < sizeOfChromosome; i++){
	//	r = 1 + rand() % 3; TODO uncomment
		r = 1;
		if (r == 1) {
			g[i].index1 = 1 + rand() % 2;
			g[i].index2 = g[i].index1;
			g[i].direction = rand() % 3;
		}
		if (r == 2) {
			// TODO rewrite to universal
			g[i].index1 = 1;
			g[i].index2 = 2;
		}
		if (r == 3){
			g[i].index1 = 0;
			g[i].index2 = 0;
		}
		double parameter = 1.0;
		parameter *= rand() % 628 / 100;
		g[i].parameter = { parameter, 0 };
	}

	return g;
}
int main(void) {
	
	Gate *chromosome = generateChromosome(1,4);// just initializes the chromosome
	//printm(getGateMatrix(*chromosome, 4), 8, 8);
	getGateMatrix(*chromosome, 3);
	// TODO consider passing by address
	//puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	return EXIT_SUCCESS;
}
