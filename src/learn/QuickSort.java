package learn;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] t = { 4, 3, 7, 8, 9, 1, 5, 6, 2 };

        show(t);

        System.out.println("quick");
        show(quick(t, 0, t.length - 1));

        show(bubble(t));
    }

    public static int[] quick(int[] s1, int start, int end) {
        if (start > end) {
            return null;
        }

        int flag, a, z, stop;
        a = start;
        z = end;
        flag = s1[start];

        while (a < z) {
            while (flag <= s1[z] && a < z) {
                z--;
            }

            while (flag >= s1[a] && a < z) {
                a++;
            }

            if (a < z) {
                stop = s1[z];
                s1[z] = s1[a];
                s1[a] = stop;
            }
        }

        s1[start] = s1[a];
        s1[a] = flag;

        quick(s1, start, z - 1);
        quick(s1, z + 1, end);

        return s1;
    };

    public static int[] bubble(int[] s1) {
        System.out.println("bubble");
        int[] s2 = s1;
        for (int num = 0; num < s1.length - 1; num++) {
            for (int count = num + 1; count < s1.length; count++) {
                if (s1[num] > s1[count]) {
                    int change = s1[num];
                    s1[num] = s1[count];
                    s1[count] = change;
                }
            }
        }

        return s1;
    };

    public static void show(int[] s1) {
        for (int var = 0; var < s1.length; var++) {
            System.out.print(s1[var] + "\t");
        }
        System.out.println("\n");
    }
}
