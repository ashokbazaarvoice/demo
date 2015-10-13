import sys

def myMethod(s):
	if returnMethod(s)>0:
		print "Hello",s
	else:
		print "Hello there"

def returnMethod(s):
	return len(s)



if __name__ == '__main__':
	#myMethod("Ram")	
	myMethod(sys.argv[1])