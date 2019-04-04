import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Main {
    public static void main(String[] args) {
        int[] numbs = new int[]{8, 2, 1, 3};
        System.out.println(canBeEqualTo24(numbs));
    }

    static public boolean canBeEqualTo24(int[] nums) {
        char[] opers = {'+', '-', '*', '/'};

        List<StringBuffer> list_opers = new ArrayList<StringBuffer>();
        List<StringBuffer> numbers = new ArrayList<StringBuffer>();
        List<StringBuffer> expressions = new ArrayList<StringBuffer>();

        StringBuffer str = new StringBuffer();
        int i = 0, j = 0, l = 0, k = 0;
        while (i < 4) {
            str.append(opers[i]);
            while (j < 4) {
                str.append(opers[j]);
                while (l < 4) {
                    str.append(opers[l]);
                    list_opers.add(new StringBuffer(str));
                    str.delete(str.length() - 1, str.length());
                    l++;
                }
                str.delete(str.length() - 1, str.length());
                l = 0;
                j++;
            }
            str = new StringBuffer();
            j = 0;
            i++;
        }

        i = 0;
        j = 0;
        l = 0;

        str = new StringBuffer();

        while (i < 4) {
            str.append(nums[i]);
            while (j < 4) {
                if (i != j) {
                    str.append(nums[j]);
                } else {
                    j++;
                    continue;
                }
                while (k < 4) {
                    if ((k != i) && (k != j) && (k != l)) {
                        str.append(nums[k]);
                    } else {
                        k++;
                        continue;
                    }
                    while (l < 4) {
                        if ((l != i) && (l != j) && (l != k)) {
                            str.append(nums[l]);
                            numbers.add(new StringBuffer(str));
                            str.delete(str.length() - 1, str.length());
                            l++;
                        } else {
                            l++;
                            continue;
                        }
                    }
                    l = 0;
                    str.delete(str.length() - 1, str.length());
                    k++;
                }
                k = 0;
                str.delete(str.length() - 1, str.length());
                j++;
            }
            str = new StringBuffer();
            j = 0;
            i++;
        }

        for (StringBuffer number : numbers) {
            for (StringBuffer operators : list_opers) {
                str = new StringBuffer(number);
                str.insert(1, operators.substring(0, 1));
                str.insert(3, operators.substring(1, 2));
                str.insert(5, operators.substring(2, 3));
                expressions.add(str);
            }
        }

        List<StringBuffer> buffs = new ArrayList<>();

        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(0, '(');
            str.insert(4, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(2, '(');
            str.insert(6, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(4, '(');
            str.insert(8, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(0, '(');
            str.insert(4, ')');
            str.insert(6, '(');
            str.insert(10, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(0, '(');
            str.insert(6, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(2, '(');
            str.insert(8, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(2, '(');
            str.insert(8, ')');
            str.insert(3, '(');
            str.insert(7, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(2, '(');
            str.insert(8, ')');
            str.insert(5, '(');
            str.insert(9, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(0, '(');
            str.insert(3, '(');
            str.insert(7, ')');
            str.insert(8, ')');
            buffs.add(str);
        }
        for (StringBuffer expr : expressions) {
            str = new StringBuffer(expr);
            str.insert(0, '(');
            str.insert(1, '(');
            str.insert(5, ')');
            str.insert(8, ')');
            buffs.add(str);
        }

        expressions.addAll(buffs);
        System.out.println("На входе: " + Arrays.toString(nums));
        for (StringBuffer str1 : expressions) {
            try {
                Double value = new Double((
                        new ScriptEngineManager()
                                .getEngineByName("JavaScript")
                                .eval(str1.toString())
                ).toString());
                value = Math.round(value * 100000.0) / 100000.0;
                if (value == 24) {
                    DecimalFormat format = new DecimalFormat();
                    format.setDecimalSeparatorAlwaysShown(false);
                    System.out.println("Пояснение: " + str1 + "=" + format.format(value));
                    return true;
                } else {
                    continue;
                }
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Пояснение: Из данного набора чисел невозможно составить выражение, равное 24");
        return false;
    }
}






