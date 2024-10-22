package Mock2;

import java.util.*;
import java.util.function.Consumer;

public class MyStream<T> {

    // Source collection
    private final Collection<T> source;

    // List of intermediate operations
    private final List<Operation<?, ?>> operations = new ArrayList<>();

    // Private constructor to enforce use of 'of' method
    private MyStream(Collection<T> source) {
        this.source = source;
    }

    // Static factory method to create a stream from a collection
    public static <T> MyStream<T> of(Collection<T> collection) {
        return new MyStream<>(collection);
    }

    // Adds a filter operation to the pipeline
    public MyStream<T> filter(Predicate<? super T> predicate) {
        operations.add(new FilterOperation<>(predicate));
        return this;
    }

    // Adds a map operation to the pipeline
    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        MyStream<R> newStream = new MyStream<>(new ArrayList<>());
        newStream.operations.addAll(this.operations);
        newStream.operations.add(new MapOperation<>(mapper));
        return newStream;
    }

    // Terminal operation to collect the results
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        A container = collector.supplier();
        for (T element : source) {
            Object current = element;
            boolean shouldInclude = true;
            for (Operation<?, ?> op : operations) {
                if (op instanceof FilterOperation) {
                    @SuppressWarnings("unchecked")
                    FilterOperation<Object> filterOp = (FilterOperation<Object>) op;
                    shouldInclude = filterOp.getPredicate().test(current);
                    if (!shouldInclude) {
                        break;
                    }
                } else if (op instanceof MapOperation) {
                    @SuppressWarnings("unchecked")
                    MapOperation<Object, Object> mapOp = (MapOperation<Object, Object>) op;
                    current = mapOp.getMapper().apply(current);
                }
            }
            if (shouldInclude) {
                @SuppressWarnings("unchecked")
                T castedCurrent = (T) current;
                collector.accumulator(container, castedCurrent);
            }
        }
        return collector.finisher().apply(container);
    }

    // Functional Interfaces

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    @FunctionalInterface
    public interface Collector<T, A, R> {
        A supplier();

        void accumulator(A a, T t);

        R finisher();
    }

    // Abstract Operation class
    private static abstract class Operation<I, O> {
        abstract void apply(Object input, Object output);
    }

    // FilterOperation class
    private static class FilterOperation<T> extends Operation<T, Boolean> {
        private final Predicate<? super T> predicate;

        public FilterOperation(Predicate<? super T> predicate) {
            this.predicate = predicate;
        }

        public Predicate<? super T> getPredicate() {
            return predicate;
        }

        @Override
        void apply(Object input, Object output) {
            // Not used in this simplified implementation
        }
    }

    // MapOperation class
    private static class MapOperation<I, O> extends Operation<I, O> {
        private final Function<? super I, ? extends O> mapper;

        public MapOperation(Function<? super I, ? extends O> mapper) {
            this.mapper = mapper;
        }

        public Function<? super I, ? extends O> getMapper() {
            return mapper;
        }

        @Override
        void apply(Object input, Object output) {
            // Not used in this simplified implementation
        }
    }

    // Sample Usage of MyStream
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        // Create a stream from the list
        MyStream<String> stream = MyStream.of(words);

        // Apply filter and map operations
        MyStream<String> filteredStream = stream.filter(word -> word.length() > 5).map(String::toUpperCase);

        // Collect the results into a new list
        List<String> result = filteredStream.collect(new Collector<List<String>, List<String>, List<String>>() {
            @Override
            public List<String> supplier() {
                return new ArrayList<>();
            }

            @Override
            public void accumulator(List<String> container, String element) {
                container.add(element);
            }

            @Override
            public List<String> finisher() {
                return container -> container;
            }
        });

        // Output the result
        System.out.println(result); // Output: [BANANA, CHERRY, ELDERBERRY]
    }
}
