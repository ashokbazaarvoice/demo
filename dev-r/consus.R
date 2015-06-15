setwd('/Users/ashok.agarwal/Downloads/test')
syndi <- NULL
osyndi <- NULL
syndi = read.csv('walmart/walmart_SyndicatedReview.csv', header=FALSE, sep=',', stringsAsFactors=FALSE)
head(syndi)
syndi$V1 <-NULL
syndi$V4 <-NULL
osyndi <- subset(syndi, syndi$V11 == 0, select = -syndi$V11)
head(osyndi)
unique(osyndi$V12)
unique(osyndi$V3)

setwd('/Users/ashok.agarwal/Downloads/dev')
syndi = read.csv('clairol/clairol_SyndicatedReview.csv', header=FALSE, sep=',', stringsAsFactors=FALSE)
head(syndi)
tail(syndi)


#counts rows with column value == 0
nrow(syndi[syndi$V12 == 0,])

nrow(syndi[syndi$V12 == 1,])
30571+2018
unique(syndi$V4)

osyndi <- subset(syndi, syndi$V12 == 0, select = -syndi$V12)

nrow(osyndi)
unique(osyndi$V4)
unique(osyndi$V7)
unique(osyndi$V6)



head(osyndi)

pgestoreosyndi <- subset(osyndi, osyndi$V4 == 'pgestore')
head(pgestoreosyndi)
nrow(pgestoreosyndi)
unique(pgestoreosyndi$V7)
cata <- read.csv('clairol/clairol_otherCatalog.csv', header=FALSE, sep=',', stringsAsFactors=FALSE)
head(cata)
unique(cata$V6)

map <- read.csv('part-r-00000', header=FALSE, sep=',', stringsAsFactors=FALSE)
head(map)
pgmap <- subset(map, map$V1 == 'pgestore' & map$V3=='clairol')
head(pgmap)

pgmap[1:5, ]

# based on variable values
pgmap[which(pgmap$V3=='clairol'), ]


# sort
pgmap[ order(pgmap$V3), ]
pgmap[with(pgmap, order(pgmap$V3)), ]

rm(cata,map, osyndi, syndi)
ls()
rm(pgestoreosyndi, pgmap)
