以前是做java web的，大概两个月前，进入了游戏行业，成为了一名java游戏服务器工程师！！目前开发了一个商店功能，东西不多在这里把这两个月的有些失误和心得在这里记录一下。

1.许多东西想好了再写，尽量写出不带bug的程序
其实，这算是一句废话，谁都想写出不带bug的程序，这样你我大家都轻松。但哪有那么容易，程序员这份工作就是在和bug做斗争。不如就把他当作自己的一个目标。一个好的程序，首要的是他的逻辑是通的，这是首要的一点，但是，恕我直言，许多人这一点还做不到，包括我自己。其实，对于我这样的初级程序员来说，并不需要多么灵巧的设计，并不需要多高超的设计模式，需要的仅仅就是逻辑流畅即可。对于普通功能来说，其实很难去站到设计的角度去看，仅仅就是如何去做，至于性能，我觉得一般情况下都是够用的。

2.对于异常的处理是一门学问
  异常处理真的是一门学问，因为异常会有无数种情况，每一种情况的处理则是根据这个异常所处在的位置来随机应变。举个很简单的例子，在去策划数据的时候，你取到的值可能为空，也就是null，怎么办？如果读取单独的配置，没有用到循环的话，其实抛出一个运行时异常就好。而若在循环之中，那么就需要考虑到一些其他的情况了，因为处于循环之中，如果冷不丁的抛出一个异常，那么下面的数据就没有办法正常的加载啦，那么遇到这种情况的正确做法是用continue，跳过这一次循环。这样可以保证一点点的数据丢失而不会保证整个循环的失败，用一句古话来说就是，”小不忍而乱大谋“。这种情况还有很多很多，自己还需要在以后的编程中多学习，多领悟。

3.不要忽视数据的校验
其实这个跟上一个话题类似，这不过是从不同的角度来阐述这个问题。许多异常的原因就是因为数据不可靠的问题，比如我现在要在商店中购买一个商品，客户端像服务器，发送相关的参数，价格，商品id，数量等等。能信任吗？显然不能，客户端就像是一个熟悉的陌生人，给你的东西不全都是好的，有可能缺斤少两。那么这时候，就要用你犀利的目光去校验一番，是不是小于零，是不是为空，要知道，客户端这种东西在外部是很容易篡改的，谁知道，给你发的那条数据是不是有毒的苹果呢？所以在校验这一点一定要谨记！！

其实，想说的还有很多，以后整理好了在慢慢记录！！希望以后养成写博客的习惯，坚持下去！！