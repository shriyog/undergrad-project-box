void intro(),day(int);

void main()
{
	int y,s,lc=0,nc=0,b,t,m,a,q=1,i,j,flg=0;
	char x[7][4]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	clrscr();
	intro();
	{
	printf("\n\n\nenter year:");
	scanf("%d",&y);

	if(y<1800)
	{ printf("\nyear should be greater than 1800");
	  getch();
	  return;
	}
	else
	{	for(a=1800;a<y;a++)
	       {
		if(a%4==0)
		{
		   if(a%100!=0)
		     lc++;
		   else
		   {
		     if(a%400==0)
		       lc++;
		   }
		}
	       }
	}
	nc=(y-1800)-lc;
	a=((lc*2)+nc)%7;
	s=a+3;
    //	printf("a s:%d %d",a,s);
	while(q<=12)
	{
	  s%=7;
	  day(q);
	  if(q==1||q==3||q==5||q==7||q==8||q==10||q==12)
	  t=31;
	  if(q==2)
	  {
		if(y%4==0)
		{
		   if(y%100!=0)
		     t=29;
		   else
		   {
		     if(y%400==0)
		       t=29;
		   }
		}
		else
		t=28;
	  }
	  if(q==4||q==6||q==9||q==11)
	    t=30;

	   for(j=1;j<=t;j++)
	   {
	      if(flg==0)
	       {
		 for(i=0;i<7;i++)
		 printf("%4s",(x+i));
		 printf("\n");
		 for(m=1;m<=s;m++)
		      printf("    ");
	       }
	       printf("%4d",j);
		   for(lc=7;lc<=35;lc+=7)
		   {
		     if(j+s==lc)
		     { printf("\n");
		     b=j;
		     }
		   }
	    flg=1;
	   }
	   flg=0;
	   s=t-b;
	   q++;
	   delay(1000);
	   printf("\n");

	}
	getch();
	return;
      }
}

void day(int f)
{
      printf("\n\t    ");
      switch(f)
      {
	case 1:
	printf("January"); break;
	case 2:
	printf("February"); break;
	case 3:
	printf("March"); break;
	case 4:
	printf("April"); break;
	case 5:
	printf("May"); break;
	case 6:
	printf("June"); break;
	case 7:
	printf("July"); break;
	case 8:
	printf("August"); break;
	case 9:
	printf("September"); break;
	case 10:
	printf("October"); break;
	case 11:
	printf("November"); break;
	case 12:
	printf("December"); break;
    }
    printf("\n");
}

void intro()
{
      printf("\n\n\n\n\n\t\t\t\t\b\b\b--==**CALENDER**==--");
      delay(1000);
      printf("\n\n\t\t\t  A PROJECT IN C PROGRAMMING");
      delay(500);
      printf("\n\n\t\t\t\t\t\b\bby");
      printf("\n\n\t\t\t\tSHRIYOG INGALE\n\t\t\t\t\t\b&");
      printf("\n\t\t\t\t\bSWAPNIL  GAIKWAD");
      delay(3000);

      return;
}
 /*
int pass()
{
    char x[5];
    int i;
    char y[5]={'1','2','3','4','5'};
    printf("\n\nEnter password:");
    for(i=0;i<5;i++)
    {
	x[i]=getche();
	delay(200);
	printf("\b*");
    }
    return(xstrcmp(y,x));
}
int xstrcmp(char *p,char *q)
{      int i;
       for(i=0;i<5;i++)
       {
	  if(*p!=*q)
	    break;
	    p++;
	    q++;
       }
       if(i==5)
	 return 0;
       else
	 return 1;
}
*/