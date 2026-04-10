# Overview

This project demonstrates core Kotlin language concepts through a practical console-based task management application. As a software engineer, my goal is to deepen my understanding of Kotlin's modern features—including data classes, immutability patterns, collection operations, higher-order functions, and control flow expressions—while building a fully functional application that solves a real-world problem.

The Task Manager is a command-line utility that allows users to create, manage, and organize tasks with priority levels. The application showcases several key Kotlin features: **data classes** for representing immutable task objects, **mutable and immutable collections** for storing and manipulating tasks, **scope functions** for filtering and searching, and **string formatting** with ANSI color codes for an enhanced terminal user experience. The priority-based sorting and status tracking demonstrate practical application of Kotlin's expressive syntax.

This project serves as a learning vehicle for understanding how Kotlin's type system, functional programming paradigms, and standard library empower developers to write cleaner, more maintainable code compared to Java while maintaining strong interoperability with the Java ecosystem.

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

**IDE & Tools:**
- IntelliJ IDEA Community Edition (or any Kotlin-compatible IDE such as Android Studio)
- Kotlin Compiler (kotlinc)
- Git for version control

**Programming Language & Libraries:**
- **Kotlin 1.8+** — A modern, statically-typed language running on the JVM with first-class support for functional programming
- **Kotlin Standard Library (stdlib)** — Provides built-in collection types (MutableList, mutableListOf), extension functions (count, sortedBy, find), and string utilities
- **Kotlin Data Classes** — Auto-generated equals(), hashCode(), copy(), and toString() methods for clean, concise object representation
- **No external dependencies** — The application uses only Kotlin's standard library and built-in console I/O functions

The project demonstrates core Kotlin constructs without requiring external frameworks, making it lightweight and portable.

# Useful Websites

- [Kotlin Official Documentation](https://kotlinlang.org/docs/home.html) — Comprehensive guide to Kotlin syntax, standard library, and best practices
- [Kotlin Standard Library Reference](https://kotlinlang.org/api/latest/jvm/stdlib/) — Complete API documentation for stdlib functions and collections
- [Data Classes in Kotlin](https://kotlinlang.org/docs/data-classes.html) — Deep dive into immutable data modeling patterns
- [Collections in Kotlin](https://kotlinlang.org/docs/collections-overview.html) — Overview of mutable and immutable collection types, transformations, and operations
- [Kotlin Control Flow](https://kotlinlang.org/docs/control-flow.html) — Guide to when expressions, loops, and other control structures
- [JetBrains Kotlin by Example](https://play.kotlinlang.org/byExample/overview) — Interactive Kotlin examples and sandbox for experimentation

# Future Work

- **Data Persistence:** Implement file-based or database storage (SQLite/Room) to persist tasks between sessions
- **Due Dates & Reminders:** Add task deadline fields and notification system for upcoming or overdue tasks
- **Task Categories/Tags:** Allow users to organize tasks by category or multiple tags for better filtering and organization
- **Search & Filter Enhancement:** Implement advanced filtering options (by priority, completion status, date range, or keyword search)
- **Recurring Tasks:** Support for recurring tasks (daily, weekly, monthly) with automatic generation
- **User Authentication:** Add multi-user support with login and personal task lists
- **GUI Desktop Application:** Port the application to a Kotlin/Java desktop framework (Compose Desktop, JavaFX) for improved usability
- **Mobile App:** Develop a mobile version using Kotlin Multiplatform Mobile (KMM) to share business logic across platforms
- **Performance Optimization:** Implement indexing and lazy loading for applications managing large task sets
- **Unit Tests:** Add comprehensive unit tests using JUnit and Kotest to ensure reliability and maintainability
