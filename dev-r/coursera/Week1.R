getwd()
ls()
dir()
setwd("~/dev/mygithub/experiment/dev-r/coursera")
x <- rnorm(10)
x
x <- 1:20
x <- 1
x <- vector("numeric", length = 10)
x <- c(1,2,3)
class(x)
as.character(x)
as.numeric(x)
as.logical(x)
x <- list(1,2,3)
for(i in 1:10)
  print(i)
for(i in seq_along(x))
  print(i)

coin <- rbinom(1,1, 0.5)
coin
function myFunc():
  print("Hello")

add2 <- function(x,y=1){
  x+y
}

add2(3,5)

x <- seq(1,2,len = 10)
x

x <- list(a = 1:3)
lapply(x,mean)
lapply(x,add2)