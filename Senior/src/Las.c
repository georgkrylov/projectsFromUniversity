/*
 * transpose.c
 *
 *  Created on: 23 окт. 2015 г.
 *      Author: Georgiy
 */
#include "transpose.h"
#include <complex.h>

void transpose(double complex *A, int nrows,
        int ncols){
	int i = 0;
	int j = 0;
	double complex  buff;
	double complex  buff2;
	for (i = 0 ; i <ncols;i++){
		for (j = i+1 ; j<nrows; j++){
			buff = A[j * ncols + i];
			buff2 =A[i * ncols +j] ;
			A[j*ncols+i] = buff2;
			A[i*ncols+j]=buff;
		}
	}
}
void Kronecker_CProduct(double complex *C, double complex *A, int nrows,
                            int ncols, double complex *B, int mrows, int mcols)
{
   int ccols, i, j, k, l;
   int block_increment;
   double complex *pB;
   double complex *pC, *p_C;

   ccols = ncols * mcols;
   block_increment = mrows * ccols;
   for (i = 0; i < nrows; C += block_increment, i++)
      for (p_C = C, j = 0; j < ncols; p_C += mcols, A++, j++)
         for (pC = p_C, pB = B, k = 0; k < mrows; pC += ccols, k++)
            for (l = 0; l < mcols; pB++, l++) *(pC+l) = *A * *pB;

}
void multiply(){

}
