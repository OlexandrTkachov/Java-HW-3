package itstep.learning;

import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Store {
    List<Object> products = new ArrayList<>();

    public void printAll() {
        for (Object product : products) {
            System.out.println(((Product)product).getProductInfo());
        }
    }

    public void run() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final String productFile = "src/main/resources/storeInfo.json";
        final String categoryFile = "src/main/resources/storeCategories.json";

        System.out.println("Вітаємо в магазині");
        Scanner scanner = new Scanner(System.in);

//        List<Object> c = new ArrayList<>();
//        //Pair<String, List<String>> temp = ;
//        c.add(new Pair<>(Headwear.class.getName(), Arrays.asList(Headwear.class.getDeclaredFields()[0].toString())));
//        c.add(new Pair<>(Footwear.class.getName(), Arrays.asList(Footwear.class.getDeclaredFields()[0].toString())));
//        System.out.println(c);
//        JsonUtils.saveToJson(c, categoryFile);

        String choice;
        while (true) {
            System.out.println();
            System.out.println("Якщо бажаєте переглянути товари - введіть \"1\";");
            System.out.println("Якщо бажаєте додати товар - введіть \"2\";");
            System.out.println("Якщо бажаєте працювати з усією інформацією магазину - введіть \"3\";");
            System.out.print("Якщо бажаєте вийти з магазину - введіть \"0\": ");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                String printingChoice;
                while (true) {
                    System.out.print("Якщо бажаєте переглянути усі товари - введіть \"1\", якщо за категоріями - \"2\": ");
                    printingChoice = scanner.nextLine();
                    if (printingChoice.equals("1")) {
                        this.printAll();
                        break;
                    }
                    else if (printingChoice.equals("2")) {
                        System.out.println("Усі категорії:");
                        for (Pair<String, List<String>> c : Objects.requireNonNull(JsonUtils.categoryConversion(JsonUtils.loadFromJson(categoryFile)))) {
                            System.out.println(c.getKey());
                        }
                        System.out.print("Введіть потрібну категорію: ");
                        String category = scanner.nextLine();
                        for (Object product : products) {
                            if (product.getClass().getName().equals(category)) {
                                System.out.println(((Product)product).getProductInfo());
                            }
                        }
                        break;
                    }
                    else System.out.println("Ви ввели неправильний знак. Спробуйте ще раз.");
                }
            }
            else if (choice.equals("2"))
            {
                System.out.print("Введіть назву товару: ");
                String name = scanner.nextLine();
                System.out.print("Введіть ціну товару: ");
                Product p = new Product(name, Double.parseDouble(scanner.nextLine()), new LinkedHashMap<>());
                String continuingChoice;
                while (true) {
                    System.out.print("Якщо бажаєте ще додати поля товару - введіть \"+\", якщо ні - \"-\": ");
                    continuingChoice = scanner.nextLine();
                    if (continuingChoice.equals("+")) {
                        System.out.print("Введіть назву поля: ");
                        String fieldName = scanner.nextLine();
                        System.out.print("Введіть значення поля: ");
                        p.getFields().put(fieldName, scanner.nextLine());
                    } else if (continuingChoice.equals("-")) break;
                    else System.out.println("Ви ввели неправильний знак. Спробуйте ще раз.");
                }
                String className = JsonUtils.getCategory(Objects.requireNonNull(JsonUtils.categoryConversion(JsonUtils.loadFromJson(categoryFile))), p.getFields());
                products.add(Class.forName(className).getConstructor(String.class, double.class, Map.class).newInstance(name, p.getPrice(), p.getFields()));
            }
            else if (choice.equals("3"))
            {
                String addingChoice;
                while (true) {
                    System.out.print("Якщо бажаєте записати інформацію у файл - введіть \"1\", якщо зчитати - \"2\": ");
                    addingChoice = scanner.nextLine();
                    if (addingChoice.equals("1")) {
                        JsonUtils.saveToJson(products, productFile);
                        break;
                    }
                    else if (addingChoice.equals("2")) {
                        products = JsonUtils.loadFromJson(productFile);
//                        products = new ArrayList<>();
//                        List<Object> copiedProducts = JsonUtils.loadFromJson(productFile);
//                        for (Object product : copiedProducts) {
//                            String className = JsonUtils.getCategory(Objects.requireNonNull(JsonUtils.categoryConversion(JsonUtils.loadFromJson(categoryFile))), p.getFields());
//                            products.add(Class.forName(className).getConstructor(String.class, double.class, Map.class).newInstance(name, p.getPrice(), p.getFields()));
//                            products.add(Objects.requireNonNull(JsonUtils.productConversion(product)));
//                        }
//                        products = JsonUtils.loadFromJson(productFile);
//                        System.out.println(products);
                        break;
                    }
                    else System.out.println("Ви ввели неправильний знак. Спробуйте ще раз.");
                }
            }
            else if (choice.equals("0"))
            {
                System.out.println("Бувайте!");
                return;
            }
            else System.out.println("Ви ввели неправильний знак. Спробуйте ще раз.");
        }
    }
}
