void main()
{
	int y,x,lc=0,nc=0,m,n,a,b,j=0,k=0,l=0;
	clrscr();
	printf("\nenter year:");
	scanf("%d",&y);
	x=y;
	printf("\nenter month no:");
	scanf("%d",&m);
	printf("\nenter date:");
	scanf("%d",&n);
	clrscr();
	printf("\n\nThat is:%d/%d/%d",n,m,y);
	n--;
	for(a=1970;a<y;a++)
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
	for(y=1;y<m;y++)
	{
	  if(y==1||y==3||y==5||y==7||y==8||y==10||y==12)
	    j++;
	  if(y==2)
	    k++;
	  if(y==4||y==6||y==9||y==11)
	    l++;
	}
	nc=(x-1970)-lc;
	a=((lc*2)+(j*31)+(l*30)+(k*28)+nc+n)%7;
	getch();
	printf("\n\nDay is:");
	switch(a)
	{
	   case 0:
	   printf("thursday");
	   break;
	   case 1:
	   printf("friday");
	   break;
	   case 2:
	   printf("saturday");
	   break;
	   case 3:
	   printf("sunday");
	   break;
	   case 4:
	   printf("monday");
	   break;
	   case 5:
	   printf("tuesday");
	   break;
	   case 6:
	   printf("wednesday");
	   break;
	}
	getch();
	return;
}

