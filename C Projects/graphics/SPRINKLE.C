#include<conio.h>
#include<graphics.h>
#include<stdlib.h>
#include<math.h>

void tri(int p[3][2],int);
void rotate(int [3][2],int);
void transform(int [3][2]);
void main()
{
	int gd,gm,x,y,i=0,d=100,sp=0;
	float a=1.0;
	int pts[5][2];
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

	pts[0][0]=10;
	pts[0][1]=30;
	pts[1][0]=10;
	pts[1][1]=180;
	pts[2][0]=80;
	pts[2][1]=118;
	pts[3][0]=50;
	pts[3][1]=87;
	pts[4][0]=30;
	pts[4][1]=67;
	while(!kbhit())
	{
	circle(x/2,y/2,3);
	line(x/2,y/2,x/2,(y/2)+230);

	rotate(pts,a);
       //	rotate(pts,a+120);
     //	rotate(pts,a+240);
	delay(d);
	setcolor(0);
	rotate(pts,a);
//	rotate(pts,a+120);
  //	rotate(pts,a+240);
	setcolor(15);
	a+=2;
	if(a==360)
	a=0;
	if(d<20){}
	else
	d--;

	}
	getch();
	return;
}

void tri(int p[5][2],int i)
{
	   int x=639/2;
	   int y=479/2;
      //	   line(x+p[0][0]*1,y+p[0][1]*1,x+p[1][0]*1,y+p[1][1]*1);
    //	   line(x+p[1][0]*1,y+p[1][1]*1,x+p[2][0]*1,y+p[2][1]*1);
	   line(x+3,y+3,x+p[1][0]*1,y+p[1][1]*1);
	   putpixel(x+p[2][0],y+p[2][1],i);
	   putpixel(x+p[1][0],y+p[1][1],2);
	   putpixel(x+p[3][0],y+p[3][1],3);
	   putpixel(x+p[0][0],y+p[0][1],4);
	   putpixel(x+p[4][0],y+p[4][1],5);
	   return;
}

void rotate(int t1[5][2],int a)
{
	int i=0,j=0,k=0;
	float t2[2][2];
	int res[3][2];
	t2[0][0]=(float)cos(a*3.14/180.0);
	t2[0][1]=(float)sin(a*3.14/180.0);
	t2[1][0]=-(float)sin(a*3.14/180.0);
	t2[1][1]=(float)cos(a*3.14/180.0);
     //	printf("%f %f %f %f",t2[0][0],t2[0][1],t2[1][0],t2[1][1]);

	for(i=0;i<5;i++)
	{
		for(j=0;j<2;j++)
		{       res[i][j]=0;
			for(k=0;k<2;k++)
			res[i][j]+=t1[i][k]*t2[k][j];
		}
	}
	tri(res,a);
}