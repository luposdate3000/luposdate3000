#./launcher.main.kts --run --mainClass=Endpoint --endpointMode=Java_Sockets --inlineMode=Enable --releaseMode=Enable --dryMode=Enable
rm config.pro
for f in $(./launcher.main.kts --run --mainClass=Endpoint --endpointMode=Java_Sockets --inlineMode=Enable --releaseMode=Enable --dryMode=Enable | tr ":" "\n" | tr " " "\n" | sort | grep "^build-cache.*\.jar$")
do
echo "-injars ./$f" >> config.pro
done
for f in $(./launcher.main.kts --run --mainClass=Endpoint --endpointMode=Java_Sockets --inlineMode=Enable --releaseMode=Enable --dryMode=Enable | tr ":" "\n" | tr " " "\n" | sort | grep "^/root.*\.jar$")
do
echo "-libraryjars $f" >> config.pro
done
echo "-outjars find-leak.jar" >> config.pro
echo '-libraryjars /usr/lib/jvm/java-1.11.0-openjdk-amd64/jmods/java.base.jmod(!**.jar;!module-info.class)' >>config.pro
cat <<EOT >> config.pro
-forceprocessing
-optimizationpasses 5
-allowaccessmodification
-dontobfuscate
-printmapping find-leak.mapping.txt
-printconfiguration find-leak.config.pro.generated
-printusage find-leak.usage.txt
-keep public class lupos.endpoint.LuposdateEndpoint{
    public String importIntermediateFiles(String);
}
-ignorewarnings
EOT
/usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java -jar /usr/share/java/proguard.jar @config.pro > find-leak.log 2>&1
