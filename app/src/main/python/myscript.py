
# we will use Inception V3 for our application
import numpy as np

#from .models import SaveMuzzleImage

# Create your views here.
import json
import os
import cv2
import glob
import base64
# from matplotlib import pyplot as plt
# import os
# from PIL import Image as im
# import operator

def main(img1,img2):
    return matchSingleImage(img1,img2)


def matching_function(imagetocomp,comp): #can add or remove desti
  immg=imagetocomp
  comm=comp

  folders=os.listdir(''+str(comm)+'/')

  numberresult = []
  foldername = []
  cmplist = []


  for folder in folders:
    cpp=''+str(comp)+'/'+str(folder)+'/'+str("newFrames")+'/'+str("grayscale")+'/'
    comparewith = os.listdir(''+str(cpp)+'/')
    print(cpp)
    print(comparewith)
    for cp in comparewith:
      print("image 2 ")
#       img1 = cv2.imread(''+str(immg)+'',0) # specific image to compare
#
#       img2 = cv2.imread(''+str(cpp)+'/'+str(cp)+'',0)
#       print("image 2 "+img1)
#
#       sift = cv2.xfeatures2d.SIFT_create()
#       surf = cv2.xfeatures2d.SURF_create()
#
#
#       kp1, des1 = sift.detectAndCompute(img1,None)
#       kp2, des2 = sift.detectAndCompute(img2,None)
#
#
#       FLANN_INDEX_KDTREE = 0
#       index_params = dict(algorithm = FLANN_INDEX_KDTREE, trees = 5)
#       search_params = dict(checks=50)
#
#       flann = cv2.FlannBasedMatcher(index_params,search_params)
#
#       matches = flann.knnMatch(des1,des2,k=2)
#       xrange=range
#
#
#       matchesMask = [[0,0] for i in xrange(len(matches))]
#
#       lineno = []
#
#       # cmplist=[]
#       for i,(m,n) in enumerate(matches):
#           if m.distance < 0.7*n.distance:
#               matchesMask[i]=[1,0]
#               lineno.append(i)
#       xx=len(lineno)
#       xxx=xx
#       foldername.append(folder)
#       cmplist.append(xxx)



#   res=np.stack((foldername, cmplist), axis=-1)
#   # print(res)
#   list1 = res.tolist()
#
#   aaa=sorted(list1, key = lambda x: int(x[1]))
#   aa=aaa[-1]
#
#   # print(aaa[-1])
#   # print(aa[1])
#   result={
#       "class_name":aa[0],
#       "match_point":aa[1]
#   }
#   print("cattel folder name is",aa[0]," value:",aa[1])
  return "aa[0]"



def matchSingleImage(cp1,cp2):
     decode_data1 = base64.b64decode(cp1)
     np_data1 = np.fromstring(decode_data1,np.uint8)
     img1 = cv2.imdecode(np_data1,cv2.IMREAD_UNCHANGED)
     img1 = cv2.cvtColor(img1,cv2.COLOR_BGR2GRAY)

     decode_data2 =base64.b64decode(cp1)
     np_data2 = np.fromstring(decode_data2,np.uint8)
     img2 = cv2.imdecode(np_data2,cv2.IMREAD_UNCHANGED)
     img2 = cv2.cvtColor(img2,cv2.COLOR_BGR2GRAY)

     sift = cv2.xfeatures2d.SIFT_create()
     surf = cv2.xfeatures2d.SURF_create()

     kp1, des1 = sift.detectAndCompute(img1,None)
     kp2, des2 = sift.detectAndCompute(img2,None)


     FLANN_INDEX_KDTREE = 0
     index_params = dict(algorithm = FLANN_INDEX_KDTREE, trees = 5)
     search_params = dict(checks=50)

     flann = cv2.FlannBasedMatcher(index_params,search_params)

     matches = flann.knnMatch(des1,des2,k=2)
     xrange=range

     matchesMask = [[0,0] for i in xrange(len(matches))]

     lineno = []
     for i,(m,n) in enumerate(matches):
         if m.distance < 0.7*n.distance:
             matchesMask[i]=[1,0]
             lineno.append(i)
     xx=len(lineno)
     return xx
