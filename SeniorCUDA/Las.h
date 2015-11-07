/*
 * transpose.h
 *
 *  Created on: 23 окт. 2015 г.
 *      Author: Georgiy
 */
#include <cuComplex.h>
#ifndef TRANSPOSE_H_
#define TRANSPOSE_H_
void transpose(cuDoubleComplex * A, int nrows, int ncols);
void Kronecker_CProduct(cuDoubleComplex * C, cuDoubleComplex *A, int nrows,
                            int ncols, cuDoubleComplex *B, int mrows, int mcols);
void complexConj(cuDoubleComplex *A, int nrows, int ncols);
void multiplyC(cuDoubleComplex *C, cuDoubleComplex *A, int nrows,
                            int ncols, cuDoubleComplex *B, int mrows, int mcols);
#endif /* TRANSPOSE_H_ */
