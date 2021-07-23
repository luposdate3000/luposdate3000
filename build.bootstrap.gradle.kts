import java.io.File
fun readConfig():MutableMap<String,String>{
var res=mutableMapOf<String,String>()
File("build.config").forEachLine{line->
val a=line.split("=")
if(a.size==2){
res[a[0]]=a[1]
}
}
return res
}
fun writeConfig(cfg:MutableMap<String,String>){
File("build.config").printWriter().use{out->
for((k,v) in cfg){
out.println("$k=$v")
}
}
}


tasks.register("configure-target-JVM") {
val cfg=readConfig()
cfg["target"]="JVM"
writeConfig(cfg)
}
