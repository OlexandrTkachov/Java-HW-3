package itstep.learning;

import java.util.Map;

public class Product {
    private String name;
    private double price;
    private Map<String, String> fields;

    public Product(String name, double price, Map<String, String> fields) {
        this.name = name;
        this.price = price;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public String getProductInfo() {
        StringBuilder answer = new StringBuilder();
        answer.append(getName());
        answer.append("\n\t price: ").append(getPrice());
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            answer.append("\n\t").append(entry.getKey()).append(": ").append(entry.getValue());
        }
        return answer.toString();
    }
}
