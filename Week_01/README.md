学习笔记


1. 学到了字节码，四种字节码指令，如何查看字节码清单和详情
2. 学习了JVM内存模型
    1. 什么是线程栈，栈帧，局部变量表，操作数栈
    2. 什么是堆，年轻代(Young)，老年代(Old)，年轻代(Eden)又分为新生儿和S0、S1
    3. 什么是非堆，元数据(常量池和方法区)，压缩类空间，代码缓存等
3. JVM的一些常用的启动参数及其关系和影响
4. 三类类加载器以及特性，自定义类加载器等
5. jps,jinfo,jstat,jmap,jstack,jcmd,jsconsole,jvisualvm,jmc等命令行和工具的使用
6. GC的基本思想，引用计数，标记清除，对象分代，什么是GC Root
7. Serial GC, Parallel GC, CMS GC, G1 GC, ZGC的设计思想和适用场景