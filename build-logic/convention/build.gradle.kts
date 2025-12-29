plugins {
    `kotlin-dsl`
}
group = "com.ryanphillips.pokedex.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

// TODO: Register all Convention Plugins.
//gradlePlugin {
//    plugins {
//        register() {
//            id = ""
//            implementationClass = ""
//        }
//
//    }
//}