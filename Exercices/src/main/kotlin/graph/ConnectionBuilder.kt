package graph

class ConnectionBuilder(private val cost: Double, private val connections: MutableList<Connection>) {
    infix fun to(node: Node): Node {
        connections.add(Connection(node, cost))
        return node
    }
}

