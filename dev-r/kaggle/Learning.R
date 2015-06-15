# Set working directory and import datafiles as Text
setwd('/Users/ashok.agarwal/dev-r/kaggle')
trainData = read.csv('train.csv', header=TRUE, stringsAsFactors=FALSE)
testData =read.csv("test.csv", header=TRUE, stringsAsFactor=FALSE)

# lets try all men died and female survived

for(i in 1:nrow(testData)) {
  if (testData$Sex[i] == "female") {
    testData$Survived[i] = 1
  } else {
    testData$Survived[i] = 0
  }
}

#y$B <- NULL removes column B from dataframe y

submit <- data.frame(PassengerId = testData$PassengerId, Survived = testData$Survived)
write.csv(submit, file = "prediction_2.csv", row.names = FALSE)

# compare prediction1 with prediction2

prop.table(table(test$Survived))

prop.table(table(testData$Survived))

testData$Survived <- NULL



# Divides dataframe by factor
paths = by(testData, testData$Sex,function(x) x)

set.seed(2)

pathFP <- sample(nrow(paths[1]), 25)

testDataSex = split(testData, f = testData$Sex)
head(testDataSex$male)

pathFP <- NULL
pathFS <- NULL 

pathFP <- testDataSex$female[sample(nrow(testDataSex$female), 29),]
pathFS <- testDataSex$female[sample(nrow(testDataSex$female), 123),]
#class(testDataSex$female)
indexes = sample(1:nrow(testDataSex$female), size=29)
pathFP <- testDataSex$female[indexes]
pathFS <- testDataSex$female[-indexes]
intersect(pathFP$PassengerId, pathFS$PassengerId)

pathMP <- NULL
pathMS <- NULL
#pathMP <- testDataSex$male[sample(nrow(testDataSex$male), 216),]
#pathMS <- testDataSex$male[sample(nrow(testDataSex$male), 50),]

#index<-seq_len(nrow(testDataSex$male))
#indexes3<-index[!index %in% indexes1]
indexes1 = sample(1:nrow(testDataSex$male), size=50)
#pathMS=testDataSex$male[indexes1]
pathMP=testDataSex$male[indexes1]
nrow(pathMP)
nrow(pathMS)
intersect(pathMP$PassengerId, pathMS$PassengerId)

pathFP$Survived <- rep(0, 29)
pathFS$Survived <- rep(1, 123)
pathMP$Survived <- rep(0, 216)
pathMS$Survived <- rep(1, 50)

submit <- data.frame(PassengerId = pathFP$PassengerId, Survived = pathFP$Survived)
submit <- rbind(submit, data.frame(PassengerId = pathFS$PassengerId, Survived = pathFS$Survived))
submit <- rbind(submit, data.frame(PassengerId = pathMP$PassengerId, Survived = pathMP$Survived))
submit <- rbind(submit, data.frame(PassengerId = pathMS$PassengerId, Survived = pathMS$Survived))

write.csv(submit, file = "prediction_3.csv", row.names = FALSE)

prop.table(table(submit$Survived))

?split

testDataF <- NULL
testDataM <- NULL
# split test dataset into proportion
for(i in 1:nrow(testData)) {
  if (testData$Sex[i] == "female") {
    testDataF = rbind(testDataF, testData[i,])
  } else {
    testDataM = rbind(testDataM, testData[i,])
  }
}

nrow(testDataM)
nrow(testDataF)
head(testDataM)
tail(testDataM)

nrow(testDataSex$female)
nrow(testDataSex$male)
nrow(testData)

randomSample = function(df,n) { 
  return (df[sample(nrow(df), n),])
}

testDataSexMS <-randomSample(testDataSex$male, 50)
length(testDataSexMS)
testDataSexMP <-testDataSex$male[-testDataSexMS]

testDataSexFS <-randomSample(testDataSex$male, 50)
testDataSexFS <-randomSample(testDataSex$male, 50)

iris
colnames(iris)
names(iris)
class(iris)
dim(iris)
nrow(iris)
head(iris)
tail(iris)
unique(iris$Species)
summary(iris)
nrow(iris[iris$Species=="virginica",])
iris[,1:4]
iris[1:4,]
iris[1:4,5]
iris[1:4,1:5]
iris[,5]
mean(iris[iris$Species == "virginica",]$Sepal.Length)
boxplot(iris[iris$Species=="virginica",1:4])
iris_vir = subset(iris, iris$Species=="virginica")
iris_vir = subset(iris, iris$Species=="virginica", select=-iris$Species)
iris_vir = iris[iris$Species=="virginica", ]
iris_vir = iris[iris$Species=="virginica",1:4 ]
str(iris)
pairs(iris[,1:4],col=iris[,5])
pairs(iris[,1:4])


fisher.iris <- iris[,1:4]
chisplot(as.matrix(fisher.iris))
plot(iris$Petal.Length, iris$Petal.Width, main="Edgar Anderson's Iris Data")
pairs(iris[1:4], main = "Edgar Anderson's Iris Data", pch = 21, bg = c("red", "green", "blue")[unclass(iris$Species)])

# Define cars vector with 5 values
cars <- c(1, 3, 6, 4, 9)

# Create a pie chart with defined heading and
# custom colors and labels
pie(cars, main="Cars", col=rainbow(length(cars)), labels=c("Mon","Tue","Wed","Thu","Fri"))

data <- read.csv("hw1_data.csv")
> #What are the column names of the dataset?
  > colnames(data)
[1] "Ozone"   "Solar.R" "Wind"    "Temp"    "Month"   "Day"    
> #Extract the first 2 rows of the data frame and print them to the console
  > data[1:2,]
Ozone Solar.R Wind Temp Month Day
1    41     190  7.4   67     5   1
2    36     118  8.0   72     5   2
> #How many observations (i.e. rows) are in this data frame?
  > dim(data)
[1] 153   6
> #Extract the last 2 rows of the data frame and print them to the console
  > n <- nrow(data)
> data[(n-1):n,]
Ozone Solar.R Wind Temp Month Day
152    18     131  8.0   76     9  29
153    20     223 11.5   68     9  30
> #What is the value of Ozone in the 47th row?
  > data$Ozone[47]
[1] 21
> #How many missing values are in the Ozone column of this data frame?
  > sum(is.na(data$Ozone))
[1] 37
> #What is the mean of the Ozone column in this dataset? Exclude missing values (coded as NA) from this calculation.
  > mean(data$Ozone, na.rm=T)
[1] 42.12931
> #Extract the subset of rows of the data frame where Ozone values are above 31 and Temp values are above 90. What is the mean of Solar.R in this subset?
  > mean(data[data$Ozone>31 & data$Temp > 90,]$Solar.R, na.rm=T)
[1] 212.8
> #What is the mean of "Temp" when "Month" is equal to 6?
  > mean(data[data$Month==6,]$Temp, na.rm=T)
[1] 79.1
> #Take a look at the 'iris' dataset that comes with R. In this dataset, what is the mean of 'Sepal.Length' for the species virginica?
  > library(datasets)
> data(iris)
> mean(iris[iris$Species == "virginica",]$Sepal.Length)
[1] 6.588
> #Continuing with the 'iris' dataset from Question 4, what R code returns a vector of the means of the variables 'Sepal.Length', 'Sepal.Width', 'Petal.Length', and 'Petal.Width'?
  > apply(iris[,1:4], 2, mean)
Sepal.Length  Sepal.Width Petal.Length  Petal.Width 
5.843333     3.057333     3.758000     1.199333 
> #Load the 'mtcars' dataset in R. How can one calculate the average miles per gallon (mpg) by number of cylinders in the car (cyl)?
  > library(datasets)
> data(mtcars)
> tapply(mtcars$mpg, mtcars$cyl, mean)
4        6        8 
26.66364 19.74286 15.10000 
> #Continuing with the 'mtcars' dataset from Question 6, what is the absolute difference between the average horsepower of 4-cylinder cars and the average horsepower of 8-cylinder cars?
  > abs(mean(mtcars[mtcars$cyl==4,]$hp) - mean(mtcars[mtcars$cyl==8,]$hp))
[1] 126.5779
> 