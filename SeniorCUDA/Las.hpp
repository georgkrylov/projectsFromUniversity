/*
 * transpose.c
 *
 *  Created on: 23 окт. 2015 г.
 *      Author: Georgiy
 */
#include "Las.h"
#include <cuComplex.h>

void transpose(cuDoubleComplex *A, int nrows, int ncols){
	int i = 0;
	int j = 0;
	cuDoubleComplex  buff;
	cuDoubleComplex  buff2;
	for (i = 0 ; i <ncols;i++){
		for (j = i+1 ; j<nrows; j++){
			buff = A[j * ncols + i];
			buff2 =A[i * ncols +j] ;
			A[j*ncols+i] = buff2;
			A[i*ncols+j]=buff;
		}
	}
}
void complexConj(cuDoubleComplex *A, int nrows, int ncols){
	int i = 0;
	int j = 0;
	cuDoubleComplex buff;
	for (i = 0 ; i <nrows;i++){
		for (j = 0 ; j<ncols; j++){
			buff = A[i*ncols + j];
			if (cuCreal(buff) == 0 && cuCimag(buff) == 0) continue;
			A[i*ncols + j] = { cuCreal(buff), -cuCimag(buff) };
		}
	}
}
void Kronecker_CProduct(cuDoubleComplex *C, cuDoubleComplex *A, int nrows,
		int ncols, cuDoubleComplex *B, int mrows, int mcols)
{
	int ccols, i, j, k, l;
	int block_increment;
	cuDoubleComplex *pB;
	cuDoubleComplex *pC, *p_C;
	ccols = ncols * mcols;
	block_increment = mrows * ccols;
	for (i = 0; i < nrows; C += block_increment, i++)
		for (p_C = C, j = 0; j < ncols; p_C += mcols, A++, j++)
			for (pC = p_C, pB = B, k = 0; k < mrows; pC += ccols, k++)
				for (l = 0; l < mcols; pB++, l++) *(pC+l) =cuCmul( *A , *pB);

}


void multiplyC(cuDoubleComplex *C, cuDoubleComplex *A, int nrows,
		int ncols, cuDoubleComplex *B, int mrows, int mcols){
	int i,j,k;
	cuDoubleComplex sum;
	for (i = 0; i< nrows; i++){
		for (j = 0 ; j< mcols; j++){
			sum = make_cuDoubleComplex(0,0);
			for (k = 0; k< ncols;k++){
				sum = cuCadd(sum,cuCmul( A[i*ncols+ k] , B[k*mcols+j]));
			}
			C[i*mcols+j] = sum;
		}
	}
}
