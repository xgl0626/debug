package com.example.d2doctor.utils

import com.example.d2doctor.bean.Feature

/**
 * @date 2021-04-09
 * @author Sca RayleighZ
 * Describe: 生成伪数据(Fake News)
 */

fun provideFakeData(): List<Feature> =
    listOf(
        Feature(
            nickName = "RayJoe",
            content = "RecyclerView dataList并发修改时如何通过线程锁控制线性执行，Kotlin是一種在Java虛擬機上執行的靜態型別程式語言，它也可以被編譯成為JavaScript原始碼。它主要是由俄羅斯聖彼得堡的JetBrains開發團隊所發展出來的"
        ),
        Feature(
            nickName = "SpreadWater",
            content = "Handler无参构造方法导致程序崩溃，Swift程式語言，支援多編程範式和編譯式，用來撰寫基於macOS/OS X、iOS、iPadOS、watchOS和tvOS的軟體。 蘋果公司於2014年在蘋果開發者年會發布了Swift程式語言。"
        ),
        Feature(
            nickName = "xgl",
            content = "Android 自定义View的相关问题，苹果于2014年WWDC苹果开发者大会发布的新开发语言，可与Objective-C共同运行于macOS和iOS平台，用于搭建基于苹果平台的应用程序。Swift是一款 ..."
        )

    )