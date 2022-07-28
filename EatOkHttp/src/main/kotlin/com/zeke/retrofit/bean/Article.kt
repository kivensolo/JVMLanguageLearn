package com.zeke.retrofit.bean

class Article {
    /**
     * apkLink :
     * audit : 1
     * author :
     * canEdit : false
     * chapterId : 502
     * chapterName : 自助
     * collect : false
     * courseId : 13
     * desc :
     * descMd :
     * envelopePic :
     * fresh : false
     * id : 14755
     * link : https://juejin.im/post/6857457525764620302
     * niceDate : 1天前
     * niceShareDate : 1天前
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1597374671000
     * realSuperChapterId : 493
     * selfVisible : 0
     * shareDate : 1597374671000
     * shareUser : 飞洋
     * superChapterId : 494
     * superChapterName : 广场Tab
     * tags : []
     * title : 🔥Android进阶基础系列：Window和WindowManager ，全面理解！
     * type : 0
     * userId : 31360
     * visible : 1
     * zan : 0
     */
    val contentType: String = "artical"
    var apkLink: String? = null
    var audit = 0
    var author: String? = null
    var canEdit = false
    var chapterId = 0
    var chapterName: String? = null
    var collect = false
    var courseId = 0
    var desc: String? = null
    var descMd: String? = null
    var envelopePic: String? = null
    var isFresh = false
    var id = 0
    var link: String? = null
    var niceDate: String? = null
    var niceShareDate: String? = null
    var origin: String? = null
    var prefix: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var realSuperChapterId = 0
    var selfVisible = 0
    var shareDate: Long = 0
    var shareUser: String? = null
    var superChapterId = 0
    var superChapterName: String? = null
    var title: String? = null
    var type = 0
    var userId = 0
    var visible = 0
    var zan = 0
    var tags: List<*>? = null
    var isTop = false
    var originId = 0

//    override fun toString(): String {
//        return "Article(contentType='$contentType', apkLink=$apkLink, audit=$audit, author=$author, canEdit=$canEdit, chapterId=$chapterId, chapterName=$chapterName, collect=$collect, courseId=$courseId, desc=$desc, descMd=$descMd, envelopePic=$envelopePic, isFresh=$isFresh, id=$id, link=$link, niceDate=$niceDate, niceShareDate=$niceShareDate, origin=$origin, prefix=$prefix, projectLink=$projectLink, publishTime=$publishTime, realSuperChapterId=$realSuperChapterId, selfVisible=$selfVisible, shareDate=$shareDate, shareUser=$shareUser, superChapterId=$superChapterId, superChapterName=$superChapterName, title=$title, type=$type, userId=$userId, visible=$visible, zan=$zan, tags=$tags, isTop=$isTop, originId=$originId)"
//    }
}