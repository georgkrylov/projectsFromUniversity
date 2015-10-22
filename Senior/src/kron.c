/*
 * kron.c
 *
 *  Created on: 23 окт. 2015 г.
 *      Author: Georgiy
 */

#include"kron.h"
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
