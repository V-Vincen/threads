总结：

1、对于 map/set 的选择
线程不安全（未加同步锁）
HashMap
TreeMap
LinkedHashMap

线程安全（加了同步锁）
    并发较低的情况下：
    HashTable：是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序，当我们添加一个元素的时候，它会添加到队列的尾部；当我们获取一个元素的时候，它会返回队列头部的元素
    Collections.sychronizedXXX

    并发较高的情况下：
    CopyOnWriteArrayList：写时复制，适合用在写少读多的情况下。
    ConcurrentHashMap：在 JDK8 做了优化，以前是通过分段锁来实现--初始化时容器分成16段，JDK8 摒弃了 Segment 的概念，使用 CAS + Synchronzied 来实现。
    ConcurrentSkipListMap：它是一个线程安全有序的 Map，采用跳表实现有序高效并发；相当于 TreeMap--采用红黑树实现排序。

2：队列
线程不安全
ArrayList：底层实现结构--数组
LinkedList：底层实现结构--链表

线程安全
Colletions.sychronizedXXX

    非阻塞队列：
    ConcurrentLinkedQueue：（线程安全的无界队列，底层采用单链表，支持 FIFO。）是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序，当我们添加一个元素的时候，它会添加到队列的尾部；当我们获取一个元素的时候，它会返回队列头部的元素。

    阻塞队列：
    BlockingQueue
        ArrayBlockingQueue：是一个底层用数组（准确的说是一个循环数组（可以类比一个圆环），所有的下标在到达最大长度时自动从0继续开始。）实现的有界阻塞队列，有界是指他的容量大小是固定的，不能扩充容量，在初始化时就必须确定队列大小。它通过可重入的独占锁 ReentrantLock 来控制并发，Condition 来实现阻塞。
        LinkedBlockingQueue：是一个底层用单向链表实现的可以是有界的也可以是无界的（Integer.MAX_VALUE）阻塞队列，采用 ReentrantLock 来控制并发，添加采用的是 putLock，移除采用的则是 takeLock，使用两个独占锁来控制消费和生产。
        DelayQueue：是一个支持延时获取元素的无界阻塞队列，队列使用PriorityQueue来实现。队列中的元素必须实现 Delayed 接口，在创建元素时可以指定多久才能从队列中获取当前元素，只有在延迟期满时才能从队列中提取元素。

    ConcurrentLinkedDeque：线程安全的双端无界阻塞队列，底层采用双向链表，支持 FIFO 和 FILO。