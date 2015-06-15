# Set working directory and import datafiles as Text
setwd('/Users/ashok.agarwal/dev-r/kaggle')
trainData = read.csv('train.csv', header=TRUE, stringsAsFactors=FALSE)
testData =read.csv("test.csv", header=TRUE, stringsAsFactor=FALSE)

# import datafiles as Factors(Catgeory/Categorical Data)
train <- read.csv("~/dev-r/kaggle/train.csv")
test <- read.csv("~/dev-r/kaggle/test.csv")

# Tells the structure that our data is stored in is called a dataframe in R
str(trainData)

# Find relation between Age and Survived
# table command is one of the most basic summary statistics functions in R
counts <- table(trainData$Survived, trainData$Sex)

#Percentage of sex survived and perished
FemalePerishedPercent = counts[1]*100/(counts[1]+counts[2])
MalePerishedPercent = counts[2]*100/(counts[1]+counts[2])
FemaleSuvivedPercent = counts[3]*100/(counts[3]+counts[4])
MaleSuvivedPercent = counts[4]*100/(counts[3]+counts[4])


# let’s add our ‘everyone dies’ prediction to the test set dataframe. 
# To do this we’ll need to use a new command, 
# ‘rep’ that simply repeats something by the number of times we tell it to:
test$Survived <- rep(0, 418)

# there was no ‘Survived’ column in the dataframe, it will create one
# for us and repeat our ‘0’ prediction 418 times, the number of rows we have. 
# If this column already existed, it would overwrite it with the new values, so be careful! 

# to submit a csv file with the PassengerId as well as our Survived predictions to Kaggle. 
# So let’s extract those two columns from the test dataframe, store them in a new container, 
# and then send it to an output file:

submit <- data.frame(PassengerId = test$PassengerId, Survived = test$Survived)
write.csv(submit, file = "theyallperish.csv", row.names = FALSE)