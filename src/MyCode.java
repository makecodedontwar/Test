import java.util.Arrays;

public class MyCode {
    public static void main(String[] args) {
        System.out.println(intersection(new int[]{1, 2, 3}, new int[]{2, 3, 4})); // toString() у массива не определён

        Arrays.stream(intersection(new int[]{1, 2, 3}, new int[]{2, 3, 4}))
                .forEach(System.out::println);

        System.out.println("hello world");
    }

    private static int[] intersection(int[] a, int[] b) {
        return Arrays.stream(a)
                .distinct()
                .filter(i -> Arrays.stream(b).anyMatch(j -> i == j))
                .toArray();
    }
}