
#include<conio.h>
#include<graphics.h>
#include<stdlib.h>
#include<math.h>

void tri(int p[3][2]);
void rotate(int [3][2],float,int);
void transform(int [3][2]);
void main()
{
	int gd,gm,x,y,i=0,d=100,v=30;
	float a=1.0;
	int pts[3][2];
	detectgraph(&gd,&gm);
	initgraph(&gd,&gm,"c://tc/bgi");
	x=getmaxx();
	y=getmaxy();
	/*
	printf("\nEnter point: ");
	scanf("%d %d",&pts[0][0],&pts[0][1]);
	printf("\nEnter point: ");
	scanf("%d %d",&pts[1][0],&pts[1][1]);
	printf("\nEnter point: ");
	scanf("%d %d",&pts[2][0],&pts[2][1]);
	printf("\n\nenter angle:");
	scanf("%f",&a);
	*/

	pts[0][0]=3;
	pts[0][1]=-3;
	pts[1][0]=20;
	pts[1][1]=-55;
	pts[2][0]=80;
	pts[2][1]=-118;
       //	pts[0][2]=pts[1][2]=pts[2][2]=1;
	line(x/2,0,x/2,y);
	line(0,y/2,x,y/2);
	for(i=x/2;i<x;i+=10)
	circle(i,y/2,1);
	for(i=x/2;i>0;i-=10)
	circle(i,y/2,1);
	for(i=y/2;i<y;i+=10)
	circle(x/2,i,1);
	for(i=y/2;i>0;i-=10)
	circle(x/2,i,1);
	tri(pts);

	while(!kbhit())
	{circle(x/2,y/2,3);
	 line(x/2,y/2,x/2,(y/2)+230);
	rotate(pts,a,v);
	rotate(pts,a+120,v);
	rotate(pts,a+240,v);
	delay(d);
	cleardevice();
	a+=2;
	if(a==720)
	a=0;
	if(d<20){}
	else
	d--;
	v+=2;
	}
	getch();
	return;
}

void tri(int p[3][2])
{
	   int x=639/2;
	   int y=479/2;
	   line(x+p[0][0]*1,y+p[0][1]*1,x+p[1][0]*1,y+p[1][1]*1);
	   line(x+p[1][0]*1,y+p[1][1]*1,x+p[2][0]*1,y+p[2][1]*1);
	   line(x+p[2][0]*1,y+p[2][1]*1,x+p[0][0]*1,y+p[0][1]*1);
	   return;
}

void transform(int t1[3][2])
{
	int i=0,j=0,k=0;
	int t2[3][3],res[3][3];
	t2[0][0]=0;
	t2[0][1]=1;
	t2[0][2]=1;
	t2[1][0]=-1;
	t2[1][1]=0;
	t2[1][2]=1;
	t2[2][0]=1;
	t2[2][1]=1;
	t2[2][2]=1;
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		{       res[i][j]=0;
			for(k=0;k<3;k++)
			res[i][j]+=t1[i][k]*t2[k][j];
		}
	}
	tri(res);
}

void rotate(int t1[3][2],float a,int v)
{
	int i=0,j=0,k=0;
	float t2[2][2];
	int res[3][2];
	t2[0][0]=(float)cos(a*3.14/180.0);
	t2[0][1]=(float)sin(a*3.14/180.0);
       //	a+=v;
	t2[1][0]=-(float)sin(a*3.14/180.0);
	a+=v;
	t2[1][1]=(float)cos(a*3.14/180.0);
     //	printf("%f %f %f %f",t2[0][0],t2[0][1],t2[1][0],t2[1][1]);

	for(i=0;i<3;i++)
	{
		for(j=0;j<2;j++)
		{       res[i][j]=0;
			for(k=0;k<2;k++)
			res[i][j]+=t1[i][k]*t2[k][j];
		}
	}
	tri(res);
}