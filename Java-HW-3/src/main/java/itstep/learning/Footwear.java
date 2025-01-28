package itstep.learning;

import java.util.Map;

public class Footwear extends Product {
    private String footSize;

    public Footwear(String name, double price, Map<String, String> fields) {
        super(name, price, null);
        this.footSize = fields.get("footSize");
    }

    public String getFootSize() {
        return footSize;
    }

    @Override
    public String getProductInfo() {
        StringBuilder answer = new StringBuilder();
        answer.append(getName());
        answer.append("\n\t price: ").append(getPrice());
        answer.append("\n\t foot size: ").append(getFootSize());
        return answer.toString();
    }
}
