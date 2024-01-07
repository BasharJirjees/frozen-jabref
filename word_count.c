
//Student_Name: Bashar Jirjees
//Student_ID: V00947950

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[])
{
   FILE *file = fopen(argv[1], "r");
   
   if (file == NULL || argc == 1 ){
 
       printf("%s\n", "File Doesn't Exist!!");
       exit(1);
   }
 
   char cf;
   int words_count = 0;
   int check = 0;
   
   while ((cf = fgetc(file)) != EOF){
       
       //Checking if end of word is reached to reset flag.
       
       if (cf == ' '|| cf == '\n' || cf == '\0' || cf == '\t'){
           
          check = 0;
       }
       
       //Word counter has been incremented when continuing to the next word and set the flag.
       
       else if (check == 0){
           check = 1;
           words_count++;
       }
   
   }
   
    printf("%d %s\n", words_count, argv[1]);
    
    fclose(file);
    return 0;
}

//References:
//https://codeforwin.org/c-programming/c-program-count-characters-words-lines-in-file
//https://www.ibm.com/docs/en/i/7.1?topic=functions-main-function
