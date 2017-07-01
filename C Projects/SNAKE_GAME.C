#include<stdio.h>
#include<conio.h>
#include<graphics.h>
#include<stdlib.h>
static int scr=0;
void main()
{
	int gd,gm,i=0,t=0,j=0,j1=0,lvl=2,x1,y1,r=0,p,q,s=2,x,y,flg=0,sp=200;
	char ch,tmp;
	char u[100];
	int octave[7] = { 130, 146, 164, 174, 196, 220, 246};
	detectgraph(&gd,&gm);
	initgraph(&gd,&gm,"c://tc/bgi");
	x=getmaxx()/2;
	y=getmaxy()/2;
	settextstyle(GOTHIC_FONT, HORIZ_DIR,5);
	outtextxy(200,35,"Snake Fiesta");
	settextstyle(DEFAULT_FONT, HORIZ_DIR,1);
	setcolor(4);
	outtextxy(275,110,"LOADING...");
	setcolor(15);
	outtextxy(250,200,"copyright c 2013 ");
	circle(333,204,5);
	outtextxy(235,220,"All rights reserved");
	setcolor(3);
	rectangle(210,130,400,140);
	setcolor(2);
	for(i=210;i<400;i++)
	{
		line(i,131,i,139);
		if(i==300)
		      delay(2000);
		else
		{ 	if(i>350)
			{delay(3);
			continue;
			}
		}
		delay(20);
	}
	cleardevice();
	setcolor(5);
	settextstyle(GOTHIC_FONT, HORIZ_DIR,5);
	outtextxy(200,35,"Snake Fiesta");
	settextstyle(DEFAULT_FONT, HORIZ_DIR,1);
	setcolor(3);
	outtextxy(220,450,"Press space to exit");
	setcolor(15);
	rectangle(100,100,500,400);
	circle(x,y,5);
	p=x;
	q=y;
	getch();
       //	return;
	while(1)
	{
		setcolor(15);
		rectangle(100,100,500,400);
		if(kbhit())
		{       r=0;
			ch=getche();
			if(ch==32)
			  break;
			  x1=x;
			  y1=y;
			if(s>7)
			{ sp-=40;
			  s=2;
			  printf("\nlevel %d",lvl);
			  lvl++;
			}
			while(!kbhit())
			{
				sound(octave[random(7)]*4);
				if(r==0)
				{
				   u[t]=ch;
				   tmp=u[--t];
				}
				if(flg==0)
				{ randomize();
				  while(1)
				  {
				   p=random(getmaxx());
				   q=random(getmaxy());
				   if(p>490||p<110||q<110||q>390)
				   continue;
				   else
				   break;
				  }
				   setcolor(15);
				   rectangle(p,q,p+10,q+10);
				   flg=1;
				}
				if((x>p-10&&x<p+10)&&(y>q-10&&y<q+10))
				{
					s+=1;
					scr+=1;
					sound(5000);
					delay(200);
					nosound();
					setcolor(0);
					rectangle(p,q,p+10,q+10);
					flg=0;
					continue;
				}
				if(x>490||x<110||y<110||y>390)
				{
					nosound();
					setcolor(4);
					circle(x,y,5);
					settextstyle(DEFAULT_FONT, HORIZ_DIR,2);
					outtextxy(230,220,"Game over");
					printf("\n\n\t\t\n\n\nscore:%d",scr*10);
					break;
				}

				setcolor(2);
				i=0;
				if(r<s)
				{
				j=r;
				j1=s-r;
				}
				else
				{j=s;
				j1=-1;
				}

				while(i<=j)
				{
					if(i==0)
					setcolor(4);
					if(ch==75)
					circle(x+i*10,y,5);
					if(ch==77)
					circle(x-i*10,y,5);
					if(ch==72)
					circle(x,y+i*10,5);
					if(ch==80)
					circle(x,y-i*10,5);
					i++;
					setcolor(2);
				}

				i=0; setcolor(2);
				while(i<=j1)
				{
					if(i==0&&r==0)
					setcolor(4);
					if(tmp==75)
					circle(x1+i*10,y1,5);
					if(tmp==77)
					circle(x1-i*10,y1,5);
					if(tmp==72)
					circle(x1,y1+i*10,5);
					if(tmp==80)
					circle(x1,y1-i*10,5);
					i++;
					setcolor(2);
				}
				delay(sp);
				setcolor(0);i=0;
				while(i<=j)
				{
					if(ch==75)
					circle(x+i*10,y,5);
					if(ch==77)
					circle(x-i*10,y,5);
					if(ch==72)
					circle(x,y+i*10,5);
					if(ch==80)
					circle(x,y-i*10,5);
					i++;
				}
				i=0;
				while(i<=j1)
				{
					if(tmp==75)
					circle(x1+i*10,y1,5);
					if(tmp==77)
					circle(x1-i*10,y1,5);
					if(tmp==72)
					circle(x1,y1+i*10,5);
					if(tmp==80)
					circle(x1,y1-i*10,5);
					i++;
				}

			     switch(ch)
			     {
				case 77:
					x+=10;
					break;
				case 75:
					x-=10;
					break;
				case 80:
					y+=10;
					break;
				case 72:
					y-=10;
					break;
			     }
			     r++;

			}
		  t++;
		}
	}
      nosound();
      getch();
      return;
}

