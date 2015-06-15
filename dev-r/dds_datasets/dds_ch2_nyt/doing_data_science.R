setwd('/Users/ashok.agarwal/dev-r/dds_datasets/dds_ch2_nyt')
data1 <- read.csv("nyt1.csv")
head(data1)
nrow(data1)
tail(data1)
cor(data1)
plot(cor(data1))
boxplot(data1)
unique(data1$Age)
unique(data1$Gender)
unique(data1$Clicks)
unique(data1$Signed_In)
unique(data1$Impressions)

data1$sscode[data1$Impressions<=0] <- "NoImps"
data1$sscode[data1$Impressions>0] <- "Imps"
data1$sscode <- factor(data1$sscode)

# subset on the basis of coulmn value
data3 <- data1[data1$Impressions == 0,]
nrow(data1[data1$Impressions == 0,])

data2 <- subset(data1, Impressions>0)
nrow(data2)
455375+3066

head(data2)
data2$ctr <- (data2$Clicks/data2$Impressions)
plot(data2$ctr, data2$Clicks)
abline(lm(data2$ctr~data2$Clicks))
abline(lm(data2$Clicks~data2$ctr))
title("Regression of data2$Clicks on data2$ctr")

hist(data2$Clicks)
hist(data2$Impressions)
hist(data1$Age, breaks=12, col="red")

age_group <- c(-Inf, 0, 18, 24, 34, 44, 54, 64, Inf)
data1$age_cat <- cut(data1$Age, age_group)
data1$age_cat_int <- findInterval(data1$Age, age_group)
str(data1)
summary(data1)
data1$hasimps <- cut(data1$Impressions,c(-Inf, 0, Inf))
