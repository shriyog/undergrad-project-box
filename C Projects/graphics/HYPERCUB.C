#include<stdio.h>
#include<conio.h>
#include<graphics.h>
#include<dos.h>
#include<stdlib.h>
#include<math.h>
#define X getmaxx()
#define Y getmaxy()

float a[8][4],b[8][4],c[4][4];

void convertxy(float *x,float  *y,float *z)
{
	(*x)=(*x)+(*z)*cos(135*M_PI/180);
	(*y)=(*y)-(*z)*sin(135*M_PI/180);
}
  /*
void set()
{
   a[0][0]=0;a[0][1]=0;a[0][2]=0;a[0][3]= 1;
   a[1][0]=60;a[1][1]= 0;a[1][2]= 0;a[1][3]= 1;
   a[2][0]=60;a[2][1]=60;a[2][2]= 0;a[2][3]= 1;
   a[3][0]= 0;a[3][1]=60;a[3][2]= 0;a[3][3]= 1;
   a[4][0]= 0;a[4][1]=60;a[4][2]= 60;a[4][3]= 1;
   a[5][0]= 0;a[5][1]= 0;a[5][2]= 60;a[5][3]= 1;
   a[6][0]=60;a[6][1]= 0;a[6][2]= 60;a[6][3]= 1;
   a[7][0]=60;a[7][1]=60;a[7][2]= 60;a[7][3]= 1;

   b[0][0]=0;b[0][1]= 0;b[0][2]= 0;b[0][3]= 1;
   b[1][0]=60;b[1][1]= 0;b[1][2]= 0;b[1][3]= 1;
   b[2][0]=60;b[2][1]=60;b[2][2]= 0;b[2][3]= 1;
   b[3][0]= 0;b[3][1]=60;b[3][2]= 0;b[3][3]= 1;
   b[4][0]= 0;b[4][1]=60;b[4][2]= 60;b[4][3]= 1;
   b[5][0]= 0;b[5][1]= 0;b[5][2]= 60;b[5][3]= 1;
   b[6][0]=60;b[6][1]= 0;b[6][2]= 60;b[6][3]= 1;
   b[7][0]=60;b[7][1]=60;b[7][2]= 60;b[7][3]= 1;
}
*/
void set()
{
   a[0][0]=-30;a[0][1]=0;a[0][2]=0;a[0][3]= 1;
   a[1][0]=60;a[1][1]= 0;a[1][2]= 0;a[1][3]= 1;
   a[2][0]=60;a[2][1]=60;a[2][2]= 0;a[2][3]= 1;
   a[3][0]= -30;a[3][1]=60;a[3][2]= 0;a[3][3]= 1;
   a[4][0]= -30;a[4][1]=60;a[4][2]= 60;a[4][3]= 1;
   a[5][0]= -30;a[5][1]= 0;a[5][2]= 60;a[5][3]= 1;
   a[6][0]=60;a[6][1]= 0;a[6][2]= 60;a[6][3]= 1;
   a[7][0]=60;a[7][1]=60;a[7][2]= 60;a[7][3]= 1;

   b[0][0]=-60;b[0][1]=-60;b[0][2]=-60;b[0][3]= 1;
   b[1][0]=60;b[1][1]=-60;b[1][2]= -60;b[1][3]= 1;
   b[2][0]=60;b[2][1]=60;b[2][2]=-60;b[2][3]= 1;
   b[3][0]= -60;b[3][1]=60;b[3][2]= -60;b[3][3]= 1;
   b[4][0]= -60;b[4][1]=60;b[4][2]= 60;b[4][3]= 1;
   b[5][0]= -60;b[5][1]=-60;b[5][2]= 60;b[5][3]= 1;
   b[6][0]=60;b[6][1]=-60;b[6][2]= 60;b[6][3]= 1;
   b[7][0]=60;b[7][1]=60;b[7][2]= 60;b[7][3]= 1;
}


void draw()
{
	 float x,y,z,x1,y1,z1;
	 int i;
	 char ch[10]={'a','b','c','d','e','f','g','h','j'},str[2];
	 setbkcolor(0);
	 for(i=0;i<7;i++)
	 {
		x=a[i][0]; y=a[i][1]; z=a[i][2];
		convertxy(&x,&y,&z);
		x1=a[i+1][0]; y1=a[i+1][1]; z1=a[i+1][2];
		convertxy(&x1,&y1,&z1);
		line(x+X/2,Y/2-y,x1+X/2,Y/2-y1);
		str[0]=ch[i];
		str[1]='\0';
		outtextxy(x+X/2,Y/2-y,str);
	 }
	 x=a[7][0]; y=a[7][1]; z=a[7][2];
	 convertxy(&x,&y,&z);
	 x1=a[2][0]; y1=a[2][1]; z1=a[2][2];
	 convertxy(&x1,&y1,&z1);
	 str[0]=ch[i];
	 str[1]='\0';
	 outtextxy(x+X/2,Y/2-y,str);
	 line(x+X/2,Y/2-y,x1+X/2,Y/2-y1);

	 x=a[5][0]; y=a[5][1]; z=a[5][2];
	 convertxy(&x,&y,&z);
	 x1=a[0][0]; y1=a[0][1]; z1=a[0][2];
	 convertxy(&x1,&y1,&z1);
	 line(x+X/2,Y/2-y,x1+X/2,Y/2-y1);

	 x=a[3][0]; y=a[3][1]; z=a[3][2];
	 convertxy(&x,&y,&z);
	 x1=a[0][0]; y1=a[0][1]; z1=a[0][2];
	 convertxy(&x1,&y1,&z1);
	 line(x+X/2,Y/2-y,x1+X/2,Y/2-y1);
	 x=a[6][0]; y=a[6][1]; z=a[6][2];
	   convertxy(&x,&y,&z);
	 x1=a[1][0]; y1=a[1][1]; z1=a[1][2];
	 convertxy(&x1,&y1,&z1);
	 line(x+X/2,Y/2-y,x1+X/2,Y/2-y1);

	 x=a[4][0]; y=a[4][1]; z=a[4][2];
	 convertxy(&x,&y,&z);
	 x1=a[7][0]; y1=a[7][1]; z1=a[7][2];
	 convertxy(&x1,&y1,&z1);
	 line(x+X/2,Y/2-y,x1+X/2,Y/2-y1);
}

void  mul()
{
   int i,j,k;

   for(i=0;i<8;i++)
	   for(j=0;j<4;j++)
		a[i][j]=0;

   for( i=0;i<8;i++)
   {
	 for(j=0;j<4;j++)
	 {
		for(k=0;k<4;k++)
		{
			   a[i][j]=a[i][j]+b[i][k]*c[k][j];
		}
	 }
}
}
void identity()
{
	 int i,j;
	 for(i=0;i<4;i++)
	 {
		   for(j=0;j<4;j++)
		   {
			if(i==j)
				c[i][j]=1;
			else
				c[i][j]=0;
		   }
	 }
}

void scale(float sf,int clr,int flg)
{
	  float a=sf;
	  float i,j,k,l;
	  identity();              /*
	  printf("\n\n\n\tSCALING :-\n");
	  printf("\n Enter scaling factor :- ");
	  scanf("%f",&s);        */
	 setcolor(clr);

	i=(float)cos(a*3.14/180.0);
	j=(float)sin(a*3.14/180.0);
	k=-(float)sin(a*3.14/180.0);
	l=(float)cos(a*3.14/180.0);

	if(flg==1)
	{
	c[0][0]=i;
	c[0][1]=j;
	c[1][0]=k;
	c[1][1]=l;
	}
	if(flg==2)
	{
	c[1][1]=i;
	c[1][2]=j;
	c[2][1]=k;
	c[2][2]=l;
	}
	if(flg==3)
	{
	c[0][0]=i;
	c[0][2]=-j;
	c[2][0]=-k;
	c[2][2]=l;
	}
	if(flg==4)
	{
	c[0][0]=i;
	c[0][3]=j;
	c[3][0]=k;
	c[3][3]=l;

	}
	  mul();
	  draw();


}
void main()
{
	int gd=DETECT,gm,m=1;
	char ch;
	float f=0;
	while(1)
	{
	   detectgraph(&gd,&gm);
	   initgraph(&gd,&gm,"c:\\tc\\bgi");
	   cleardevice();
	   gotoxy(0,0);        /*
	   printf("\n\n");
	   printf("\n\t---------------------------------------------------------------");
	   printf("\n\t*******************  3-D  TRANSFORMATIONS  *********************");
	   printf("\n\t----------------------------------------------------------------");
	   printf("\n\n\tCHOOSE OPTION:-\n");
	   printf("\n\t\t1.SCALING\n\t\t2.EXIT");
	   printf("\n\n\n\n\tEnter Your Choice:-  ");
	   scanf("%d",&ch);  */
	   cleardevice();
	  /* setcolor(WHITE);

	   line(X/2,Y/2,getmaxx(),Y/2);
	   line(getmaxx()/2,0,getmaxx()/2,getmaxy()/2);
	   line(X/2,Y/2,(X-Y)/2,Y);

	   outtextxy(getmaxx()-10,getmaxy()/2+10,"X");
	   outtextxy(getmaxx()/2+10,10,"Y");
	   outtextxy((X-Y)/2-20,Y-10,"Z");
	     */
	   set();
	   setcolor(2);
     //	   draw();


	   while(1)
	   {
		if(kbhit())
		{
		ch=getche();
		if(ch==32)
		m=(m+1)%5;
		else
		exit(1);
		}
		//getch();
		scale(f,2,m);
		delay(20);

	       //	return;
		scale(f,0,m);
		f+=2;
		if(f>=360)
		f=0;

	   }
			   /*
	    while(1)
	   {
		if(kbhit())
		break;
		scale(f,2,2);
		delay(20);
	       //	getch();
	       //	return;
		scale(f,0,2);
		f+=2;
		if(f>=360)
		f=0;

	   }
	    while(1)
	   {
		if(kbhit())
		break;
		scale(f,2,3);
		delay(20);
	       //	getch();
	       //	return;
		scale(f,0,3);
		f+=2;
		if(f>=360)
		f=0;

	   }  */
		closegraph();
	}

}













