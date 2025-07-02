-dontwarn okhttp3.internal.graal.**
-dontwarn okhttp3.internal.platform.**
-dontwarn com.oracle.svm.**
-dontwarn org.graalvm.**
-dontwarn android.os.Build$VERSION
-dontnote okhttp3.internal.platform.android.AndroidSocketAdapter

# Keep kotlinx.datetime.Instant and related metadata
-keep class kotlinx.datetime.** { *; }

# Keep Kotlinx Serialization data models
-keep class com.jonathansteele.news.News { *; }
-keep class com.jonathansteele.news.News$Article { *; }