# Set working directory and import datafiles as Text
setwd('/Users/ashok.agarwal/Downloads')
syndiRev = read.csv('clairol_SyndicatedReview.csv', header=FALSE, stringsAsFactors=FALSE)

str(syndiRev)
syndiRev1 = subset(syndiRev, syndiRev$V10==0)

syndiRev2 = syndiRev[syndiRev$V10==0, ]

# Divides dataframe by factor
syndiRevNew = by(syndiRev, syndiRev$V11,function(x) x)
nrow(syndiRevNew[1])
ncol(syndiRev)
nrow(syndiRev)

dim(syndiRev)

#y$B <- NULL removes column B from dataframe y

syndiRev <- syndiRev[,colSums(is.na(syndiRev))<nrow(syndiRev)]
syndiRev$V10 <- as.numeric(as.character(syndiRev$V10))

head(syndiRev1, n=10)

tail(syndiRevNew[1], n=10)


money = "$money"
sub(pattern = "\\$", replacement = "", x = money)

money = "usd/money"
sub(pattern = "\\/", replacement = ",", x = money)

tail(syndiRev$V6, n=10)
unique(syndiRev$V6)

# At any time we can list the objects which we have created
ls()

# Objects can be removed from the current workspace with the rm function: rm(x,y)
rm(syndiRev2)

names(syndiRev) <- c("PlotID","Lat","Lon","Count")

x<-c(7.5,8.2,3.1,5.6,8.2,9.3,6.5,7.0,9.3,1.2,14.5,6.2)

dim(x)
x[1:6]
x[,2]

x<-c(5,7,9)
y<-c(6,3,4)
z<-cbind(x,y)

dim(z)

z<-matrix(c(5,7,9,6,3,4),nrow=3)

z

z[2,]

setwd('/Users/ashok.agarwal/dev-r')
heisenberg <- read.csv(file="simple.csv",head=TRUE,sep=",")
heisenberg

nrow(heisenberg)
ncol(heisenberg)
str(heisenberg)
names(heisenberg)

heisenberg$trial
heisenberg$mass
summary(heisenberg)
class(heisenberg)

ls()

rm(x,y,z,syndiRev)
ls()

testtrialA <- subset(heisenberg, trial=="A", select=-trial)
testtrialA

testtrialB <- subset(heisenberg, trial=="B", select=-trial)
testtrialB
heisenberg$trial <- NULL
apply(heisenberg, 1, mean)
heisenberg
m <- matrix(c(1:10, 11:20), nrow = 10, ncol = 2)
m
apply(m,1,mean)
apply(m,2,mean)
apply(m,1,function(x) x/2)
apply(m,2,function(x) x/2)

l <- list(a = 1:10, b = 11:20)
l
lapply(l,mean)
