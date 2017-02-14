/*
 ============================================================================
 Name        : BayesianNetwork.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define defaultFilename "input.txt"

#define BUFFSIZE 100
#define argsNumber 20

char *vars[argsNumber];
char *cpts[argsNumber];


FILE* openFile();
int readVaraiables(FILE*);
int readCPTs(FILE*);
void printVariables(int);
void printTables(int);

int main(void) {
	setvbuf(stdout, NULL, _IONBF, 0);
	FILE* fileDescriptor = openFile();
	if (fileDescriptor == NULL) {
		printf("error occurred,can not open the file\n");
		return -1;
	}
	int variablesCount = readVaraiables(fileDescriptor);
	printVariables(variablesCount);
	int tablesCount = readCPTs(fileDescriptor);
	printTables(tablesCount);
	return 0;
}

FILE* openFile() {
	char* filename;
	filename = calloc(1, sizeof(char) * BUFFSIZE);
	printf(
			"Please input filename, in case you press ENTER, the default %s will be used\n",
			defaultFilename);
	/*fgets(filename, BUFFSIZE, stdin);
	if (strcmp(filename, "\n") == 0) {

		printf("%s will be used as an input file\n", defaultFilename);
	}
*/
	filename = strcpy(filename, defaultFilename);
	FILE *fp = fopen(filename, "r");
	free(filename);
	return fp;
}

int readVaraiables(FILE* FILE) {
	char* BUFF;
	char* token;
	token = calloc(1, sizeof(char) * BUFFSIZE);
	BUFF = calloc(1, sizeof(char) * BUFFSIZE);
	int i;
	fgets(BUFF, BUFFSIZE, FILE);
	i = 0;
	token = strtok(BUFF, ",");
	while (token != NULL) {

		vars[i] = calloc(1, (strlen(token)) * sizeof(char));
		strncpy(vars[i], token, strlen(token));
		token = strtok(NULL, ",");
		i++;
	}
	free(BUFF);
	free(token);
	return i;
}
void printVariables(int variablesCount) {
	int i;
	for (i = 0; i < variablesCount; i++) {
		printf("%s\n", vars[i]);
	}
}
void printTables(int tablesCount) {
	int i;
	for (i = 0; i < tablesCount; i++) {
		printf("%s\n", cpts[i]);
	}
}

int readCPTs(FILE* FILE) {
	char* BUFF;
	char* token;
	token = calloc(1, sizeof(char) * BUFFSIZE);
	BUFF = calloc(1, sizeof(char) * BUFFSIZE);
	int i;
	fgets(BUFF, BUFFSIZE, FILE);
	i = 0;
	token = strtok(BUFF, ";");
	while (token != NULL) {

		cpts[i] = (char*)calloc(1, sizeof(char) * strlen(token));
		strncpy(cpts[i], token,strlen(token));
		token = strtok(NULL, ";");
		i++;
	}
	free(BUFF);
	free(token);
	return i;
}
