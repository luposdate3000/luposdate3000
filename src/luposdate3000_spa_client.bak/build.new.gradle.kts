
buildscript {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}
evaluationDependsOn(":src:luposdate3000_shared")
evaluationDependsOn(":src:luposdate3000_parser")
evaluationDependsOn(":src:luposdate3000_buffer_manager_inmemory")
evaluationDependsOn(":src:luposdate3000_dictionary")
evaluationDependsOn(":src:luposdate3000_operator_arithmetik")
evaluationDependsOn(":src:luposdate3000_operator_base")
evaluationDependsOn(":src:luposdate3000_operator_factory")
evaluationDependsOn(":src:luposdate3000_optimizer_ast")
evaluationDependsOn(":src:luposdate3000_optimizer_logical")
evaluationDependsOn(":src:luposdate3000_optimizer_physical")
evaluationDependsOn(":src:luposdate3000_result_format")
evaluationDependsOn(":src:luposdate3000_triple_store_manager")
evaluationDependsOn(":src:luposdate3000_operator_physical")
evaluationDependsOn(":src:luposdate3000_jena_wrapper_off")
evaluationDependsOn(":src:luposdate3000_test_buffermanager")
evaluationDependsOn(":src:luposdate3000_kv")
evaluationDependsOn(":src:luposdate3000_endpoint")
evaluationDependsOn(":src:luposdate3000_vk")
evaluationDependsOn(":src:luposdate3000_operator_logical")
evaluationDependsOn(":src:luposdate3000_triple_store_id_triple")
evaluationDependsOn(":src:luposdate3000_shared_js_browser")
plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
    id("org.jetbrains.kotlin.multiplatform") version "1.8.0"
}
repositories {
    mavenLocal()
    google()
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
}
group = "luposdate3000"
version = "0.0.1"
apply(plugin = "maven-publish")
kotlin {
    explicitApi()
    metadata {
        compilations.forEach {
            it.kotlinOptions {
                freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
                freeCompilerArgs += "-Xnew-inference"
                freeCompilerArgs += "-Xinline-classes"
            }
        }
    }
    js(IR) {
        moduleName = "Luposdate3000_Endpoint"
        browser {
            compilations.forEach {
                it.kotlinOptions {
                    freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
                    freeCompilerArgs += "-Xnew-inference"
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.ionspin.kotlin:bignum:0.3.3")
                implementation(project(":src:luposdate3000_shared"))
                implementation(project(":src:luposdate3000_parser"))
                implementation(project(":src:luposdate3000_buffer_manager_inmemory"))
                implementation(project(":src:luposdate3000_dictionary"))
                implementation(project(":src:luposdate3000_operator_arithmetik"))
                implementation(project(":src:luposdate3000_operator_base"))
                implementation(project(":src:luposdate3000_operator_factory"))
                implementation(project(":src:luposdate3000_optimizer_ast"))
                implementation(project(":src:luposdate3000_optimizer_logical"))
                implementation(project(":src:luposdate3000_optimizer_physical"))
                implementation(project(":src:luposdate3000_result_format"))
                implementation(project(":src:luposdate3000_triple_store_manager"))
                implementation(project(":src:luposdate3000_operator_physical"))
                implementation(project(":src:luposdate3000_jena_wrapper_off"))
                implementation(project(":src:luposdate3000_test_buffermanager"))
                implementation(project(":src:luposdate3000_kv"))
                implementation(project(":src:luposdate3000_vk"))
                implementation(project(":src:luposdate3000_endpoint"))
                implementation(project(":src:luposdate3000_operator_logical"))
                implementation(project(":src:luposdate3000_triple_store_id_triple"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("tone", "14.7.77"))
                implementation(npm("nexusui", "2.1.6"))
                implementation(npm("jquery", "3.7.0"))
                implementation(npm("please-wait", "0.0.5"))
                implementation(npm("randomcolor", "0.6.2"))
                implementation(npm("uri.js", "0.1.3"))
                implementation(npm("spectrum", "1.0.1"))
                implementation(npm("modernizr", "3.12.0"))
                implementation(npm("fastclick", "1.0.6"))
                implementation(npm("underscore", "1.13.6"))
                implementation(npm("backbone", "1.4.1"))
                implementation(npm("codemirror", "6.0.1"))
                implementation(npm("@fontsource/source-sans-pro", "5.0.5"))
                implementation(npm("foundation", "4.2.1-1"))
                implementation(npm("x2js", "3.4.4"))

                implementation(project(":src:luposdate3000_shared_js_browser"))
            }
        }
    }
    sourceSets["commonMain"].kotlin.srcDir("/src/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_endpoint/src/commonMain/kotlin")
    sourceSets["commonMain"].resources.srcDir("/src/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_endpoint/src/commonMain/resources")
    sourceSets["jsMain"].kotlin.srcDir("/src/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_endpoint/src/jsMain/kotlin")
    sourceSets["jsMain"].resources.srcDir("/src/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_endpoint/src/jsMain/resources")
}
tasks.register("luposSetup") {
    fun fixPathNames(s: String): String {
        var res = s.trim()
        var back = ""
        while (back != res) {
            back = res
            res = res.replace("\\", "/").replace("/./", "/").replace("//", "/")
        }
        return res
    }
    val regexDisableNoInline = "(^|[^a-zA-Z])noinline ".toRegex()
    val regexDisableInline = "(^|[^a-zA-Z])inline ".toRegex()
    val regexDisableCrossInline = "(^|[^a-zA-Z])crossinline ".toRegex()
    for (bp in listOf(File(buildDir.parentFile, "/src"), File(rootDir, "src/luposdate3000_shared_inline/src"))) {
        for (it in bp.walk()) {
            val tmp = it.toString()
            val ff = File(tmp)
            if (ff.isFile && ff.name.endsWith(".kt")) {
                File(ff.absolutePath + ".tmp").printWriter().use { out ->
                    var line = 0
                    ff.forEachLine { line2 ->
                        var s = line2
                        s = s.replace("SOURCE_FILE_START.*SOURCE_FILE_END".toRegex(), "SOURCE_FILE_START*/\"${fixPathNames(ff.absolutePath)}:$line\"/*SOURCE_FILE_END")
                        s = s.replace("/*NOINLINE*/", "noinline ")
                        s = s.replace("/*CROSSINLINE*/", "crossinline ")
                        s = s.replace("/*INLINE*/", "inline ")
                        out.println(s)
                        line++
                    }
                }
                File(ff.absolutePath + ".tmp").copyTo(ff, true)
                File(ff.absolutePath + ".tmp").delete()
            }
        }
    }
}
configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    enableExperimentalRules.set(true)
    ignoreFailures.set(true)
    filter {
        exclude("**/build/**")
    }
}
