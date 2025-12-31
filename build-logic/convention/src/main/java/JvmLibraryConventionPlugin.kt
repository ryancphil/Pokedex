import com.ryanphillips.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This Convention Plugin is for configuring Jvm Library Modules.
 */
class JvmLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}