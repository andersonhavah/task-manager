/**
 * ============================================================
 *  Task Manager in Kotlin
 *
 *  Author : Anderson Havah
 *  ─────────────────────────────────────────────────────────
 *  ✔ val  — immutable variables (task fields, constants)
 *  ✔ var  — mutable variables (nextId counter, menu loop flag)
 *  ✔ Expressions    — when expressions, string templates, if/else
 *  ✔ Conditionals   — if/else for validation; when for menu dispatch
 *  ✔ Loops          — while (menu loop), for (list display)
 *  ✔ Functions      — standalone and member functions throughout
 *  ✔ Classes        — TaskManager class encapsulates all logic
 *  ✔ Data class     — Task data class with auto equals/copy/toString
 *  ✔ Collections (create)   — mutableListOf<Task>()
 *  ✔ Collections (modify)   — add(), removeIf(), index assignment
 * ============================================================
 */

// ── Data Class ────────────────────────────────────────────────────────────────

/**
 * Represents a single task.
 * A data class auto-generates equals(), hashCode(), copy(), and toString().
 * All properties use val (immutable) — "edits" produce a new copy via copy().
 */
data class Task(
    val id          : Int,
    val title       : String,
    val priority    : String  = "Medium",   // Low | Medium | High
    val isCompleted : Boolean = false
)

// ── TaskManager Class ─────────────────────────────────────────────────────────

/**
 * Manages the task collection and exposes add / remove / list operations.
 * Demonstrates class definition, member functions, and collection usage.
 */
class TaskManager {

    // Collection creation — MutableList<Task> starts empty
    private val tasks: MutableList<Task> = mutableListOf()
    private var nextId: Int = 1   // var — mutable, increments with every new task

    // ── Add ──────────────────────────────────────────────────────────────────

    /**
     * Validates inputs, builds a Task, and appends it to the list.
     * Returns a result message string.
     */
    fun addTask(title: String, priority: String): String {
        val trimmedTitle = title.trim()   // val — result of trim never changes

        if (trimmedTitle.isBlank()) {
            return "Error: Title cannot be empty."
        }

        val validPriorities = listOf("Low", "Medium", "High")
        val chosenPriority  = validPriorities.find {
            it.equals(priority.trim(), ignoreCase = true)
        } ?: return "Error: Priority must be Low, Medium, or High."

        // Collection modification — add() appends to the MutableList
        val newTask = Task(id = nextId, title = trimmedTitle, priority = chosenPriority)
        tasks.add(newTask)
        nextId++   // mutable counter incremented

        return "Task #${newTask.id} \"${newTask.title}\" added (priority: ${newTask.priority})."
    }

    // ── Remove ───────────────────────────────────────────────────────────────

    /**
     * Removes the task with the given id.
     * Demonstrates removeIf() for collection modification.
     */
    fun removeTask(id: Int): String {
        val target = tasks.find { it.id == id }
            ?: return "Error: No task found with ID $id."

        tasks.removeIf { it.id == id }   // collection modification
        return "Task #${target.id} \"${target.title}\" removed."
    }

    // ── Mark Complete ─────────────────────────────────────────────────────────

    /**
     * Marks a task as completed using data class copy().
     * copy() produces a new Task with only isCompleted changed,
     * which replaces the old entry in the list (index assignment).
     */
    fun markComplete(id: Int): String {
        val index = tasks.indexOfFirst { it.id == id }
        if (index == -1) return "Error: No task found with ID $id."

        tasks[index] = tasks[index].copy(isCompleted = true)   // collection modification
        return "Task #${tasks[index].id} \"${tasks[index].title}\" marked as complete."
    }

    // ── List ─────────────────────────────────────────────────────────────────

    /**
     * Prints all tasks sorted by priority (High → Medium → Low).
     * Demonstrates a for loop and when/if conditional expressions.
     */
    fun listTasks() {
        if (tasks.isEmpty()) {
            println("  No tasks yet. Add one with option 1.")
            return
        }

        // Immutable priority-order map used for sorting
        val priorityOrder = mapOf("High" to 0, "Medium" to 1, "Low" to 2)
        val sorted = tasks.sortedBy { priorityOrder[it.priority] ?: 99 }

        // Table header
        println()
        println("  ${"ID".padEnd(5)} ${"TITLE".padEnd(28)} ${"PRIORITY".padEnd(10)} STATUS")
        println("  ${"─".repeat(58)}")

        // for loop — iterate every task and print a formatted row
        for (task in sorted) {
            val idCell    = "#${task.id}".padEnd(5)
            val titleCell = task.title.take(27).padEnd(28)
            val priCell   = task.priority.padEnd(10)
            val status    = if (task.isCompleted) "Done" else "Pending"

            // when expression picks the colour based on priority
            val priorityColored = when (task.priority) {
                "High"   -> priCell.colored(RED)
                "Medium" -> priCell.colored(YELLOW)
                else     -> priCell.colored(GREEN)
            }
            val statusColored = if (task.isCompleted) status.colored(GREEN) else status

            println("  $idCell $titleCell $priorityColored $statusColored")
        }

        // String template summarising task counts
        println()
        val done    = tasks.count { it.isCompleted }
        val pending = tasks.count { !it.isCompleted }
        println("  Total: ${tasks.size}  |  Pending: $pending  |  Completed: $done")
    }

    fun taskCount(): Int = tasks.size
}

// ── ANSI colour helpers ───────────────────────────────────────────────────────

const val RED    = "\u001B[31m"
const val GREEN  = "\u001B[32m"
const val YELLOW = "\u001B[33m"
const val CYAN   = "\u001B[36m"
const val BOLD   = "\u001B[1m"
const val RESET  = "\u001B[0m"

fun String.colored(code: String) = "$code$this$RESET"
fun String.bold()                = "$BOLD$this$RESET"

// ── Menu helper functions ─────────────────────────────────────────────────────

/** Prints the main menu. */
fun printMenu() {
    println()
    println("  ${"═".repeat(34)}".colored(CYAN))
    println("  ${"  📋  KOTLIN TASK MANAGER".padEnd(34)}".colored(CYAN))
    println("  ${"═".repeat(34)}".colored(CYAN))
    println("  [1]  Add a task")
    println("  [2]  Remove a task")
    println("  [3]  Mark task as complete")
    println("  [4]  List all tasks")
    println("  [0]  Exit")
    println("  ${"─".repeat(34)}".colored(CYAN))
}

/** Prints [prompt] and returns the trimmed user input. */
fun input(prompt: String): String {
    print(prompt)
    return readLine()?.trim() ?: ""
}

// ── Entry Point ───────────────────────────────────────────────────────────────

fun main() {
    val manager = TaskManager()   // val — reference to the manager never changes

    println("\n  Welcome to the Kotlin Task Manager!".bold())

    var running = true   // var — flipped to false when the user chooses to exit

    while (running) {
        printMenu()
        val choice = input("  Enter choice: ")

        // when expression dispatches on the user's menu choice
        when (choice) {

            "1" -> {
                println("\n  ── Add Task ────────────────────".colored(CYAN))
                val title    = input("  Title    : ")
                val priority = input("  Priority [Low / Medium / High]: ")
                println("\n  " + manager.addTask(title, priority))
            }

            "2" -> {
                println("\n  ── Remove Task ─────────────────".colored(CYAN))
                manager.listTasks()
                val idInput = input("\n  Enter task ID to remove: ")
                val id      = idInput.toIntOrNull()
                val result  = if (id != null) manager.removeTask(id)
                else            "Error: Please enter a valid number."
                println("\n  $result")
            }

            "3" -> {
                println("\n  ── Mark Complete ───────────────".colored(CYAN))
                manager.listTasks()
                val idInput = input("\n  Enter task ID to mark complete: ")
                val id      = idInput.toIntOrNull()
                val result  = if (id != null) manager.markComplete(id)
                else            "Error: Please enter a valid number."
                println("\n  $result")
            }

            "4" -> {
                println("\n  ── All Tasks ───────────────────".colored(CYAN))
                manager.listTasks()
            }

            "0" -> {
                println("\n  Goodbye! 👋\n".bold())
                running = false   // mutable var set to false — exits the while loop
            }

            else -> println("\n  Invalid choice. Enter 0 – 4.".colored(RED))
        }

        // Pause after every action except exit so output stays readable
        if (running) {
            input("\n  Press Enter to continue...")
        }
    }
}