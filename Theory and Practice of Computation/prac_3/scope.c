#include <stdio.h>
#include <stdlib.h>

void main()
{
   int x = 1;
   int y = 2;
   
   void p()
   {
      printf("%d",x+y);
   }
   
   void q()
   {
      int x = 3;
      void r()
      {
         int y = 4;
	 p();
      }
      r();
   }
    
   q();
   
}    