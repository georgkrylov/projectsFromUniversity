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
		//then it is a rotation
		switch (g.direction){
		case X:
				matrix[0] = cos(g.parameter);
				matrix[1] = -I*sin(g.parameter);
				matrix[2] = -I*sin(g.parameter);
				matrix[3] = cos(g.parameter);s
				break;
		case Y:
				break;
		case Z:
				break;
		}
	}
}
int main(void) {
	Qbit i = 0;
	int matrix[2];
	getQbitMatrix(i,matrix);
	int j;
	for (j=0;j<2;j++) printf("%i\n",matrix[j]);
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	return EXIT_SUCCESS;
}
