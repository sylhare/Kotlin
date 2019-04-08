package graph

import main.kotlin.graph.Node

class ConnexionBuilder(val cost: Double, private val connexions: MutableList<Connexion>) {
    infix fun to(node: Node): Node {
        connexions.add(Connexion(node, cost))
        return node
    }
}

