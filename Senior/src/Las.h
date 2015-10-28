/*
 * transpose.h
 *
 *  Created on: 23 окт. 2015 г.
 *      Author: Georgiy
 */
#include <complex.h>
#ifndef TRANSPOSE_H_
#define TRANSPOSE_H_

void transpose(double complex *A, int nrows, int ncols);
void Kronecker_CProduct(double complex *C, double complex *A, int nrows,
                            int ncols, double complex *B, int mrows, int mcols);
void complexConj(double complex *A, int nrows, int ncols);
void multiplyC(double complex *C, double complex *A, int nrows,
                            int ncols, double complex *B, int mrows, int mcols);
#endif /* TRANSPOSE_H_ */
