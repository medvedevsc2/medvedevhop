    package com.example.medvedev.repository;

    import com.example.medvedev.model.Shoe;
    import java.util.ArrayList;
    import java.util.List;
    import org.springframework.stereotype.Repository;

    @Repository
    public class InMemoryShoeDao {

        private final List<Shoe> shoes = new ArrayList<>();

        InMemoryShoeDao() {
            shoes.add(Shoe.builder().id(1).brand("Nike").size(42)
                    .description("Спортивные кроссовки для бега и активного отдыха.")
                    .price(120.99).model("Air Zoom Pegasus").build());

            shoes.add(Shoe.builder().id(2).brand("Adidas").size(44)
                    .description("Комфортные и стильные кеды на каждый день.")
                    .price(99.99).model("Superstar").build());

            shoes.add(Shoe.builder().id(3).brand("Puma").size(41)
                    .description("Легкие и удобные кроссовки для спорта и прогулок.")
                    .price(89.99).model("RS-X").build());

            shoes.add(Shoe.builder().id(4).brand("Reebok").size(43)
                    .description("Классические кроссовки с улучшенной амортизацией.")
                    .price(79.99).model("Classic Leather").build());

            shoes.add(Shoe.builder().id(5).brand("New Balance").size(42)
                    .description("Кроссовки с высокой износостойкостью и стильным дизайном.")
                    .price(110.50).model("574").build());
        }

        public List<Shoe> showShoes(String brand, String model) {
            return shoes.stream()
                    .filter(shoe -> brand == null || shoe.getBrand().equalsIgnoreCase(brand))
                    .filter(shoe -> model == null || shoe.getModel().equalsIgnoreCase(model))
                    .toList();
        }

        public Shoe findById(int id) {
            return shoes.stream().filter(shoe -> shoe.getId() == id).findFirst().orElse(null);
        }
    }