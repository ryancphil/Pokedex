import com.android.build.gradle.LibraryExtension
import com.ryanphillips.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * This Convention Plugin is for configuring Android Compose Library Modules.
 * This plugin just combines our Android Library Convention Plugin
 * with our Android Compose extension.
 */
class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("pokedex.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}