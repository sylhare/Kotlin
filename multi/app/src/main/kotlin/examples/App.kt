package examples

import examples.Core.Companion.world

class App {

    companion object {
        fun hello() = "hello ${world()}"
    }
}