setwd('/Users/ashok.agarwal/Downloads')
syndiRev = read.csv('clairol_SyndicatedReview.csv', header=FALSE, sep=',')
head(syndiRev)
tail(syndiRev)

# Remove column from dataset 
syndiRev$V1 <- NULL
syndiRev$V4 <- NULL
syndiRev$V5 <- NULL
syndiRev$V8 <- NULL
syndiRev$V10 <- NULL
syndiRev$V5 <- NULL

# Divides dataframe by factor
syndiRevNew = by(syndiRev, syndiRev$V11,function(x) x)
head(syndiRevNew[0])

rm(syndiRevNew)

x1 <- subset(syndiRev, select= -syndiRev$V11)
head(x1)
tail(x1)

unique(syndiRev$V3)

syndiRev1 <- NULL
syndiRev0 <- NULL
for(i in 1:nrow(syndiRev)) {
  if (syndiRev$V11[i] == 1) {
    syndiRev1 = rbind(syndiRev1, syndiRev[i,])
  } else {
    syndiRev0 = rbind(syndiRev0, syndiRev[i,])
  }
}

head(syndiRev0)
head(syndiRev1)
unique(syndiRev0$V3)
unique(syndiRev0$V6)
unique(syndiRev1$V3)
unique(syndiRev1$V6)
split(syndiRev0, syndiRev0$V6)

# delete variables v3 and v5
mydata$v3 <- mydata$v5 <- NULL

setwd('/Users/ashok.agarwal/Downloads')
orev = read.csv('clairol_otherReview.csv', header=FALSE, sep=',')
head(orev, n=1)
tail(orev)
orev$V10 <- NULL
orev$V11 <- NULL
unique(orev$V7)

testdata <- NULL
testdata = read.csv('osmapexport_part-r-00000', header=FALSE, sep="\", stringsAsFactors=FALSE)
str(testdata)
head(testdata, n=100)

testdata$V3 <- strsplit(testdata$V1, "\\/")

