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
typedef char Qbit;
void getQbitMatrix(Qbit i, int matrix[2]){
	if (i){
		matrix[0] = 1;
		matrix[1] = 0;
	} else
	{
		matrix[0]=0;
		matrix[1]=1;
	}
}

int main(void) {
	Qbit i = 0;
	int matrix[2];
	getQbitMatrix(i,matrix);
	int j;
	for (j=0;j<2;j++) printf("%i\n",)
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	return EXIT_SUCCESS;
}
