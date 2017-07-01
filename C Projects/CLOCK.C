#include<stdlib.h>
#include<math.h>
#include<conio.h>
#include<graphics.h>
#include<dos.h>

void tri(int p[2][2],int);
void rotate(int [2][2],int);
void transform(int [2][2]);
void main()
{
	int a=0,gd,gm,x,y,i=0,d=1000,sp=0,m=0,h=0,seth=0,setm=0;
	int pts[2][2],ptsm[2][2],ptsh[2][2];
	struct  time t;

	gettime(&t);
	 printf("The current time is: %2d:%02d:%02d.%02d\n",
	  t.ti_hour, t.ti_min, t.ti_sec, t.ti_hund);
	seth=t.ti_hour;
	if(seth>=12)
	seth-=12;
	setm=t.ti_min;
	detectgraph(&gd,&gm);
	initgraph(&gd,&gm,"c://tc/bgi");
	x=getmaxx();
	y=getmaxy();
	pts[0][0]=ptsm[0][0]=ptsh[0][0]=1;
	pts[0][1]=ptsm[0][1]=ptsh[0][1]=1;
	pts[1][0]=ptsm[1][0]=ptsh[1][0]=1;
	pts[1][1]=-100;
	ptsm[1][1]=-90;
	ptsh[1][1]=-80;
      //	printf("\n\nEnter time: ");
    //	scanf("%d %d",&seth,&setm);

	m=6*setm;
	h=(30*seth)+(setm/2);
	while(!kbhit())
	{
	cleardevice();
	outtextxy(270,80,"Analog Clock");
	circle(x/2,y/2,3);
	circle(x/2,y/2,120);
	line(x/2,y/2-115,x/2,y/2-120);
	line(x/2-115,y/2,x/2-120,y/2);
	line(x/2+115,y/2,x/2+120,y/2);
	line(x/2,y/2+115,x/2,y/2+120);
	rotate(pts,a);
	rotate(ptsm,m);
	rotate(ptsh,h);
	a+=6;

	if(a==360)
	{

	m+=6;
	a=0;
	if(m>=12&&m%12==0)
		h+=1;
	}

	delay(d);
	}
	getch();
	return;
}

void tri(int p[2][2],int i)
{
	   int x=639/2;
	   int y=479/2;
	   line(x,y,x+p[1][0],y+p[1][1]);
	   putpixel(x+p[1][0],y+p[1][1],3);
	   return;
}

void rotate(int t1[2][2],int a)
{
	int i=0,j=0,k=0;
	float t2[2][2];
	int res[2][2];
	t2[0][0]=(float)cos(a*3.14/180.0);
	t2[0][1]=(float)sin(a*3.14/180.0);
	t2[1][0]=-(float)sin(a*3.14/180.0);
	t2[1][1]=(float)cos(a*3.14/180.0);
      //	printf("%f %f %f %f",t2[0][0],t2[0][1],t2[1][0],t2[1][1]);

	for(i=0;i<2;i++)
	{
		for(j=0;j<2;j++)
		{       res[i][j]=0;
			for(k=0;k<2;k++)
			res[i][j]+=t1[i][k]*t2[k][j];
		}
	}
     //	printf("%d %d %d %d",res[0][0],res[0][1],res[1][0],res[1][1]);
       //	if(a>=30&&a%30==0)
      //	printf("\na:k");
	tri(res,a);
}