## 1) How do you decide what data structure should be used for different parts of a component?

In Java, I decide on data structures by evaluating the expected usage patterns, constraints, and performance needs:

- **Access pattern & complexity**: If I need constant-time lookups, I generally use a `HashMap`. If I need sorted traversal, I use `TreeMap` or `PriorityQueue`.
- **Concurrency requirements**: For multi-threaded scenarios, I may choose a `ConcurrentHashMap` or `ConcurrentLinkedQueue`.
- **Insertion/deletion patterns**: If frequent random inserts/deletes are expected, a `LinkedList` or `ConcurrentLinkedDeque` might be more efficient than array-based structures.
- **Memory considerations**: Depending on object size and the frequency of GC, I may opt for simpler structures (like arrays) or specialized structures (e.g., off-heap solutions) to reduce overhead.

Overall, I evaluate time complexity, memory footprint, and concurrency needs before selecting or implementing a data structure.

---

## 2) Have you designed custom data structures such as a hash map or priority queue? If so, describe the problem you were trying to solve and the solution, highlighting design considerations and trade-offs.

Yes, I once designed a custom in-memory index for a high-performance search feature. The goal was to achieve:

- **Ultra-fast lookups** on structured data keys.
- **Concurrent updates** without locking the entire data structure.

### Solution

- Implemented a segmented, lock-free indexing structure (similar to a `ConcurrentHashMap` but specialized for our data model).
- Used atomic operations (via `java.util.concurrent.atomic` classes) to reduce contention.
- Maintained an internal balanced tree for partial-key searches and a hash-based approach for exact matches.

### Design Considerations / Trade-Offs

- **Memory Usage**: Storing partial-index trees took more memory than a simpler map-based solution.
- **Throughput vs. Complexity**: Lock-free or fine-grained locking solutions are more complex to implement and debug, but they significantly improved throughput in our use case.
- **Consistency**: Needed to balance eventual consistency in some parts of the index to handle extremely high update rates (i.e., we allowed slight staleness in search results instead of blocking all readers during updates).

---

## 3) Have you designed or developed a system that requires handling high throughput (1000’s of events/updates per second) with low latency (<1 msec per event) with data from multiple sources and potentially with different data formats/messaging protocols? If so, describe the architecture with emphasis on performance considerations.

Yes, I worked on a real-time analytics system ingesting thousands of events per second from multiple data sources (REST, gRPC, and messaging platforms such as Kafka).

### Architecture Overview

1. **Message Ingestion Layer (Kafka / HTTP / gRPC)**
    - Leveraged Kafka for the majority of high-volume data streams.
    - Used non-blocking I/O in Java (Netty) for HTTP and gRPC endpoints to handle large concurrent connections.

2. **Stream Processing / Aggregation**
    - Deployed a microservice that uses Java’s parallel streams and RxJava/Project Reactor for event processing pipelines.
    - Applied backpressure and partitioning to maintain sub-millisecond latencies.

3. **In-Memory Caching / Fast Access**
    - Utilized a Redis cluster for quick aggregation lookups; critical counters were also stored in local in-memory caches (e.g., `ConcurrentHashMap`) for sub-millisecond access.

4. **Data Persistence**
    - Batched writes to an OLAP store (e.g., Apache Cassandra or ClickHouse) using asynchronous I/O.

5. **Performance Considerations**
    - **Low Latency**: Tuned JVM (GC settings, off-heap buffers) and used non-blocking frameworks to minimize context switching.
    - **High Throughput**: Horizontal scaling with container orchestration (Kubernetes); each microservice replica handles partial load.
    - **Monitoring & Profiling**: Employed tools like Java Flight Recorder (JFR) and Micrometer to identify bottlenecks and optimize.

