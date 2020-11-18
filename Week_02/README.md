## 环境
- 环境Macbook Pro 4C16G
- java 1.8.0_131

## 1、使用GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。
```
java  -XX:+UseSerialGC -Xms512m  -Xmx512m -XX:+PrintGCDetails Week_02.mybean.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [DefNew: 139760K->17472K(157248K), 0.0298496 secs] 139760K->46625K(506816K), 0.0298850 secs] [Times: user=0.03 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157248K->17471K(157248K), 0.0582037 secs] 186401K->87681K(506816K), 0.0582400 secs] [Times: user=0.03 sys=0.02, real=0.06 secs]
[GC (Allocation Failure) [DefNew: 157247K->17470K(157248K), 0.0396163 secs] 227457K->132305K(506816K), 0.0396452 secs] [Times: user=0.03 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 157216K->17471K(157248K), 0.0460062 secs] 272051K->181291K(506816K), 0.0460370 secs] [Times: user=0.03 sys=0.02, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 157178K->17470K(157248K), 0.0349659 secs] 320998K->217811K(506816K), 0.0349996 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157246K->17471K(157248K), 0.0524729 secs] 357587K->260298K(506816K), 0.0525144 secs] [Times: user=0.03 sys=0.02, real=0.06 secs]
[GC (Allocation Failure) [DefNew: 157247K->17469K(157248K), 0.0486893 secs] 400074K->304201K(506816K), 0.0487380 secs] [Times: user=0.03 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 156574K->17471K(157248K), 0.0426119 secs] 443306K->349140K(506816K), 0.0426429 secs] [Times: user=0.02 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 157247K->157247K(157248K), 0.0000185 secs][Tenured: 331668K->266706K(349568K), 0.0716552 secs] 488916K->266706K(506816K), [Metaspace: 2715K->2715K(1056768K)], 0.0717325 secs] [Times: user=0.07 sys=0.00, real=0.07 secs]
[GC (Allocation Failure) [DefNew: 139776K->17469K(157248K), 0.0121995 secs] 406482K->317354K(506816K), 0.0122425 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
[GC (Allocation Failure) [DefNew: 157245K->17471K(157248K), 0.0350201 secs] 457130K->364454K(506816K), 0.0350645 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157162K->157162K(157248K), 0.0000337 secs][Tenured: 346982K->314172K(349568K), 0.0711390 secs] 504145K->314172K(506816K), [Metaspace: 2715K->2715K(1056768K)], 0.0712363 secs] [Times: user=0.07 sys=0.00, real=0.07 secs]
执行结束!共生成对象次数:6652
Heap
 def new generation   total 157248K, used 99744K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,  71% used [0x00000007a0000000, 0x00000007a6168128, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
  to   space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
 tenured generation   total 349568K, used 314172K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 349568K,  89% used [0x00000007aaaa0000, 0x00000007bdd6f238, 0x00000007bdd6f400, 0x00000007c0000000)
 Metaspace       used 2722K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K


java  -XX:+UseParallelGC -Xms512m  -Xmx512m -XX:+PrintGCDetails Week_02.mybean.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 131584K->21491K(153088K)] 131584K->42913K(502784K), 0.0176083 secs] [Times: user=0.04 sys=0.07, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 153072K->21490K(153088K)] 174494K->87969K(502784K), 0.0308104 secs] [Times: user=0.05 sys=0.12, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 153074K->21502K(153088K)] 219553K->135397K(502784K), 0.0262713 secs] [Times: user=0.04 sys=0.10, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 153086K->21503K(153088K)] 266981K->175442K(502784K), 0.0326256 secs] [Times: user=0.06 sys=0.10, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 153087K->21500K(153088K)] 307026K->215479K(502784K), 0.0279312 secs] [Times: user=0.04 sys=0.10, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 153084K->21501K(80384K)] 347063K->255630K(430080K), 0.0242711 secs] [Times: user=0.04 sys=0.08, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 80078K->34422K(116736K)] 314207K->273127K(466432K), 0.0058842 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 92890K->48089K(116736K)] 331595K->292251K(466432K), 0.0145455 secs] [Times: user=0.06 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 106969K->57686K(116736K)] 351131K->311038K(466432K), 0.0105660 secs] [Times: user=0.04 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 116228K->36610K(116736K)] 369580K->324845K(466432K), 0.0164449 secs] [Times: user=0.04 sys=0.06, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 94782K->18421K(116736K)] 383017K->340453K(466432K), 0.0182841 secs] [Times: user=0.04 sys=0.07, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 18421K->0K(116736K)] [ParOldGen: 322031K->235401K(349696K)] 340453K->235401K(466432K), [Metaspace: 2715K->2715K(1056768K)], 0.0484275 secs] [Times: user=0.22 sys=0.01, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->20850K(116736K)] 294281K->256252K(466432K), 0.0077901 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 79730K->20692K(116736K)] 315132K->275984K(466432K), 0.0042573 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 79494K->19505K(116736K)] 334787K->294905K(466432K), 0.0100133 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 78353K->21185K(116736K)] 353753K->314210K(466432K), 0.0049990 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 80065K->23046K(116736K)] 373090K->337015K(466432K), 0.0084905 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 23046K->0K(116736K)] [ParOldGen: 313969K->267046K(349696K)] 337015K->267046K(466432K), [Metaspace: 2715K->2715K(1056768K)], 0.0430362 secs] [Times: user=0.20 sys=0.01, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->17806K(116736K)] 325926K->284852K(466432K), 0.0038535 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 76306K->16287K(116736K)] 343353K->300341K(466432K), 0.0045507 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 75167K->22027K(116736K)] 359221K->321560K(466432K), 0.0086064 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 80907K->21530K(116736K)] 380440K->342036K(466432K), 0.0129551 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 21530K->0K(116736K)] [ParOldGen: 320505K->288930K(349696K)] 342036K->288930K(466432K), [Metaspace: 2715K->2715K(1056768K)], 0.0439048 secs] [Times: user=0.20 sys=0.01, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->22254K(116736K)] 347810K->311185K(466432K), 0.0028976 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 81134K->21398K(116736K)] 370065K->330331K(466432K), 0.0057015 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 80252K->23006K(116736K)] 389184K->352664K(466432K), 0.0069174 secs] [Times: user=0.02 sys=0.01, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 23006K->0K(116736K)] [ParOldGen: 329657K->306539K(349696K)] 352664K->306539K(466432K), [Metaspace: 2715K->2715K(1056768K)], 0.0401313 secs] [Times: user=0.17 sys=0.00, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->21450K(116736K)] 365419K->327989K(466432K), 0.0066901 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 80330K->15958K(116736K)] 386869K->343013K(466432K), 0.0099318 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 15958K->0K(116736K)] [ParOldGen: 327055K->311088K(349696K)] 343013K->311088K(466432K), [Metaspace: 2715K->2715K(1056768K)], 0.0693220 secs] [Times: user=0.20 sys=0.01, real=0.07 secs]
执行结束!共生成对象次数:7216
Heap
 PSYoungGen      total 116736K, used 2633K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b5812740,0x00000007b8f00000)
  from space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
  to   space 57856K, 0% used [0x00000007bc780000,0x00000007bc780000,0x00000007c0000000)
 ParOldGen       total 349696K, used 311088K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 88% used [0x00000007a0000000,0x00000007b2fcc048,0x00000007b5580000)
 Metaspace       used 2722K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K


java  -XX:+UseConcMarkSweepGC -Xms512m  -Xmx512m -XX:+PrintGCDetails Week_02.mybean.GCLogAnalysis
正在执行...
[GC (Allocation Failure) [ParNew: 139776K->17468K(157248K), 0.0189811 secs] 139776K->47365K(506816K), 0.0190254 secs] [Times: user=0.03 sys=0.08, real=0.02 secs]
[GC (Allocation Failure) [ParNew: 157244K->17472K(157248K), 0.0300001 secs] 187141K->95511K(506816K), 0.0300685 secs] [Times: user=0.05 sys=0.09, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157248K->17464K(157248K), 0.0491658 secs] 235287K->137693K(506816K), 0.0492441 secs] [Times: user=0.17 sys=0.03, real=0.05 secs]
[GC (Allocation Failure) [ParNew: 157240K->17471K(157248K), 0.0513861 secs] 277469K->182496K(506816K), 0.0514430 secs] [Times: user=0.19 sys=0.02, real=0.05 secs]
[GC (Allocation Failure) [ParNew: 157220K->17471K(157248K), 0.0555859 secs] 322245K->230208K(506816K), 0.0556267 secs] [Times: user=0.21 sys=0.03, real=0.06 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 212737K(349568K)] 230744K(506816K), 0.0003171 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.004/0.004 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 156764K->17471K(157248K), 0.0355684 secs] 369501K->275484K(506816K), 0.0356218 secs] [Times: user=0.12 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 157247K->17469K(157248K), 0.0523831 secs] 415260K->321950K(506816K), 0.0524222 secs] [Times: user=0.22 sys=0.03, real=0.05 secs]
[GC (Allocation Failure) [ParNew: 157245K->157245K(157248K), 0.0000254 secs][CMS[CMS-concurrent-abortable-preclean: 0.005/0.173 secs] [Times: user=0.43 sys=0.05, real=0.18 secs]
 (concurrent mode failure): 304480K->247248K(349568K), 0.0766461 secs] 461726K->247248K(506816K), [Metaspace: 2715K->2715K(1056768K)], 0.0767355 secs] [Times: user=0.07 sys=0.00, real=0.07 secs]
[GC (Allocation Failure) [ParNew: 139776K->17471K(157248K), 0.0164737 secs] 387024K->295920K(506816K), 0.0165148 secs] [Times: user=0.11 sys=0.00, real=0.02 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 278448K(349568K)] 295992K(506816K), 0.0001406 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 157247K->17470K(157248K), 0.0290933 secs] 435696K->344305K(506816K), 0.0291294 secs] [Times: user=0.14 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157246K->157246K(157248K), 0.0000195 secs][CMS[CMS-concurrent-abortable-preclean: 0.002/0.075 secs] [Times: user=0.20 sys=0.02, real=0.07 secs]
 (concurrent mode failure): 326834K->300864K(349568K), 0.0707210 secs] 484081K->300864K(506816K), [Metaspace: 2715K->2715K(1056768K)], 0.0707923 secs] [Times: user=0.07 sys=0.00, real=0.07 secs]
[GC (Allocation Failure) [ParNew: 139776K->17471K(157248K), 0.0109363 secs] 440640K->348674K(506816K), 0.0109757 secs] [Times: user=0.07 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 331203K(349568K)] 348906K(506816K), 0.0001644 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 34421 K (157248 K)][Rescan (parallel) , 0.0004444 secs][weak refs processing, 0.0000067 secs][class unloading, 0.0002371 secs][scrub symbol table, 0.0003210 secs][scrub string table, 0.0001333 secs][1 CMS-remark: 331203K(349568K)] 365624K(506816K), 0.0011791 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 156747K->17471K(157248K), 0.0160786 secs] 454136K->358201K(506816K), 0.0161073 secs] [Times: user=0.10 sys=0.01, real=0.02 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 340729K(349568K)] 358990K(506816K), 0.0001729 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[GC (Allocation Failure) [ParNew: 157247K->157247K(157248K), 0.0000300 secs][CMS[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.02 sys=0.00, real=0.04 secs]
 (concurrent mode failure): 340729K->332008K(349568K), 0.0792317 secs] 497977K->332008K(506816K), [Metaspace: 2715K->2715K(1056768K)], 0.0999243 secs] [Times: user=0.07 sys=0.00, real=0.10 secs]
执行结束!共生成对象次数:7291
Heap
 par new generation   total 157248K, used 5750K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,   4% used [0x00000007a0000000, 0x00000007a059da90, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
  to   space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
 concurrent mark-sweep generation total 349568K, used 332008K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2722K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K


java  -XX:+UseG1GC -Xms512m  -Xmx512m -XX:+PrintGC Week_02.mybean.GCLogAnalysis
正在执行...
[GC pause (G1 Evacuation Pause) (young) 31M->9469K(512M), 0.0049687 secs]
[GC pause (G1 Evacuation Pause) (young) 41M->22M(512M), 0.0136799 secs]
[GC pause (G1 Evacuation Pause) (young) 82M->46M(512M), 0.0082855 secs]
[GC pause (G1 Evacuation Pause) (young) 116M->69M(512M), 0.0158973 secs]
[GC pause (G1 Evacuation Pause) (young) 185M->104M(512M), 0.0230586 secs]
[GC pause (G1 Evacuation Pause) (young) 230M->145M(512M), 0.0240780 secs]
[GC pause (G1 Evacuation Pause) (young) 273M->181M(512M), 0.0224499 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 312M->223M(512M), 0.0247112 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001600 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0022391 secs]
[GC remark, 0.0017068 secs]
[GC cleanup 232M->232M(512M), 0.0006338 secs]
[GC pause (G1 Evacuation Pause) (young)-- 423M->348M(512M), 0.0170700 secs]
[GC pause (G1 Evacuation Pause) (mixed) 353M->329M(512M), 0.0075675 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 332M->331M(512M), 0.0031654 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001795 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0025399 secs]
[GC remark, 0.0034083 secs]
[GC cleanup 342M->342M(512M), 0.0052616 secs]
[GC pause (G1 Evacuation Pause) (young) 418M->357M(512M), 0.0068372 secs]
[GC pause (G1 Evacuation Pause) (mixed) 376M->316M(512M), 0.0057777 secs]
[GC pause (G1 Evacuation Pause) (mixed) 342M->290M(512M), 0.0203927 secs]
[GC pause (G1 Evacuation Pause) (mixed) 320M->285M(512M), 0.0088686 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 291M->287M(512M), 0.0043086 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0003439 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0018901 secs]
[GC remark, 0.0028196 secs]
[GC cleanup 292M->291M(512M), 0.0009033 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000296 secs]
[GC pause (G1 Evacuation Pause) (young) 417M->325M(512M), 0.0079026 secs]
[GC pause (G1 Evacuation Pause) (mixed) 341M->302M(512M), 0.0080182 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 303M->303M(512M), 0.0020127 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000750 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0008766 secs]
[GC remark, 0.0018380 secs]
[GC cleanup 308M->308M(512M), 0.0008551 secs]
[GC pause (G1 Evacuation Pause) (young) 404M->335M(512M), 0.0039909 secs]
[GC pause (G1 Evacuation Pause) (mixed) 354M->318M(512M), 0.0116610 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 321M->319M(512M), 0.0021646 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001733 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0012056 secs]
[GC remark, 0.0015818 secs]
[GC cleanup 327M->327M(512M), 0.0007012 secs]
[GC pause (G1 Evacuation Pause) (young) 411M->342M(512M), 0.0077473 secs]
[GC pause (G1 Evacuation Pause) (mixed) 359M->327M(512M), 0.0095376 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 328M->327M(512M), 0.0023008 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0000970 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0009010 secs]
[GC remark, 0.0016955 secs]
[GC cleanup 333M->333M(512M), 0.0005570 secs]
[GC pause (G1 Evacuation Pause) (young) 406M->345M(512M), 0.0029225 secs]
[GC pause (G1 Evacuation Pause) (mixed) 364M->332M(512M), 0.0060565 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 335M->333M(512M), 0.0048020 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0002256 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0021211 secs]
[GC remark, 0.0028443 secs]
[GC cleanup 341M->341M(512M), 0.0007680 secs]
[GC pause (G1 Evacuation Pause) (young) 399M->350M(512M), 0.0081850 secs]
[GC pause (G1 Evacuation Pause) (mixed) 373M->340M(512M), 0.0072942 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 342M->340M(512M), 0.0011856 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001444 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0010892 secs]
[GC remark, 0.0015003 secs]
[GC cleanup 347M->347M(512M), 0.0006314 secs]
[GC pause (G1 Evacuation Pause) (young) 394M->359M(512M), 0.0047651 secs]
执行结束!共生成对象次数:6085
```
#### 总结
1. 在Xms=512m和Xmx=512m的情况下，通过GCEasy分析，串行GC的平均GC暂停时间最长，并行GC和CMS GC的平均暂停时间相当，G1平均暂停时间最短
2. 并行GC对年轻代的GC频率比串行GC高，年轻代在没完全满的时候就会触发GC
3. CMS GC多次产生concurrent mode failure， 因为新生代GC和老年代GC同时进行，而老年代空间又不足时，导致对老年代的GC退化成串行GC
4. G1GC在经过短暂的并发标记后，使用的混合GC模式(同时清理年轻代和老年代)和对年轻代的GC模式用时几乎相等


## 2、使用压测工具（wrk或sb），演练gateway-server-0.0.1-SNAPSHOT.jar 示例。


- -Xms512m -Xmx512m

```

java -XX:+UseSerialGC -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t16 -d60s --latency http://localhost:8088/api/hello

@1
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    52.29ms  214.29ms   1.87s    93.78%
    Req/Sec     2.70k     0.98k    4.20k    72.36%
  Latency Distribution
     50%  292.00us
     75%  419.00us
     90%    5.52ms
     99%    1.27s
  2073615 requests in 1.00m, 247.57MB read
  Socket errors: connect 0, read 0, write 0, timeout 16
Requests/sec:  34511.27
Transfer/sec:      4.12MB
@2
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     4.04ms   31.47ms 499.75ms   98.22%
    Req/Sec     3.36k   706.43     5.14k    89.20%
  Latency Distribution
     50%  251.00us
     75%  312.00us
     90%  425.00us
     99%  168.85ms
  2130691 requests in 1.00m, 254.38MB read
Requests/sec:  35466.08
Transfer/sec:      4.23MB

java -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t16 -d60s --latency http://localhost:8088/api/hello

@1
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.88ms  136.44ms   1.76s    95.31%
    Req/Sec     2.51k     1.01k    4.42k    65.25%
  Latency Distribution
     50%  304.00us
     75%  429.00us
     90%    1.69ms
     99%  632.28ms
  1949414 requests in 1.00m, 232.74MB read
  Socket errors: connect 0, read 0, write 0, timeout 31
Requests/sec:  32463.77
Transfer/sec:      3.88MB
@2
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    12.63ms   76.11ms   1.03s    96.72%
    Req/Sec     3.01k   719.14     6.49k    81.62%
  Latency Distribution
     50%  277.00us
     75%  359.00us
     90%  543.00us
     99%  437.79ms
  2053135 requests in 1.00m, 245.12MB read
Requests/sec:  34187.09
Transfer/sec:      4.08MB


java -XX:+UseConcMarkSweepGC -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t16 -d60s --latency http://localhost:8088/api/hello

@1
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    50.26ms  197.79ms   1.75s    93.38%
    Req/Sec     2.51k     0.99k    4.26k    69.16%
  Latency Distribution
     50%  306.00us
     75%  459.00us
     90%   14.05ms
     99%    1.14s
  1992924 requests in 1.00m, 237.94MB read
Requests/sec:  33164.78
Transfer/sec:      3.96MB

@2
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    17.07ms   86.33ms   1.05s    95.63%
    Req/Sec     2.98k   837.43     4.37k    82.06%
  Latency Distribution
     50%  269.00us
     75%  357.00us
     90%  682.00us
     99%  492.20ms
  2274112 requests in 1.00m, 271.51MB read
  Socket errors: connect 0, read 0, write 0, timeout 48
Requests/sec:  37856.45
Transfer/sec:      4.52MB


java -XX:+UseG1GC -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t16 -d60s --latency http://localhost:8088/api/hello
@1
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    34.20ms  155.24ms   1.67s    94.89%
    Req/Sec     2.08k     1.04k    6.20k    58.58%
  Latency Distribution
     50%  337.00us
     75%  546.00us
     90%   16.75ms
     99%  909.53ms
  1731574 requests in 1.00m, 206.73MB read
Requests/sec:  28815.48
Transfer/sec:      3.44MB
@2
Running 1m mybean @ http://localhost:8088/api/hello
  16 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    31.06ms  129.69ms   1.37s    93.97%
    Req/Sec     2.76k     0.91k    8.93k    77.94%
  Latency Distribution
     50%  291.00us
     75%  405.00us
     90%    1.89ms
     99%  721.63ms
  2172125 requests in 1.00m, 259.33MB read
  Socket errors: connect 0, read 0, write 0, timeout 16
Requests/sec:  36140.70
Transfer/sec:      4.31MB
```

- wrk使用4个线程

```

java -XX:+UseSerialGC -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t4 -d60s --latency http://localhost:8088/api/hello

@1
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    46.77ms  184.99ms   1.69s    94.04%
    Req/Sec    10.87k     4.08k   22.34k    73.28%
  Latency Distribution
     50%  491.00us
     75%  837.00us
     90%   50.75ms
     99%    1.06s
  2242422 requests in 1.00m, 267.72MB read
  Socket errors: connect 0, read 0, write 0, timeout 28
Requests/sec:  37349.01
Transfer/sec:      4.46MB
@2
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    41.46ms  152.93ms   1.32s    93.15%
    Req/Sec    11.26k     3.61k   22.42k    74.63%
  Latency Distribution
     50%  483.00us
     75%  781.00us
     90%   63.16ms
     99%  863.88ms
  2362874 requests in 1.00m, 282.10MB read
Requests/sec:  39236.93
Transfer/sec:      4.68MB

java -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t4 -d60s --latency http://localhost:8088/api/hello

@1
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    23.87ms  106.66ms   1.09s    95.02%
    Req/Sec     9.47k     4.10k   21.58k    62.11%
  Latency Distribution
     50%  553.00us
     75%    0.98ms
     90%   12.41ms
     99%  662.96ms
  2126825 requests in 1.00m, 253.92MB read
Requests/sec:  35409.21
Transfer/sec:      4.23MB
@2
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    54.81ms  190.06ms   1.74s    92.01%
    Req/Sec    13.57k     3.77k   29.39k    85.08%
  Latency Distribution
     50%  424.00us
     75%  604.00us
     90%  163.97ms
     99%    1.07s
  2614503 requests in 1.00m, 312.15MB read
Requests/sec:  43514.75
Transfer/sec:      5.20MB


java -XX:+UseConcMarkSweepGC -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t4 -d60s --latency http://localhost:8088/api/hello

@1
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    47.39ms  193.33ms   1.58s    94.64%
    Req/Sec    10.08k     3.83k   22.34k    69.36%
  Latency Distribution
     50%  538.00us
     75%    0.94ms
     90%   43.10ms
     99%    1.19s
  2172779 requests in 1.00m, 259.41MB read
Requests/sec:  36158.73
Transfer/sec:      4.32MB

@2
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    73.36ms  229.37ms   1.63s    91.51%
    Req/Sec    12.41k     3.54k   22.81k    81.70%
  Latency Distribution
     50%  458.00us
     75%  741.00us
     90%  225.15ms
     99%    1.20s
  2494848 requests in 1.00m, 297.86MB read
Requests/sec:  41526.75
Transfer/sec:      4.96MB


java -XX:+UseG1GC -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t4 -d60s --latency http://localhost:8088/api/hello
@1
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    16.04ms   77.13ms 997.12ms   95.29%
    Req/Sec    10.41k     3.98k   19.15k    74.95%
  Latency Distribution
     50%  508.00us
     75%  785.00us
     90%    5.70ms
     99%  386.38ms
  2210757 requests in 1.00m, 263.94MB read
Requests/sec:  36806.69
Transfer/sec:      4.39MB
@2
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    28.56ms  105.71ms   1.13s    92.71%
    Req/Sec    12.04k     3.75k   27.19k    80.36%
  Latency Distribution
     50%  464.00us
     75%  673.00us
     90%   62.26ms
     99%  536.45ms
  2622636 requests in 1.00m, 313.12MB read
Requests/sec:  43642.93
Transfer/sec:      5.21MB
```
- 测试一下G1 GC的MaxGCPauseMillis
```

java -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -jar -Xms512m -Xmx512m  gateway-server-0.0.1-SNAPSHOT.jar
wrk -c 30 -t4 -d60s --latency http://localhost:8088/api/hello

@1
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.12ms  121.07ms   1.37s    95.55%
    Req/Sec     8.46k     3.71k   27.50k    67.74%
  Latency Distribution
     50%  626.00us
     75%    1.14ms
     90%   16.00ms
     99%  679.41ms
  1902930 requests in 1.00m, 227.19MB read
Requests/sec:  31661.53
Transfer/sec:      3.78MB
@2
Running 1m mybean @ http://localhost:8088/api/hello
  4 threads and 30 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.61ms   42.24ms 535.14ms   96.33%
    Req/Sec    13.36k     3.49k   23.12k    84.55%
  Latency Distribution
     50%  421.00us
     75%  540.00us
     90%  807.00us
     99%  248.05ms
  2475893 requests in 1.00m, 295.60MB read
Requests/sec:  41202.30
Transfer/sec:      4.92MB
```
#### 总结
1. java程序经过预热之后吞吐量和响应时间都更好
2. 测试存在随机性，wrk使用16个线程时，经过多次测试发现四种GC在吞吐量大致一样，怀疑应用程序没有足够的cpu进行gc, 所以使用4个线程进行压测
3. 并行GC比串行GC的吞吐量高，但延时也高，并行GC和CMS GC、G1 GC性能相当
4. 吞吐量越高，延时也越高，G1 GC能在较高的吞吐量下还能保持较低的延迟
5. G1 GC设置了MaxGCPauseMillis吞吐量不会降低太多，但延迟降低了非常多


## 3、写一段代码，使用HttpClient或OkHttp访问http://localhost:8801，代码提交到Github。

```
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author songjiyang
 */
public class HttpClientExample {
    public static void main(String[] args) throws IOException {

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:8801");
        HttpResponse response = httpClient.execute(get);

        HttpEntity entity = response.getEntity();
        // 通过EntityUtils中的toString方法将结果转换为字符串
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }
}
```