rm -rf src/luposdate3000_coverage_merged
mkdir src/luposdate3000_coverage_merged
for f in $(ls src \
	| grep -v machinelearning \
	| grep -v luposdate3000_scripting \
	| grep -v luposdate3000_spa_client \
	| grep -v luposdate3000_launch \
	| grep -v luposdate3000_coverage_merged \
	| grep -v luposdate3000_buffer_manager_persistent \
	| grep -v luposdate3000_endpoint_launcher_none \
	| grep -v luposdate3000_jena_wrapper_off \
	)
do
echo $f
cp -r src/$f/* src/luposdate3000_coverage_merged
done
cat src/*/module_config | sort | uniq | grep dependency > src/luposdate3000_coverage_merged/module_config
./launcher.main.kts --setup --intellijMode=Disable
cat src/luposdate3000_coverage_merged/build.gradle.kts \
	| grep -v evaluationDependsOn \
	| grep -v "implementation.project" \
	| grep -v srcDir.*luposdate3000_shared_inline \
	> src/luposdate3000_coverage_merged/build.gradle.kts2
mv src/luposdate3000_coverage_merged/build.gradle.kts2 src/luposdate3000_coverage_merged/build.gradle.kts

cd src/luposdate3000_coverage_merged
grep -rl "\*suspend\*" --include *.kt | xargs sed -i "s-/\*suspend\* /--g" $f
grep -rl "\*suspend\*" --include *.kt | xargs sed -i "s-/\*suspend\*/--g" $f
find -name "*.kt" | xargs sed -i "s/public abstract class/internal abstract class/g"
find -name "*.kt" | xargs sed -i "s/public  abstract fun/internal abstract fun/g"
find -name "*.kt" | xargs sed -i "s/public abstract  fun/internal abstract fun/g"
find -name "*.kt" | xargs sed -i "s/public abstract fun/internal abstract fun/g"
find -name "*.kt" | xargs sed -i "s/public abstract val/internal abstract val/g"
find -name "*.kt" | xargs sed -i "s/public actual class/internal actual class/g"
find -name "*.kt" | xargs sed -i "s/public actual  fun/internal actual fun/g"
find -name "*.kt" | xargs sed -i "s/public actual fun/internal actual fun/g"
find -name "*.kt" | xargs sed -i "s/public actual inline fun/internal actual inline fun/g"
find -name "*.kt" | xargs sed -i "s/public actual object/internal actual object/g"
find -name "*.kt" | xargs sed -i "s/public actual override fun/internal actual override fun/g"
find -name "*.kt" | xargs sed -i "s/public annotation class/internal annotation class/g"
find -name "*.kt" | xargs sed -i "s/public class/internal class/g"
find -name "*.kt" | xargs sed -i "s/public companion object/internal companion object/g"
find -name "*.kt" | xargs sed -i "s/public constructor/internal constructor/g"
find -name "*.kt" | xargs sed -i "s/public const val/internal const val/g"
find -name "*.kt" | xargs sed -i "s/public data class/internal data class/g"
find -name "*.kt" | xargs sed -i "s/public enum class/internal enum class/g"
find -name "*.kt" | xargs sed -i "s/public expect class/internal expect class/g"
find -name "*.kt" | xargs sed -i "s/public expect object/internal expect object/g"
find -name "*.kt" | xargs sed -i "s/public external class/internal external class/g"
find -name "*.kt" | xargs sed -i "s/public external fun/internal external fun/g"
find -name "*.kt" | xargs sed -i "s/public  fun/internal fun/g"
find -name "*.kt" | xargs sed -i "s/public fun/internal fun/g"
find -name "*.kt" | xargs sed -i "s/public inline fun/internal inline fun/g"
find -name "*.kt" | xargs sed -i "s/public inline operator fun/internal operator fun/g"
find -name "*.kt" | xargs sed -i "s/public interface/internal interface/g"
find -name "*.kt" | xargs sed -i "s/public lateinit var/internal lateinit var/g"
find -name "*.kt" | xargs sed -i "s/public object/internal object/g"
find -name "*.kt" | xargs sed -i "s/public open class/internal open class/g"
find -name "*.kt" | xargs sed -i "s/public open  fun/internal open fun/g"
find -name "*.kt" | xargs sed -i "s/public open fun/internal open fun/g"
find -name "*.kt" | xargs sed -i "s/public  operator fun/internal operator fun/g"
find -name "*.kt" | xargs sed -i "s/public operator fun/internal operator fun/g"
find -name "*.kt" | xargs sed -i "s/public  override fun/internal override fun/g"
find -name "*.kt" | xargs sed -i "s/public override fun/internal override fun/g"
find -name "*.kt" | xargs sed -i "s/public typealias/internal typealias/g"
find -name "*.kt" | xargs sed -i "s/public val/internal val/g"
find -name "*.kt" | xargs sed -i "s/public var/internal var/g"
cd ../..
#./gradlew :src:luposdate3000_coverage_merged:assemble
./gradlew :src:luposdate3000_coverage_merged:koverReport
