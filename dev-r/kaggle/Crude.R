# Set working directory and import datafiles as Text
setwd('/Users/ashok.agarwal/dev-r/kaggle')
trainData = read.csv('train.csv', header=TRUE, stringsAsFactors=FALSE)
testData =read.csv("test.csv", header=TRUE, stringsAsFactor=FALSE)

library('rpart')
tree_model <- rpart(Survived~Pclass+Sex+Age,data=trainData,method="class")
test_predictions <- round(predict(tree_model,newdata=testData)[,2],0)

model_submission <- cbind(testData$PassengerId,test_predictions)

colnames(model_submission) <- c("PassengerId","Survived")

write.csv(model_submission,"mysubmission_na_age.csv",row.names=FALSE)
