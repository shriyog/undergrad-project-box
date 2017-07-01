#include<conio.h>
#include<graphics.h>
#include<stdlib.h>
#include<math.h>

void rocket();
void tri(int p[3][2]);
void rotate(int [3][2],float,int,int);
void transform(int [3][2]);
int x=639/2;
int y=479/2;
void main()
{
	int gd,gm,i=0,d=25,f=0,fr=0,inc=20;
	float a=1.0;
	int pts[3][2];
	char ch;
	detectgraph(&gd,&gm);
	initgraph(&gd,&gm,"c://tc/bgi");


	pts[0][0]=3;
	pts[0][1]=-3;
	pts[1][0]=20;
	pts[1][1]=-55;
	pts[2][0]=80;
	pts[2][1]=-118;

      //	tri(pts);                 //639 479

	while(1)
	{
	outtextxy(230,20,"Press any key to start");
	if(kbhit())
	{
	  ch=getche();
	  if(ch==120||ch==88)
	  break;
	while(!kbhit())
	{
		if(fr>=319)
		f=1;

	 if(f==1){
	 rocket();
	x++;
	y++;
	fr=-5;
	inc+=2;
	}

	if(ch==32)
	fr+=3;
	circle(x,y,3);
	rectangle(fr,240,fr-5,244);
	line(319,242,319,y+230);
	rotate(pts,a,f,inc);
	rotate(pts,a+120,f,inc);
	rotate(pts,a+240,f,inc);
	delay(d);
	cleardevice();
	outtextxy(230,20,"press Space to fire.....'X' to exit");
	a+=2;
	if(a==360)
	a=0;
	   }
	}
	}

	return;
}

void rocket()
{
	int userpat,style=3;
	int x,y,r,x1,x2,x3,y1,y2,y3;
	userpat = 1;
  /*	 setlinestyle(style, userpat, 1);
	 for(x=0,y=getmaxy()-80;x<250,y>130;x+=10,y-=20)
	 {
		setcolor(12);
		line(x,y,x+10,y-20);
		delay(300);
		setcolor(0);
		line(x,y,x+10,y-20);
	 }
	  */
   x=319;
   y=479/2;
   x1=y1=x2=y2=x3=y3=x;
   for(;x1>80;)
   {
	setcolor(12);
	line(x,y,x1-=8,y1);
	setcolor(4);
	line(x,y,x2-=6,y2-=2);
	setcolor(12);
	line(x,y,x3-=6,y3-=6);
	setcolor(4);
	line(x,y,y2,x2);
	setcolor(12);
	line(x,y,y1,x1);
	setcolor(4);
	line(x,y,2*x-x1,y1);
	setcolor(12);
	line(x,y,2*x-x2,y2);
	setcolor(4);
	line(x,y,2*x-x3,y3);
	setcolor(12);
	line(x,y,2*x-y2,x2);
	setcolor(4);
	line(x,y,x,2*y-x1);
	setcolor(12);
	line(x,y,x2,2*y-y2);
	setcolor(4);
	line(x,y,x3,2*y-y3);
	setcolor(12);
	line(x,y,y2,2*y-x2);
	setcolor(4);
	line(x,y,2*x-y2,2*y-x2);
	setcolor(12);
	line(x,y,2*x-x3,2*y-y3);
	setcolor(4);
	line(x,y,2*x-x2,2*y-y2);
	delay(300);
	}
   delay(300);
   setfillstyle(1,0);
   bar(70,70,190,200);

}

void tri(int p[3][2])
{
	   line(x+p[0][0]*1,y+p[0][1]*1,x+p[1][0]*1,y+p[1][1]*1);
	   line(x+p[1][0]*1,y+p[1][1]*1,x+p[2][0]*1,y+p[2][1]*1);
	   line(x+p[2][0]*1,y+p[2][1]*1,x+p[0][0]*1,y+p[0][1]*1);
	   return;
}

void rotate(int t1[3][2],float a,int flg,int inc)
{
	int i=0,j=0,k=0;
	float t2[2][2];
	int res[3][2];
	t2[0][0]=(float)cos(a*3.14/180.0);
	t2[0][1]=(float)sin(a*3.14/180.0);
	if(flg==1)
	a+=inc;
	t2[1][0]=-(float)sin(a*3.14/180.0);
	if(flg==1)
	a+=240;
	t2[1][1]=(float)cos(a*3.14/180.0);

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