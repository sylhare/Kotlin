package examples

class QueueTest {

    enum class Status { Failed, Succeeded }
    data class Job(val id: String, var status: Status)

    class Client {
        fun send(job: Job): Unit = println("sent $job")
    }

    class QueueHandler {
        private val client = Client()
        fun handle(job: Job) {
            job.also { it.status = Status.Failed }
            client.send(job)
        }
    }
}
