
import random


def rotate(a,b,c):
    """
    эта функция определяет, с какой стороны от вектора AB находится точка C (положительное возвращаемое значение
    соответствует левой стороне, отрицательное — правой).
    """
    return (b[0]-a[0])*(c[1]-b[1])-(b[1]-a[1])*(c[0]-b[0])

def qsort(start,a,P):           
  if (len(P)==1) or (len(P)==0):
    return P                    
  k = random.randint(0,len(P)-1)
                                
                                
  dk=abs(a[start][0]-a[P[k]][0])+abs(a[start][1]-a[P[k]][1])
  left   = []
  center = []
  right  = []
  for j in range(len(P)):
    dj=abs(a[start][0]-a[P[j]][0])+abs(a[start][1]-a[P[j]][1])
    x = rotate (a[start],a[P[k]],a[P[j]])
    if   x<0:  left.append(P[j])
    elif x>0: right.append(P[j])
    else:
      if   dj>dk:  left.append(P[j])
      elif dj<dk: right.append(P[j])
      else:      center.append(P[j])
  return(qsort(start,a,left) + center + qsort(start,a,right))


def grahamscan(a):
  n = len(a)          
  P = []
  for i in range(0,n): 
       P.append(i)

  for i in range(0,n): 
      if ((a[P[i]][0] <  a[P[0]][0]) or # если P[i] точка лежить лівіше P[0] точки
         ((a[P[i]][0] == a[P[0]][0]) and (a[P[i]][1] < a[P[0]][1]))): # міняємо місцями номери цих точок
             P[i], P[0] = P[0], P[i] 

  start=P[0]          
  del(P[0])
  n = len(P)
  # сортировка всех точек (кроме P[0]-ой), по степени их левизны относительно стартовой точки
  P=qsort(start,a,P) 

  S = [start,P[0]]   #pseudo stack
  #Якщо дані точки розташовано на одній прямій, вивести список з двох крайніх точок і припинити виконання алгоритму.
  line=True;           
  for i in range(2,n):
    line=line and (rotate(a[0],a[1],a[i])==0)  
    if (not line): break
  if (line): return S  

  #Все, что нам осталось сделать, так это срезать углы. Для этого нужно пройтись по всем вершинам и удалить те из них,
  # в которых выполняется правый поворот (угол в такой вершине оказывается больше развернутого).
  for i in range(1,n):
    while (rotate(a[S[-2]],a[S[-1]],a[P[i]])<0): del S[-1]
    S.append(P[i])
  return S


if __name__ == '__main__':
    a = [[0,0],[0,10],[5,5],[10,10],[10,0],[15,5],[13,5]]
    print(grahamscan(a))